	

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Servlet implementation class ViewStories
 */
public class ViewStories extends HttpServlet {

	int yes = 0;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
		doPost(req, resp);
	}

	public static String getXml(int i) {
		String out = "";
		DatabaseReader dr = new DatabaseReader();
		ArrayList<Story> newspaper;
		
		newspaper = dr.getStories(i);
		
		///////////////////////////////////////////
		Iterator<Story> iter = newspaper.iterator();
		Story headline;
		while(iter.hasNext())
		{
			headline = iter.next();
			out += ("<item> \n");
			out += ("<title>" + headline.getTitle() + "</title> \n");
			//out += ("Title: \"" + headline.getTitle() + "\". <br>");
			
			out += ("<link>" + headline.getURL() + "</link> \n");
			//out += ("URL: \"" + headline.getURL() + "\". <br>");
			
			out += ("<description>" + headline.getDescription() + "</description> \n");
			//out += ("Description: \"" + headline.getDescription() + "\". <br>");
			//out += ("Category: \"" + headline.getCategory() + "\". <br>");
			//out += ("Private? \"" + headline.getPrivate() + "\". <br>");
			//out += ("Submitted by: \"" + headline.getName() + "\". <br><br><br>");
			
			out += ("</item>");
			out += ("\n");
		}
		////////////////////////////////////////////
		
		return out;		
	}
	
	public static String getText(int i){
		return getText(i, "notloggedin");
	}
	
	public static String getText(int i, String username){
		String out = "";
		DatabaseReader dr = new DatabaseReader();
		ArrayList<Story> newspaper;
		
		newspaper = dr.getStories(i);
		
		///////////////////////////////////////////
		Iterator<Story> iter = newspaper.iterator();
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
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{


		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<title>");
		out.println("Publishing Complete");
		out.println("</title>");
		out.println("<body>");
		out.println("<b>Publishing complete</b><br><br>");
		
		out.println(getText(10));

		out.println("</body>");
		out.println("</html>");
		out.flush();
		

	}

}
