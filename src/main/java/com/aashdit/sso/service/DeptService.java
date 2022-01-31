package com.aashdit.sso.service;

import java.util.List;
import javax.validation.Valid;
import com.aashdit.sso.entity.Department;
/**
 * 
 * @author Tarun Chakrabarty
 * @since 24-Jan-2022
 * 
 *
 */

public interface DeptService {
	
	public String saveDept(Department dept);
	
	public List<Department> showAllDept();
	
	public String updateDeptDetails(@Valid Department dept);
	
	public void deleteDept(int deptId);

}
