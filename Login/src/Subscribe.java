

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.util.ArrayList;
//import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Subscribe
 */
@WebServlet("/Subscribe")
public class Subscribe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Subscribe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter pw = response.getWriter();
		pw.write("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<body>\r\n" + 
				"<form name=\"myform2\" action=\"/Login/Subscribe\">\r\n" + 
				"	Deccan Hearals <input name = \"news\" value = \"Deccan Hearld\" type=\"checkbox\" checked><br>\r\n" + 
				"	Kannada Prabha <input name = \"news\" value = \"Kannada Prabha\" type=\"checkbox\"><br>\r\n" + 
				"	Wall street Journul <input name = \"news\" value = \"Wall street Journul\" type=\"checkbox\">\r\n" + 
				"	<input type=\"submit\" value=\"Send form!\">\r\n" + 
				"</form>\r\n" + 
				"</body>\r\n" + 
				"</html>");
		
		response.setContentType("text/html");
		String userName[] = request.getParameterValues("news");
		String userName1 = request.getParameter("uname");
		String password = request.getParameter("password");
		//String news[] = null;
		//int[] index = new int[10];
		String str = new String();
		response.getHeaderNames();
		for(String newsValues: userName ) {
			 
			     System.out.println(newsValues);
			 }
			try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ganesh","root","Mypassword@34");
			con.createStatement();
			String sql = "insert into login"
					+ "(list_of_papers)" + "values (?)"+
					 " where uname = ? and password = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, str);
			stmt.setString(1, userName1);
			stmt.setString(1, password);
			
			
			}catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
	
		
		
			
	

	/**o
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
