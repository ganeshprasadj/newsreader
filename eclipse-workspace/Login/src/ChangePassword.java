

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
			String OldPassword = request.getParameter("oldPassword");
			String sql1 = "select * from login"
					+ " where uname = ? and password = ?";
			PreparedStatement stmt1 = con.prepareStatement(sql1);
			
			stmt1.setString(1, userName);
			stmt1.setString(2, OldPassword);
			ResultSet rs = null;
			rs = stmt1.executeQuery();
			
			//String rss = rs.toString();
			if (rs.next()) {
			String sql = "update login SET password = ?" + "Where uname = ?";
					
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, password);
			stmt.setString(2, userName);
			// 3. Execute SQL query
			stmt.execute();
			}else {
				pw.write("<html><body><p>Old password is incorrect please try again<a href=\"ChangePassword\">ChangePassword.html</a>");
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
