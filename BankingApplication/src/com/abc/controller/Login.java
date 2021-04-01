package com.abc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.model.Model;

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	
	private HttpSession session;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String custid = request.getParameter("custid");
		
		String pwd = request.getParameter("password");
		 session = request.getSession(true);
		
		try {
			Model m = new Model();
			
			m.setCustid(custid);
			m.setPwd(pwd);
			
			int accno = m.login();
			
			if(accno!=0) {
				
				session.setAttribute("accno", accno);
				
				response.sendRedirect("Home.html");
			}
			else {
				response.sendRedirect("Error.html");
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
