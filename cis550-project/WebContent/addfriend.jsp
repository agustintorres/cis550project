<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
</head>
<body>

<!-- Menu Bar -->
<A HREF="index.jsp">Home</A> | <A HREF="register.html">Register</A> | <A HREF="about.html">About</A> | <A HREF="login.html">Login</A> | <A HREF="logout">Logout</A> |
<A HREF="publish.jsp">Publish Stories</A> | <A HREF="friends.html">Friends</A> | <A HREF="addfriend.jsp">Add Friends</A>
<br><br>

<p>Add a Friend</p>
<form method="POST" action="addfriend"/>
Your username: <%=session.getAttribute("username")%> <br>
Username of your friend: <input name="uid2" type="text" /> <br>
<input type="submit" value="Submit" /> <br>
</form>


</body>
</html>