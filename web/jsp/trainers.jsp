<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/project.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/membership.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/trainer.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Title</title>
</head>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:bundle basename="html.text">
    <body>
    <h1 class="m-4 almost-white-text"><fmt:message key="trainers"/></h1>
    <div class="m-top-70 d-flex justify-content-center">
        <c:forEach var="trainer" items="${trainers}">
            <div class="m-left-50 align-content-center trainer-card">
                <form action="userServlet" name="view_profile" method="post">
                    <input type="image" onclick="this.form.submit()" class="trainer-photo" src="images/${trainer.getPhotoReference()}"
                         alt="photo"/>
                    <input type="hidden" name="command" value="view_user_profile">
                    <input type="hidden" name="username" value=<c:out value="${trainer.getUsername()}"/>>
                </form>
                <div class="text-block">
                    <p>${trainer.getUsername()}</p>
                </div>
            </div>
        </c:forEach>
    </div>
    </body>
</fmt:bundle>
</html>
