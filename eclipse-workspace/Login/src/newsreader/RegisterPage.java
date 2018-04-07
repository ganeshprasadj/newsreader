package newsreader;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

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
		String dOB  = request.getParameter("DOB");
		String emailId = request.getParameter("emailID");
		String refreshPeriod = request.getParameter("refreshPeriod");
		
		String papersRegistered[] = request.getParameterValues("news");
		String str = new String();
		for(String newsValues: papersRegistered ) {
			str = str + newsValues + "," ;
		}
		
		
		try{

			Users user1 = new Users();
			Subscription subscription = new Subscription();
			

			
			
			user1.setName(name);
			user1.setUname(userName);
			user1.setPassword(password);
			user1.setDOB(dOB);
			user1.setEmailId(emailId);
			user1.setRefreshPeriod(refreshPeriod);
			user1.setSubscription(subscription);
			
			subscription.setUname(userName);
			subscription.setSubscription(str);
			
			try {	
				Session session = HibernateUtilities.getSessionFactory().openSession();
				session.beginTransaction();
//				session.get(Users.class, userName);
				session.save(user1);
				session.save(subscription);
				session.getTransaction().commit();
				
			}catch(Exception e) {
				
				System.out.println(e.getLocalizedMessage());
				
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
