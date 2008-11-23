import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class LogoutServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
    	String username = req.getParameter("username");
    	String password = req.getParameter("password");

    	DatabaseReader dr = new DatabaseReader();
    	boolean isValid = dr.isValidAuthentication(username, password);
    	
        PrintWriter out = resp.getWriter();

        //Get the session only if it exists (false flag) and invalidate it
	    HttpSession session = req.getSession(false);
	    session.invalidate();
	    
        
        out.println("<html>");
        out.println("<head>");
        out.println("<title>");
        out.println("Logout Successful");
        out.println("</title>");
        out.println("<meta http-equiv=\"REFRESH\" content=\"3; url=/cis550-project\">");
        out.println("</head>");
	    out.println("<body>");
	    out.println("<b>Logging out...</b><br><br>");
	    out.println("You will now be redirected to the home page.");
	    out.println("</body>");
	    out.println("</html>");
	    out.flush();
	            
        //User user = new User(username, password, birthday, location, profession);
        //DatabaseRecorder dr = new DatabaseRecorder();
        //dr.recordUser(user);
        //System.out.println("User recorded successfully!");
        //HttpSession session = req.getSession(true);
        //session.putValue("myappl.connection", conn);
    }
}