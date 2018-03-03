package com.revature.model;

import java.sql.Date;

public class DetailEmpView {
	private String author;
	private String email;
	private Date submitted;
	private String description;
	private String status;
	private Date resolved;
	private String resolver;
	private int reimbId;
	
	public DetailEmpView(String author, String email, Date submitted, String description, String status, Date resolved,
			String resolver, int reimbId) {
		super();
		this.author = author;
		this.email = email;
		this.submitted = submitted;
		this.description = description;
		this.status = status;
		this.resolved = resolved;
		this.resolver = resolver;
		this.reimbId = reimbId;
	}
	
	public DetailEmpView() {
		
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Date submitted) {
		this.submitted = submitted;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getResolved() {
		return resolved;
	}

	public void setResolved(Date resolved) {
		this.resolved = resolved;
	}

	public String getResolver() {
		return resolver;
	}

	public void setResolver(String resolver) {
		this.resolver = resolver;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}
	
	
	
}
