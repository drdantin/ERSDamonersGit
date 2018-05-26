package com.revature.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controller.AddReimbursementController;
import com.revature.controller.AproveReimbController;
import com.revature.controller.DenyReimbController;
import com.revature.controller.GetAllEmpReimbController;
import com.revature.controller.GetAllReimbPageController;
import com.revature.controller.LoginController;
import com.revature.controller.LogoutController;

import com.revature.controller.GetEmpReimb;


public class TheRequestHelperForMaster {
	public static int count;
	public static String process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getRequestURI() + " in request helper");
		switch(request.getRequestURI()) {
		case "/ERS_Damoners/Login.do":
			return LoginController.login(request,response);
		case "/ERS_Damoners/aproveReimb.do":
			return AproveReimbController.aproveReimb(request,response);
		case "/ERS_Damoners/addReimbursement.do":
			return AddReimbursementController.addReimb(request,response);
		case "/ERS_Damoners/GetEmpReimb.do":
			return GetEmpReimb.getEmpReimb(request, response);
		case "/ERS_Damoners/Logout.do":
			return LogoutController.logOut(request, response);
		case "/ERS_Damoners/GetAllReimbPageController.do":
			return GetAllReimbPageController.getAllReimbPage(request, response);
		case	"/ERS_Damoners/GetAllEmpReimbController.do":
			return GetAllEmpReimbController.getAllEmpReimb(request,response);
		case	"/ERS_Damoners/denyReimb.do":
			return DenyReimbController.denyReimb(request,response);
		default:
			return "/ERS_Damoners/welcome.html";
		}
	}
}
