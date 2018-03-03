package com.revature.model;

import java.io.Serializable;

public class User implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String ers_username;
private String ers_password;


public String getErs_username() {
	return ers_username;
}
public void setErs_username(String ers_username) {
	this.ers_username = ers_username;
}
public String getErs_password() {
	return ers_password;
}
public void setErs_password(String ers_password) {
	this.ers_password = ers_password;
}

public User(String ers_username, String ers_password) {
	super();
	
	this.ers_username = ers_username;
	this.ers_password = ers_password;
	
}
}
