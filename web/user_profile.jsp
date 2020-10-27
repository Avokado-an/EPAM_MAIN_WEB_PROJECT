<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:bundle basename="html.text">
    <head>
        <title>Title</title>
        <link rel="stylesheet" type="text/css" href="css/profile.css"/>
        <link rel="stylesheet" type="text/css" href="css/membership.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">
    </head>
    <body class="main-body">
    <jsp:include page="navbar.jsp"/>
    <div class="d-inline-block">
        <h1 class="m-left-170 m-top-70">${username}</h1>
        <form class="m-left-170" action="userServlet" method="post">
            <input type="hidden" name="command" value="go_to_redact_profile">
            <input type="hidden" name="username" value="${username}">
            <input type="hidden" name="description" value="${description}">
            <input class="dark-submit" type="submit" value="<fmt:message key="redact"/>">
        </form>
    </div>
    <div class="m-top-70 image-upload d-inline-block">
        <form action="FileUploadingServlet" enctype="multipart/form-data" method="post">
            <label for="file-input">
                <img alt="Submit" class="image" src="../../../">
            </label>
            <input class="d-none" onchange="this.form.submit()" id="file-input" type="file" name="photo_reference"/>
        </form>
        <div class="user-description">
                ${description}
        </div>
    </div>
    <h2 class="m-left-170 m-top-70"><fmt:message key="current_membership"/></h2>
    <div align="center" class="d-flex m-left-170 m-top-70">
        <div class="align-content-center d-inline-block">
            <div class="membership-card m-3">
                <div class="hollow-square m-5">
                    <div class="month-amount">${membership.getMonths()}</div>
                </div>
                <div class="m-3"><fmt:message key="month"/></div>
                <div class="filled-square"></div>
                <div class="m-3">${membership.getName()}</div>
                <div class="filled-square"></div>
                <div class="m-3"><fmt:message key="amount_of_visits"/>: ${membership.getAmountOfAttendees()}</div>
                <div class="filled-square"></div>
                <div class="m-3">${membership.getPrice()} BYN</div>
            </div>
        </div>
    </div>
    <h2 class="m-left-170 m-top-70"><fmt:message key="current_trainers"/></h2>
    <div class="m-left-170 m-top-70 d-flex justify-content-center">
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
