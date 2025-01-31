package com.darshan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.darshan.model.Model;

@WebServlet("/Register")
public class Register extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String custid = request.getParameter("custid");
		int accno = Integer.parseInt(request.getParameter("accno"));
		String pwd = request.getParameter("pwd");
		int bal = Integer.parseInt(request.getParameter("bal"));
		String email = request.getParameter("email");
		
		try {
			Model m = new Model();
			m.setName(name);
			m.setCustid(custid);
			m.setAccno(accno);
			m.setBalance(bal);
			m.setPwd(pwd);
			m.setEmail(email);
			
			boolean b = m.register();
			
			if(b==true) {
				response.sendRedirect("SuccessReg.html");
			}
			else {
				response.sendRedirect("FailureReg.html");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
