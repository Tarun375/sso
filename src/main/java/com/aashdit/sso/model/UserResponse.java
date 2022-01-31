package com.aashdit.sso.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Tarun Chakrabarty
 * @since  2022
 *
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements Serializable {

	private static final long serialVersionUID = -279639504161103657L;
	
	private String token;
	private String message;
}
