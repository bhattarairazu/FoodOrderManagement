package com.fusemachine.canteenmanagement.FoodOrderingSystem.controller;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.Role;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.User;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.RoleRepository;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Utils.Jwtutil;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.payload.JWTLoginSuccessResponse;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.payload.LoginRequest;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.*;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.validations.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private MapValiationError mapValiationError;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private Jwtutil jwtRequestFilter;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private RoleRepository roleRepository;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        ResponseEntity<?> errorMap = mapValiationError.MapValidationError(result);
        if(errorMap!=null) return errorMap;


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String jwt = jwtRequestFilter.generateToken(userDetails);

//        if(authentication.isAuthenticated()){
//            return ResponseEntity.ok("h");
//        }
      //  String jwt = SecurityConstants.TOKEN_PREFIX+jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTLoginSuccessResponse(true,jwt));
   //     return ResponseEntity.ok("Hello");
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result){
        //validate password match
        userValidator.validate(user,result);
        ResponseEntity<?> errorMap = mapValiationError.MapValidationError(result);
        if(errorMap!=null) return errorMap;

        User newUser = userService.save(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

//    @GetMapping("/")
//    public ResponseEntity<Map<String,String>> insertRawData(){
//        String [] roleslist = {"ROLE_ADMIN","ROLE_EMPLOYEE"};
//
//        //initially adding roles to the database
//        for(String str:roleslist){
//            if(roleRepository.findRoleByName(str)==null) {
//                Role role = new Role();
//                role.setName(str);
//                roleRepository.save(role);
//            }
//        }
//
//
//
//
//        Map<String,String> map = new HashMap<>();
//        map.put("message","Dummy Data Created");
//
//        return new ResponseEntity<>(map,HttpStatus.CREATED);
//
//
//
//
//
//
//    }





}
