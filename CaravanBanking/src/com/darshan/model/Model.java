package com.darshan.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Statement;

public class Model {
		private String name;
		private String custid;
		private int accno;
		private String pwd;
		private int balance;
		private String email;
		
		private int raccno;
		public ArrayList al = new ArrayList();
		public ArrayList sal = new ArrayList();
		public ArrayList ral = new ArrayList();
		
		private Connection con;
		private PreparedStatement pstmt;
		
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCustid() {
			return custid;
		}
		public void setCustid(String custid) {
			this.custid = custid;
		}
		public int getAccno() {
			return accno;
		}
		public void setAccno(int accno) {
			this.accno = accno;
		}
		public String getPwd() {
			return pwd;
		}
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
		public int getBalance() {
			return balance;
		}
		public void setBalance(int balance) {
			this.balance = balance;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public int getRaccno() {
			return raccno;
		}
		public void setRaccno(int raccno) {
			this.raccno = raccno;
		}
		
		public Model() throws Exception {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankapplication","root","Darshan@!123");
			
			System.out.println("Connection established successfully");
		}
		
		public boolean register() throws Exception{
			String  s = "insert into caravanbank values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, name);
			pstmt.setString(2, custid);
			pstmt.setInt(3, accno);
			pstmt.setString(4, pwd);
			pstmt.setInt(5, balance);
			pstmt.setString(6, email);
			
			int rs =pstmt.executeUpdate();
			
			if(rs>0) {
				return true;
			}
			
			return false;
		}
		
		public boolean login() throws Exception {
			String s = "select * from caravanbank where custid=? and password=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, custid);		
			pstmt.setString(2, pwd);		
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()==true) {
				accno = res.getInt("accno");
				return true;
			}
			return false;
		}
			
		public boolean checkBalance() throws Exception{
			
			String s = "select balance from caravanbank where accno=?";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1,accno);
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()==true) {
				balance = res.getInt("balance");
				return true;
			}
			return false;
		}
		public boolean changePwd() throws Exception {

			String s = "update caravanbank set password=? where accno=?";
			pstmt =con.prepareStatement(s);
			pstmt.setString(1, pwd);
			pstmt.setInt(2, accno);
			
			int x= pstmt.executeUpdate();
			
			if(x>0) {
				return true;
			}
			return false;
		}
		public boolean transfer() throws Exception {
			
			String s1 = "select * from caravanbank where accno = ?";
			pstmt = con.prepareStatement(s1);
			pstmt.setInt(1, raccno);
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()==true) {
				System.out.println("Inside while -1");
				String s2 = "update caravanbank set balance=balance-? where accno = ?";
				pstmt = con.prepareStatement(s2);
				pstmt.setInt(1,balance);
				pstmt.setInt(2,accno);
				int y1 = pstmt.executeUpdate();
				
				if(y1>0) {
					System.out.println("Inside while -2");
					int x = res.getInt("balance");
					
					if(x>0) {
						String s3 = "update caravanbank set balance=balance+? where accno = ?";
						pstmt = con.prepareStatement(s2);
						pstmt.setInt(1,balance);
						pstmt.setInt(2,raccno);
						int y2 = pstmt.executeUpdate();
						
						if(y2>0) {
							System.out.println("Inside while -3");
							String s4 = "insert into getstatement values(?,?,?)";
							pstmt = con.prepareStatement(s4);
							pstmt.setInt(1, accno);
							pstmt.setInt(2, raccno);
							pstmt.setInt(3, balance);
							
							int y = pstmt.executeUpdate();
							if(y>0) {
								return true;
							}
							else {
								return false;
							}
						}
					}
					else {
						return false;
					}
				}
			}
			
			return false;
			
		}
		public ArrayList getStatement() throws SQLException {
			String s = "select * from getstatement where accno=?";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, accno);
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()==true) {
				sal.add(res.getInt("accno"));
				ral.add(res.getInt("raccno"));
				al.add(res.getInt("balance"));
			}
			return al;
		}
		
		public boolean applyLoan() throws Exception {
			String s = "select * from caravanbank where accno=?";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, accno);
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()==true) {
				 name = res.getString("name");
				 email = res.getString("email");
				return true; 
			}
			
			 
			
			return false;
		}
}
