package newsreader;


import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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


/**
 * Servlet implementation class reader
 */
@WebServlet("/reader")
public class reader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public reader() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setHeader("Cache-Control","no-cache"); 
		response.setHeader("Cache-Control","no-store"); 
		response.setDateHeader("Expires", 0); 
		response.setHeader("Pragma","no-cache"); 

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
			Session session = HibernateUtilities.getSessionFactory().openSession();
			session.beginTransaction();
			Users user = session.get(Users.class, userName);
			session.getTransaction().commit();
			String rss = user.getNewspaper_list();
			if(rss == null) {
				pw.write("<html><body><p>You haven't registered to any of the News Paper Please Register</p><a href = \"SignUp.html\">Login</a></body></html>");
			}
			else {

				pw.write("<html>");

				pw.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
						"<style>\r\n" + 
						"body {\r\n" + 
						"    font-family: \"Lato\", sans-serif;\r\n" + 
						"}\r\n" + 
						"\r\n" + 
						".sidenav {\r\n" + 
						"    width: 130px;\r\n" + 
						"    position: fixed;\r\n" + 
						"    z-index: 1;\r\n" + 
						"    top: 20px;\r\n" + 
						"    left: 10px;\r\n" + 
						"    background: #eee;\r\n" + 
						"    overflow-x: hidden;\r\n" + 
						"    padding: 8px 0;\r\n" + 
						"}\r\n" + 
						"\r\n" + 
						".sidenav a {\r\n" + 
						"    padding: 6px 8px 6px 16px;\r\n" + 
						"    text-decoration: none;\r\n" + 
						"    font-size: 25px;\r\n" + 
						"    color: #2196F3;\r\n" + 
						"    display: block;\r\n" + 
						"}\r\n" + 
						"\r\n" + 
						".sidenav a:hover {\r\n" + 
						"    color: #064579;\r\n" + 
						"}\r\n" + 
						"\r\n" + 
						".main {\r\n" + 
						"    margin-left: 140px; /* Same width as the sidebar + left position in px */\r\n" + 
						"    font-size: 14px; /* Increased text to enable scrolling */\r\n" + 
						"    padding: 0px 10px;\r\n" + 
						"}\r\n" + 
						"\r\n" + 
						"@media screen and (max-height: 450px) {\r\n" + 
						"    .sidenav {padding-top: 15px;}\r\n" + 
						"    .sidenav a {font-size: 18px;}\r\n" + 
						"}\r\n" + 
						"</style>\r\n" + 
						"</head>\r\n" + 
						"<body>\r\n" + 
						"\r\n" + 
						"<div class=\"sidenav\">\r\n" + 
						"  <a href=\"/Login/LogoutServlet\">Logout</a>\r\n" + 
						"</div><div class=\"main\">");

				String[] Split = rss.split(",");

				for(String newsPaper : Split) {

					if(newsPaper.equals("Wall street Journul")) {

						pw.write("<p style=\\\"font-family:courier;font-size:300%;text-align:center;\\\"><b>"+newsPaper+"</b></p>");
						doc = dBuilder.parse("http://www.wsj.com/xml/rss/3_7041.xml");
						NodeList nList = doc.getElementsByTagName("item");

						for (int temp = 0; temp < 10 ; temp++) {
							Node nNode = nList.item(temp);
							Element eElement = (Element) nNode;
							pw.write("<p><b>Title"+":</b>"+ eElement
									.getElementsByTagName("description")
							.item(0)
							.getTextContent()+"</p>");
							pw.write("<p><b>Link</b>"+":"+"<a href=\"" +eElement
									.getElementsByTagName("link")
							.item(0)
							.getTextContent()+" \">"+ eElement
							.getElementsByTagName("title")
							.item(0)
							.getTextContent() +"</a>"+"</p>");
							pw.write("<p>-------------------------------------------------------------------</p>");
						}
					}
					else if(newsPaper.equals("Deccan Hearld")) {

						pw.write("<p style=\"font-family:courier;font-size:300%;text-align:center;\"><b>"+newsPaper+"</b></p>");
						doc = dBuilder.parse("http://www.deccanherald.com/rss-internal/top-stories.rss");
						NodeList nList = doc.getElementsByTagName("item");

						for (int temp = 0; temp < 10 ; temp++) {
							Node nNode = nList.item(temp);
							Element eElement = (Element) nNode;
							pw.write("<p><b>Title"+":</b>"+ eElement
									.getElementsByTagName("description")
							.item(0)
							.getTextContent()+"</p>");
							pw.write("<p><b>Link</b>"+":"+"<a href=\"" +eElement
									.getElementsByTagName("link")
							.item(0)
							.getTextContent()+" \">"+ eElement
							.getElementsByTagName("title")
							.item(0)
							.getTextContent() +"</a>"+"</p>");
							pw.write("<p>-------------------------------------------------------------------</p>");
						}
					}
					else if(newsPaper.equals("Kannada Prabha")) {

						pw.write("<p style=\"font-family:courier;font-size:300%;text-align:center;\"><b>"+newsPaper+"</b></p>");
						doc = dBuilder.parse("http://www.kannadaprabha.com/rss/kannada-top-news-1.xml");
						NodeList nList = doc.getElementsByTagName("item");

						for (int temp = 0; temp < 10 ; temp++) {
							Node nNode = nList.item(temp);
							Element eElement = (Element) nNode;
							pw.write("<p><b>Title"+":</b>"+ eElement
									.getElementsByTagName("description")
							.item(0)
							.getTextContent()+"</p>");
							pw.write("<p><b>Link</b>"+":"+"<a href=\"" +eElement
									.getElementsByTagName("link")
							.item(0)
							.getTextContent()+" \">"+ eElement
							.getElementsByTagName("title")
							.item(0)
							.getTextContent() +"</a>"+"</p>");
							pw.write("<p>-------------------------------------------------------------------</p>");
						}
					}
				}

				doc.getDocumentElement().normalize();
			}

		}

		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
