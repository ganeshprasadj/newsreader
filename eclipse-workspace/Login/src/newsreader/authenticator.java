package newsreader;

import java.io.IOException;
import java.io.PrintWriter;

import org.hibernate.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/authenticator")
public class authenticator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public authenticator() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("uname");
		String password = request.getParameter("password");
		PrintWriter pw = response.getWriter();
		
		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();
		Users user = session.get(Users.class, userName);
		session.getTransaction().commit();
		
		if(user.getUname() != null) {
			
			String psswrd = user.getPassword(); 
			
			if(!(password.equals(psswrd))) {
				response.sendRedirect("login.html");
			}
			
			else {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("uname", userName);
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Login/reader");
				request.setAttribute("uname", userName);
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
