package com.revature.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.service.ServiceDao;
//LoginController does the businness logic oriented around the login action. Within its static login 
//method it uses BufferedReader to get the input stream asynchronously coming from reimbJavascript Ajax
//call. Within javascript side it sends login username and password data. It is checked and what is 
//returned from the Controllers business logic is information which routes to a new page and that uses
//ObjectMapper to send the database info to the reciever end of the reimbJavascipt login call. 
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
				System.out.println("in ismaanger = false and isauthenticated = true");
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