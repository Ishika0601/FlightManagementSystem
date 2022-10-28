package com.cg.service;

import com.cg.bean.User;
import com.cg.dao.UserDao;
import com.cg.exception.InvalidUserException;
import com.cg.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service("userService")
public class UserServiceImpl implements  UserService{
     @Autowired
     UserDao userDao;

    @Transactional
    @Override
    public User addUser(User u) {

        return userDao.save(u);
    }

    @Transactional
    @Override
    public User updateUser(User newAccount, BigInteger userId) {
        Optional<User> optus=userDao.findById(userId); 
        if(optus.isEmpty()){
        	//throw exception if no user found
            throw new UserNotFoundException("No user found with id "+userId);
        }
        User u=optus.get();
        u.setId(newAccount.getId());
        u.setUserName(newAccount.getUserName());
        u.setUserPassword(newAccount.getUserPassword());
        u.setEmail(newAccount.getEmail());
        u.setUserPhone(newAccount.getUserPhone());
        u.setUserType(newAccount.getUserType());
        User u1 = userDao.save(u);

        return  u1;


    }
    
    @Transactional
    @Override
    public void deleteUser(BigInteger userId) {
    	Optional<User> optus=userDao.findById(userId);
    	if(optus.isEmpty()){
    		//throw exception if no user found
    		throw new UserNotFoundException("No user found with id "+userId);
        }
        userDao.deleteById(userId);

    }

    
    @Override
    public User viewUser(BigInteger userId) {
        Optional<User> optus=userDao.findById(userId);
        if(optus.isEmpty()){
        	//throw exception if no user found
        	throw new UserNotFoundException("No user found with id "+userId);
        }
        return optus.get();
    }
    
    
    @Override
    public List<User> viewUser() {
        return userDao.findAll();

    }

    @Override
    public void validateUser(User user) {
    	//phone no should be of 10 digits & not start with 0
    	//email's local part should have alphanumeric characters
        BigInteger phoneNo=user.getUserPhone();
        String phno = phoneNo.toString();
        Pattern p= Pattern.compile("^[1-9][0-9]{9}$");
        Matcher m=p.matcher(phno);
        String email=user.getEmail();
        Pattern p1=Pattern.compile("^[A-Za-z0-9]*@[a-zA-Z]+[.][a-zA-Z]{2,4}$");
        Matcher m1=p1.matcher(email);
         if((!m1.matches()) && (m.matches())){
        	 throw new InvalidUserException("Email is invalid");
        }
         else if ((!m.matches()) && (m1.matches())) {
        	throw new InvalidUserException("Phone number is invalid");
         }
         else if((!m.matches()) && (!m1.matches())){
             throw new InvalidUserException("Phone number and email are invalid");
         }
        
    }

}
