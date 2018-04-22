package newsreader;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

@WebServlet("/ChangeCredentials")
public class ChangeCredentials extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChangeCredentials() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Subscription subscriptionAdd = new Subscription();

		//For clearing the Cache
		response.setHeader("Cache-Control","no-cache"); 
		response.setHeader("Cache-Control","no-store"); 
		response.setDateHeader("Expires", 0); 
		response.setHeader("Pragma","no-cache"); 

		//Getting a Cookie and a Http session attribute
		HttpSession httpSession = request.getSession();
		Cookie cookies[] = request.getCookies();
		String loggedInUser = (String)httpSession.getAttribute("uname");
		String name = request.getParameter("name");
		String str = new String("");
		//String newsValues = null;

		String emailId = request.getParameter("emailId");
		String subscription[] = request.getParameterValues("newspaper");

		if(subscription != null) {
			for(String newsValues: subscription ) {
					str = str + newsValues + ",";
			}
		}
		
		Session sessionJSP = HibernateUtilities.getSessionFactory().openSession();
		Users user = sessionJSP.load(Users.class, loggedInUser);
		Subscription subscriptionObj = sessionJSP.load(Subscription.class, loggedInUser);

		assert(user.getName() != null);

		Transaction tr = sessionJSP.beginTransaction();
		if(name != null && !name.isEmpty())
			user.setName(name);
		if(emailId != null && !emailId.isEmpty())
			user.setEmailId(emailId);

		String registeredPapers = subscriptionObj.getSubscription();

		if(registeredPapers != null && !registeredPapers.isEmpty())
			str = str + registeredPapers;

		if(str != null && !str.isEmpty()) {
			subscriptionObj.setSubscription(str);
			user.setSubscription(subscriptionObj);
		}
		sessionJSP.update(user);
		sessionJSP.update(subscriptionObj);
		response.sendRedirect("/Login/reader");
		tr.commit();
		sessionJSP.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
