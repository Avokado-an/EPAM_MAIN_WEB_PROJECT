<%--
  Created by IntelliJ IDEA.
  User: Mi
  Date: 01.09.2020
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="userServlet" name="registration" method="post">
    <input type="hidden" name="command" value="registration">
    <input type="text" name="username">
    <input type="password" name="password">
    <input type="submit" value="registration" name="registration"/>
</form>
<p>${message}</p>
<a href="login.jsp">login</a>
</body>
</html>
