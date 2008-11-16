<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<!--    <title><s:text name="HelloWorld.message"/></title> -->
</head>

<body>
<!-- <h2><s:property value="message"/></h2> -->
<!--
<p>Stories</p>
<s:if test="stories.size > 0">
	<table>
		<s:iterator value="stories">
			<tr id="row_<s:property value="id"/>">
				<td>
					<s:property value="firstName" />
				</td>
				<td>
					<s:property value="lastName" />
				</td>
				<td>
					<s:url id="removeUrl" action="remove">
						<s:param name="id" value="id" />
					</s:url>
					<s:a href="%{removeUrl}" theme="ajax" targets="persons">Remove</s:a>
					<s:a id="a_%{id}" theme="ajax" notifyTopics="/edit">Edit</s:a>
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>
-->



<table class="cust">  
    <tr>  
        <th>Title</th>  
        <th>Description</th>  
        <th>URL</th>
        <th>Category</th>  
        <th>Name</th>  
        <th>isPrivate</th>
    </tr>  
    <s:iterator value="stories" id="story" status="cust_stat">  
        <tr class="<s:if test="#cust_stat.odd == true">odd</s:if><s:else>even</s:else>">  
            <td><s:property value="title" /></td>  
            <td><s:property value="description" /></td>  
            <td><s:property value="url" /></td>  
            <td><s:property value="category" /></td>  
            <td><s:property value="name" /></td>  
            <td><s:property value="isPrivate" /></td> 
        </tr>  
    </s:iterator>  
</table>  

<h3>Languages</h3>
<ul>
    <li>
        <s:url id="url" action="HelloWorld">
            <s:param name="request_locale">en</s:param>
        </s:url>
        <s:a href="%{url}">English</s:a>
    </li>
    <li>
        <s:url id="url" action="HelloWorld">
            <s:param name="request_locale">es</s:param>
        </s:url>
        <s:a href="%{url}">Espanol</s:a>
    </li>
</ul>

</body>
</html>
