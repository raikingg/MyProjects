package com.nit.servletes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/contact")
public class contactus extends HttpServlet {
	private Connection cn;
	public void init()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","Shivanand");
		}
		catch(Exception k)
		{
			k.printStackTrace();
		}}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name=req.getParameter("t1");
		String email=req.getParameter("t2");
		String clientid=req.getParameter("t3");
		String message=req.getParameter("t4");
		
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		try
		{
			PreparedStatement ps=cn.prepareStatement("insert into contact values(?,?,?,?)");
			ps.setString(1,name);
			ps.setString(2,email);
			ps.setString(3,clientid);
			ps.setString(4,message);
			
			RequestDispatcher rd=req.getRequestDispatcher("contact.html");
			rd.include(req,res);
			ps.executeUpdate();
			out.println("<center>");
			out.println("<font color='red'>");
			out.println("<h2>Your Message Has Been Received</font> </h2></center>");
			
		}
		catch(Exception k)
		{
			out.println("<center>");
			out.println("<font color='red'>");
			out.println("<h2>Your Message Not Receive</font></h2></center>");
		
			
	}

}}
