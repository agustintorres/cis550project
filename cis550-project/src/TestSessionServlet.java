
import java.io.*;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

public class TestSessionServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws IOException, ServletException {

		res.setContentType("text/html");

		// got hold of the user session

		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession(true);

		// declare out Counter object

		Counter counter;
		if(session.isNew()) {
			counter = new Counter();
			session.setAttribute("counter", counter);
		}

		// incrementing page view count in our Counter object

		counter = ((Counter)session.getAttribute("counter"));
		counter.increment();

		// retrieving session info

		long currentTime = System.currentTimeMillis();
		long creationTime = session.getCreationTime();
		long lastAccessed = session.getLastAccessedTime();
		long maxInterval = session.getMaxInactiveInterval();
		String sessionId = session.getId();
		String mode = req.getParameter("mode");

		// print content

		out.print("<html><head>");
		out.print("<style>body, p { font-family:tahoma;");
		out.print("font-size:12pt; } a { text-decoration:none;");
		out.print("color:blue; }</style></head>");
		out.print("<body>");

		if(session.isNew())	{
			out.print("<p>New Session created.</p>");
		} else {
			out.print("<p>Old Session. ");
			out.print("Age : ");
			out.print( (currentTime - creationTime) / 60000 );
			out.print(" minutes.</p>");
		}

		out.print("<p>You have visited this page : ");
		out.print( counter.getCount() );
		out.print(" times.<br>");
		out.print("Created at : ");
		out.print( new Date(creationTime) );
		out.print("<br>");
		out.print("Last accessed : ");
		out.print( new Date(lastAccessed) );
		out.print("<br>");
		out.print("SessionID : ");
		out.print( sessionId );

		out.print("</p><p>");
		out.print("Note! Session will be inactivated after ");
		out.print("a period of inactivity of <b>");
		out.print( (maxInterval / 60) );
		out.print("</b> minutes.");
		out.print("</p><p>");

		if( mode != null && mode.equals("invalidate")) {
			session.invalidate();
			out.print("<a href=\"/servlet/com.stardeveloper.");
			out.print("servlets.TestSessionServlet\">");
			out.print("Start New Session.</a>");
		} else {
			out.print("<a href=\"/servlet/");
			out.print("com.stardeveloper.servlets.");
			out.print("TestSessionServlet?mode=invalidate\">");
			out.print("Invalidate this Session.</a>");
		}

		out.print("<p align=\"center\">");
		out.print("<a href=\"http://www.stardeveloper.com\" ");
		out.print("style=\"font-size:8pt;color:silver;\">");
		out.print("© Stardeveloper.com</a></p>");
		out.print("</p></body></html>");

		out.close();
	}
}