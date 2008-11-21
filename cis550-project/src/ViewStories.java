	

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

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{

		DatabaseReader dr = new DatabaseReader();
		ArrayList<Story> newspaper;
		
		newspaper = dr.getStories(3);
		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<title>");
		out.println("Publishing Complete");
		out.println("</title>");
		out.println("<body>");
		out.println("<b>Publishing complete</b><br><br>");
		
		///////////////////////////////////////////
		Iterator<Story> iter = newspaper.iterator();
		Story headline;
		while(iter.hasNext())
		{
			headline = iter.next();
			out.println("Title: \"" + headline.getTitle() + "\". <br>");
			out.println("URL: \"" + headline.getURL() + "\". <br>");
			out.println("Description: \"" + headline.getDescription() + "\". <br>");
			out.println("Category: \"" + headline.getCategory() + "\". <br>");
			out.println("Private? \"" + headline.getPrivate() + "\". <br>");
			out.println("Submitted by: \"" + headline.getName() + "\". <br><br><br>");
			
		}
		////////////////////////////////////////////
		

		out.println("</body>");
		out.println("</html>");
		out.flush();
		

	}

}
