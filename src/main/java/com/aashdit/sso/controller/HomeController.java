package com.aashdit.sso.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aashdit.sso.SsoApplication;
import com.aashdit.sso.entity.User;
import com.aashdit.sso.service.UserService;
@Controller
public class HomeController {
	
	//private static final Logger logger = LoggerFactory.getLogger(SsoApplication.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	public String home() {
		return "login";
	}
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/logoutSuccess")
	public String logout() {
		return "logout";
	}
	
	@RequestMapping("/resetPassword")
	public String resetPassword() {
		return "verifyUser";
	}
	@PostMapping("/resetProccess")
	public String resetProccess(String username,String email) {
		if(userService.resetProcess(username,email))
			return "resetSuccess";
		else
			return "verifyUser";
	}
	
	@PostMapping("/resetSuccess")
	public String resetSuccess(String username,String password) {
		userService.resetSuccess(username,password);
		return "login";
		
	}
	
	  @RequestMapping("/register")
	  public String register() 
	  {
		  return "register"; 
	  }
	 
	@PostMapping("/registration")
	public String registration(User user) {
		System.out.println(user);
		
		userService.saveUser(user);
		
		return "login";
	}
	@PostMapping("/loginProcess")
	public String loginProcess(@RequestParam("username") String username,@RequestParam("password") String password) {
		System.out.println(username);
		 if(userService.verifyUser(username,password))
			 return "home";
		 else 
			 return "login";
	}

}
