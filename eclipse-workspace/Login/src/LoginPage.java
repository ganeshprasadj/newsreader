

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginPage
 */
@WebServlet("/LoginPage")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginPage() {
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
		String userName = request.getParameter("uname");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String papersRegistered[] = request.getParameterValues("news");
		String str = new String();
		for(String newsValues: papersRegistered ) {
		     str = str + newsValues + "," ;
		 }
		try{
			//Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ganesh","root","Mypassword@34");
			
			//Statement stmt = (Statement) con.createStatement();
			String sql = "insert into login"
					+ "(name,uname,password,list_of_papers)" + "values (?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, userName);
			stmt.setString(3, password);
			stmt.setString(4,str);
			// 3. Execute SQL query
			stmt.execute();
		
			}catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
		}
//		PrintWriter pw = null;
		pw.write("<html><head></head><body><p>You are succesfully Registered to the following News Papers" + str +".Click here to return to login page <a href=\"Index.html\" >Sign In</a></p></body></html>");
//		pw.write("<html><head></head><body><p>You are succesfully Registered.Click here to return to login page <a href=\"ChangePassword.html\">Change Password</a></p></body></html>");
//		RequestDispatcher rd = request.getRequestDispatcher("/Subscribe");  
//        request.setAttribute("name", name);
//        request.setAttribute("uname", userName);
//        request.setAttribute("password", password);
//        
//        rd.forward(request, response); 

		
        //System.out.println("Nannu kalla");
        //String userName1[] = request.getParameterValues("news");
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
