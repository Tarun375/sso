package com.aashdit.sso.service;



import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.aashdit.sso.SsoApplication;
import com.aashdit.sso.entity.User;
import com.aashdit.sso.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{
	
	//private static final Logger logger = LoggerFactory.getLogger(SsoApplication.class);
	
	@Autowired
	UserRepository userRepository;
	@Override
	public String saveUser(User user) {
		String password = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(password);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
	    //System.out.println(formatter.format(date)); 
	    user.setCreatedBy(user.getFullName());
	    user.setCreatedOn(formatter.format(date));
		User user1 = userRepository.save(user);
		System.out.println("Helloooo " +user1);
		return "Registration Successfull!!";
	}
	@Override
	public boolean verifyUser(String username, String password) {
		//String encryptPassword = new BCryptPasswordEncoder().encode(password);
		User user = userRepository.findByUserName(username);
		System.out.println(user);
		if(new BCryptPasswordEncoder().matches(password, user.getPassword()))
			return true;
		else 
			return false;
		
	}
	@Override
	public boolean resetProcess(String username, String email) {
		
		User user = userRepository.findByUserName(username);
		System.out.println(user);
		if(user.getUserName().equals(username) && user.getEmail().equals(email))
			return true;
			
		else
			return false;
		
		
	}
	@Override
	public void resetSuccess(String username,String password) {
		String encryptPassword = new BCryptPasswordEncoder().encode(password);
		userRepository.resetSuccess(username,encryptPassword);		
		
		
	}

}
