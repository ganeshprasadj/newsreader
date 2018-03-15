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
 * Servlet implementation class LoginForm
 */
@WebServlet("/LoginForm")
public class LoginForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginForm() {
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
			String userName = request.getParameter("uname");
			String password = request.getParameter("password");
			if(!(userName.isEmpty())) {
				Cookie ck = new Cookie("uname",userName);
				response.addCookie(ck);
				System.out.println(ck.getValue());
				Session session = HibernateUtilities.getSessionFactory().openSession();
				session.beginTransaction();
				Users user = session.get(Users.class, userName);
				session.getTransaction().commit();
				if(user.getUname() != null) {		
					String psswrd = user.getPassword(); 
					if(!(password.equals(psswrd))) {
						pw.write("<html><body><p>Password is incorrect please try again<a href = \"Index.html\">Login</a></body></html>");
					}
					else {
						String rss = user.getNewspaper_list();
						if(rss == null) {
							pw.write("<html><body><p>You haven't registered to any of the News Paper Please Register</p><a href = \"SignUp.html\">Login</a></body></html>");
						}
						else {
							String[] Split = rss.split(",");
							String l =  new String();
							//				for(String news:Split) {
							//					if(news.equals("Wall street Journul")) {
							//						 doc = dBuilder.parse("http://www.wsj.com/xml/rss/3_7041.xml");
							//					}
							//					else if(news.equals("Deccan Hearld")) {
							//				
							//						
							//						doc = dBuilder.parse("http://www.deccanherald.com/rss-internal/top-stories.rss");
							//					}
							//					else if(news.equals("Kannada Prabha")) {
							//						doc = dBuilder.parse("http://www.kannadaprabha.com/rss/kannada-top-news-1.xml");
							//					}
							//				}
							//					doc.getDocumentElement().normalize();
							//					//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
							//					NodeList nList = doc.getElementsByTagName("item");
							//					pw.write("<html><head>Top Headlines</head>");
							//
							//					//System.out.println("----------------------------");
							//					for (int temp = 0; temp < nList.getLength(); temp++) {
							//						Node nNode = nList.item(temp);
							//						//if(temp == 0)
							//						//pw.write("<h1>Current Element"+":"+nNode.getNodeName()+"</h1>");
							//						Element eElement = (Element) nNode;
							//						pw.write("<p><b>Title</b>"+":<b>"+ eElement
							//								.getElementsByTagName("title")
							//						.item(0)
							//						.getTextContent()+"</b></p>");
							//						pw.write("<p><b>link</b>"+":"+"<a href=>" +eElement
							//								.getElementsByTagName("link")
							//						.item(0)
							//						.getTextContent()+"</a>"+"</p>");
							////						pw.write("<p>description"+":"+ eElement
							////								.getElementsByTagName("description")
							////						.item(0)
							////						.getTextContent()+"</p>");
							//						pw.write("<p>-------------------------------------------------------------------</p>");
							//					}
							//				}
							//				}
							//			}

							request.setAttribute("news", Split);
							String s1 = "<!DOCTYPE html>\r\n" + 
									"<html>\r\n" + 
									"<head>\r\n" + 
									"<meta charset=\"ISO-8859-1\">\r\n" + 
									"<title>Subscription</title>\r\n" + 
									"</head>\r\n" + 
									"<body>\r\n" + 

										"<form action = \"/Login/WebXml\">\r\n" +


										"<select name=\"news\">\r\n" ;
							for(String newsPaper : Split) {
								l= l + ("<option value=\""+newsPaper+"\">"+newsPaper+"</option>\r\n");
							}
							String k = ("</select>\r\n" + 
									"<input type = \"submit\" value = \"Go\" >\r\n" + 
									"</form>\r\n" + 
									"</body>\r\n" + 
									"</html>");
							//				request.setAttribute("Split", rss);
							//				RequestDispatcher rd = request.getRequestDispatcher("display.html");
							//				rd.forward(request, response);
							String html = s1 + l + k;
							pw.write(html);  
						}
					}
				}
				//				}
				//			}
				//			}
				else {
					pw.write("<html><head>Not Register</head><body><p>The User not Registered</p><p>Please register using the below link</p><a href =\"SignUp.html\">SignUp</a></p></body></html>");
				}
				//		}else {
				//			String[] names = request.getParameterValues("names");
				//			List list =  Arrays.asList(names);
				//		}

				// 3. Execute SQL query
				//				ResultSet rs = stmt.executeQuery();
				//				rs.next();
				//				
				//			}catch(Exception e) {
				//				pw.write("<html><head>Not Register</head><body><p>The User not Registered</p></body></html>");
				//			}
				//			if(rss.isEmpty()) {	
				//				pw.write("<html><head>Not Register</head><body><p>The User not Registered</p></body></html>");
				//			}else {
				//			pw.write("<html><body><p>Logged in succesfully Welcome"+rs.getString(1) +"</p></body></html>");
				//			}

			}
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
