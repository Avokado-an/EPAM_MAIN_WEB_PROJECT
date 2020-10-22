<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:bundle basename="html.text">
    <head>
        <title>Title</title>
        <link rel="stylesheet" type="text/css" href="css/basic_style.css"/>
        <link rel="stylesheet" type="text/css" href="css/input.css"/>
    </head>
    <body>
    <jsp:include page="navbar.jsp"/>
    <div>
        <img class="position-absolute" src="img/image2.png">
        <h1 class="header m-5"><fmt:message key="register"/></h1>
        <form action="userServlet" name="registration" method="post">
            <input type="hidden" name="command" value="registration">
            <p class="m-5"><input class="bg-dark" type="text" placeholder="<fmt:message key="username"/>"
                                  name="username"
                                  required="required"></p>
            <p class="m-5"><input class="bg-dark" type="email" placeholder="<fmt:message key="email"/>" name="email"
                                  required="required"></p>
            <p class="m-5"><input class="bg-dark" type="password" placeholder="<fmt:message key="password"/>"
                                  name="password"
                                  required="required"></p>
            <p class="m-5"><input class="bg-dark" type="submit" value=
                <fmt:message key="register"/> name="registration"/></p>
        </form>
        <p class="m-5">${message}</p>
    </div>
    </body>
</fmt:bundle>
</html>
