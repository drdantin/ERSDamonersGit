package com.revature.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controller.AcceptController;
import com.revature.controller.AddReimbursementController;
import com.revature.controller.DenyController;
import com.revature.controller.LoginController;
import com.revature.controller.Logout;
import com.revature.controller.ViewAllReimbController;
import com.revature.controller.ViewEmployeeReimbController;
import com.revature.controller.WriterEmpView;


public class TheRequestHelperForMaster {
	public static int count;
	public static String process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getRequestURI() + " in request helper");
		switch(request.getRequestURI()) {
		case "/ERS_Damoners/Login.do":
			return LoginController.login(request,response);
		case "/ERS_Damoners/accept.do":
			return AcceptController.accept(request,response);
		case "/ERS_Damoners/addReimbursement.do":
			return AddReimbursementController.addReimb(request,response);
		case "/ERS_Damoners/WriterEmpView.do":
			return WriterEmpView.writeView(request, response);
		case "/ERS_Damoners/Logout.do":
			return Logout.getOut(request, response);
		case "/ERS_Damoners/viewAllReimbController.do":
			return ViewAllReimbController.viewAllReimb(request, response);
		case	"/ERS_Damoners/GetDetailViewController.do":
			return ViewEmployeeReimbController.getViewEmployeeReimb(request,response);
		case	"/ERS_Damoners/Deny.do":
			return DenyController.deny(request,response);
		default:
			return "/ERS_Damoners/welcome.html";
		}
		//GetDetailViewController.do
		
	
		
	}
}
