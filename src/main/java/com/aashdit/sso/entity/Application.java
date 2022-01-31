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

/**
 * @author Tarun Chakrabarty.
 *
 *         29-Jan-2022
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "sso_applicationmaster")
public class Application {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int appId;
	@NotEmpty(message = "Application Name is required")
	private String appName;
	private String deptNo;
	private String appUrl;

	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	private String updatedBy;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;
	
	private Boolean isActive;

	@Transient
	private String userUniqueError;

}
