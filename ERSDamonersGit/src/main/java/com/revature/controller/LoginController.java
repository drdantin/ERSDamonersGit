package com.revature.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.service.ServiceDao;

public class LoginController {

	public static String login(HttpServletRequest request,HttpServletResponse response) {
		ServiceDao serveDao = new ServiceDao();
		boolean isManager = false;
		boolean isAuthenticated = false;
		String successMessage = "Success";
		String loginFailed = "Login Failed";
		String manMessage = "man";
		String username = "";
		String password = "";

		if(request.getMethod().equals("GET")) {
			return "welcome.html";
		}

		try {	
			String json="";
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

			if(br != null){
				json = br.readLine();
			}
			ObjectMapper mapper = new ObjectMapper();
			String[] arrayJson = mapper.readValue(json, String[].class);
			response.setContentType("application/json");            

			username = arrayJson[0];
			password = arrayJson[1];

		}catch(Exception e) {
			e.printStackTrace();
		}

		if(username.equals("")) {
			return "index.html";
		}
		else {
			request.getSession().setAttribute("loggedusername", username);
			request.getSession().setAttribute("loggedpassword", password);

			isManager = serveDao.isManager(username,password);
			isAuthenticated = serveDao.authenticate(username, password);
	
			if(isManager == true && isAuthenticated == true) {
				ObjectMapper mapper = new ObjectMapper();
				response.setContentType("application/json");        
				try {
					mapper.writeValue(response.getOutputStream(), manMessage);
				}catch(IOException e) {
					e.printStackTrace();
				}
				return "yes";
			}
			else if(isManager == true && isAuthenticated == false) {
				return "/manPage.do";
			}
			else if(isManager == false && isAuthenticated == true) {
				ObjectMapper mapper = new ObjectMapper();
				response.setContentType("application/json");        
				try {
					mapper.writeValue(response.getOutputStream(), successMessage);
				}catch(IOException e) {
					e.printStackTrace();
				}
				return "yes";
			}
			else if(isManager == false && isAuthenticated == false) {
				ObjectMapper mapper = new ObjectMapper();
				response.setContentType("application/json");        
				try {
					mapper.writeValue(response.getOutputStream(), loginFailed);
				}catch(IOException e) {
					e.printStackTrace();
				}
				return "yes";
			}
			return "/welcome.do";
		}
	}
}	