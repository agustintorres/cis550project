import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class LoginServlet extends HttpServlet
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

        if (isValid){
        	out.println("<html>");
        	out.println("<head>");
        	out.println("<title>");
        	out.println("Login Successful");
        	out.println("</title>");
        	out.println("<meta http-equiv=\"REFRESH\" content=\"3; url=/cis550-project\">");
        	out.println("</head>");
	        out.println("<body>");
	        out.println("<b>Login Successful!</b><br><br>");
	        out.println("You will now be redirected to the home page.");
	        //out.println("The paramter username was \"" + username + "\".");
	        //out.println("The paramter password was \"" + password + "\".");
	        out.println("</body>");
	        out.println("</html>");
	        out.flush();
	        
	        //Create session and put user into session
	        HttpSession session = req.getSession(true);
	        session.setAttribute("username", username);
	        
        } else {
        	out.println("<html>");
        	out.println("<title>");
        	out.println("Authentication Failed");
        	out.println("</title>");
	        out.println("<body>");
	        out.println("<b>Authentication Failed</b><br><br>");
	        //out.println("The paramter username was \"" + username + "\".");
	        //out.println("The paramter password was \"" + password + "\".");
	        out.println("<p>Please try again.</p>");
	        
	        out.println("<form method=\"POST\" action=\"login\"/>");
			out.println("Username: <input name=\"username\" type=\"text\" /> <br>");
			out.println("Password: <input name=\"password\" type=\"password\" /> <br>");
			out.println("<input type=\"submit\" value=\"Submit\"/>");
			out.println("</form>");
	        
	        out.println("</body>");
	        out.println("</html>");
	        out.flush();
        }
        
        //User user = new User(username, password, birthday, location, profession);
        //DatabaseRecorder dr = new DatabaseRecorder();
        //dr.recordUser(user);
        //System.out.println("User recorded successfully!");
        //HttpSession session = req.getSession(true);
        //session.putValue("myappl.connection", conn);
    }
}