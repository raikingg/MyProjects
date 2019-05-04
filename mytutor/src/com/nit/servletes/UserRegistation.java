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


@WebServlet("/User")
public class UserRegistation extends HttpServlet {
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
		String  email=req.getParameter("t1");
		String  age=req.getParameter("t2");
		String phone=req.getParameter("t3");
		String course=req.getParameter("t4");
		String  psw=req.getParameter("t5");
		
		
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		try
		{
			PreparedStatement ps=cn.prepareStatement("insert into userregistation values(?,?,?,?,?)");
			ps.setString(1, email);
			ps.setString(2, age);
			ps.setString(3,phone);
			ps.setString(4,course);
			ps.setString(5,psw);
			
			
			RequestDispatcher rd=req.getRequestDispatcher("signup.html");
			rd.include(req,res);
			ps.executeUpdate();
			out.println("<center>");
			out.println("<font color='#00ff40'>");
			out.println("<h2>Your Registation Sucessfull</h2></font></center>");
		}
		catch(Exception k)
		{out.println("<center>");
		out.println("<font color='red'>");
			out.println("<h2>Error your Registation </h2></font></center>");
		
			
	}

}}
