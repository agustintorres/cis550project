<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Publish a Story</title>
</head>
<body>

<!-- Menu Bar -->
<A HREF="index.jsp">Home</A> | <A HREF="register.html">Register</A> | <A HREF="about.html">About</A> | <A HREF="login.html">Login</A> | <A HREF="logout">Logout</A> |
<A HREF="publish.jsp">Publish Stories</A> | <A HREF="friends.html">Friends</A> | <A HREF="addfriend.html">Add Friends</A>
<br><br>

<p>Publish Story</p>
<form method="POST" action="StoryServlet"/>
Title: <input name="title" type="text" /> <br>
URL: <input name="url" type="text" /> <br>
Description: <br><textarea name="description" cols = 40 rows = 10 /> </textarea> <br>
name: <input name="name" type="text" /> <br>
Category:
<select name = "category">
  <option value ="News">News</option>
  <option value ="Sports">Sports</option>
  <option value ="Politics">Politics</option>
  <option value ="Misc">Misc</option>
</select> <br>

Private:
<input type="checkbox" name="private" value="private" /> 



<input type="submit" value="Submit" /> <br>
</form>

</body>
</html>