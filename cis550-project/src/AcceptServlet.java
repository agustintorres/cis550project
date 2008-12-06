

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AcceptServlet
 */
public class AcceptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession(true);
		String uid1 = (String) session.getAttribute("username");
		String friend = req.getParameter("user");
		
		DatabaseRecorder dr = new DatabaseRecorder();
		boolean result = dr.addPendingFriend(uid1, friend);
		
		if(result == true){
        	out.println("<html>");
        	out.println("<head>");
        	out.println("<title>");
        	out.println("Friend added!");
        	out.println("</title>");
			out.println("<head> <meta http-equiv=\"REFRESH\" content=\"2;url=http://localhost:8080/cis550-project/viewfriends\"></HEAD>");
	        out.println("<body>");
	        out.println("<b>Friend added!</b><br><br>");
	        out.println("You will now be redirected to your friends page.");
	        out.println("</body>");
	        out.println("</html>");
		}else{
        	out.println("<html>");
        	out.println("<head>");
        	out.println("<title>");
        	out.println("Friend could not be added.");
        	out.println("</title>");
			out.println("<head> <meta http-equiv=\"REFRESH\" content=\"2;url=http://localhost:8080/cis550-project/viewfriends\"></HEAD>");
	        out.println("<body>");
	        out.println("<b>Friend could not be added.</b><br><br>");
        	out.println("You will now be redirected to your friends page.");
	        out.println("</body>");
	        out.println("</html>");
		}
		

		
		out.flush();
		
	}

}
