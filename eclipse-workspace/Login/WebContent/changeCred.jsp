<%@page import="newsreader.Subscription"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<%@page import="org.hibernate.Session"%>
<%@page import="java.util.*"%>
<%@page import="org.hibernate.Transaction"%>

<%!String newPassword;%>
<%
	HttpSession httpSession = request.getSession();
	ArrayList<String> subscriptions = new ArrayList<String>();
	String[] Split = null;
	subscriptions.add("Deccan Hearld");
	subscriptions.add("Kannada Prabha");
	subscriptions.add("Wall Street Journol");
	String userName = (String) httpSession.getAttribute("uname");
	Session sessionJSP = newsreader.HibernateUtilities.getSessionFactory().openSession();
	sessionJSP.beginTransaction();
	newsreader.Users user = sessionJSP.get(newsreader.Users.class, userName);
	String news = user.getSubscription().getSubscription();
	//assert news!= null : "News is empty";
	if (news != null && !(news.isEmpty()))
		Split = news.split(",");
%>

<body>
	<form action="/Login/ChangeCredentials" method="post">
		Name: <input type="text" name="name" /> Email ID: <input type="email"
			name="emailId" />
		<%
			int i = 0;
		if (news != null && !(news.isEmpty())){
			for (Iterator<String> iter = subscriptions.iterator(); iter.hasNext();) {
				String newspaper = iter.next();
				if (newspaper.equals(Split[i])) {
					//iter.remove();
				} else {
		%></br> <input type="checkbox" name="newspaper" value="<%=newspaper%>"><%=newspaper%>
		<%
			}
			}}else
			{
				for (Iterator<String> iter = subscriptions.iterator(); iter.hasNext();){
					String newspaper = iter.next();%>
					</br> <input type="checkbox" name="newspaper" value= "<%=newspaper%>"><%=newspaper%>
				<%}	
			}
		%>

		<input type="Submit" name="Submit" />
	</form>
</body>
</html>