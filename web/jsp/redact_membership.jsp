<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:bundle basename="property.text">
    <head>
        <script type="text/javascript"
                src="${pageContext.request.contextPath}/js/project.js"></script>
        <title>Title</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basic_style.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/input.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/toggle.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">
    </head>
    <body>
    <jsp:include page="navbar.jsp"/>
    <h1 class="m-5"><fmt:message key="membership_data"/></h1>
    <form action="userServlet" name="redact" method="post">
        <input type="hidden" name="command" value="${next_command}">
        <jstl:choose>
            <jstl:when test="${!title.isEmpty() and title != null}">
                <p><input class="bg-dark m-3" type="text" value="${title}" name="title"
                          required="required" pattern="\w{4,29}" title="<fmt:message key="username_password_characters"/>"></p>
                <p><input class="bg-dark m-3" type="number" value="${price}" name="price"
                          required="required"></p>
                <p><input class="bg-dark m-3" type="text" value="${amount_of_visits}"
                          name="amount_of_visits" required="required"
                          pattern="\d{1,2}|∞" title="<fmt:message key="membership_attendees_characters"/>"></p>
                <p><input class="bg-dark m-3" type="number" value="${months}"
                          name="months" required="required"></p>
            </jstl:when>
            <jstl:otherwise>
                <p><input class="bg-dark m-3" type="text" placeholder="<fmt:message key="title"/>" name="title"
                          required="required" pattern="\w{4,29}" title="<fmt:message key="username_password_characters"/>"></p>
                <p><input class="bg-dark m-3" type="number" placeholder="<fmt:message key="price"/>" name="price"
                          required="required"></p>
                <p><input class="bg-dark m-3" type="text" placeholder="<fmt:message key="amount_of_visits"/>"
                          name="amount_of_visits" required="required" pattern="\d{1,2}|∞"
                          title="<fmt:message key="membership_attendees_characters"/>"></p>
                <p><input class="bg-dark m-3" type="number" placeholder="<fmt:message key="month"/>"
                          name="months" required="required"></p>
            </jstl:otherwise>
        </jstl:choose>
        <p class="m-3">
            <fmt:message key="is_active"/>:
            <label class="switch">
                <input type="checkbox" onclick="this.value = !this.value" name="is_active" value="${true}" checked>
                <span class="slider round"></span>
            </label>
        </p>
        <p><input class="bg-dark m-3" type="submit" value=
            <fmt:message key="submit"/> name="redact"/></p>
    </form>
    <p>${message}</p>
    </body>
</fmt:bundle>
</html>
