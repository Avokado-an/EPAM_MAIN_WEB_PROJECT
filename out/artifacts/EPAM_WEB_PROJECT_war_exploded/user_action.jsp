<%--
  Created by IntelliJ IDEA.
  User: Mi
  Date: 01.09.2020
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>${username}</p>
<form action="userServlet" name="logout" method="post">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="logout" name="logout"/>
</form>
</body>
</html>
