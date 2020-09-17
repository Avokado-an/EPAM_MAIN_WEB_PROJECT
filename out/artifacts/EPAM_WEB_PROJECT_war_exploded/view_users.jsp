<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mi
  Date: 02.09.2020
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${message}
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of users</h2></caption>
        <tr>
            <th>Username</th>
            <th>Is active</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><c:out value="${user.getUsername()}"/></td>
                <td><c:out value="${user.isActive()}"/></td>
                <td>
                    <form action="userServlet" name="logout" method="post">
                        <input type="hidden" name="command" value="block_user">
                        <input type="hidden" value="${user.getUsername()}" name="username"/>
                        <input type="submit" value="block user">
                    </form>
                </td>
                <td>
                    <form action="userServlet" name="logout" method="post">
                        <input type="hidden" name="command" value="unblock_user">
                        <input type="hidden" value="${user.getUsername()}" name="username"/>
                        <input type="submit" value="unblock user">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<form action="userServlet" name="logout" method="post">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="logout" name="logout"/>
</form>
</body>
</html>
