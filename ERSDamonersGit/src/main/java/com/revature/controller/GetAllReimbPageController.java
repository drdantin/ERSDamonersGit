package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//This is the only class which does not return 'yes' for the masterservlet doPost method. This method
//returns the html path as a String to be initialized by String endpoint in MasterServlet doPost method.
//The html string is initialized to endpoint and endpoint discerns whether to forward or not. 
//*******This needs to be further looked into
public class GetAllReimbPageController {
	public static String getAllReimbPage(HttpServletRequest request,HttpServletResponse response) {
		return "/managerPartialHTML/getAllReimbPage.html";
}
}