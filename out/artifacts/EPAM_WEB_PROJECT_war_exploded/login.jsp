<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:bundle basename="html.text">
    <html>
    <head>
        <title>Title</title>
    </head>
    <body>
    <form action="userServlet" name="select language" method="post">
        <input type="hidden" value="change_language" name="command">
        <select name="language">
            <option disabled><fmt:message key="choose_language"/></option>
            <option value="en_US"><fmt:message key="english"/></option>
            <option value="ru_RU"><fmt:message key="russian"/></option>
        </select>
        <input type="hidden" value="/login.jsp" name="current_page">
        <input type="submit" value="Отправить">
    </form>
    <form action="userServlet" name="login" method="post">
        <input type="hidden" name="command" value="login">
        <input type="text" name="username" required="required">
        <input type="password" name="password" required="required">
        <input type="submit" value=
            <fmt:message key="login"/> name="login"/>
    </form>
    <p>${message}</p>
    <a href="registration.jsp"><fmt:message key="register"/></a>
    </body>
</fmt:bundle>
</html>
