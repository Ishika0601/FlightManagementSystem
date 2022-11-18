package com.cg.service;

import com.cg.bean.User;
import java.math.BigInteger;
import java.util.List;

public interface UserService {
	
	//add a user
    public  User addUser(User u);
    
    //update user based on user id
    public User updateUser(User newAccount, BigInteger userId);
    
    //delete user
    public void deleteUser(BigInteger userId);
    
    //view user based on user id
    public User viewUser(BigInteger userId);
    
    //view all users
    public List<User> viewUser();
    
    //validate user
    public void validateUser(User user);

    public User viewByUserName(String Name);

    public  User viewByEmail(String Email);
    public User patchUser(User newAccount, BigInteger userId);
}
