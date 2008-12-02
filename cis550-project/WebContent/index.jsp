<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome!</title>
<meta http-equiv="REFRESH" content="0;url=/cis550-project/home">
</head>
<body>

<% 
	//This is a scriptlet
	if(session.isNew() || session.getAttribute("username") == null) {
		out.println("<p>Welcome!</p>");
	} else {
		out.println("<p> Welcome, " + session.getAttribute("username") + "! </p>");
	}
%>

<p>The time is now <%= new java.util.Date() %></p>

<!-- Menu Bar -->
<A HREF="index.jsp">Home</A> | <A HREF="register.html">Register</A> | <A HREF="about.html">About</A> | <A HREF="login.html">Login</A> | <A HREF="logout">Logout</A> |
<A HREF="publish.jsp">Publish Stories</A> | <A HREF="friends.html">Friends</A> | <A HREF="addfriend.jsp">Add Friends</A>
<br><br>

<form method="POST" action="SearchServlet"/>
Search: <input name="searchtext" type="text" /> <br>
<input type="submit" value="Submit" />
</form>
<br><br>

<b>Top 5 Stories:</b><br><br>

<A HREF="http://consumerist.com/5074161/breaking-circuit-city-closing-155-stores">Breaking: Circuit City Closing 155 Stores!</A> <br>
By: <A HREF="user.html">user123</A> <br>
I guess if their stock is trading at 0.26 cents, then I guess this is what happens.... <br>
<A HREF="comments.html">Comments (193)</A>      <A HREF="vote.html">Vote!</A>

<br><br>

<A HREF="http://consumerist.com/5074161/breaking-circuit-city-closing-155-stores">Trees Are Nature's Climate Air Conditioners, Study Finds </A> <br>
By: <A HREF="user.html">user124</A> <br>
If you're reading this, I probably don't need to waste my time trying to convince you that trees are great. They absorb carbon dioxide, they can be used to power small remote sensors and they're pretty darn nice to look at too. <br>
<A HREF="comments.html">Comments (155)</A>      <A HREF="vote.html">Vote!</A>

<br><br>

<A HREF="http://consumerist.com/5074161/breaking-circuit-city-closing-155-stores">NYC Green Taxi Program Red Lighted by Federal Judge</A> <br>
By: <A HREF="user.html">user125</A> <br>
A federal judge has stopped Mayor Bloomberg's attempt to clean up the air in New York City by using fuel-efficient hybrid taxis. <br>
<A HREF="comments.html">Comments (154)</A>      <A HREF="vote.html">Vote!</A>

<br><br>

<A HREF="http://consumerist.com/5074161/breaking-circuit-city-closing-155-stores">The greatest defunct Web sites and dotcom disasters</A> <br>
By: <A HREF="user.html">user126</A> <br>
At the turn of the Millenium the Internet burst out of academia and hobbyism in a volcano of money, sex and possibility. <br>
<A HREF="comments.html">Comments (100)</A>      <A HREF="vote.html">Vote!</A>

<br><br>

<A HREF="http://consumerist.com/5074161/breaking-circuit-city-closing-155-stores">FOX Buries Cheney Endorsement</A> <br>
By: <A HREF="user.html">user127</A> <br>
As you probably know, Dick Cheney dealt perhaps the final blow to John McCain's campaign earlier today by offering up his endorsement of the McCain-Palin ticket. If you happened to be a FOX News viewer, however, you'd have been lucky to find out. <br>
<A HREF="comments.html">Comments (0)</A>      <A HREF="vote.html">Vote!</A>

</body>
</html>