package com.servlet.submit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/submit")
public class submitServlet extends HttpServlet{
	//create query
	
	private static final String INSERT_QUERY = "INSERT INTO ridedetails(name,phone,time,drivername,date,carmodel,carsize,source,kilometers,destination,customeremail) VALUES (?,?,?,?,?,?,?,?,?,?,?))";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
	PrintWriter pw = res.getWriter();//get print writer
	res.setContentType("text/html");
	String name = req.getParameter("name");
	String phone = req.getParameter("phone");
	String time = req.getParameter("time");
	String drivername = req.getParameter("drivername");
	String date = req.getParameter("date");
	String carmodel = req.getParameter("carmodel");
	String carsize = req.getParameter("carsize");
	String source = req.getParameter("source");
	String kilometers = req.getParameter("kilometers");
	String destination = req.getParameter("destination");
	String customeremail = req.getParameter("customeremail");
	
//load the jdbc driver
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
//create connection	
	try(Connection con = DriverManager.getConnection("jdbc:mysql:///sys","root","Rootpassword123");
			PreparedStatement ps = con.prepareStatement(INSERT_QUERY);){
	//set the values
		ps.setString(1, name);
		ps.setString(2, phone);
		ps.setString(3, time);
		ps.setString(4, drivername);
		ps.setString(5, date);
		ps.setString(6, carmodel );
		ps.setString(7, carsize);
		ps.setString(8, source);
		ps.setString(9, kilometers);
		ps.setString(10,destination);
		ps.setString(11, customeremail);
		
  
		//the query executed
		int count = ps.executeUpdate();
		if(count==0) {
			pw.println("YOUR JOURNEY CANCELLED");
		}else {
			pw.println(" BOOKING ACCEPTED HAPPY JOURNEY ");
		}
		
		
	}catch(SQLException se) {
		pw.println(se.getMessage());
		se.printStackTrace();
	}catch(Exception e) {
		pw.println(e.getMessage());
		e.printStackTrace();
	}
		
//*
	//close stream
	pw.close();
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
}
