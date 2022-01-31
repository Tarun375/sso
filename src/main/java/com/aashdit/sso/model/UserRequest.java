package com.aashdit.sso.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Tarun Chakrabarty
 * @since 25-Jan-2022
 * 
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest implements Serializable {

	private static final long serialVersionUID = 6514494171116551703L;

	private String userName;
	private String password;
}
