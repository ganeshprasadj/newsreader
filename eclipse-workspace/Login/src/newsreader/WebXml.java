package newsreader;
//import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		PrintWriter pw = response.getWriter();
		//RequestDispatcher rd = request.getRequestDispatcher("newspaper.html");
		try {
		//File inputFile = new File("./input.xml");
		//rd.getClass();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		pw.write("<html><head>XML</head>");
		String NewsPaper = request.getParameter("news");
		if(NewsPaper.equals("Wall street Journul")) {
			Document doc = dBuilder.parse("http://www.wsj.com/xml/rss/3_7041.xml");
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		    NodeList nList = doc.getElementsByTagName("item");
		    pw.write("<html><head>Top Headlines:</head>");
		    
		    //System.out.println("----------------------------");
		    for (int temp = 0; temp < nList.getLength(); temp++) {
		        Node nNode = nList.item(temp);
		       //if(temp == 0)
		        	//pw.write("<h1>Current Element"+":"+nNode.getNodeName()+"</h1>");
		        Element eElement = (Element) nNode;
		        pw.write("<p>title"+":<b>"+ eElement
		                   .getElementsByTagName("title")
		                   .item(0)
		                   .getTextContent()+"</b></p>");
		        pw.write("<p>link"+":<b>"+"<href>" +eElement
		                   .getElementsByTagName("link")
		                   .item(0)
		                   .getTextContent()+"</href>"+"</b></p>");
		        pw.write("<p>description"+":<b>"+ eElement
		                   .getElementsByTagName("description")
		                   .item(0)
		                   .getTextContent()+"</b></p>");
		        pw.write("<p>-------------------------------------------------------------------</p>");
		    }
		}
		else if(NewsPaper.equals("Deccan Hearld")) {
			Document doc = dBuilder.parse("http://www.deccanherald.com/rss-internal/top-stories.rss");
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		    NodeList nList = doc.getElementsByTagName("item");
		    pw.write("<html><head>XML</head>");
		    
		    //System.out.println("----------------------------");
		    for (int temp = 0; temp < nList.getLength(); temp++) {
		        Node nNode = nList.item(temp);
		       //if(temp == 0)
		        	//pw.write("<h1>Current Element"+":"+nNode.getNodeName()+"</h1>");
		        Element eElement = (Element) nNode;
		        pw.write("<p>title"+":"+ eElement
		                   .getElementsByTagName("title")
		                   .item(0)
		                   .getTextContent()+"</p>");
		        pw.write("<p>link"+":"+"<href>" +eElement
		                   .getElementsByTagName("link")
		                   .item(0)
		                   .getTextContent()+"</href>"+"</p>");
		        pw.write("<p>description"+":"+ eElement
		                   .getElementsByTagName("description")
		                   .item(0)
		                   .getTextContent()+"</p>");
		        pw.write("<p>-------------------------------------------------------------------</p>");
		    }
		}
		else if(NewsPaper.equals("Kannada Prabha")) {
			Document doc = dBuilder.parse("http://www.kannadaprabha.com/rss/kannada-top-news-1.xml");
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		    NodeList nList = doc.getElementsByTagName("item");
		    pw.write("<html><head>XML</head>");
		    
		    //System.out.println("----------------------------");
		    for (int temp = 0; temp < nList.getLength(); temp++) {
		        Node nNode = nList.item(temp);
		       //if(temp == 0)
		        	//pw.write("<h1>Current Element"+":"+nNode.getNodeName()+"</h1>");
		        Element eElement = (Element) nNode;
		        pw.write("<p>title"+":"+ eElement
		                   .getElementsByTagName("title")
		                   .item(0)
		                   .getTextContent()+"</p>");
		        pw.write("<p>link"+":"+"<href>" +eElement
		                   .getElementsByTagName("link")
		                   .item(0)
		                   .getTextContent()+"</href>"+"</p>");
		        pw.write("<p>description"+":"+ eElement
		                   .getElementsByTagName("description")
		                   .item(0)
		                   .getTextContent()+"</p>");
		        pw.write("<p>-------------------------------------------------------------------</p>");
		    }
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
