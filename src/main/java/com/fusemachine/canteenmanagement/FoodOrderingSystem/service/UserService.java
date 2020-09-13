package com.fusemachine.canteenmanagement.FoodOrderingSystem.service;


import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Role;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService  {

    public User save(User user);

    public User findByEmail(String email);

    public List<User> findAllByRoles(Role role);

    public boolean isEmailExist(String email);

    public void deleteById(int id);

    public List<User> findAll();

    public User findById(int id);

    public User findByUsername(String usernmae);



}
