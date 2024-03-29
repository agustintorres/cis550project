import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@SuppressWarnings("serial")
public class DetailServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
		doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
		HttpSession session = req.getSession();
		String theUser;
		if(session.isNew() || session.getAttribute("username") == null) {
			theUser = "";
		}
		else{
		theUser =  (String) session.getAttribute("username");
		}
		
		//String theUser = req.getParameter("uid");
		int theStory = Integer.parseInt(req.getParameter("sid"));

		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<title>");
		out.println("Story Detail");
		out.println("</title>");
		out.println("<body>");
		if(theUser.equals("")){
		out.println("<br> Log in to comment, vote, or post! <br>");
		}
		
		out.println(PageGenerator.getMenuBar());
		out.println("<br>");
		
		//list story details
		DatabaseReader drdr = new DatabaseReader();
		Story headline = drdr.getStoryById(theStory);
		
		out.println("<a href=\"" + headline.getURL() + "\">" + headline.getTitle() + "</a> . <br>");
		out.println("Description: \"" + headline.getDescription() + "\". <br>");
		out.println("Category: \"" + headline.getCategory() + "\". <br>");
		out.println("Private? \"" + headline.getPrivate() + "\". <br>");
		out.println("Submitted by: \"" + headline.getName() + "\". <br>");
		out.println("Time: \"" + headline.getStorytime() + "\". <br>");
		out.println("Votes: \"" + headline.getVotes() + "\". <br><br><br>");
		
		//give vote option
		if(!(theUser.equals(""))){		
		out.println("<a href=\"/cis550-project/vote?sid=" + theStory + "\">Vote!</a>");		
		}
		
		//list all other comments
		
		ArrayList<Comment> chatlog = drdr.getComments(theStory);
		if (chatlog != null){
			Iterator<Comment> iter = chatlog.iterator();
			Comment quip;
			while(iter.hasNext()){
				quip = iter.next();
				out.println(quip.toHtml());
			}
		}
		else {
			out.println("No comments found. <br><br>");
		}
		//give comment option
		if(!(theUser.equals(""))){	
		out.println("<br><br>");
		out.println("<form method=\"POST\" action=\"comment\"/>" +
				"Comment: <input name=\"commenttext\" type=\"text\" /> <br>" +
				"<input name=\"uid\" type=\"hidden\" value=\"" + theUser + "\" /> <br>" +
				"<input name=\"sid\" type=\"hidden\" value=\"" + theStory + "\" /> <br>" +
				"<input type=\"submit\" value=\"Submit\" />" +
				"</form>" +
				"<br><br>");
		}
		

		
		out.println("</body>");
		out.println("</html>");
		out.flush();
		

	}

}
