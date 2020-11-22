<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:bundle basename="property.text">
    <head>
        <title>Title</title>
        <script type="text/javascript"
                src="${pageContext.request.contextPath}/js/project.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basic_style.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">
    </head>
    <body style="background-color: rgba(11, 16, 28, 1);">
    <div>
        <div class="h-1250">
            <img class="position-absolute w-100 img-abs" src="img/image1.png">
            <jsp:include page="navbar.jsp"/>
            <div class="text-color-white m-3">
                    ${message}
            </div>
            <div align="center">
                <jsp:include page="abonements.jsp"/>
            </div>
        </div>
        <div align="center" class="m-top-70 h-450">
            <jsp:include page="trainers.jsp"/>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
    </body>
</fmt:bundle>
</html>
