import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class XmlGenerator extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
		doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
		HttpSession session = req.getSession();
		String theUser;
		if(session.isNew() || session.getAttribute("username") == null) {
			theUser = "";
		}
		
		theUser =  (String) session.getAttribute("username");

		
		PrintWriter out = resp.getWriter();
		out.println("<?xml version=\"1.0\"?>");
		out.println("<rss version=\"2.0\">");
		out.println("<channel>");
		out.println("<title>AAM Digg</title>");
		out.println("<link>http://www.upenn.edu/</link>");
		out.println("<description>CIS 550 Project.</description>");
		out.println(" <language>en-us</language>");
		out.println(" <pubDate>Tue, 10 Jun 2003 04:00:00 GMT</pubDate>");

		    /* 
		    <item>
		      <title>Star City</title>
		      <link>http://liftoff.msfc.nasa.gov/news/2003/news-starcity.asp</link>
		      <description>How do Americans get ready to work with Russians aboard the
		        International Space Station? They take a crash course in culture, language
		        and protocol at Russia's Star City.</description>
		      <pubDate>Tue, 03 Jun 2003 09:39:21 GMT</pubDate>
		      <guid>http://liftoff.msfc.nasa.gov/2003/06/03.html#item573</guid>
		    </item>
		 
		    <item>
		      <title>Space Exploration</title>
		      <link>http://liftoff.msfc.nasa.gov/</link>
		      <description>Sky watchers in Europe, Asia, and parts of Alaska and Canada
		        will experience a partial eclipse of the Sun on Saturday, May 31st.</description>
		      <pubDate>Fri, 30 May 2003 11:06:42 GMT</pubDate>
		      <guid>http://liftoff.msfc.nasa.gov/2003/05/30.html#item572</guid>
		    </item>
		 
		    <item>
		      <title>The Engine That Does More</title>
		      <link>http://liftoff.msfc.nasa.gov/news/2003/news-VASIMR.asp</link>
		      <description>Before man travels to Mars, NASA hopes to design new engines
		        that will let us fly through the Solar System more quickly.  The proposed
		        VASIMR engine would do that.</description>
		      <pubDate>Tue, 27 May 2003 08:37:32 GMT</pubDate>
		      <guid>http://liftoff.msfc.nasa.gov/2003/05/27.html#item571</guid>
		    </item>
		 
		    <item>
		      <title>Astronauts' Dirty Laundry</title>
		      <link>http://liftoff.msfc.nasa.gov/news/2003/news-laundry.asp</link>
		      <description>Compared to earlier spacecraft, the International Space
		        Station has many luxuries, but laundry facilities are not one of them.
		        Instead, astronauts have other options.</description>
		      <pubDate>Tue, 20 May 2003 08:56:02 GMT</pubDate>
		      <guid>http://liftoff.msfc.nasa.gov/2003/05/20.html#item570</guid>
		    </item>
		    */
		if (theUser.equals("")){
			out.println("");
		}else{
			DatabaseReader theReader = new DatabaseReader();
			Iterator<User> iter = theReader.getFriendsByName(theUser, false).iterator();
			while(iter.hasNext()){					
				out.println(getAllXml(iter.next().getUsername()));
			}
		}
		out.println("</channel>");
		out.println("</rss>");
		out.flush();
	}
	
	public static String getAllXml(String username){
		String out = "";
		DatabaseReader dr = new DatabaseReader();
		ArrayList<Story> newspaper;
		ArrayList<Comment> complaints;
		ArrayList<Story> ballot;
		
		newspaper = dr.getStoriesByUser(username);
		complaints = dr.getCommentsByUser(username);
		ballot = dr.getVotesByUser(username);
		
		
		///////////////////////////////////////////
		Iterator<Story> iter = newspaper.iterator();
		Story headline;
		while(iter.hasNext())
		{
			headline = iter.next();
			out += ("<item> \n");
			out += ("<tag>Story</tag> \n");
			out += ("<title>" + headline.getTitle() + "</title> \n");		
			out += ("<link>" + headline.getURL() + "</link> \n");	
			out += ("<description>" + headline.getDescription() + "</description> \n");
			out += ("</item>");
			out += ("\n");
		}
		////////////////////////////////////////////
		///////////////////////////////////////////
		iter = ballot.iterator();
		while(iter.hasNext())
		{
			headline = iter.next();
			out += ("<item> \n");
			out += ("<tag>Vote</tag> \n");
			out += ("<title> Voted on: " + headline.getTitle() + "</title> \n");		
			out += ("<link>" + headline.getURL() + "</link> \n");			
			out += ("<description>" + headline.getDescription() + "</description> \n");
			out += ("</item>");
			out += ("\n");
		}
		////////////////////////////////////////////
		///////////////////////////////////////////
		Iterator<Comment> iterc = complaints.iterator();
		Comment quip;
		while(iterc.hasNext())
		{
			quip = iterc.next();
			out += ("<item> \n");
			out += ("<tag>Comment</tag> \n");
			out += ("<title>" + quip.getStorytitle() + "</title> \n");			
			out += ("<link>" + quip.getStorylink() + "</link> \n");		
			out += ("<description>"+ username + " said " + quip.getText() + "</description> \n");
			out += ("<commentTime>"+ quip.getCommenttime()+ "</commentTime>");
			out += ("</item>");
			out += ("\n");
		}
		////////////////////////////////////////////
		
		return out;	
	}
	

	public static String getStoryXml(String username) {
		String out = "";
		DatabaseReader dr = new DatabaseReader();
		ArrayList<Story> newspaper;
		
		newspaper = dr.getStories(10);
		
		///////////////////////////////////////////
		Iterator<Story> iter = newspaper.iterator();
		Story headline;
		while(iter.hasNext())
		{
			headline = iter.next();
			out += ("<item> \n");
			out += ("<title>" + headline.getTitle() + "</title> \n");
			//out += ("Title: \"" + headline.getTitle() + "\". <br>");
			
			out += ("<link>" + headline.getURL() + "</link> \n");
			//out += ("URL: \"" + headline.getURL() + "\". <br>");
			
			out += ("<description>" + headline.getDescription() + "</description> \n");
			//out += ("Description: \"" + headline.getDescription() + "\". <br>");
			//out += ("Category: \"" + headline.getCategory() + "\". <br>");
			//out += ("Private? \"" + headline.getPrivate() + "\". <br>");
			//out += ("Submitted by: \"" + headline.getName() + "\". <br><br><br>");
			
			out += ("</item>");
			out += ("\n");
		}
		////////////////////////////////////////////
		
		return out;		
	}
}
