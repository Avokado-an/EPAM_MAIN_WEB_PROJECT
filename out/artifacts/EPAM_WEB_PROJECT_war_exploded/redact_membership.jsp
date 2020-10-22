<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:bundle basename="html.text">
    <head>
        <title>Title</title>
        <link rel="stylesheet" type="text/css" href="css/basic_style.css"/>
        <link rel="stylesheet" type="text/css" href="css/input.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">
    </head>
    <body>
    <jsp:include page="navbar.jsp"/>
    <h1 class="m-5"><fmt:message key="redact"/></h1>
    <form action="userServlet" name="redact" method="post">
        <input type="hidden" name="command" value="redact_membership">
        <p><input class="bg-dark m-3" type="text" placeholder="<fmt:message key="title"/>" name="title"
                  required="required"></p>
        <p><input class="bg-dark m-3" type="number" placeholder="<fmt:message key="price"/>" name="price"
                  required="required"></p>
        <p><input class="bg-dark m-3" type="text" placeholder="<fmt:message key="amount_of_visits"/>"
                  name="amount_of_visits" required="required"></p>
        <p><input class="bg-dark m-3" type="text" placeholder="<fmt:message key="month"/>"
                  name="months" required="required"></p>
        <p><input class="bg-dark m-3" type="submit" value=
            <fmt:message key="redact"/> name="redact"/></p>
    </form>
    <p>${message}</p>
    </body>
</fmt:bundle>
</html>
