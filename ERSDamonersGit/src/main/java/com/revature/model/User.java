package com.revature.model;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private String firstName;
	private String lastName;
	private String passWord;
	private String userEmail;
	private String userName;
	private int userRoleId;
	
	public User(String ers_username, String ers_password) {
		super();

		this.userName = ers_username;
		this.passWord = ers_password;
	}

	public String getUsername() {
		return userName;
	}
	public void setUsername(String ersUsername) {
		this.userName = ersUsername;
	}
	public String getPassword() {
		return passWord;
	}
	public void setPassword(String ersPassword) {
		this.passWord = ersPassword;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}
	
	
}
