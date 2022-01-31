package com.aashdit.sso.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.aashdit.sso.entity.Application;
import com.aashdit.sso.entity.Department;
import com.aashdit.sso.repository.ApplicationRepository;
import com.aashdit.sso.service.ApplicationService;

/**
 * @author Tarun Chakrabarty.
 *
 *         29-Jan-2022
 */

@Controller
public class ApplicationController {

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private ApplicationRepository applicationRepository;

	@GetMapping("/addAppForm")
	public String addAppForm() {

		return "addAppPage";

	}

	@PostMapping("/addingApp")
	public String addingApp(@RequestBody(required = true) @ModelAttribute("app") @Valid Application app,
			BindingResult br, Model model) {
		String saveApp = applicationService.saveApp(app);
		System.out.println(saveApp);
		return "superAdminDashboard";

	}
	// Show All Applications
	@GetMapping("/showAllApp")
	public String showAllApp(Model model) {
		List<Application> allAppList = applicationService.showAllApp();
		model.addAttribute("allAppList", allAppList);
		return "showAppList";

	}

	// Update Application details form
	@GetMapping("/appUpdationForm/{appId}")
	public String appUpdateForm(@PathVariable("appId") int appId, Model model) {
		Application app = applicationRepository.findById(appId).get();
		model.addAttribute("app", app);
		return "appUpdatePage";
	}

	// Application updating process
	@PostMapping("/appUpdating")
	public String updateAppData(@RequestBody(required = true) @ModelAttribute("app") @Valid Application app,
			BindingResult br, final Model model) {
		app.setAppId(app.getAppId());
		applicationService.updateAppDetails(app);
		model.addAttribute("message", "Application data updated Successfully...");
		return "showAppList";

	}

	// deleting Application
	@GetMapping("/deleteApp/{appId}")
	public String deleteApp(@PathVariable("appId") int appId, Model model) {
		applicationService.deleteApp(appId);
		model.addAttribute("message", "Application deleted Successfully...");
		return "showAppList";

	}

}
