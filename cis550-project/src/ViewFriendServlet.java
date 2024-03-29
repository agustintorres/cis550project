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
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Locale;



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
		out.println("<head>");
		out.println("<title>");
		out.println("Your Friends");
		out.println("</title>");
		
		String username = "notloggedin";
		boolean loggedin = false;
		if(session.isNew() || session.getAttribute("username") == null) {
			//not logged in, redirect
			out.println("<meta http-equiv=\"REFRESH\" content=\"3; url=/cis550-project/login.html\">");
		} else {
			//dont redirect (ie dont do anything)
			loggedin = true;
		}
		
		out.println("</head>");
		out.println("<body>");
		
		if (!loggedin) {
			out.println("<p>Please log in so that you can view your friends.</p>");
			out.println("<p>You will now be redirected to the login page.</p>");
		} else {
			out.println(PageGenerator.getMenuBar());
			out.println("<b>Your Friends</b><br><br>");
			out.println(getText(uid1, false));
			DatabaseReader dr = new DatabaseReader();
			if(dr.getFriendsByName(uid1, true) != null){
				out.println("<b>Pending Friends</b><br><br>");
				out.println(getText(uid1, true));
			}
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
			out += "Age: "+ getAgeByDate(users.get(x).getBirthday())+ " <br>";
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
	
	public static int getAgeByDate(String bday){
		int result = 0;	
		SimpleDateFormat dateF = new SimpleDateFormat("yyyy-MM-dd");
		try {
		Date birthday = dateF.parse(bday);
		Calendar age = Calendar.getInstance(Locale.getDefault());
		age.setTimeInMillis(Math.abs(birthday.getTime()-System.currentTimeMillis()));
		result = age.get(Calendar.YEAR)-1970;
		
		if(age.get(Calendar.MONTH) > 0 || age.get(Calendar.DAY_OF_MONTH) > 0){
			result++;
		}
		
		return result;
		
		} catch (ParseException e){
			e.printStackTrace();
			return 0;
		}
		
		
	}
	
}
