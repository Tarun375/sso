package com.aashdit.sso.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aashdit.sso.entity.User;
import com.aashdit.sso.repository.UserRepository;
import com.aashdit.sso.service.UserDetailsImpl;
import com.aashdit.sso.service.UserServiceImpl;
import com.aashdit.sso.util.JwtTokenUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Biswa Bhusan Sahoo
 * @since 2022
 *
 */
@Controller
@Slf4j
public class HomeController {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@RequestMapping("/")
	public String home() {
		return "login";
	}

	/*
	 * @RequestMapping("/login") public String login() { return "login"; }
	 */

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userDetailsImpl= (UserDetailsImpl) authentication.getPrincipal();
		if (authentication.isAuthenticated()) {
			Optional<User> optionalUser = userRepository.findByUserName(userDetailsImpl.getUsername());
			User user = optionalUser.get();
			if (user.getRole().equals("superAdmin"))
				return "superAdminDashboard";

			if (user.getRole().equals("admin"))
				return "adminDashboard";

			if (user.getRole().equals("user"))
				return "userDashboard";

			return "Role not found";
		} 
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
	public String resetProccess(String username, String email) {

		if (userService.resetProcess(username, email))
			return "resetSuccess";
		else
			return "verifyUser";
	}

	@PostMapping("/resetSuccess")
	public String resetSuccess(String username, String password) {
		userService.resetSuccess(username, password);
		return "login";

	}

	@RequestMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/registration")
	public String registration(@RequestBody(required = true) @ModelAttribute("user") @Valid User user, BindingResult br,
			final Model model) {

		if (br.hasErrors()) {
			model.addAttribute("user", user);
			return "register";
		}
		if (userService.checkUserExists(user)) {
			br.addError(new ObjectError("userUniqueError", "Email or MobileNo or userName ALready Exist"));
			return "register";
		}
		userService.saveUser(user);

		log.debug("User Registration Successfully");
		return "registrationSuccess";
	}

	@PostMapping("/loginProcess")
	public String loginProcess(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model) {
		try {
			if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
				Optional<User> optionalUser = userRepository.findByUserName(username);
				User user = optionalUser.get();
				if (user.getRole().equals("superAdmin"))
					return "superAdminDashboard";

				if (user.getRole().equals("admin"))
					return "adminDashboard";

				if (user.getRole().equals("user"))
					return "userDashboard";

				return "Role not found";
			} else {
				if (userService.verifyUser(username, password)) {
					Optional<User> optionalUser = userRepository.findByUserName(username);
					User user = optionalUser.get();
					if (user.getRole().equals("superAdmin"))
						return "superAdminDashboard";

					if (user.getRole().equals("admin"))
						return "adminDashboard";

					if (user.getRole().equals("user"))
						return "userDashboard";

					return "Role not found";

				} else {
					model.addAttribute("error", "Invalid Password.");
					return "login";
				}
			}
		} catch (UsernameNotFoundException e) {
			model.addAttribute("error", "Unknown User.");
			return "login";
		}

	}

	@RequestMapping("/showAllUsers")
	public ModelAndView showAllUsers(Model model) {
		ModelAndView map = new ModelAndView("showUserList");
		String role = "user";
		List<User> users = userService.findByRole(role);
		if (users == null)
			model.addAttribute("error", "Users not found!");
		map.addObject("users", users);
		return map;
	}

	@RequestMapping("/showAllAdmins")
	public ModelAndView showAllAdmins(Model model) {
		ModelAndView map = new ModelAndView("showAdminList");
		String role = "admin";
		List<User> admins = userService.findByRole(role);
		if (admins == null)
			model.addAttribute("error", "Admins not found!");
		map.addObject("admins", admins);
		return map;
	}

	@GetMapping("/departmentUsers")
	public ModelAndView showUsersDepartmentWise(Model model) {
		ModelAndView map = new ModelAndView("showDepartmentUserList");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		System.out.println("The current username is: " + username);
		Optional<User> user1 = userRepository.findByUserName(username);
		User user = user1.get();
		System.out.println("The current user is: " + user);
		List<User> deptUsers = userService.showUsersDepartmentWise(user.getDepartmentName());
		if (deptUsers == null)
			model.addAttribute("error", "Users not found!");
		map.addObject("users", deptUsers);
		return map;

	}
}
