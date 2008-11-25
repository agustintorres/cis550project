import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.LinkedList;
import javax.servlet.*;
import javax.servlet.http.*;


public class SearchServlet extends HttpServlet {
	
	public static LinkedList<String> words;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
		doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
		String searchText = req.getParameter("searchText");
		
		//Parse search text
		
		StringTokenizer st = new StringTokenizer(searchText);
	     while (st.hasMoreTokens()) {
	         words.add(st.nextToken());
	     }
	     
	     SearchInvertedIndex sii = new SearchInvertedIndex(words);
	     sii.createRanking();
		
	}

}
