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
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    
    /*
     URI : http://localhost:9001/user/showAllUsers
     METHOD : GET
     */
    @GetMapping("/showAllUsers")
    public List<User> showAllUser(){
        List<User> list =userService.viewUser();
        return  list;
    }
    
    /*
     URI : http://localhost:9001/user/addUser
     METHOD : POST
     {
  		"email": "lakshya@gmail.com",
  		"id": 1,
  		"userName": "Lakshya",
  		"userPassword": "lakshya",
  		"userPhone": 1234567894,
  		"userType": "customer"
	}
     */
    @PostMapping("/addUser")
    public User newUser(@RequestBody User user){
    	userService.validateUser(user);
        return  userService.addUser(user);
    }
    
    /*
     URI : http://localhost:9001/user/showById/15
     METHOD : GET
     */
    @GetMapping("/showById/{userId}")
    public User showById(@PathVariable BigInteger userId){
    	if(!userId.getClass().getSimpleName().equals("BigInteger")) {
    		throw new InputMismatchException("User Id should be a big integer");
    	}
        return  userService.viewUser(userId);
    }
    
    /*
     URI : http://localhost:9001/user/modifyUser/11
     METHOD : PUT
     {
  		"email": "ram@gmail.com",
  		"id": 11,
  		"userName": "Ram",
  		"userPassword": "ram",
  		"userPhone": 1234567895,
  		"userType": "admin"
	}
     */
    @PutMapping("/modifyUser/{userId}")
    public User updateUser(@RequestBody User newU,@PathVariable BigInteger userId){
    	if(!userId.getClass().getSimpleName().equals("BigInteger")) {
    		throw new InputMismatchException("User Id should be a big integer");
    	}
    	userService.validateUser(newU);
        return userService.updateUser(newU,userId);
    }
    
    /*
     URI : http://localhost:9001/user/deleteUser/13
     METHOD : DELETE
     */
    @DeleteMapping("/deleteUser/{userId}")
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
