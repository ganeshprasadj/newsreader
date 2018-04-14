package newsreader;


import java.io.IOException;
import java.io.PrintWriter;


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
import javax.xml.ws.Response;

import org.hibernate.Session;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



@WebServlet("/reader")
public class reader extends HttpServlet {
	final int MAX_NEWS_ITEMS = 10;
	private static final long serialVersionUID = 1L;

	public reader() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//For clearing the Cache
		response.setHeader("Cache-Control","no-cache"); 
		response.setHeader("Cache-Control","no-store"); 
		response.setDateHeader("Expires", 0); 
		response.setHeader("Pragma","no-cache"); 
		
		
//Getting a Cookie and a Http session attribute
		HttpSession httpSession = request.getSession();
		Cookie cookies[] = request.getCookies();
		String loggedInUser = (String)httpSession.getAttribute("uname");
		String loggedInCookie = null;
		Subscription subscription = new Subscription();
		
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("uname")) loggedInCookie = cookie.getValue();
		}

		if(loggedInUser == request.getParameter("uname")){

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
		//		Users user = session.get(Users.class, userName);
				Subscription subscriptions = session.get(Subscription.class, userName);
				session.getTransaction().commit();
				String rss = subscriptions.getSubscription();
//				if(rss == null) {
//					pw.write("<html><body><p>You haven't registered to any of the News Paper Please Register</p><a href = \"SignUp.html\">Login</a></body></html>");
//				}
//				else {

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
							"    top: 1px;\r\n" + 
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
							".container {\r\n" + 
							"								  margin: 50px auto\r\n" + 
							"								  width: 640px;\r\n" + 
							"								  text-align: center;\r\n" + 
							"\r\n" + 
							"								  .dropdown { text-align: right; }\r\n" + 
							"								}\r\n" + 
							"\r\n" + 
							"								.dropdown {\r\n" + 
							"								  $color: #363d47;\r\n" + 
							"								  position: relative;\r\n" + 
							"								  height: 27px;\r\n" + 
							"								  background: $color;\r\n" + 
							"								  border: 1px solid;\r\n" + 
							"								  border-color: darken($color, 7%) darken($color, 8%) darken($color, 9%);\r\n" + 
							"								  border-radius: 3px;\r\n" + 
							"								  @include inline-block;\r\n" + 
							"								  @include linear-gradient(top, lighten($color, 9%), $color);\r\n" + 
							"								  @include box-shadow(inset 0 1px rgba(white, .1), 0 1px 2px rgba(black, .15));\r\n" + 
							"\r\n" + 
							"								  a {\r\n" + 
							"								    font-size: 12px;\r\n" + 
							"								    text-decoration: none;\r\n" + 
							"								    text-shadow: 0 1px black;\r\n" + 
							"								  }\r\n" + 
							"\r\n" + 
							"								  > a {\r\n" + 
							"								    float: left;\r\n" + 
							"								    position: relative;\r\n" + 
							"								    height: 27px;\r\n" + 
							"								    line-height: 26px;\r\n" + 
							"								    padding: 0 12px;\r\n" + 
							"								    color: #ecf0f1;\r\n" + 
							"								    border-radius: 2px 0 0 2px;\r\n" + 
							"\r\n" + 
							"								    &:before {\r\n" + 
							"								      content: '';\r\n" + 
							"								      display: inline-block;\r\n" + 
							"								      vertical-align: -2px;\r\n" + 
							"								      width: 10px;\r\n" + 
							"								      height: 11px;\r\n" + 
							"								      margin-right: 10px;\r\n" + 
							"								      background: url('../img/settings.png') 0 0 no-repeat;\r\n" + 
							"								    }\r\n" + 
							"\r\n" + 
							"								    &:hover {\r\n" + 
							"								      background: lighten($color, 4%);\r\n" + 
							"								      background: rgba(white, .04);\r\n" + 
							"								    }\r\n" + 
							"\r\n" + 
							"								    &:active { background: darken($color, 1%); }\r\n" + 
							"								  }\""+

							".main {\r\n" + 
							"    margin-left: 300px; /* Same width as the sidebar + left position in px */\r\n" + 
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
							  "\"</div><div class=\\\"main\\\">"+
							  "         <a href=\"changepassword.jsp\">Change Password</a></li>\r\n"    + 
							  "         <a href=\"addsubs.jsp\">Add/Remove Subscription</a></li>\r\n" + 
							  "         <a href=\"changeCred.jsp\">Change Credentials</a></li>\r\n" + 
							  "			<a href=\"/Login/Deleteaccount\">Delete account</a></li>\r\n\" "+
							  "			<a href=\"/Login/LogoutServlet\">Logout</a>\r\n "+
							  "      </div>\r\n" + 
							  "    </div>\r\n" + 
							  "  </section>");

					String[] Split = rss.split(",");

					for(String newsPaper : Split) {

						if(newsPaper.equals("Wall street Journul")) {

							pw.write("<p style=\\\"font-family:courier;font-size:300%;text-align:center;\\\"><b>"+newsPaper+"</b></p>");
							doc = dBuilder.parse("http://www.wsj.com/xml/rss/3_7041.xml");
							NodeList nList = doc.getElementsByTagName("item");
							showNewsElement(nList,pw);
//							for (int temp = 0; temp < 10 ; temp++) {
//								Node nNode = nList.item(temp);
//								Element eElement = (Element) nNode;
//								pw.write("<p><b>Title"+":</b>"+ eElement
//										.getElementsByTagName("description")
//								.item(0)
//								.getTextContent()+"</p>");
//								pw.write("<p><b>Link</b>"+":"+"<a href=\"" +eElement
//										.getElementsByTagName("link")
//								.item(0)
//								.getTextContent()+" \">"+ eElement
//								.getElementsByTagName("title")
//								.item(0)
//								.getTextContent() +"</a>"+"</p>");
//								pw.write("<p>-------------------------------------------------------------------</p>");
//							}
						}
						else if(newsPaper.equals("Deccan Hearld")) {

							pw.write("<p style=\"font-family:courier;font-size:300%;text-align:center;\"><b>"+newsPaper+"</b></p>");
							doc = dBuilder.parse("http://www.deccanherald.com/rss-internal/top-stories.rss");
							NodeList nList = doc.getElementsByTagName("item");
							showNewsElement(nList,pw);
//							for (int temp = 0; temp < 10 ; temp++) {
//								Node nNode = nList.item(temp);
//								Element eElement = (Element) nNode;
//								pw.write("<p><b>Title"+":</b>"+ eElement
//										.getElementsByTagName("description")
//								.item(0)
//								.getTextContent()+"</p>");
//								pw.write("<p><b>Link</b>"+":"+"<a href=\"" +eElement
//										.getElementsByTagName("link")
//								.item(0)
//								.getTextContent()+" \">"+ eElement
//								.getElementsByTagName("title")
//								.item(0)
//								.getTextContent() +"</a>"+"</p>");
//								pw.write("<p>-------------------------------------------------------------------</p>");
//							}
						}
						else if(newsPaper.equals("Kannada Prabha")) {

							pw.write("<p style=\"font-family:courier;font-size:300%;text-align:center;\"><b>"+newsPaper+"</b></p>");
							doc = dBuilder.parse("http://www.kannadaprabha.com/rss/kannada-top-news-1.xml");
							NodeList nList = doc.getElementsByTagName("item");
							showNewsElement(nList,pw);
//							for (int temp = 0; temp < 10 ; temp++) {
//								Node nNode = nList.item(temp);
//								Element eElement = (Element) nNode;
//								pw.write("<p><b>Title"+":</b>"+ eElement
//										.getElementsByTagName("description")
//								.item(0)
//								.getTextContent()+"</p>");
//								pw.write("<p><b>Link</b>"+":"+"<a href=\"" +eElement
//										.getElementsByTagName("link")
//								.item(0)
//								.getTextContent()+" \">"+ eElement
//								.getElementsByTagName("title")
//								.item(0)
//								.getTextContent() +"</a>"+"</p>");
//								pw.write("<p>-------------------------------------------------------------------</p>");
//							}
							showNewsElement(nList,pw);
						}
					}

					doc.getDocumentElement().normalize();
				}

			

			catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}else {
			response.sendRedirect("login.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void showNewsElement(NodeList nList, PrintWriter pw) {
		
		int maxNewsItems = max(MAX_NEWS_ITEMS, nList.getLength());
		for (int newsIndex = 0; newsIndex < maxNewsItems; newsIndex++) {
		
			Node nNode = nList.item(newsIndex);
			if(nNode != null) {
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
			else {
				pw.write("No News available at this time");
				break;
			}
		}
	}
	
	private int max(int a, int b) {
		return a > b ? a : b;
	}

}
