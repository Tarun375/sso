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
import com.aashdit.sso.entity.Department;
import com.aashdit.sso.entity.User;
import com.aashdit.sso.repository.DeptRepository;
import com.aashdit.sso.service.DeptServiceImpl;
import com.aashdit.sso.service.UserServiceImpl;

/**
 * 
 * @author Tarun Chakrabarty
 * @since 24-Jan-2022
 * 
 *
 */

@Controller
public class DeptController {

	@Autowired
	private DeptServiceImpl deptService;

	@Autowired
	private DeptRepository deptRepository;

	// Department adding form
	@GetMapping("/addDept")
	public String addDept() {
		return "addDeptForm";
	}

	// Adding new department
	@PostMapping("/deptAdding")
	public String deptAdding(@RequestBody(required = true) @ModelAttribute("dept") @Valid Department dept,
			BindingResult br, Model model) {
		String saveDept = deptService.saveDept(dept);
		System.out.println(saveDept);
		return "adminDashboard";
	}

	// show all deparment list

	@GetMapping("/showAllDept")
	public String showAllDept(Model model) {
		List<Department> deptList = deptService.showAllDept();
		model.addAttribute("deptList", deptList);
		return "showDeptList";
	}

	// Update Department details form
	@GetMapping("/deptUpdationForm/{deptId}")
	public String adminUpdateForm(@PathVariable("deptId") int deptId, Model model) {
		Department dept = deptRepository.findById(deptId).get();
		model.addAttribute("dept", dept);
		return "deptUpdatePage";
	}

	// Department updating process
	@PostMapping("/deptUpdating")
	public String updateDeptData(@RequestBody(required = true) @ModelAttribute("dept") @Valid Department dept,
			BindingResult br, final Model model) {
		dept.setDeptId(dept.getDeptId());
		deptService.updateDeptDetails(dept);
		model.addAttribute("message", "Department data updated Successfully...");
		return "showDeptList";

	}

	// deleting Department
	@GetMapping("/deleteDept/{deptId}")
	public String deleteAdmin(@PathVariable("deptId") int deptId, Model model) {
		deptService.deleteDept(deptId);
		model.addAttribute("message", "Department deleted Successfully...");
		return "showDeptList";

	}

}
