package com.aashdit.sso.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name="sso_usermaster")
@Table
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String fullName;
	private String email;
	private String userName;
	private String password;
	private String mobno;
	private String departmentName;
	private String role; 
	private String createdBy;
	private String createdOn; 
	private String updatedBy;
	private String updatedOn;
	private String isActive;
		
	
	
		
	  
	 }
	
	
	


