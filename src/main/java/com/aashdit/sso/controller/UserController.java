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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
 * @author Tarun Chakrabarty
 * @since 15-Jan-2022
 * 
 *
 */
@Controller
@Slf4j

public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@GetMapping("/")
	public String home() {
		return "login";
	}

	/*
	 * @RequestMapping("/login") public String login() { return "login"; }
	 */

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
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

	// forward to logout page after logout successfully
	@GetMapping("/logoutSuccess")
	public String logout() {
		return "logout";
	}

	// send the request to verify page...reset process begins
	@GetMapping("/resetPassword")
	public String resetPassword() {
		return "verifyUser";
	}

	// verifying your details for reseting your password...reset is under process
	@PostMapping("/resetProccess")
	public String resetProccess(String username, String email) {

		if (userService.resetProcess(username, email))
			return "resetSuccess";
		else
			return "verifyUser";
	}

	// actually reset your password....reset Success
	@PostMapping("/resetSuccess")
	public String resetSuccess(String username, String password) {
		userService.resetSuccess(username, password);
		return "login";

	}

	// displaying the registration form
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	// Registering the new user
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

	// processing the login by taking input username and password
	@PostMapping("/loginProcess")
	public String loginProcess(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model) {
		try {
			/*if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
				Optional<User> optionalUser = userRepository.findByUserName(username);
				User user = optionalUser.get();
				if (user.getRole().equals("superAdmin"))
					return "superAdminDashboard";

				if (user.getRole().equals("admin"))
					return "adminDashboard";

				if (user.getRole().equals("user"))
					return "userDashboard";

				return "Role not found";
			} else {*/
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
			
		} catch (UsernameNotFoundException e) {
			model.addAttribute("error", "Unknown User.");
			return "login";
		}

	}

	@GetMapping("/showAllUsers")
	public ModelAndView showAllUsers(Model model) {
		ModelAndView map = new ModelAndView("showUserList");
		String role = "user";
		List<User> users = userService.findByRole(role);
		if (users == null)
			model.addAttribute("error", "Users not found!");
		map.addObject("users", users);
		return map;
	}

	@GetMapping("/showAllAdmins")
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
		String username = auth.getName(); // showing annonymus user
		System.out.println("The current username is: " + username);
		User user = userRepository.findByUserName(username).get();
		System.out.println("The current user is: " + user);
		String role = "user";
		List<User> deptUsers = userService.showUsersDepartmentWise(user.getDepartmentName(), role);
		if (deptUsers == null)
			model.addAttribute("error", "Users not found!");
		map.addObject("users", deptUsers);
		return map;

	}

	// Update admin data form
	@GetMapping("/adminUpdationForm/{userId}")
	public String adminUpdateForm(@PathVariable("userId") int userId, Model model) {
		User user = userRepository.findById(userId).get();
		model.addAttribute("user", user);
		return "adminUpdatePage";
	}

	// Update user data form
	@GetMapping("/userUpdationForm/{userId}")
	public String userUpdateForm(@PathVariable("userId") int userId, Model model) {
		User user = userRepository.findById(userId).get();
		model.addAttribute("user", user);
		return "userUpdatePage";
	}

	// updating admin Details
	@PostMapping("/updateAdminData")
	public String updateAdminData(@RequestBody(required = true) @ModelAttribute("user") @Valid User user,
			BindingResult br, final Model model) {
		userService.updateAdminDetails(user);
		model.addAttribute("message", "Admin data updated Successfully...");
		return "showAdminList";

	}

	// updating user Details
	@PostMapping("/updateUserData")
	public String updateUserData(@RequestBody(required = true) @ModelAttribute("user") @Valid User user,
			BindingResult br, final Model model) {
		userService.updateUserDetails(user);
		model.addAttribute("message", "User data updated Successfully...");
		return "showUserList";

	}

	// deleting admin
	@GetMapping("/deleteAdmin/{userId}")
	public String deleteAdmin(@PathVariable("userId") int userId, Model model) {
		userService.deleteAdmin(userId);
		model.addAttribute("message", "Admin deleted Successfully...");
		return "showAdminList";

	}

	// deleting user
	@GetMapping("/deleteUser/{userId}")
	public String deleteUser(@PathVariable("userId") int userId, Model model) {
		userService.deleteUser(userId);
		model.addAttribute("message", "User deleted Successfully...");
		return "showUserList";

	}

}
