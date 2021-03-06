package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Employee;
import com.revature.servlets.TheRequestHelperForMaster;

public class TheMasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static boolean isWritten = false;  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(TheRequestHelperForMaster.process(request,response)).forward(request, response);
	}
//RequestDispatcher-Defines an object that receives requests from the client and sends them to any resource (such as a servlet, HTML file, 
//or JSP file) on the server.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String check = "yes";
		String endpoint = TheRequestHelperForMaster.process(request,response);
		
		if(check != endpoint) {
			request.getRequestDispatcher(endpoint).forward(request, response);
		}else {
			request.getRequestDispatcher(endpoint);
		}
	}
}
