package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.revature.model.ReimbAndEmployee;
import com.revature.model.Reimbursement;

public class Dao {
	
	private static String url= "jdbc:oracle:thin:@usfdatabase.crmoxg68rqs9.us-east-2.rds.amazonaws.com:1521:orcl";
	private static String username= "damoners";
	private static String password= "ers3";
	static{ try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
     } catch (ClassNotFoundException e) {
         e.printStackTrace();
     }}

	public static boolean isManager(String user_name, String pass_word) {
		
		String userRole = "";
		try(Connection conn = DriverManager.getConnection(url, username, password);){
			String sql= "select user_role from ers_user_roles where exists("
					+ "select ers_username from ers_users where ers_user_roles.ers_user_role_id ="
					+ "ers_users.user_role_id and ers_users.ers_username=" +  
					"'" + user_name + "')"; 
			PreparedStatement stmt = conn.prepareCall(sql);
			ResultSet rset = stmt.executeQuery();
			while(rset.next()) {
			userRole = rset.getString(1);
			}
			if(userRole.equals("Manager")) {
				return true;
			}
					
			conn.close();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false ;
	}
	
	public static boolean authenticate(String user_name, String pass_word) {
		boolean isAuthenticated = false;
		int isMatch = 0;
		try(Connection conn = DriverManager.getConnection(url, username, password);){
			String sql= "select count(*) from ers_users where ers_password = "+
			"'"+pass_word+"'";
			PreparedStatement stmt = conn.prepareCall(sql);
			ResultSet rset = stmt.executeQuery();
			while(rset.next()) {
			isMatch = rset.getInt(1);
			}
			if(isMatch == 1){
				isAuthenticated = true;
				}else {
				isAuthenticated = false;
				}
			conn.close();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return isAuthenticated;
	}
	
	public static boolean addReimbursement(int user_id, int reimb_amount, int reimb_type,String description) {
		try(Connection conn = DriverManager.getConnection(url, username, password);){
			String sql ="{call ADDREIMB(?,?,?,?)}";
			CallableStatement stmt = conn.prepareCall(sql);
			stmt.setInt(1, reimb_amount);
			stmt.setInt(2, reimb_type);
			stmt.setString(3, description);
			stmt.setInt(4, user_id);
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean accept(int reimbid, int resolverid, int statusid) {
		System.out.println(reimbid + " " + resolverid + " " + statusid);
		try(Connection conn = DriverManager.getConnection(url, username, password);){
			String sql ="{call ACCEPT(?,?,?)}";
			CallableStatement stmt = conn.prepareCall(sql);
			stmt.setInt(1, reimbid);
			stmt.setInt(2, resolverid);
			stmt.setInt(3, statusid);
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean deny(int reimbid, int resolverid, int statusid) {
		try(Connection conn = DriverManager.getConnection(url, username, password);){
			String sql ="{call DENY(?,?,?)}";
			CallableStatement stmt = conn.prepareCall(sql);
			stmt.setInt(1, reimbid);
			stmt.setInt(2, resolverid);
			stmt.setInt(3, statusid);
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println("in sqlException for deny");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
public static int getUserId(String user_name) {
	int userId = 0;
	try(Connection conn = DriverManager.getConnection(url, username, password);){
		String sql= "select ers_users_id from ers_users where ers_username ="+ "'"+ user_name+"'";
		PreparedStatement stmt = conn.prepareCall(sql);
		ResultSet rset = stmt.executeQuery();
		while(rset.next()) {
			userId = rset.getInt(1);
			
			}
		conn.close();
		stmt.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return userId;
}

public static int getTypeId(String type) {
	int userId = 0;
	try(Connection conn = DriverManager.getConnection(url, username, password);){
		String sql= "select reimb_type_id from ers_reimbursement_type where reimb_type ="+ "'"+ type+"'";
		PreparedStatement stmt = conn.prepareCall(sql);
		ResultSet rset = stmt.executeQuery();
		while(rset.next()) {
			userId = rset.getInt(1);
			
			}
		conn.close();
		stmt.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return userId;
}


public static List<Reimbursement> getView(int user_id){
	List<Reimbursement> list = new ArrayList<Reimbursement>();
	try(Connection conn = DriverManager.getConnection(url, username, password);){
		String sql= "select reimb_amount,reimb_submitted,reimb_resolved,reimb_description,reimb_status"
				+ " from ers_reimbursement natural inner join ers_reimbursement_status where reimb_author ="+"'"+user_id+"'";
		PreparedStatement stmt = conn.prepareCall(sql);
		ResultSet rset = stmt.executeQuery();
		int i = 0;
		while(rset.next()) {
			list.add(new Reimbursement());
			list.get(i).setAmount(rset.getInt(1));
			list.get(i).setSubmitted(rset.getDate(2));
			list.get(i).setResolved(rset.getDate(3));
			list.get(i).setDescription(rset.getString(4));
			list.get(i).setStatus(rset.getString(5));
			++i;
			}
		
		
		conn.close();
		stmt.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return list;
}

public static List<ReimbAndEmployee> getEmployeeDescription_View(){
List<ReimbAndEmployee> list = new ArrayList<ReimbAndEmployee>();
try(Connection conn = DriverManager.getConnection(url, username, password);){
	String sql= "select user_first_name, user_last_name, user_email, reimb_resolver,reimb_submitted, reimb_resolved, "
			+ "reimb_description, reimb_status, reimb_id from ers_reimbursement er inner join ers_users es on"+ 
			" er.reimb_author = es.ers_users_id inner join ers_reimbursement_status rs on er.reimb_status_id = rs.reimb_status_id";
	PreparedStatement stmt = conn.prepareCall(sql);
	ResultSet rset = stmt.executeQuery();
	int i = 0;
	//Date someDate = new Date(System.currentTimeMillis());
	while(rset.next()) {
		list.add(new ReimbAndEmployee());
		list.get(i).setFirstname(rset.getString(1));
		list.get(i).setLastname(rset.getString(2));
		list.get(i).setUseremail(rset.getString(3));
		list.get(i).setResolver(rset.getInt(4));
		list.get(i).setSubmitted(rset.getDate(5));
		list.get(i).setResolved(rset.getDate(6));
		list.get(i).setDescription(rset.getString(7));
		list.get(i).setStatus(rset.getString(8));
		list.get(i).setReimbId(rset.getInt(9));
		++i;
		}
	conn.close();
	stmt.close();
}catch(SQLException e) {
	e.printStackTrace();
}
	return list;	
}

public static String getResolver(int user_id) {
	
	String resFirstname = "";
	String resLastname = "";
	String fullname = "";
	
	try(Connection conn = DriverManager.getConnection(url, username, password);){
		String sql= "select user_first_name, user_last_name from ers_users where ers_users_id = "+"'"+user_id+"'";
		PreparedStatement stmt = conn.prepareCall(sql);
		ResultSet rset = stmt.executeQuery();
		while(rset.next()) {
		resFirstname = rset.getString(1);
		resLastname = rset.getString(2);
		}
		fullname = resFirstname.concat(resLastname);
		conn.close();
		stmt.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return fullname;
}

public static int getResId(String user_name) {
System.out.println(" in getResId of DAO and the username is -- this should be mel ---> " + username);
System.out.println("Have checked this statement out in sql and it worked--if still broken than check query from here");
	int reimb_resolver = 0;
	try(Connection conn = DriverManager.getConnection(url, username, password);){
		
		String sql= "select ers_users_id from ers_users where ers_username ="+"'"+user_name+"'";
		PreparedStatement stmt = conn.prepareCall(sql);
		ResultSet rset = stmt.executeQuery();
		while(rset.next()) {
		reimb_resolver = rset.getInt(1);
		}
System.out.println(" The reimbresolver id from database is " + reimb_resolver);
		conn.close();
		stmt.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return reimb_resolver;
}

public static int getStatusId(String status) {
	
	int reimb_status_id = 0;
	
	try(Connection conn = DriverManager.getConnection(url, username, password);){
		String sql= "select ers_status_id from ers_reimbursement_status where reimb_status= "+"'"+status+"'";
		PreparedStatement stmt = conn.prepareCall(sql);
		ResultSet rset = stmt.executeQuery();
		while(rset.next()) {
		reimb_status_id = rset.getInt(1);
		}
		
		conn.close();
		stmt.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return reimb_status_id;
	}
}
