package com.aashdit.sso.service;

import java.util.List;
import javax.validation.Valid;
import com.aashdit.sso.entity.Application;


/**
 * @author Tarun Chakrabarty.
 *
 * 29-Jan-2022
 */
public interface ApplicationService {
	
public String saveApp(Application app);
	
	public List<Application> showAllApp();
	
	public String updateAppDetails(@Valid Application app);
	
	public void deleteApp(int appId);


}
