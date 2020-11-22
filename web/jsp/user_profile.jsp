<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:bundle basename="property.text">
    <head>
        <script type="text/javascript"
                src="${pageContext.request.contextPath}/js/project.js"></script>
        <title>Title</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/membership.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">
    </head>
    <body class="main-body">
    <jsp:include page="navbar.jsp"/>
    <div class="d-inline-block">
        <h1 class="m-left-170 m-top-70">${username}</h1>
        <c:if test="${is_profile_owner}">
            <form class="m-left-170" action="userServlet" method="post">
                <input type="hidden" name="command" value="go_to_redact_profile">
                <input type="hidden" name="username" value="${username}">
                <input type="hidden" name="description" value="${description}">
                <input class="inverse-color-hover-submit" type="submit" value="<fmt:message key="redact"/>">
            </form>
        </c:if>
        <c:if test="${!is_profile_owner and user_role.toString().equals(\"CLIENT\")}">
            <form class="m-left-170" action="userServlet" method="post">
                <input type="hidden" name="command" value="sign_for_coach">
                <input type="hidden" name="username" value="${username}">
                <input class="inverse-color-hover-submit" type="submit" value="<fmt:message key="sign"/>">
            </form>
        </c:if>
    </div>
    <div class="m-top-70 image-upload d-inline-block">
        <c:choose>
            <c:when test="${is_profile_owner}">
                <form class="float-left" action="FileUploadingServlet" enctype="multipart/form-data" method="post">
                    <label for="file-input">
                        <img alt="Submit" class="image" src="images/${photo_reference}">
                    </label>
                    <input class="d-none" onchange="this.form.submit()" id="file-input" type="file" accept=".png,.jpg"
                           name="photo_reference"/>
                </form>
            </c:when>
            <c:otherwise>
                <img class="image" src="images/${photo_reference}">
            </c:otherwise>
        </c:choose>
        <div class="m-left-170 user-description">
                ${description}
        </div>
    </div>
    <c:if test="${(user_role.toString().equals(\"CLIENT\") and is_profile_owner) or
      (user_role.toString().equals(\"ADMIN\") and !is_profile_owner)}">
        <h1 class="m-left-170 m-top-70"><fmt:message key="current_membership"/></h1>
        <c:choose>
            <c:when test="${membership != null}">
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
                            <div class="m-3"><fmt:message
                                    key="amount_of_visits"/>: ${membership.getAmountOfAttendees()}</div>
                            <div class="filled-square"></div>
                            <div class="m-3">${membership.getPrice()} BYN</div>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <p class="m-left-170 m-top-70"><fmt:message key="no_memberships"/></p>
            </c:otherwise>
        </c:choose>
        <div class="m-left-170 m-top-70 h-450">
            <jsp:include page="trainers.jsp"/>
        </div>
    </c:if>
    <jsp:include page="footer.jsp"/>
    </body>
</fmt:bundle>
</html>
