package com.darshan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.darshan.model.Model;

@WebServlet("/Transfer")
public class Transfer extends HttpServlet {

    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int accno = (int)session.getAttribute("accno");
		int amt = Integer.parseInt(request.getParameter("amt"));
		int raccno = Integer.parseInt(request.getParameter("raccno"));
		
		
		try {
			Model m = new Model();
			m.setAccno(accno);
			m.setBalance(amt);
			m.setRaccno(raccno);
			boolean b = m.transfer();
			if(b==true) {
				response.sendRedirect("TransferSuccess.html");
			}
			else {
				response.sendRedirect("TransferFailure.html");
			}
		}
		catch(Exception e) {
			
		}
		
	}

}
