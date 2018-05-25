package com.revature.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbursement;
import com.revature.service.ServiceDao;

public class DenyReimbController {
	
	public static String denyReimb(HttpServletRequest request,HttpServletResponse response) {
		ServiceDao serveDao = new ServiceDao();
		Reimbursement reimbObj;
		int resolverId =0;
		boolean isDenied = false;
		int statusId = 402;
		
		String resolverUsername = (String)request.getSession().getAttribute("loggedusername");
		System.out.println("the resolvername for the deny controller is " + resolverUsername);
		if(request.getMethod().equals("GET")) {
			return "welcome.html";
		}
		ObjectMapper mapper = new ObjectMapper();
		try {	
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String thejson = "";
			if(br != null){
				thejson = br.readLine();
				System.out.println("the thejson is " + thejson);
				
				reimbObj = mapper.readValue(thejson, Reimbursement.class);
				response.setContentType("application/json");   
				resolverId = serveDao.getResId(resolverUsername);
				System.out.println("The resolver id is ---> " + resolverId);
				isDenied = serveDao.deny(reimbObj.getReimbId(), resolverId, statusId);
							
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(isDenied == true) {
			String message = "Denied";
			ObjectMapper mapPrint = new ObjectMapper();
			response.setContentType("application/json");        
			try {
				mapPrint.writeValue(response.getOutputStream(), message);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			String message = "A problem has occurred with your denied command";
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