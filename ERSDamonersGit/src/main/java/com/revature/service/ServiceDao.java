package com.revature.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.Dao;
import com.revature.model.DetailView;
import com.revature.model.ViewReimbPojo;

public class ServiceDao implements Service {
	
	private List<ViewReimbPojo> list;
	@Override
	public boolean isManager(String user_name, String pass_word) {
		boolean is_Manager = false;
		is_Manager = Dao.isManager(user_name, pass_word);
		return is_Manager;
	}

	@Override
	public boolean authenticate(String user_name, String pass_word) {
		boolean isAuthenticated = false;
		isAuthenticated = Dao.authenticate(user_name, pass_word);
		return isAuthenticated;
	}

	@Override
	public boolean addReimbursement(int user_id, int reimbursement, int reimb_type, String reimb_description) {
		boolean isTrue = Dao.addReimbursement(user_id, reimbursement, reimb_type, reimb_description);
		return isTrue;
	}

	@Override
	public int getUserId(String user_name) {
		int userId = 0;
		userId = Dao.getUserId(user_name);
		return userId;
	}
	
	@Override
	public int getType(String type) {
		int typeid;
		typeid = Dao.getTypeId(type);
		return typeid;
	}
	
	@Override
	public List<ViewReimbPojo> getView(int user_id) {
		
		list = Dao.getView(user_id);
		return list;
	}
	
	@Override
	public List<DetailView> getDetailView(){
		List<DetailView> list;
		list = Dao.getDetailView();			
		return list;
	}
	
	@Override
	public String getResolver(int user_id) {
		String fullname = Dao.getResolver(user_id);
		
		return fullname;
	}
	
	public int getResId(String username) {
		int getRes = Dao.getResId(username);
		
		return getRes;
	}
	
	public int getStatusId(String status) {
		int getStatus = Dao.getStatusId(status);
		return getStatus;
	}
	
	public boolean accept(int reimbid, int resolverid, int statusid) {
		boolean isAccept = Dao.accept(reimbid, resolverid, statusid);
		return isAccept;
	}
	
	public boolean deny(int reimbid, int resolverid, int statusid) {
		boolean isDenied = Dao.deny(reimbid, resolverid, statusid);
		return isDenied;
	}
}
