package com.aashdit.sso.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aashdit.sso.entity.Department;
import com.aashdit.sso.repository.DeptRepository;
import com.aashdit.sso.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeptServiceImpl implements DeptService{
	
	@Autowired
	private DeptRepository deptRepository;

	@Override
	public String saveDept(Department dept) {
		Date date=new Date();
		dept.setCreatedOn(date);
		dept.setIsActive(Boolean.TRUE);
		Department saveDept = deptRepository.save(dept);
		log.debug("Department added Successfull :: {}", saveDept.getDeptName());
		return "Department added Successfully!";
	}

	public List<Department> showAllDept() {
		
		List<Department> deptList = deptRepository.findAll();
		return deptList;
		
	}
	
	

}
