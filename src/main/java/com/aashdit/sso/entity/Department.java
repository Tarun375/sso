package com.aashdit.sso.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sso_deptmaster")
public class Department implements Serializable{
	
	@Transient
	private static final long serialVersionUID = -6958455362895051210L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int deptId;
	@NotEmpty(message = "Department Name is required")
	private String deptName;
	private String deptCode;
	private String createdBy;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	private String updatedBy;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;
	private Boolean isActive;
	
	@Transient
	private String deptUniqueError;

}
