<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:bundle basename="html.text">
    <head>
        <title>Title</title>
        <link rel="stylesheet" type="text/css" href="css/table_outlook.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">
    </head>
    <body>
    <jsp:include page="navbar.jsp"/>
        ${message}
    <h1><fmt:message key="user_list"/></h1></caption>
    <div align="center">
        <table border="0" cellpadding="5">
            <tr>
                <th><fmt:message key="username"/></th>
                <th><fmt:message key="is_active"/></th>
                <c:choose>
                    <c:when test="${user_role.toString().equals(\"ADMIN\")}">
                        <th><fmt:message key="user_role"/></th>
                        <th><fmt:message key="set_activity"/></th>
                        <th><fmt:message key="change_user_role"/></th>
                    </c:when>
                </c:choose>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${user.getUsername()}"/></td>
                    <td><c:out value="${user.isActive()}"/></td>
                    <td><c:out value="${user.getType().toString()}"/></td>
                    <c:choose>
                        <c:when test="${user.isActive() and user_role.toString().equals(\"ADMIN\")}">
                            <td>
                                <form action="userServlet" name="logout" method="post">
                                    <input type="hidden" name="command" value="block_user">
                                    <input type="hidden" value="${user.getUsername()}" name="username"/>
                                    <input class="bg-dark" type="submit" value=<fmt:message key="block_user"/>>
                                </form>
                            </td>
                        </c:when>
                        <c:when test="${!user.isActive() and user_role.toString().equals(\"ADMIN\")}">
                            <td>
                                <form action="userServlet" name="logout" method="post">
                                    <input type="hidden" name="command" value="unblock_user">
                                    <input type="hidden" value="${user.getUsername()}" name="username"/>
                                    <input class="bg-dark" type="submit" value=<fmt:message key="unblock_user"/>>
                                </form>
                            </td>
                        </c:when>
                    </c:choose>
                    <c:choose>
                        <c:when test="${user.getType().toString().equals(\"CLIENT\") and user_role.toString().equals(\"ADMIN\")}">
                            <td>
                                <form action="userServlet" name="logout" method="post">
                                    <input type="hidden" name="command" value="mark_user_as_trainer">
                                    <input type="hidden" value="${user.getUsername()}" name="username"/>
                                    <input class="bg-dark" type="submit" value=<fmt:message key="trainer"/>>
                                </form>
                            </td>
                        </c:when>
                        <c:when test="${user.getType().toString().equals(\"TRAINER\") and user_role.toString().equals(\"ADMIN\")}">
                            <td>
                                <form action="userServlet" name="logout" method="post">
                                    <input type="hidden" name="command" value="mark_trainer_as_user">
                                    <input type="hidden" value="${user.getUsername()}" name="username"/>
                                    <input class="bg-dark" type="submit" value=<fmt:message key="user"/>>
                                </form>
                            </td>
                        </c:when>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
    </div>
    </body>
</fmt:bundle>
</html>
