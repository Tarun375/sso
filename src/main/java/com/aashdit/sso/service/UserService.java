package com.aashdit.sso.service;

import java.util.List;

import com.aashdit.sso.entity.User;

public interface UserService {
	public String saveUser(User user);

	public boolean verifyUser(String username, String password);

	public boolean resetProcess(String username, String email);

	public Integer resetSuccess(String username, String password);
	
	public List<User> findAllUsers();
	
	public List<User> findByRole(String role);
}
