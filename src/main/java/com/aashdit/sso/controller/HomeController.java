package com.aashdit.sso.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aashdit.sso.entity.User;
import com.aashdit.sso.service.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@Autowired
	private UserServiceImpl userService;

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
	public String loginProcess(@RequestParam("username") String username, @RequestParam("password") String password) {
		if (userService.verifyUser(username, password))
			return "home";
		else
			return "login";
	}

}
