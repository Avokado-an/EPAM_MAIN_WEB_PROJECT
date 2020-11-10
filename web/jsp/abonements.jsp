<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/membership.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Title</title>
</head>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:bundle basename="html.text">
    <body>
    <h1 class="m-4 almost-white-text"><fmt:message key="list_of_memberships"/></h1>
    <c:if test="${user_role.toString().equals(\"ADMIN\")}">
        <div class="m-3">
            <form action="userServlet" method="post">
                <input type="hidden" name="command" value="go_to_add_membership">
                <input type="hidden" value="${membership.getId()}" name="id"/>
                <input class="input-button" type="submit" value=<fmt:message key="add"/>>
            </form>
        </div>
    </c:if>
    <div class="m-4 d-flex justify-content-center">
        <c:forEach  var="membership" items="${memberships}">
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
                <c:if test="${user_role.toString().equals(\"CLIENT\")}">
                    <div class="m-3">
                        <form action="userServlet" method="post">
                            <input type="hidden" name="command" value="purchase_membership">
                            <input type="hidden" value="${membership.getId()}" name="id"/>
                            <input class="input-button" type="submit" value=<fmt:message key="purchase"/>>
                        </form>
                    </div>
                </c:if>
                <c:if test="${user_role.toString().equals(\"ADMIN\")}">
                    <div class="m-3">
                        <form action="userServlet" method="post">
                            <input type="hidden" name="command" value="go_to_redact_membership">
                            <input type="hidden" value="${membership.getId()}" name="id"/>
                            <input class="input-button" type="submit" value=<fmt:message key="redact"/>>
                        </form>
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </div>
    </body>
</fmt:bundle>
</html>
