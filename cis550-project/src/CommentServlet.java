import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import javax.servlet.*;
import javax.servlet.http.*;


@SuppressWarnings("serial")
public class CommentServlet extends HttpServlet {


	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
		
		String commentText = req.getParameter("commenttext");
		String username = req.getParameter("uid");
		int storyid = Integer.parseInt(req.getParameter("sid"));
		
		//Add comment to database
		//cid
		//uid
		//storyid
		//text
		//parent
		//time
		
		DatabaseRecorder dbrec = new DatabaseRecorder();
		
		boolean status = dbrec.recordComment(storyid, username, commentText);
		
		
		//Reload the detail page
		
			
		PrintWriter out = resp.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Commenting Complete");
		out.println("</title>");
		out.println("<meta http-equiv=\"REFRESH\" content=\"3; url=/cis550-project/detail?sid=" + storyid + "&uid=" + username + "\">");
		out.println("</head>");
		out.println("<body>");
		if (status){
			out.println("<b>Commenting complete</b><br><br>");
		}
		else{
			out.println("<b>Commenting failed</b><br><br>");
		}

		//out.println(getText(words, 10));

		out.println("</body>");
		out.println("</html>");
		out.flush();

	}

}
