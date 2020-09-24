<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:bundle basename="html.text">
    <head>
        <title>Title</title>
    </head>
    <body>
    <form action="userServlet" name="registration" method="post">
        <input type="hidden" name="command" value="registration">
        <input type="text" name="username" required="required">
        <input type="email" name="email" required="required">
        <input type="password" name="password" required="required">
        <input type="submit" value=
            <fmt:message key="register"/> name="registration"/>
    </form>
    <p>${message}</p>
    <a href="login.jsp"><fmt:message key="login"/></a>
    </body>
</fmt:bundle>
</html>
