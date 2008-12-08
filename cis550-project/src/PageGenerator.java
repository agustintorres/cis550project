
public class PageGenerator {
	
	public PageGenerator(){
	}
	
	public static  String getMenuBar(){
		String output ="";
		output += "<!-- Menu Bar -->";

		output += "<A HREF=\"/cis550-project/home\">Home</A> | <A HREF=\"register.html\">Register</A> | <A HREF=\"about.html\">About</A> | <A HREF=\"login.html\">Login</A> | <A HREF=\"/cis550-project/logout\">Logout</A> |";

		output += "<A HREF=\"publish.jsp\">Publish Stories</A> | <A HREF=\"viewfriends\">Friends</A> | <A HREF=\"addfriend.jsp\">Add Friends</A> | <A HREF=\"feed\">RSS feeds</A> | <A HREF=\"recommendations\">My Recommendations</A>";
		output += "| <A HREF=\"/cis550-project/cleandead\">Clean Dead Links</A> |";
		output += "<br><br>";
		
		return output;
	}

}
