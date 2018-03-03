package com.revature.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controller.AcceptController;
import com.revature.controller.AddReimbursementController;
import com.revature.controller.DenyController;
import com.revature.controller.GetDetailViewController;
import com.revature.controller.LoginController;
import com.revature.controller.Logout;
import com.revature.controller.ViewAllReimbController;
import com.revature.controller.WriterEmpView;


public class TheRequestHelperForMaster {
	public static int count;
	public static String process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getRequestURI());
		switch(request.getRequestURI()) {
		case "/ERSDamoners/Login.do":
			return LoginController.login(request,response);
		case "/ERSDamoners/accept.do":
			return AcceptController.accept(request,response);
		case "/ERSDamoners/addReimbursement.do":
			return AddReimbursementController.addReimb(request,response);
		case "/ERSDamoners/WriterEmpView.do":
			return WriterEmpView.writeView(request, response);
		case "/ERSDamoners/Logout.do":
			return Logout.getOut(request, response);
		case "/ERSDamoners/viewAllReimbController.do":
			return ViewAllReimbController.viewAllReimb(request, response);
		case	"/ERSDamoners/GetDetailViewController.do":
			return GetDetailViewController.getDetailData(request,response);
		case	"/ERSDamoners/Deny.do":
			return DenyController.deny(request,response);
		default:
			return "/ERSDamoners/welcome.html";
		}
		//GetDetailViewController.do
		
	
		
	}
}
