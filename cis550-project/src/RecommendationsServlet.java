import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class RecommendationsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
		doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{


		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<title>");
		out.println("My Recommendations");
		out.println("</title>");
		out.println("<body>");
		
		HttpSession session = req.getSession();
		String username = "notloggedin";
		if(session.isNew() || session.getAttribute("username") == null) {
			//out.println("<p>Welcome!</p>");
		} else {
			//out.println("<p> Welcome, " + session.getAttribute("username") + "! </p>");
			username = (String) session.getAttribute("username");
		}
		
        out.println("<br>");
		out.println(PageGenerator.getMenuBar());
		out.println("<br>");
		
		//out.println("<form method=\"POST\" action=\"SearchServlet\"/>" +
				//"Search: <input name=\"searchText\" type=\"text\" /> <br>" +
				//"<input type=\"submit\" value=\"Submit\" />" +
				//"</form>" +
				//"<br><br>");
		
		
		//if(dr.getRecommendations("agustin") != null){
			out.println("<b>Recommendations</b><br><br>");
			out.println(getText(username));
		//}
		
		
		out.println("</body>");
		out.println("</html>");
		out.flush();
		

	}
	
public static String getText(String username){
		
		String out = "";
		DatabaseReader dr = new DatabaseReader();
		
		HashSet<Story> pstories = dr.userHasPublishedAStory(username);
	    HashSet<Story> vstories = dr.userHasVotedOnAStory(username);
	    HashSet<Story> cstories = dr.userHasCommentedOnAStory(username);
	    
	    HashSet<Story> recommendedStories = new HashSet<Story>();
	    //if user has never shared, voted, or commented on anything, then recommend...
	    if ( (pstories == null) && (vstories == null) && (cstories ==null) ) {
	    	//for each category, get the most popular story and the stories with the highest number of comments
	    	HashSet<Story> popstories = dr.getMostPopularStoryInEachCategory();
	    	HashSet<Story> mostcommented = dr.getMostCommentedStoryInEachCategory();
	    	recommendedStories = new HashSet<Story>();
	    	if (popstories != null) {
	    		recommendedStories.addAll(popstories);
	    	}
	    	if (mostcommented != null) {
	    		recommendedStories.addAll(mostcommented);
	    	}
	    	//out += "nothing";
	    } else {
		    recommendedStories = new HashSet<Story>();
		    if (pstories != null) {
		    	recommendedStories.addAll(pstories);
		    }
		    if (vstories != null) {
		    	recommendedStories.addAll(vstories);
		    }
		    if (cstories != null) {
		    	recommendedStories.addAll(cstories);
		    }
	    }
	    
	    //remove stories by this user
	    Iterator<Story> iter = recommendedStories.iterator();
	    while (iter.hasNext()) {
	    	//System.out.println("checking for stories by this user..");
	    	Story s = iter.next();
	    	String user = s.getName();
	    	if (user.equals(username)) {
	    		//System.out.println("Story removed");
	    		iter.remove();
	    	}
	    }
	    
	    ///////////////////////////////////////////
		iter = recommendedStories.iterator();
		Story headline;
		while(iter.hasNext())
		{
			headline = iter.next();
			//out += ("Title: \"" + headline.getTitle() + "\". <br>");
			out += ("<a href=\"" + headline.getURL() + "\">" + headline.getTitle() + "</a> . <br>");
			out += ("Description: \"" + headline.getDescription() + "\". <br>");
			out += ("Category: \"" + headline.getCategory() + "\". <br>");
			out += ("Private? \"" + headline.getPrivate() + "\". <br>");
			out += ("Submitted by: \"" + headline.getName() + "\". <br>");
			//out += ("<a href=\"/vote?sid=x&uid=y\">Vote!</a>");
			out += ("<a href=\"/cis550-project/detail?sid="+ headline.getStoryid() + "&uid=" + username + "\">Details...</a> <br><br><br>");
		}
		////////////////////////////////////////////
	    
		return out;
	}

}
