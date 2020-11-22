<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:bundle basename="property.text">
    <head>
        <title>Title</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/input.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css"/>
    </head>
    <body>
    <div class="bg-dark m-top-70">
        <div class="row w-100 text-color-white">
            <div class="col-lg-2 m-left-200 col-md-6 col-sm-6">
                <h4 class="m-top-70"><fmt:message key="location"/></h4>
                <div>
                    <p><fmt:message key="city"/>: ${sessionScope.department_city}</p>
                    <p><fmt:message key="street"/>: ${sessionScope.department_street}</p>
                    <p><fmt:message key="house"/>: ${sessionScope.department_house}</p>
                </div>
            </div>
            <div class="col-lg-3 m-left-50 m-bot-30 col-md-6 col-sm-6">
                <img class="img-w-300-forward m-top-70" src="img/${sessionScope.department_picture_reference}">
            </div>
            <div class="col-lg-4 m-left-50 col-md-6 col-sm-6">
                <h4 class="m-top-70"><fmt:message key="contacts"/></h4>
                <p><fmt:message key="phone_number"/>: ${sessionScope.department_phone_number}</p>
                <p><fmt:message key="email"/>: ${sessionScope.department_email}</p>
            </div>
        </div>
    </div>
    </body>
</fmt:bundle>
</html>
