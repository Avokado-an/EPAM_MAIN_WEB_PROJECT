<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/membership.css"/>
    <link rel="stylesheet" type="text/css" href="css/trainer.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Title</title>
</head>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:bundle basename="html.text">
    <body>
    <h1 class="m-4 almost-white-text"><fmt:message key="trainers"/></h1>
    <div class="m-4 d-flex justify-content-center">
        <c:forEach var="trainer" items="${trainers}">
            <div class="align-content-center d-inline-block">
                <div class="m-3">
                    <div>${trainer.getUsername()}</div>
                </div>
            </div>
        </c:forEach>
    </div>
    </body>
</fmt:bundle>
</html>
