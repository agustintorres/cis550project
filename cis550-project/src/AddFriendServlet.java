import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class AddFriendServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
    	
    	HttpSession session = req.getSession(true);
    	String uid1 = (String) session.getAttribute("username");
    	String uid2 = req.getParameter("uid2");
    	
        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<title>");
        out.println("Friend Added");
        out.println("</title>");
        out.println("<body>");
        out.println("<b>Friend added successfully</b><br><br>");
        out.println("You added " + uid2 + " as a friend. Hopefully he/she will accept.");
        out.println("</body>");
        out.println("</html>");
        out.flush();
        
        Friend f = new Friend(uid1, uid2);
        DatabaseRecorder dr = new DatabaseRecorder();
        dr.recordFriend(f);
    }
}
