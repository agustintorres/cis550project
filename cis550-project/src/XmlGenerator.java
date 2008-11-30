import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class XmlGenerator extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
		doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{


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
		out.println(ViewStories.getXml(10));
		out.println("</channel>");
		out.println("</rss>");


		out.flush();
		

	}

}
