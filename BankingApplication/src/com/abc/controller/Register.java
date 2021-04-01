package com.abc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.model.Model;

@WebServlet("/Register")
public class Register extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String custid = request.getParameter("custid");
		
		String saccno = request.getParameter("accno");//collects the data in form od string
		int accno = Integer.parseInt(saccno);//Converts String data to int data
		
		String pwd = request.getParameter("password");
		
		String sbal = request.getParameter("bal");//collects the data in form od string
		int bal = Integer.parseInt(sbal);//Converts String data to int data
		
		String email = request.getParameter("email");
		
		try {
			Model m = new Model();
			
			m.setName(name);
			m.setCustid(custid);
			m.setAccno(accno);
			m.setPwd(pwd);
			m.setBal(bal);
			m.setEmail(email); 
			
			boolean b = m.Register();
			
			if(b==true) {
				response.sendRedirect("SuccessReg.html");
			}
			else {
				response.sendRedirect("FailureReg.html");
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
