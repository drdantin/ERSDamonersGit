package com.revature.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.revature.dao.Dao;
import com.revature.model.Reimbursement;
import com.revature.service.ServiceDao;

public class AddReimbursementController{
	
	public static String addReimb(HttpServletRequest request,HttpServletResponse response) {
		ServiceDao serveDao = new ServiceDao();
		boolean isSuccess = false;

		if(request.getMethod().equals("GET")) {
			return "index.html";
		}
		ObjectMapper mapper = new ObjectMapper();
		try {	
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String thejson = "";
			if(br != null){
				thejson = br.readLine();
				
				Reimbursement ajax = mapper.readValue(thejson, Reimbursement.class);
				response.setContentType("application/json");   
				String username = (String)request.getSession().getAttribute("loggedusername");
				int user_id = serveDao.getUserId(username);
				int getType = serveDao.getType(ajax.getType());
				
				isSuccess = serveDao.addReimbursement(user_id,ajax.getAmount(),getType,ajax.getDescription());
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
			if(isSuccess == true) {
			String successMessage = "Reimbursement Added";
			ObjectMapper mapPrint = new ObjectMapper();
			response.setContentType("application/json");        
			try {
				mapPrint.writeValue(response.getOutputStream(), successMessage);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		return "yes";
	}
}