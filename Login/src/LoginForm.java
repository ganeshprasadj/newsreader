

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		try{
			//Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ganesh","root","Mypassword@34");
			String userName = request.getParameter("uname");
			String password = request.getParameter("password");
			//Statement stmt = (Statement) con.createStatement();
			if(!(userName.isEmpty())) {
			String sql = "select * from login"
					+ " where uname = ? and password = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setString(2, password);
			ResultSet rs = null;
			rs = stmt.executeQuery();
			//String rss = rs.toString();
			if (rs.next()) {
//				pw.write("<!DOCTYPE html>\r\n" + 
//						"<html>\r\n" + 
//						"<head>\r\n" + 
//						"<meta charset=\"ISO-8859-1\">\r\n" + 
//						"<title>Subscription</title>\r\n" + 
//						"</head>\r\n" + 
//						"<body>\r\n" + 
//						"<form action = \"/Login/WebXml\">\r\n" + 
//						"<select name=\"news\">\r\n" + 
//						"  <option value=\"DH\">Deccan Hearld</option>\r\n" + 
//						"  <option value=\"KP\">Kannada</option>\r\n" + 
//						"  <option value=\"WSJ\">Wall street</option>\r\n" + 
//						"</select>\r\n" + 
//						"<input type = \"submit\" value = \"Go\" >\r\n" + 
//						"</form>\r\n" + 
//						"</body>\r\n" + 
//						"</html>");
				String rss = rs.getString("list_of_papers");
				String[] Split = rss.split(",");
				
				String s = "<!DOCTYPE html>\r\n" + 
						"<html>\r\n" + 
						"<head>\r\n" + 
						"<meta charset=\"ISO-8859-1\">\r\n" + 
						"<title>Subscription</title>\r\n" + 
						"</head>\r\n" + 
						"<body>\r\n" + 
						"<form action = \"/Login/WebXml\">\r\n" + 
						"<select name=\"news\">\r\n" ;
						for(String newsPaper : Split) {
							s.concat("<option value=\""+newsPaper+"\">"+newsPaper+"</option>\r\n");
						}
						s.concat("</select>\r\n" + 
						"<input type = \"submit\" value = \"Go\" >\r\n" + 
						"</form>\r\n" + 
						"</body>\r\n" + 
						"</html>");
				
			pw.write(s);
			}
			//	RequestDispatcher rd = request.getRequestDispatcher("/");  
		       // rd.include(request, response); 
		        
                
           else {
           	pw.write("<html><head>Not Register</head><body><p>The User not Registered</p><p>Please register using the below link</p><a href =\"SignUp.html\">SignUp</a></p></body></html>");
           }
		}else {
			String[] names = request.getParameterValues("names");
			List list =  Arrays.asList(names);
		}
		
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
