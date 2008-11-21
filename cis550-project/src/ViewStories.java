	

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

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
		/*
		String title = req.getParameter("title");
		String url = req.getParameter("url");
		String description = req.getParameter("description");
		String category = req.getParameter("category");
		String isprivate = req.getParameter("private");
		String name =  req.getParameter("name");
		*/
		DatabaseRecorder dr = new DatabaseRecorder();
		Story headline = dr.getStory();
		PrintWriter out = resp.getWriter();

		out.println("<html>");
		out.println("<title>");
		out.println("Publishing Complete");
		out.println("</title>");
		out.println("<body>");
		out.println("<b>Publishing complete</b><br><br>");
		out.println("The paramter title was \"" + headline.getTitle() + "\".");
		out.println("The paramter url was \"" + headline.getURL() + "\".");
		out.println("The paramter description was \"" + headline.getDescription() + "\".");
		out.println("The paramter category was \"" + headline.getCategory() + "\".");
		out.println("The paramter private was \"" + headline.getPrivate() + "\".");
		out.println("The paramter name was \"" + headline.getName() + "\".");
		out.println("</body>");
		out.println("</html>");
		out.flush();
		

	}

}
