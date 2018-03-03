package com.revature.model;

import java.sql.Date;

public class DetailView {
	
	private String firstname;
	 private String lastname;
	 private String useremail;
	 private int resolver;
	 private Date submitted;
	 private Date resolved;
	 private String description;
	 private int amount;
	 private String status;
	 private int reimbId;
	 
	 public DetailView(String firstname, String lastname, String useremail, String author, int resolver,
			Date submitted, Date resolved, String description, int amount, String status,int reimbId) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.useremail = useremail;
		this.resolver = resolver;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.amount = amount;
		this.status = status;
		this.reimbId = reimbId;
	}
	 
	 public DetailView() {
		 
	 }

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public Date getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Date submitted) {
		this.submitted = submitted;
	}

	public Date getResolved() {
		return resolved;
	}

	public void setResolved(Date resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}
	
	 
	 
}
