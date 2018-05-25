package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllReimbPageController {
	public static String getAllReimbPage(HttpServletRequest request,HttpServletResponse response) {
		return "/managerPartialHTML/getAllReimbPage.html";
}
}