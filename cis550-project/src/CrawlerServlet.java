import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@SuppressWarnings("serial")
public class CrawlerServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
		doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{

		//String[] urlPaths = {"Non working path", "http://www.sun.com", "Another path"};
		URL url = null;
		int myCode;
		CrawlerLink current;
		DatabaseReader dr = new DatabaseReader();
		ArrayList<CrawlerLink> links = dr.getLinks();
		
		ArrayList<CrawlerLink> badLinks = new ArrayList<CrawlerLink>();
		
		Iterator<CrawlerLink> iter = links.iterator();
		while(iter.hasNext()) {
			
				current = iter.next();
				try {
				url = new URL(current.getUrl());
				

					myCode = ((HttpURLConnection) url.openConnection()).getResponseCode();
					
					//InputStream is = url.openStream();
					if(myCode != 200){
						//bad site found, add to remove list
						badLinks.add(current);
					}
				
				} catch(Exception e) {
					//If you get an exception you can be rather certain that you don't have a
					//connection established.
					e.printStackTrace();
					badLinks.add(current);
			}
		}
		
		//take badLinks and remove them from the database
		DatabaseRecorder dbrec = new DatabaseRecorder();
		
		dbrec.removeStories(badLinks);
		
		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<title>");
		out.println("Publishing Complete");
		out.println("</title>");
		out.println("<body>");
		
		/*
		HttpSession session = req.getSession();
		String username = "notloggedin";
		if(session.isNew() || session.getAttribute("username") == null) {
			out.println("<p>Welcome!</p>");
		} else {
			out.println("<p> Welcome, " + session.getAttribute("username") + "! </p>");
			username = (String) session.getAttribute("username");
			
		}
		*/
        out.println("<br>");
		out.println(PageGenerator.getMenuBar());
		out.println("<br>");
		
		/*
		out.println("<form method=\"POST\" action=\"SearchServlet\"/>" +
				"Search: <input name=\"searchtext\" type=\"text\" /> <br>" +
				"<input type=\"submit\" value=\"Submit\" />" +
				"</form>" +
				"<br><br>");
		
		out.println(ViewStories.getText(10, username));
		*/
		
		out.println("<br>The following stories were removed due to bad links:<br>");
		
		iter = badLinks.iterator();
		while(iter.hasNext()){
			out.println(iter.next().getUrl());
			out.println("<br><br>");
		}
		
		out.println("</body>");
		out.println("</html>");
		out.flush();


	}

}
