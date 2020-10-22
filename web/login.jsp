<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:bundle basename="html.text">
    <html>
    <head>
        <title>Title</title>
        <link rel="stylesheet" type="text/css" href="css/basic_style.css"/>
        <link rel="stylesheet" type="text/css" href="css/input.css"/>
    </head>
    <body>
    <jsp:include page="navbar.jsp"/>
    <div>
        <img class="position-absolute" src="img/image2.png">
        <h1 class="header m-5"><fmt:message key="login"/></h1>
        <form action="userServlet" name="login" method="post">
            <input type="hidden" name="command" value="login">
            <p class="m-5"><input class="bg-dark" type="text" name="username"
                                  placeholder="<fmt:message key="username"/>"
                                  required="required"></p>
            <p class="m-5"><input class="bg-dark" type="password" name="password"
                                  placeholder="<fmt:message key="password"/>" required="required"></p>
            <p class="m-5"><input class="bg-dark" type="submit" value=
                <fmt:message key="login"/> name="login"/></p>
        </form>
        <p class="m-3">${message}</p>
    </div>
    </body>
</fmt:bundle>
</html>
