<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan Success Page</title>
</head>
<body>
	<%
		session = request.getSession();
		out.println("Dear," + session.getAttribute("name") + " thnak you showing your interest on the loan from Caravan Bank.");
		out.println("<br>");
		out.println("Our Executive will contact you soon on your email address mentioned below");
		out.println("<br>");
		out.println(session.getAttribute("email"));
	%>
</body>
</html>