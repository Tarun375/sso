package com.aashdit.sso.service;

import org.springframework.stereotype.Service;

import com.aashdit.sso.entity.User;

@Service
public interface UserService {
	public String saveUser(User user);

	public boolean verifyUser(String username, String password);

	public boolean resetProcess(String username, String email);

	public void resetSuccess(String username,String password);
	
	

}
