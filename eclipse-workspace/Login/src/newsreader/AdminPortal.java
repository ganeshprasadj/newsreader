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

@WebServlet("/AdminPortal")
public class AdminPortal extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminPortal() {
        super();
        
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
				
		//response.getWriter().append("Served at: ").append(request.getContextPath());
				
				try{
					String newspaperName = request.getParameter("newspaperName");
					String category = request.getParameter("category");
					String url = request.getParameter("url");
					String[] custom = request.getParameterValues("custom");
					boolean isCustom = false;
					
					NewspaperLookup newspaperLookupObj = new NewspaperLookup();
					
					newspaperLookupObj.setNewspaperName(newspaperName);
					newspaperLookupObj.setCategory(category);
					newspaperLookupObj.setUrl(url);
					
					if(custom != null) {
						isCustom = true;
					}
					
					newspaperLookupObj.setCustom(isCustom);
					
					Session session = HibernateUtilities.getSessionFactory().openSession();
					session.beginTransaction();
					session.save(newspaperLookupObj);
					session.getTransaction().commit();	
					
				}catch(Exception e) {
					System.out.println(e.getLocalizedMessage());
				}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
