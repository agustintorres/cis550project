	

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class StoryServlet
 */
public class StoryServlet extends HttpServlet {

	int yes = 0;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
		String title = req.getParameter("title");
		String url = req.getParameter("url");
		String description = req.getParameter("description");
		String category = req.getParameter("category");
		String isprivate = req.getParameter("private");


		PrintWriter out = resp.getWriter();

		out.println("<html>");
		out.println("<title>");
		out.println("Publishing Complete");
		out.println("</title>");
		out.println("<body>");
		out.println("<b>Publishing complete</b><br><br>");
		out.println("The paramter title was \"" + title + "\".");
		out.println("The paramter url was \"" + url + "\".");
		out.println("The paramter description was \"" + description + "\".");
		out.println("The paramter category was \"" + category + "\".");
		out.println("The paramter private was \"" + isprivate + "\".");
		out.println("</body>");
		out.println("</html>");
		out.flush();
		
		if (isprivate.equals("private")){
			int yes = 1;
		}

		System.out.println("h11");
		Story story = new Story(title, url, description, category, yes);
		DatabaseRecorder dr = new DatabaseRecorder();
		dr.recordStory(story);
		System.out.println("hi2");
	}

}
