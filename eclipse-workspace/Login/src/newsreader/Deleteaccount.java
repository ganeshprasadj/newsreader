package newsreader;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

@WebServlet("/Deleteaccount")
public class Deleteaccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Deleteaccount() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//For clearing the Cache
		response.setHeader("Cache-Control","no-cache"); 
		response.setHeader("Cache-Control","no-store"); 
		response.setDateHeader("Expires", 0); 
		response.setHeader("Pragma","no-cache"); 
		
		HttpSession httpSession = request.getSession();
		Cookie cookies[] = request.getCookies();
		String userName = (String)httpSession.getAttribute("uname");
		
		Session session = HibernateUtilities.getSessionFactory().openSession();
		Transaction t = session.getTransaction();
		Users user = session.get(Users.class, userName);
		Subscription subscription = session.get(Subscription.class, userName);
		session.delete(user);
		session.delete(subscription);
		t.commit();
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
