import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.LinkedList;
import javax.servlet.*;
import javax.servlet.http.*;


public class SearchServlet extends HttpServlet {


	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
		
		LinkedList<String> words = new LinkedList<String>();

		String searchText = req.getParameter("searchtext");

		//Parse search text

		StringTokenizer st = new StringTokenizer(searchText);
		System.out.println(searchText);
		
		while (st.hasMoreTokens()) {
			words.add(st.nextToken());
		}

		PrintWriter out = resp.getWriter();

		out.println("<html>");
		out.println("<title>");
		out.println("Publishing Complete");
		out.println("</title>");
		out.println("<body>");
		out.println("<b>Publishing complete</b><br><br>");

		out.println(getText(words, 10));

		out.println("</body>");
		out.println("</html>");
		out.flush();

	}

	public static String getText(LinkedList<String> words, int i){

		String out = "";
		SearchInvertedIndex sii = new SearchInvertedIndex(words);
		LinkedList<Story> newspaper = sii.createRanking();

		///////////////////////////////////////////
		Iterator<Story> iter = newspaper.iterator();
		Story headline;
		while(iter.hasNext())
		{
			headline = iter.next();
			out += ("Title: \"" + headline.getTitle() + "\". <br>");
			out += ("URL: \"" + headline.getURL() + "\". <br>");
			out += ("Description: \"" + headline.getDescription() + "\". <br>");
			out += ("Category: \"" + headline.getCategory() + "\". <br>");
			out += ("Private? \"" + headline.getPrivate() + "\". <br>");
			out += ("Submitted by: \"" + headline.getName() + "\". <br><br><br>");

		}
		////////////////////////////////////////////

		return out;

	}

}
