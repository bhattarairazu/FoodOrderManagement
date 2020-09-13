package com.fusemachine.canteenmanagement.FoodOrderingSystem.service;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Role;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.User;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.RoleRepository;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.UserRepository;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.exceptions.GlobalExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User save(User user) {
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setEmployeeId(String.valueOf(UUID.randomUUID()));
            if(isEmailExist(user.getEmail())){
                throw new GlobalExceptions("Email "+user.getEmail()+" ALready Exist.Please enter new email");
            }
            //check if the user contains roles or not
            Set<Role> roleslist = user.getRoles();
            Set<Role> newCollection = new HashSet<>();
            //geting streams of roles from user
            for(Role role : roleslist){
                if(roleRepository.findRoleByName(role.getName())==null){
                    throw new GlobalExceptions("Role with "+role.getName()+" not found.Please enter valid role for user.Either ADMIN or EMPLOYEE");
                }
                newCollection.add(roleRepository.findRoleByName(role.getName()));
            }
           user.setRoles(newCollection);
            return userRepository.save(user);

        }catch (Exception e){
            throw new GlobalExceptions(e.getMessage());
        }
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user==null) {
            throw new GlobalExceptions("User with email "+email+" not found");
        }
        return user;
    }

    @Override
    public List<User> findAllByRoles(Role role) {
        return null;
    }

    @Override
    public boolean isEmailExist(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User findById(int id) {
        Optional<User> result = userRepository.findById(id);
        User user = null;
        if(result.isPresent()){
            user = result.get();
        }else{
            throw new UsernameNotFoundException("User not found");

        }

        return user;

    }

    public User findByUsername(String username){
        User user = userRepository.findByUsername(username);
        if(user!=null){
            return user;
        }
        return null;
    }

}
