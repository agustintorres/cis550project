import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class IndexGeneratorServlet extends HttpServlet {
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
		out.println("Publishing Complete");
		out.println("</title>");
		out.println("<body>");

		HttpSession session = req.getSession();
		String username = "";
		if(session.isNew() || session.getAttribute("username") == null) {
			out.println("<p>Welcome!</p>");
		} else {
			out.println("<p> Welcome, " + session.getAttribute("username") + "! </p>");
			username = (String) session.getAttribute("username");

		}

		out.println("<br>");
		out.println(PageGenerator.getMenuBar());
		out.println("<br>");

		out.println("<form method=\"POST\" action=\"SearchServlet\"/>" +
				"Search: <input name=\"searchtext\" type=\"text\" /> " +
		"<select name = \"searchtype\">");

		out.println("<option value =\"Keyword\">Keyword</option>"+
				"<option value =\"Category\">Category</option>"+
				"<option value =\"Most Recent\">Most Recent</option>"+
		"<option value =\"Most Popular\">Most Popular</option>");

		out.println("</select> <br>");

		out.println("Examples of valid categories are: News, Politics, Sports, Technology, and Misc <br>"+
				"<input type=\"submit\" value=\"Submit\" />" +
				"</form>" +
		"<br><br>");

		out.println(ViewStories.getText(10, username));

		out.println("<br<br><b>Top 3 Users:</b><br><br>");
		out.println(getTopUsers());

		out.println("</body>");
		out.println("</html>");
		out.flush();


	}

	public static String getTopUsers(){

		String out = "";
		DatabaseReader dr = new DatabaseReader();

		ArrayList<String> results = dr.getTopUsers();

		int index = 3;

		for(int x = 0;  x < results.size(); x++){
			if(index == 0){
				break;
			}else{
				out+= results.get(x)+" has a vote/story ratio of "+ results.get(x+1) +"<br>";
				x++;
				index--;
			}
		}

		return out;

	}

}
