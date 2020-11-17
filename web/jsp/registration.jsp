<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:bundle basename="html.text">
    <head>
        <script type="text/javascript"
                src="${pageContext.request.contextPath}/js/project.js"></script>
        <title>Title</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basic_style.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/input.css"/>
    </head>
    <body class="bg-dark">
    <jsp:include page="navbar.jsp"/>
    <div>
        <img class="position-absolute w-100" src="img/image2.png">
        <h1 class="header m-5"><fmt:message key="register"/></h1>
        <form action="userServlet" name="registration" method="post">
            <input type="hidden" name="command" value="registration">
            <p class="m-5"><input class="bg-dark" type="text" placeholder="<fmt:message key="username"/>"
                                  name="username" pattern="\w{4,29}"
                                  required="required" title="<fmt:message key="username_password_characters"/>"></p>
            <p class="m-5"><input class="bg-dark" type="email" placeholder="<fmt:message key="email"/>" name="email"
                                  required="required" pattern="^[A-Za-z0-9+_.-]+@[A-Za-z0-9]+\.[A-Za-z0-9]+$"
                                  title="<fmt:message key="email_characters"/>"></p>
            <p class="m-5"><input class="bg-dark" type="password" placeholder="<fmt:message key="password"/>"
                                  name="password" pattern="\w{4,29}"
                                  required="required" title="<fmt:message key="username_password_characters"/>"></p>
            <p class="m-5"><input class="bg-dark" type="submit" value=
                <fmt:message key="register"/> name="registration"/></p>
        </form>
        <p class="m-5">${message}</p>
    </div>
    </body>
</fmt:bundle>
</html>
