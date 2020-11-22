<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:bundle basename="property.text">

    <html>
    <head>
        <title>Title</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">
    </head>
    <body>
    <div class="h-1250">
        <img style="z-index: -1" class="position-absolute w-100" src="${pageContext.request.contextPath}/img/image2.png">
        <h1 class="m-left-170 header"><fmt:message key="404_error_message"/></h1>
        <form action="${pageContext.request.contextPath}/userServlet" name="view main" method="post">
            <input type="hidden" name="command" value="view_main_page">
            <input type="submit" class="mx-3 dark-navbar nav-input" value=
                <fmt:message key="gym"/>>
        </form>
    </div>
    <jsp:include page="footer.jsp"/>
    </body>
</fmt:bundle>
</html>

