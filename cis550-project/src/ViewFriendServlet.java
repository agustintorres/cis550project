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
import javax.servlet.*;


public class ViewFriendServlet extends HttpServlet{


	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{

		HttpSession session = req.getSession(true);
		String uid1 = (String) session.getAttribute("username");

		PrintWriter out = resp.getWriter();

		out.println("<html>");
		out.println("<title>");
		out.println("</title>");
		out.println("<body>");
		out.println("<b>Your Friends</b><br><br>");
		out.println(getText(uid1, false));
		DatabaseReader dr = new DatabaseReader();
		if(dr.getFriendsByName(uid1, true) != null){
			out.println("<b>Pending Friends</b><br><br>");
			out.println(getText(uid1, true));
		}
		out.println("</body>");
		out.println("</html>");
		out.flush();


	}
	
	public static String getText(String uid1, boolean pending){
		
		String out = "";
		DatabaseReader dr = new DatabaseReader();
		LinkedList<User> users = dr.getFriendsByName(uid1, pending);
		
		if(pending == false){
		for(int x = 0; x < users.size(); x++){
			out += "Name: "+ users.get(x).getUsername()+ " <br>";
			out += "Age: "+ users.get(x).getBirthday()+ " <br>";
			out += "Location: "+users.get(x).getLocation()+ " <br>";
			out += "Profession: "+users.get(x).getProfession()+ " <br><br>";
		}		
		}else{
			
		out += "<form method=\"POST\" action=\"accept\"/>";
		out += "<select name = \"user\">";
		
		
			for(int x = 0; x < users.size(); x++){
				out += "<option value =\""+ users.get(x).getUsername()+"\">"+users.get(x).getUsername()+"</option>";
			
			}
			
		out += "</select> <br>";
		out += "<input type=\"submit\" value=\"Accept\" /> "+
		"</form>";
			
		}
		
		return out;
	}

}
