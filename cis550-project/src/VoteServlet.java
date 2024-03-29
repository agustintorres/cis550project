	

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class VoteServlet
 */
@SuppressWarnings("serial")
public class VoteServlet extends HttpServlet {

	int yes = 0;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
		String status = "";
		
		//Get params for storyid and userid
		HttpSession session = req.getSession();
		String theUser;
		if(session.isNew() || session.getAttribute("username") == null) {
			theUser = "";
		}
		else{
		theUser =  (String) session.getAttribute("username");
		}
		
		int theStory = Integer.parseInt(req.getParameter("sid"));
		
		//Query the votes table to see if tuple exists for storyid and userid
		DatabaseReader dread = new DatabaseReader();
		if (dread.voteExists(theUser, theStory))
		{
			status = "FAILURE";
		}
		
		else
		{
			status = "SUCCESS";
			//If not, update the table to record this user and vote
			DatabaseRecorder drec = new DatabaseRecorder();
			
			drec.vote(theUser, theStory);
		}
		//Return success or failure...
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Voting Complete");
		out.println("</title>");
		out.println("<meta http-equiv=\"REFRESH\" content=\"3; url=/cis550-project/detail?sid=" + theStory + "&uid=" + theUser + "\">");
    	out.println("</head>");
		out.println("<body>");
		out.println("Voting status: " + status);
		out.println("</body>");
		out.println("</html>");
		out.flush();

	}

}
