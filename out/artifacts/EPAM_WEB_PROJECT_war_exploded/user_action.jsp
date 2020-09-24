<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:bundle basename="html.text">
    <head>
        <title>Title</title>
    </head>
    <body>
    <p>${username}</p>
    <form action="userServlet" name="logout" method="post">
        <input type="hidden" name="command" value="logout">
        <input type="submit" value=<fmt:message key="logout"/> name="logout"/>
    </form>
    </body>
</fmt:bundle>
</html>
