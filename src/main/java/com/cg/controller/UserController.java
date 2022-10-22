package com.cg.controller;

import com.cg.advice.ErrorResponse;
import com.cg.bean.User;
import com.cg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> showAllUser(){
        List<User> list =userService.viewUser();
        return  list;
    }

    @PostMapping("/users")
    public User newUser(@RequestBody User user){
        return  userService.addUser(user);
    }
    
    @GetMapping("/usersById/{userId}")
    public User showById(@PathVariable BigInteger userId){
    	if(!userId.getClass().getSimpleName().equals("BigInteger")) {
    		throw new InputMismatchException("User Id should be a big integer");
    	}
        return  userService.viewUser(userId);
    }
    
    @PutMapping("/users/{userId}")
    public User updateAccount(@RequestBody User newU,@PathVariable BigInteger userId){
    	if(!userId.getClass().getSimpleName().equals("BigInteger")) {
    		throw new InputMismatchException("User Id should be a big integer");
    	}
        return userService.updateUser(newU,userId);
    }
    
    @DeleteMapping("/users/{userId}")
    public  void deleteUser(@PathVariable BigInteger userId){
    	if(!userId.getClass().getSimpleName().equals("BigInteger")) {
    		throw new InputMismatchException("User Id should be a big integer");
    	}
        userService.deleteUser(userId);
    }
    
    // local to the RestController
 		 @ExceptionHandler(InputMismatchException.class)
 		    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
 		        List<String> details = new ArrayList<>();
 		        details.add(ex.getLocalizedMessage());
 		        ErrorResponse error = new ErrorResponse("Server error from controller", details);
 		        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
 		    }
 		
    }
