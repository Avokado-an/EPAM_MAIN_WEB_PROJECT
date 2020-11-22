<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:bundle basename="property.text">
    <head>
        <script type="text/javascript"
                src="${pageContext.request.contextPath}/js/project.js"></script>
        <title>Title</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basic_style.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/input.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">
    </head>
    <body>
    <jsp:include page="navbar.jsp"/>
    <h1 class="m-5"><fmt:message key="add_money"/></h1>
    <form action="userServlet" name="replenish" method="post">
        <input type="hidden" name="command" value="replenish_money_account">
        <p><input class="bg-dark m-3" type="number" name="money" required="required"></p>
        <p><input class="bg-dark m-3" type="submit" value=
            <fmt:message key="add_money"/> name="redact"/></p>
    </form>
    <p>${message}</p>
    </body>
</fmt:bundle>
</html>
