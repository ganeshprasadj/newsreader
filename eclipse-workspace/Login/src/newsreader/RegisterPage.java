package newsreader;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
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

	private String hashPassword(String pass) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		md.update(pass.getBytes());
		byte byteData[] = md.digest();

		//convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
		
//		if(papersRegistered != null) {
//			for(String newsValues: papersRegistered ) {
//				str = str + newsValues + "," ;
//			}
//		}

		try{

			Users user1 = new Users();
			Subscription subscription = new Subscription();

			String hashPassword = hashPassword(password);
			user1.setName(name);
			user1.setUname(userName);
			user1.setPassword(hashPassword);
			user1.setDOB(dOB);
			user1.setEmailId(emailId);
			user1.setRefreshPeriod(refreshPeriod);
			user1.setSubscription(subscription);

			subscription.setUname(userName);
			

			try {	
				Session session = HibernateUtilities.getSessionFactory().openSession();
				session.beginTransaction();

				if(papersRegistered != null) {
					for(String newsValues: papersRegistered ) {
						NewspaperLookup newspaperLookupObj = session.get(NewspaperLookup.class, newsValues);
						int id = newspaperLookupObj.getId();
						str = str + id + "," ;
					}
				}
				subscription.setSubscription(str);
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
		doGet(request, response);
	}

}
