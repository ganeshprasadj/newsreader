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
	HashSet<String> subscriptions = new HashSet<String>();
	HashSet<String> subscribedNewsPapers = new HashSet<String>();
	subscriptions.add("Deccan Hearld");
	subscriptions.add("Kannada Prabha");
	subscriptions.add("Wall Street Journal");
	String userName = (String) httpSession.getAttribute("uname");
	Session sessionJSP = newsreader.HibernateUtilities.getSessionFactory().openSession();
	sessionJSP.beginTransaction();
	newsreader.Users user = sessionJSP.get(newsreader.Users.class, userName);
	String news = user.getSubscription().getSubscription();

	String[] subscribedNews = news.split(",");
	if (subscribedNews != null) {
		for (String addSubscribedNews : subscribedNews) {
			subscribedNewsPapers.add(addSubscribedNews);
		}
	}
%>

<body>
	<form action="/Login/ChangeCredentials" method="post">
		Name: <input type="text" name="name" /> Email ID: <input type="email"
			name="emailId" />
		<%
			//			int i = 0;
			//		if (news != null && !(news.isEmpty())){
			//		for (Iterator<String> iter = subscriptions.iterator(); iter.hasNext();) {
			//		String newspaper = iter.next();
			//	if (subscribedNewsPapers.contains(newspaper)) {
			//iter.remove();
			//	i++;
			//} else {
			subscriptions.removeAll(subscribedNewsPapers);
			for (Iterator<String> iter = subscriptions.iterator(); iter.hasNext();) {
				String newspaper = iter.next();
		%></br> <input type="checkbox" name="newspaper" value="<%=newspaper%>"><%=newspaper%>
		<%
			}
		%>

		<input type="Submit" name="Submit" />
	</form>
</body>
</html>