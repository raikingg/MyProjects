package com.nit.servletes;
import javax.servlet.*;

import java.io.*;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/User1")
public class User1Login extends GenericServlet 
	{
		private Connection cn;
		public void init(ServletConfig config)throws ServletException
		{
			super.init(config);
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","Shivanand");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		public void service(ServletRequest req, ServletResponse res)throws ServletException, IOException
		{
			String email=req.getParameter("t1");
			String psw=req.getParameter("t2");
			PrintWriter out=res.getWriter();
			try{
				PreparedStatement ps=cn.prepareStatement("select count(*) from userregistation where email=? and psw=?");
				ps.setString(1, email);
				ps.setString(2, psw);
				ResultSet rs=ps.executeQuery();
				rs.next();
				int c=rs.getInt(1);
				if(c==1)
				{
					out.println("<html>");
					out.println("<body bgcolor=pink>");
					out.println("<h2>");
					RequestDispatcher rd=req.getRequestDispatcher("images.html");
					rd.include(req, res);
					out.println("</h2></body></html>");
				}else
				{
					out.println("<html>");
					out.println("<body bgcolor=	#4000ff>");
					out.println("<h2>");
					out.println("<center>");
					out.println("<font color='#ff0000'>");
					out.println("<br>");
					out.println("<br>");
					out.println("Sorry Invalid Your User name or Password");
					out.println("</h2></font></center></body></html>");
				}	
			}
			catch(Exception p){
				p.printStackTrace();
			}
		
		}
		
		}
	