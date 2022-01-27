package com.aashdit.sso.service;

import java.util.List;

import com.aashdit.sso.entity.Department;

public interface DeptService {
	
	public String saveDept(Department dept);
	
	public List<Department> showAllDept();

}
