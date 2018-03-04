package com.revature.model;

public class AddReimbursementAjax {
	private int amount;
	private String type;
	private String description;

	public AddReimbursementAjax(int amount, String type, String description) {
		super();
		this.amount = amount;
		this.type = type;
		this.description = description;
	}

	public AddReimbursementAjax() {

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

}
