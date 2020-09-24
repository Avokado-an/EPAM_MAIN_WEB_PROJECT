<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:bundle basename="resources.html">
<fmt:message key="verify_account"/>
<form action="userServlet" name="verification" method="post">
    <input type="hidden" name="command" value="verify_account">
    <input type="hidden" name="username" value="<%= request.getParameter("username") %>">
    <input type="submit" value=
        <fmt:message key="verify"/> name="verification"/>
</form>
</body>
</fmt:bundle>
</html>
