<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:bundle basename="html.text">
    <head>
        <title>Title</title>
        <link rel="stylesheet" type="text/css" href="css/basic_style.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">
    </head>
    <body>
    <div>
        <img class="position-absolute" src="img/image1.png">
        <jsp:include page="navbar.jsp"/>
            ${message}
        <div align="center">
            <jsp:include page="abonements.jsp"/>
        </div>
        <div align="center">
            <jsp:include page="trainers.jsp"/>
        </div>
    </div>
    </body>
</fmt:bundle>
</html>
