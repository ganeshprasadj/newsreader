<%@page import="newsreader.Users"%>
<%@page import="newsreader.HibernateUtilities"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%String userName = (String)session.getAttribute("uname");
System.out.println(userName);
if(userName == null){
	response.sendRedirect("login.html");
}
%>

<form action = "/Login/Changepassword" method="get" >
	User Name: <input type = "text" name = "uname"/>
	Old password: <input type = "password" name = "oldPassword"/>
	New Password: <input type = "password" name = "newPassword"/>
	<input type = "submit" value = "Change password"/>
</form>

</body>
</html>