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
<form action="userServlet" name="login" method="post">
    <input type="hidden" name="command" value="login">
    <input type="text" name="username">
    <input type="password" name="password">
    <input type="submit" value="login" name="login"/>
</form>
<p>${message}</p>
<a href="registration.jsp">register</a>
</body>
</html>
