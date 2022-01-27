package com.aashdit.sso.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.aashdit.sso.entity.User;
import com.aashdit.sso.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public String saveUser(User user) {
		Assert.notNull(user, "User can't be null");
		String password = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(password);
		Date date = new Date();
		log.debug("User {} registration ", user.getFullName());
		user.setCreatedBy(user.getFullName());
		user.setCreatedOn(date);
		user.setIsActive(Boolean.TRUE);
		User registeredUser = userRepository.save(user);
		log.debug("User Registration Successfull :: {}", registeredUser.getUserName());

		return "Registration Successfull!!";
	}

	@Override
	public boolean verifyUser(String username, String password) {
		log.debug("Verifying User :: {} ", username);
		User user = userRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		if (new BCryptPasswordEncoder().matches(password, user.getPassword()))
			return true;
		else
			return false;
	}

	@Override
	public boolean resetProcess(String username, String email) {
		log.debug("Reset Password UserName :: {}  and Email :: {} ", username, email);
		User user = userRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		if (user.getUserName().equals(username) && user.getEmail().equals(email))
			return true;
		else
			return false;
	}

	@Override
	public Integer resetSuccess(String username, String password) {
		String encryptPassword = new BCryptPasswordEncoder().encode(password);
		Integer reset = userRepository.resetSuccess(username, encryptPassword);

		log.debug("Reset Password Done :: {}", reset);
		return reset;
	}

	public Boolean checkUserExists(User user) {

		List<User> users = userRepository.findByUserNameOrMobileNoOrEmail(user.getUserName(), user.getMobileNo(),
				user.getEmail());

		if (CollectionUtils.isEmpty(users)) {
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

	public List<User> findAllUsers() {

		List<User> users = userRepository.findAll();
		return users;

	}

	public List<User> showUsersDepartmentWise(String departmentName, String role) {

		List<User> deptUsers = userRepository.findByDepartmentNameAndRole(departmentName, role);
		return deptUsers;

	}

	public List<User> findByRole(String role) {
		List<User> users = userRepository.findByRole(role);
		return users;
	}

	public String updateAdminDetails(User user) {
		Date date = new Date();
		log.debug("User {} registration ", user.getFullName());
		user.setUpdatedBy(user.getFullName());
		user.setUpdatedOn(date);
		user.setIsActive(Boolean.TRUE);
		User updatedAdmin = userRepository.save(user);
		log.debug("Admin Updation Successfull :: {}", updatedAdmin.getUserName());

		return "Updation Successfull!!";

	}

	public String updateUserDetails(User user) {
		Date date = new Date();
		log.debug("User {} updation ", user.getFullName());
		user.setUpdatedBy(user.getFullName());
		user.setUpdatedOn(date);
		user.setIsActive(Boolean.TRUE);
		User updatedUser = userRepository.save(user);
		log.debug("User Updation Successfull :: {}", updatedUser.getUserName());

		return "Updation Successfull!!";

	}

	@Transactional
	public void deleteUser(int userId) {

		userRepository.deleteById(userId);

	}

	@Transactional
	public void deleteAdmin(int userId) {

		userRepository.deleteById(userId);

	}
}
