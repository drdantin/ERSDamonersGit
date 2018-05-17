package com.revature.service;

import java.util.List;

import com.revature.model.EmployeeDescription_View;
import com.revature.model.Reimbursement;

public interface Service {
	public boolean isManager(String user_name,String pass_word);
	
	public boolean authenticate(String user_name,String pass_word);
	
	public boolean addReimbursement(int user_id,int reimbursement,int reimb_type,String description);
	
	public int getUserId(String user_name);
	
	public List<Reimbursement> getView(int user_id);
	
	public List<EmployeeDescription_View> getDetailView();
	
	public String getResolver(int user_id);
	
	public int getType(String type);
	
	public int getResId(String username);
	
	public int getStatusId(String status);
	
	public boolean accept(int reimbid, int resolverid, int statusid);
	
	public boolean deny(int reimbid, int resolverid, int statusid);
}