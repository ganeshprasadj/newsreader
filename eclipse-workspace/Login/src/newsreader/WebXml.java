package newsreader;
//import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Servlet implementation class WebXml
 */
@WebServlet("/WebXml")
public class WebXml extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public WebXml() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		Document doc = null;
		PrintWriter pw = response.getWriter();
		Cookie ck[] = request.getCookies();
		String userAttribute = (String) request.getSession().getAttribute("uname");
		if(userAttribute != null)
		{
			System.out.println(ck[0].getValue());
			//RequestDispatcher rd = request.getRequestDispatcher("newspaper.html");
			try {
				//File inputFile = new File("./input.xml");
				//rd.getClass();
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				pw.write("<html><head>XML</head>");
				String NewsPaper = request.getParameter("news");
				if(NewsPaper.equals("Wall street Journul")) 
					doc = dBuilder.parse("http://www.wsj.com/xml/rss/3_7041.xml");
				else if(NewsPaper.equals("Deccan Hearld")) 
					doc = dBuilder.parse("http://www.deccanherald.com/rss-internal/top-stories.rss");
				else if(NewsPaper.equals("Kannada Prabha"))
					doc = dBuilder.parse("http://www.kannadaprabha.com/rss/kannada-top-news-1.xml");
				doc.getDocumentElement().normalize();
				NodeList nList = doc.getElementsByTagName("item");
				pw.write("<html><head>Top Headlines:</head>");
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
				//System.out.println("----------------------------");
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					//if(temp == 0)
					//pw.write("<h1>Current Element"+":"+nNode.getNodeName()+"</h1>");
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
					//				pw.write("<p><b>Description"+":</b>"+ eElement
					//						.getElementsByTagName("description")
					//				.item(0)
					//				.getTextContent()+"</p>");
					pw.write("<p>-------------------------------------------------------------------</p>");
				}

			}catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
		else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("Index.html");
			rd.forward(request, response);
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
