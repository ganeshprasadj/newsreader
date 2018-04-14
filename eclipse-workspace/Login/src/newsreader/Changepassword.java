package newsreader;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

@WebServlet("/Changepassword")
public class Changepassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Changepassword() {
		super();
		// TODO Auto-generated constructor stub
	}
	
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//For clearing the Cache
		response.setHeader("Cache-Control","no-cache"); 
		response.setHeader("Cache-Control","no-store"); 
		response.setDateHeader("Expires", 0); 
		response.setHeader("Pragma","no-cache"); 

		//Getting a Cookie and a Http session attribute
		HttpSession httpSession = request.getSession();
		Cookie cookies[] = request.getCookies();
		String loggedInUser = (String)httpSession.getAttribute("uname");
		String UserName = request.getParameter("uname");
		if(loggedInUser.equals(UserName)){

			String userName = (String) request.getParameter("uname");
			String newPassword = (String) request.getParameter("newPassword");
			
			String oldPassword= (String) request.getParameter("oldPassword");
			Session sessionJSP = HibernateUtilities.getSessionFactory().openSession();
			Users user = sessionJSP.load(Users.class, userName);
			assert(user.getName() != null);

//			//Hash code generation
//			byte[] salt = new byte[16];
//			byte[] hash = null;
//			Random random = null;
//			random.nextBytes(salt);
//			KeySpec spec = new PBEKeySpec(newPassword.toCharArray(),salt,65536,128);
//			SecretKeyFactory f = null;
//			try {
//				f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//
//			} catch (NoSuchAlgorithmException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				hash = f.generateSecret(spec).getEncoded();
//			} catch (InvalidKeySpecException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

			String savedPasswordHash =  hashPassword(user.getPassword());
			String oldPasswordHash =  hashPassword(oldPassword);
			String newPasswordHash = hashPassword(newPassword);
			if(savedPasswordHash.equals(oldPasswordHash)) {
				Transaction tr = sessionJSP.beginTransaction();
				user.setPassword(newPasswordHash);
				sessionJSP.update(user);
				tr.commit();
				sessionJSP.close();
			}else {
				PrintWriter pw = null;
				pw.write("<html><head><title></title></head><body><p>Entered old password is incorrect please try again<a href= changepassword.jsp>Change password</a></p></html>");
			}
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
