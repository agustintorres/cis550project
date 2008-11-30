import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
		
		out.println("<form method=\"POST\" action=\"SearchServlet\"/>" +
					"Search: <input name=\"searchText\" type=\"text\" /> <br>" +
					"<input type=\"submit\" value=\"Submit\" />" +
					"</form>" +
					"<br><br>");
		
		out.println(PageGenerator.getMenuBar());
		out.println("<br>");
		out.println(ViewStories.getText(10));

		
		
		out.println("</body>");
		out.println("</html>");
		out.flush();
		

	}

}