package newsreader;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//For clearing the Cache
				response.setHeader("Cache-Control","no-cache"); 
				response.setHeader("Cache-Control","no-store"); 
				response.setDateHeader("Expires", 0); 
				response.setHeader("Pragma","no-cache");
		String Uname = request.getParameter("uname");
		
		HttpSession httpSession = request.getSession(false);
		httpSession.invalidate();
		Cookie[] cookieLogout = request.getCookies();
		
		for(Cookie cookie : cookieLogout){
    		if(cookie.getName().equals("uname")){
    			cookie.setMaxAge(-1);
    			response.addCookie(cookie);
    			break;
    		}
    	}
//		response.addCookie(cookieLogout);
		PrintWriter pw = response.getWriter();

		response.sendRedirect("index.jsp");
		pw.write("<html><body><p>You have successfully Logged out</p></body></head>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
