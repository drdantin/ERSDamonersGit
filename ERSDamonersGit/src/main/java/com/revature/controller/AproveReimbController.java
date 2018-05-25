package com.revature.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbursement;
import com.revature.service.ServiceDao;

public class AproveReimbController {
	
	public static String aproveReimb(HttpServletRequest request,HttpServletResponse response) {
		ServiceDao serveDao = new ServiceDao();
		Reimbursement reimbObj;
		int id =0;
		boolean isAccept = false;
		int statusId = 401;
		
		String userName = (String)request.getSession().getAttribute("loggedusername");
		if(request.getMethod().equals("GET")) {
			return "index.html";
		}
		ObjectMapper mapper = new ObjectMapper();
		try {	
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String thejson = "";
			if(br != null){
				thejson = br.readLine();
				
				reimbObj = mapper.readValue(thejson, Reimbursement.class);
				
				response.setContentType("application/json");   
				id = serveDao.getResId(userName);
				isAccept = serveDao.accept(reimbObj.getReimbId(), id, statusId);
							
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(isAccept == true) {
			String message = "Accepted";
			ObjectMapper mapPrint = new ObjectMapper();
			response.setContentType("application/json");        
			try {
				mapPrint.writeValue(response.getOutputStream(), message);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			String message = "A problem has occurred with your acceptance command";
			ObjectMapper mapPrint = new ObjectMapper();
			response.setContentType("application/json");        
			try {
				mapPrint.writeValue(response.getOutputStream(), message);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		return "yes";
}
}