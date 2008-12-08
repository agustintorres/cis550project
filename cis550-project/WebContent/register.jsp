<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>

<% 
	//This is a scriptlet
	if(session.isNew() || session.getAttribute("username") == null) {
		//not logged in, so allow him to register
	} else {
		//already logged in, so he can't register, 
		out.println("<meta http-equiv=\"REFRESH\" content=\"0; url=/cis550-project\">");
	}
%>

</head>
<body>

<!-- Menu Bar -->
<A HREF="/cis550-project/home">Home</A> | <A HREF="register.jsp">Register</A> | <A HREF="about.html">About</A> | <A HREF="login.html">Login</A> | <A HREF="logout">Logout</A> |
<A HREF="publish.jsp">Publish Stories</A> | <A HREF="viewfriends">Friends</A> | <A HREF="addfriend.jsp">Add Friends</A> | <A HREF="feed">RSS Feed</A> | <A HREF="recommendations">My Recommendations</A>
<br><br>

<p>Registration</p>
<form method="POST" action="register"/>
Choose a Username: <input name="username" type="text" /> <br>

Password: <input name="password" type="password" /> <br>

Birthday (yyyy-mm-dd, e.g: 1987-07-21): <input name="birthday" type="text" /> <br>
Location: <input name="location" type="text" /> <br>
Profession: <input name="profession" type="text" /> <br>
<input type="submit" value="Submit" /> <br>
</form>


</body>
</html>