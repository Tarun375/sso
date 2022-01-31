package com.aashdit.sso.service;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aashdit.sso.entity.Application;
import com.aashdit.sso.repository.ApplicationRepository;

/**
 * @author Tarun Chakrabarty.
 *
 * 29-Jan-2022
 */
@Service
public class ApplicationServiceImpl implements ApplicationService{

	@Autowired
	private ApplicationRepository applicationRepository;
	@Override
	public String saveApp(Application app) {
		Date date = new Date();
		app.setCreatedOn(date);
		app.setIsActive(Boolean.TRUE);
		//app.setJwtToken("jwttokenNotcreatedYet");
		//app.setTokenExpirydate("date");
		applicationRepository.save(app);
		return "Apllication added successfully!";
	}

	@Override
	public List<Application> showAllApp() {
		List<Application> appList = applicationRepository.findAll();
		return appList;
	}

	@Override
	public String updateAppDetails(@Valid Application app) {
		 applicationRepository.save(app);
		 return "Application updated Successfully";
	}

	@Override
	@Transactional
	public void deleteApp(int appId) {
		applicationRepository.deleteById(appId);
		
	}

}
