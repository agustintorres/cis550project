
public class PageGenerator {
	
	public PageGenerator(){
	}
	
	public static  String getMenuBar(){
		String output ="";
		output += "<!-- Menu Bar -->";
		output += "<A HREF=\"index.html\">Home</A> | <A HREF=\"register.html\">Register</A> | <A HREF=\"about.html\">About</A> | <A HREF=\"login.html\">Login</A> | <A HREF=\"logout.html\">Logout</A> |";
		output += "<A HREF=\"publish.jsp\">Publish Stories</A> | <A HREF=\"friends.html\">Friends</A> | <A HREF=\"addfriend.html\">Add Friends</A>";
		output += "<br><br>";
		
		return output;
	}

}
