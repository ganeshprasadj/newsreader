package newsreader;


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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@WebServlet("/RegisterPage")
public class RegisterPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterPage() {
		// TODO Auto-generated constructor stub

	}

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

			Users user1 = new Users();
			
			user1.setName(name);
			user1.setUname(userName);
			user1.setPassword(password);
			user1.setNewspaper_list(str);
			
			try {	
				
				Session session = HibernateUtilities.getSessionFactory().openSession();
				session.beginTransaction();
				session.save(user1);
				session.getTransaction().commit();
				
			}catch(Exception e) {
				
				System.out.println("Please choose different username");
				System.exit(0);
				
			}
			RequestDispatcher rd = request.getRequestDispatcher("display.html");
			rd.forward(request, response);
			
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
