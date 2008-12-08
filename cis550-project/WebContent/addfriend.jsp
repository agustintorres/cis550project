<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>

<% 
	//This is a scriptlet
	if(session.isNew() || session.getAttribute("username") == null) {
		//not logged in, so redirect him to login page
		out.println("<meta http-equiv=\"REFRESH\" content=\"0; url=/cis550-project/login.html\">");
	} else {
		//already logged in, so do nothing
	}
%>

</head>
<body>


<!-- Menu Bar -->
<A HREF="/cis550-project/home">Home</A> | <A HREF="register.jsp">Register</A> | <A HREF="about.html">About</A> | <A HREF="login.html">Login</A> | <A HREF="logout">Logout</A> |
<A HREF="publish.jsp">Publish Stories</A> | <A HREF="viewfriends">Friends</A> | <A HREF="addfriend.jsp">Add Friends</A> | <A HREF="feed">RSS Feed</A> | <A HREF="recommendations">My Recommendations</A>
<br><br>


<p>Add a Friend</p>
<form method="POST" action="addfriend"/>
Your username: <%=session.getAttribute("username")%> <br>
Username of your friend: <input name="uid2" type="text" /> <br>
<input type="submit" value="Submit" /> <br>
</form>


</body>
</html>