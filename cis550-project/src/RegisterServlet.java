import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class RegisterServlet extends HttpServlet
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
    	String email = req.getParameter("email");
    	String password = req.getParameter("password");
    	String name = req.getParameter("name");
    	String birthday = req.getParameter("birthday");
    	String location = req.getParameter("location");
    	String profession = req.getParameter("profession");
    	
    	
        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>");
        out.println("Registration Complete");
        out.println("</title>");
        out.println("<meta http-equiv=\"REFRESH\" content=\"5; url=/cis550-project\">");
        out.println("</head>");
        
        out.println("<body>");
        out.println("<b>Registration complete</b><br><br>");
        out.println("You will now be shortly redirected to the home page.");
        //out.println("The paramter username was \"" + username + "\".");
        //out.println("The paramter email was \"" + email + "\".");
        //out.println("The paramter password was \"" + password + "\".");
        //out.println("The paramter name was \"" + name + "\".");
        //out.println("The paramter birthday was \"" + birthday + "\".");
        //out.println("The paramter location was \"" + location + "\".");
        //out.println("The paramter profession was \"" + profession + "\".");
        out.println("</body>");
        out.println("</html>");
        out.flush();
        
        System.out.println("h11");
        User user = new User(username, password, birthday, location, profession);
        DatabaseRecorder dr = new DatabaseRecorder();
        dr.recordUser(user);
        System.out.println("User recorded successfully!");
        HttpSession session = req.getSession(true);
        //session.putValue("myappl.connection", conn);
    }
}