package newsreader;


import java.io.IOException;
import java.io.PrintWriter;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.Arrays;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.hibernate.Session;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Servlet implementation class reader
 */
@WebServlet("/reader")
public class reader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public reader() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		Document doc = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			String userName = (String) request.getAttribute("uname");
			System.out.println("Username:" + userName);
			
			
//			String password = request.getParameter("password");
//			if(!(userName.isEmpty())) {
//				Cookie ck = new Cookie("uname",userName);
//				response.addCookie(ck);
				
//				System.out.println(ck.getValue());
//				Session session = HibernateUtilities.getSessionFactory().openSession();
//				session.beginTransaction();
//				Users user = session.get(Users.class, userName);
//				session.getTransaction().commit();
//				if(user.getUname() != null) {		
//					String psswrd = user.getPassword(); 
//					if(!(password.equals(psswrd))) {
//						pw.write("<html><body><p>Password is incorrect please try again<a href = \"Index.html\">Login</a></body></html>");
//					}
//					else {
//						String rss = user.getNewspaper_list();
//						if(rss == null) {
//							pw.write("<html><body><p>You haven't registered to any of the News Paper Please Register</p><a href = \"SignUp.html\">Login</a></body></html>");
//						}
//						else {
//							String[] Split = rss.split(",");
//							String l =  new String();
//							request.setAttribute("news", Split);
//							String s1 = "<!DOCTYPE html>\r\n" + 
//									"<html>\r\n" + 
//									"<head>\r\n" + 
//									"<meta charset=\"ISO-8859-1\">\r\n" + 
//									"<title>Subscription</title>\r\n" + 
//									"</head>\r\n" + 
//									"<body>\r\n" + 
//
//										"<form action = \"/Login/WebXml\">\r\n" +
//
//
//										"<select name=\"news\">\r\n" ;
//							for(String newsPaper : Split) {
//								l= l + ("<option value=\""+newsPaper+"\">"+newsPaper+"</option>\r\n");
//							}
//							String k = ("</select>\r\n" + 
//									"<input type = \"submit\" value = \"Go\" >\r\n" + 
//									"</form>\r\n" + 
//									"</body>\r\n" + 
//									"</html>");
//							String html = s1 + l + k;
//							pw.write(html);  
//						}
//					}
//				}
//				else {
//					pw.write("<html><head>Not Register</head><body><p>The User not Registered</p><p>Please register using the below link</p><a href =\"SignUp.html\">SignUp</a></p></body></html>");
//				}
//
//			}
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
