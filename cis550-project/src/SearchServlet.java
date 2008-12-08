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
		HttpSession session = req.getSession(true);
		String name =  (String) session.getAttribute("username");

		String searchText = req.getParameter("searchtext");
		String searchType = req.getParameter("searchtype");

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

		if(searchType.equals("Keyword")){
			out.println(getKeywordSearch(words, name, 10));
		}
		
		if(searchType.equals("Category")){
			out.println(getCategorySearch(words, name, 10));
		}
		
		if(searchType.equals("Most Recent")){
			out.println(getRecentSearch(words, name, 10));
		}
		
		if(searchType.equals("Most Popular")){
			out.println(getPopularSearch(words, name, 10));
		}
		
		out.println("</body>");
		out.println("</html>");
		out.flush();

	}

	public static String getKeywordSearch(LinkedList<String> words, String username, int i){

		String out = "";
		SearchInvertedIndex sii = new SearchInvertedIndex(words);
		LinkedList<Story> newspaper = sii.createRanking();

		///////////////////////////////////////////
		Iterator<Story> iter = newspaper.iterator();
		Story headline;
		while(iter.hasNext())
		{
			headline = iter.next();
			out += ("<a href=\"" + headline.getURL() + "\">" + headline.getTitle() + "</a> . <br>");
			out += ("Description: \"" + headline.getDescription() + "\". <br>");
			out += ("Category: \"" + headline.getCategory() + "\". <br>");
			out += ("Private? \"" + headline.getPrivate() + "\". <br>");
			out += ("Submitted by: \"" + headline.getName() + "\". <br>");
			out += ("<a href=\"/cis550-project/detail?sid="+ headline.getStoryid() + "&uid=" + username + "\">Details...</a> <br><br><br>");

		}
		////////////////////////////////////////////

		return out;

	}
	
	public static String getCategorySearch(LinkedList<String> words, String username, int i){
		String out = "";
		
		DatabaseReader dr = new DatabaseReader();
		
		if(words.size() == 0 || words.size() > 1 || !dr.getDatabaseCategories().contains(words.get(0))){
			out += "Improper Category";
			return out;
		}else{
			LinkedList<Story> newspaper = dr.getStoriesByCategory(words.get(0));
			
			Iterator<Story> iter = newspaper.iterator();
			Story headline;
			while(iter.hasNext())
			{
				headline = iter.next();
				out += ("<a href=\"" + headline.getURL() + "\">" + headline.getTitle() + "</a> . <br>");
				out += ("Description: \"" + headline.getDescription() + "\". <br>");
				out += ("Category: \"" + headline.getCategory() + "\". <br>");
				out += ("Private? \"" + headline.getPrivate() + "\". <br>");
				out += ("Submitted by: \"" + headline.getName() + "\". <br>");
				out += ("<a href=\"/cis550-project/detail?sid="+ headline.getStoryid() + "&uid=" + username + "\">Details...</a> <br><br><br>");
			}
			
		}
		
		return out;
	}
	
	public static String getRecentSearch(LinkedList<String> words, String username, int i){
		String out = "";
		
		DatabaseReader dr = new DatabaseReader();
		
		LinkedList<Story> newspaper = dr.getStoriesByRecent();
		
		Iterator<Story> iter = newspaper.iterator();
		Story headline;
		while(iter.hasNext())
		{
			headline = iter.next();
			out += ("<a href=\"" + headline.getURL() + "\">" + headline.getTitle() + "</a> . <br>");
			out += ("Description: \"" + headline.getDescription() + "\". <br>");
			out += ("Category: \"" + headline.getCategory() + "\". <br>");
			out += ("Private? \"" + headline.getPrivate() + "\". <br>");
			out += ("Submitted by: \"" + headline.getName() + "\". <br>");
			out += ("<a href=\"/cis550-project/detail?sid="+ headline.getStoryid() + "&uid=" + username + "\">Details...</a> <br><br><br>");
		}
		
		return out;
	}
	
	public static String getPopularSearch(LinkedList<String> words, String username, int i){
		String out = "";
		
		DatabaseReader dr = new DatabaseReader();
		
		LinkedList<Story> newspaper = dr.getStoriesByPopular();
		
		Iterator<Story> iter = newspaper.iterator();
		Story headline;
		while(iter.hasNext())
		{
			headline = iter.next();
			out += ("<a href=\"" + headline.getURL() + "\">" + headline.getTitle() + "</a> . <br>");
			out += ("Description: \"" + headline.getDescription() + "\". <br>");
			out += ("Category: \"" + headline.getCategory() + "\". <br>");
			out += ("Private? \"" + headline.getPrivate() + "\". <br>");
			out += ("Submitted by: \"" + headline.getName() + "\". <br>");
			out += ("<a href=\"/cis550-project/detail?sid="+ headline.getStoryid() + "&uid=" + username + "\">Details...</a> <br><br><br>");
		}
		
		return out;
	}

}
