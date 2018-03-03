package com.revature.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Accept;
import com.revature.service.ServiceDao;

public class AcceptController {
	public static String accept(HttpServletRequest request,HttpServletResponse response) {
		ServiceDao serveDao = new ServiceDao();
		Accept acceptObj;
		int resolverId =0;
		boolean isAccept = false;
		int statusId = 401;
		
		String resolverUsername = (String)request.getSession().getAttribute("loggedusername");
		if(request.getMethod().equals("GET")) {
			return "index.html";
		}
		ObjectMapper mapper = new ObjectMapper();
		try {	
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String thejson = "";
			if(br != null){
				thejson = br.readLine();
				System.out.println("the thejson is " + thejson);
				
				acceptObj = mapper.readValue(thejson, Accept.class);
				
				response.setContentType("application/json");   
				resolverId = serveDao.getResId(resolverUsername);
				isAccept = serveDao.accept(acceptObj.getReimbid(), resolverId, statusId);
							
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