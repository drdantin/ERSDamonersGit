package com.revature.model;

import java.sql.Date;

public class ViewReimbPojo {
	private int reimb_amount;
	private Date subDate;
	private Date resDate;
	private String description;
	private String reimb_status;
	
	public ViewReimbPojo(int reimb_amount, Date subDate, Date resDate, String description, String reimb_status) {
		super();
		this.reimb_amount = reimb_amount;
		this.subDate = subDate;
		this.resDate = resDate;
		this.description = description;
		this.reimb_status = reimb_status;
		
	}
	public ViewReimbPojo() {
		
	}
	public int getReimb_amount() {
		return reimb_amount;
	}
	public void setReimb_amount(int reimb_amount) {
		this.reimb_amount = reimb_amount;
	}
	public Date getSubDate() {
		return subDate;
	}
	public void setSubDate(Date subDate) {
		this.subDate = subDate;
	}
	public Date getResDate() {
		return resDate;
	}
	public void setResDate(Date resDate) {
		this.resDate = resDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRiembStatus() {
		return reimb_status;
	}
	public void setRiembStatus(String reimbstatus) {
		this.reimb_status = reimbstatus;
	}
	
	
	public String toString() {
		return reimb_amount + " " + subDate + " " + resDate + " " + description + " " + reimb_status;
	}
}
