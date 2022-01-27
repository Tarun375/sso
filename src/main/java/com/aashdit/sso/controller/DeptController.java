package com.aashdit.sso.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.aashdit.sso.entity.Department;
import com.aashdit.sso.entity.User;
import com.aashdit.sso.service.DeptServiceImpl;
import com.aashdit.sso.service.UserServiceImpl;

@Controller
public class DeptController {
	
	@Autowired
	private DeptServiceImpl deptService;
	
	@GetMapping("/addDept")
	public String addDept() {
		return "addDeptForm";
	}
	
	@PostMapping("/deptAdding")
	public String deptAdding(@RequestBody(required = true) @ModelAttribute("dept") @Valid Department dept, BindingResult br,Model model) {
		String saveDept = deptService.saveDept(dept);
		System.out.println(saveDept);
		return "adminDashboard";
	}
	
	@GetMapping("/showAllDept")
	public String showAllDept(Model model) {
		List<Department> deptList= deptService.showAllDept();
		model.addAttribute("deptList", deptList);
		return "showDeptList";
	}

}
