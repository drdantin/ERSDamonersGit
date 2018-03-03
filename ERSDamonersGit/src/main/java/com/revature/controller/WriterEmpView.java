package com.revature.controller;


import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.ViewReimbPojo;
import com.revature.service.ServiceDao;

public class WriterEmpView {
	public static String writeView(HttpServletRequest request,HttpServletResponse response) {
		String isWritten = "yes";
		List<ViewReimbPojo> list;
		ServiceDao serveDao = new ServiceDao();
		String username = (String)request.getSession().getAttribute("loggedusername");
		int user_id = serveDao.getUserId(username);
		list = serveDao.getView(user_id);
		
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json");        
		try {
			mapper.writeValue(response.getOutputStream(), list);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return isWritten;
	}
}
