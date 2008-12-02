import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
		String theUser = req.getParameter("uid");
		int theStory = Integer.parseInt(req.getParameter("sid"));

		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<title>");
		out.println("Story Detail");
		out.println("</title>");
		out.println("<body>");
		
		out.println("<form method=\"POST\" action=\"SearchServlet\"/>" +
					"Search: <input name=\"searchText\" type=\"text\" /> <br>" +
					"<input type=\"submit\" value=\"Submit\" />" +
					"</form>" +
					"<br><br>");
		
		out.println(PageGenerator.getMenuBar());
		out.println("<br>");
		
		//list story details
		DatabaseReader drdr = new DatabaseReader();
		Story headline = drdr.getStoryById(theStory);
		 
		out.println("Title: \"" + headline.getTitle() + "\". <br>");
		out.println("URL: \"" + headline.getURL() + "\". <br>");
		out.println("Description: \"" + headline.getDescription() + "\". <br>");
		out.println("Category: \"" + headline.getCategory() + "\". <br>");
		out.println("Private? \"" + headline.getPrivate() + "\". <br>");
		out.println("Submitted by: \"" + headline.getName() + "\". <br>");
		out.println("Time: \"" + headline.getStorytime() + "\". <br>");
		out.println("Votes: \"" + headline.getVotes() + "\". <br><br><br>");
		
		//give vote option
		
		out.println("<a href=\"/vote?sid=" + theStory + "&uid="+ theUser +"\">Vote!</a>");
		
		out.println("<a href=\"/comment?sid=" + theStory + "&uid="+ theUser + "&commenttext=blahblahcomment" + "\">Comment!</a>");
		
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
		out.println("<br><br>");
		out.println("<form method=\"POST\" action=\"CommentServlet\"/>" +
				"Comment: <input name=\"commentText\" type=\"text\" /> <br>" +
				"<input type=\"submit\" value=\"Submit\" />" +
				"</form>" +
				"<br><br>");
		
		
		out.println("</body>");
		out.println("</html>");
		out.flush();
		

	}

}
