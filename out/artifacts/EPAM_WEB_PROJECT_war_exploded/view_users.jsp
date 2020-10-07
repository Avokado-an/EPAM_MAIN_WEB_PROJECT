<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:bundle basename="html.text">
    <head>
        <title>Title</title>
    </head>
    <body>
        ${message}
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2><fmt:message key="user_list"/></h2></caption>
            <tr>
                <th><fmt:message key="username"/></th>
                <th><fmt:message key="is_active"/></th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${user.getUsername()}"/></td>
                    <td><c:out value="${user.isActive()}"/></td>
                    <td>
                        <form action="userServlet" name="logout" method="post">
                            <input type="hidden" name="command" value="block_user">
                            <input type="hidden" value="${user.getUsername()}" name="username"/>
                            <input type="submit" value=<fmt:message key="block_user"/>>
                        </form>
                    </td>
                    <td>
                        <form action="userServlet" name="logout" method="post">
                            <input type="hidden" name="command" value="unblock_user">
                            <input type="hidden" value="${user.getUsername()}" name="username"/>
                            <input type="submit" value=<fmt:message key="unblock_user"/>>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <form action="userServlet" name="logout" method="post">
        <input type="hidden" name="command" value="logout">
        <input type="submit" value=
            <fmt:message key="logout"/> name="logout"/>
    </form>
    </body>
</fmt:bundle>
</html>
