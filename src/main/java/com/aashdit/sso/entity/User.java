package com.aashdit.sso.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Biswa Bhusan Sahoo
 * @since 25-Jan-2022
 * 
 *
 *
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "sso_usermaster")
public class User implements Serializable {

	@Transient
	private static final long serialVersionUID = 3220196314734125621L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	@NotEmpty(message = "fullName is required")
	private String fullName;

	@Column(unique = true, nullable = false)
	@NotEmpty(message = "Username is required")
	private String userName;

	@Column(nullable = false)
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Invalid Password")
	private String password;
	@Column(unique = true, nullable = false)
	@Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message = "Invalid Mobile Number")
	private String mobileNo;
	@Column(unique = true, nullable = false)
	@Email(message = "Invalid Email")
	@NotBlank(message = "Email is required")
	private String email;
	private String departmentName;
	private String role;
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
