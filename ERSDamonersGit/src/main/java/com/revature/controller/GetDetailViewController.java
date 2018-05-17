package com.revature.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.ReimbDescription_View;
import com.revature.model.EmployeeDescription_View;
import com.revature.service.ServiceDao;

public class GetDetailViewController {
	public static String getDetailData(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("In GetDetailViewController");
		String isWritten = "yes";
	
		List<EmployeeDescription_View> generalListView;
	
		List<ReimbDescription_View> completedListToSend = new ArrayList<>();
		
		List<String> resolversList = new LinkedList<String>();
		
		ServiceDao serveDao = new ServiceDao();
		
		generalListView = serveDao.getDetailView();
		
		for(int i = 0; i < generalListView.size();i++) {
			resolversList.add(serveDao.getResolver(generalListView.get(i).getResolver()));
		
		}
		
		completedListToSend = getResFullName(generalListView, resolversList);
		
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json");        
		try {
			mapper.writeValue(response.getOutputStream(), completedListToSend);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return isWritten;
	}
	private static List<ReimbDescription_View> getResFullName(List<EmployeeDescription_View> general, List<String> resolver){
	List<ReimbDescription_View> fullList = new ArrayList<ReimbDescription_View>();
	
		for(int i = 0; i < general.size();i++) {
			ReimbDescription_View view = new ReimbDescription_View();
			
			String name = general.get(i).getFirstname();
			String fullname = name.concat(" ").concat(general.get(i).getLastname());
			view.setAuthor(fullname);
			view.setEmail(general.get(i).getUseremail());
			view.setSubmitted(general.get(i).getSubmitted());
			view.setDescription(general.get(i).getDescription());
			view.setStatus(general.get(i).getStatus());
			view.setResolved(general.get(i).getResolved());
			System.out.println(" The Date in gerResFullName in GetDetailViewController is " + view.getResolved());
			view.setResolver(resolver.get(i));
			view.setReimbId(general.get(i).getReimbId());
			fullList.add(view);

		}
		return fullList;
	}
}
