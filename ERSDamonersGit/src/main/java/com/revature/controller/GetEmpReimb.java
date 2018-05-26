package com.revature.controller;


import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbursement;
import com.revature.service.ServiceDao;
//GetEmp Reimb gets employee reimbursement information through the use of request.getSession.
//getAttribute. Once the username is attained than use the username as a parameter for a service
//method to call the Dao for the user id. Than this id can be used to get Reimbursements. ObjectMapper
// is used to writeValue to an OutputStream.
public class GetEmpReimb {
	
	public static String getEmpReimb(HttpServletRequest request,HttpServletResponse response) {
		String isWritten = "yes";
		List<Reimbursement> list;
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
