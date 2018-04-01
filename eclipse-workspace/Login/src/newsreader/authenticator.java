package newsreader;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;

import org.hibernate.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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

		//For clearing the Cache
		response.setHeader("Cache-Control","no-cache"); 
		response.setHeader("Cache-Control","no-store"); 
		response.setDateHeader("Expires", 0); 
		response.setHeader("Pragma","no-cache"); 

		//For getting the request parameters
		String userName = request.getParameter("uname");
		String password = request.getParameter("password");
		PrintWriter pw = response.getWriter();
		if(userName != null) {
			
			//Getting an Hibernate session
			Session session = HibernateUtilities.getSessionFactory().openSession();
			session.beginTransaction();
			Users user = session.get(Users.class, userName);
			session.getTransaction().commit();

			//Validating if the Username and password are proper
			if(user.getUname() != null) {

				String psswrd = user.getPassword(); 

				if(!(password.equals(psswrd))) {
					response.sendRedirect("login.html");
				}

				else {
					HttpSession httpSession = request.getSession();
					httpSession.setAttribute("uname", userName);
					httpSession.setMaxInactiveInterval(2*60);
					Cookie userNameCookie =  new Cookie("uname", userName);
					response.addCookie(userNameCookie);
					userNameCookie.setMaxAge(30*60);

					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Login/reader");
					request.setAttribute("uname", userName);
					dispatcher.forward(request, response);
				}
			}
		}else {
			response.sendRedirect("login.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
