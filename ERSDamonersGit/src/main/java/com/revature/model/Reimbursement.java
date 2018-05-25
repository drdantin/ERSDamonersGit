package com.revature.model;

import java.sql.Date;

public class Reimbursement {
	
	private int reimbId;
	private int amount;
	private String type;
	private String description;
	private String author;
	private String statusId;
	private Date resolved;
	private String resolver;
	private Date submitted;
	private String status;
	private int resolverId;
		
	public Reimbursement() { }
	
	public Reimbursement(int reimbId) {
		this.reimbId = reimbId;
	}
	
	public Reimbursement( int amount, String type, String description) {
		this.amount = amount;
		this.type = type;
		this.description = description;
	}
	
	public Reimbursement (int amount, Date submitted, Date resolved, String description, String status) {
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.status = status;
	}
	
	public int getReimbId() {
		return reimbId;
	}
	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
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
	public Date getSubmitted() {
		return submitted;
	}
	public void setSubmitted(Date submitted) {
		this.submitted = submitted;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public int getResolverId() {
		return resolverId;
	}

	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}
	
	public String toString() {
		return reimbId + " " + amount + " " + type + " " + description + " " +
	author + " " + statusId + " " + resolved + " " + submitted + " " + status + " " + resolverId;
	}
}
