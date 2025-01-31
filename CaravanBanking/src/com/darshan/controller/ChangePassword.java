package com.darshan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.darshan.model.Model;

@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
       
 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pwd = request.getParameter("npwd");
		HttpSession session = request.getSession();
		int accno = (int) session.getAttribute("accno");
		
			Model m;
			try {
				m = new Model();
				m.setAccno(accno);
				m.setPwd(pwd);
				boolean b = m.changePwd();
				if(b==true) {
					response.sendRedirect("ChangePwdSuccess.html");
				}
				else {
					response.sendRedirect("ChangePwdFail.html");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
