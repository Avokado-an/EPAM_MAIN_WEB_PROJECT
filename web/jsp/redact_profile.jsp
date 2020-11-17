<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:bundle basename="html.text">
    <head>
        <script type="text/javascript"
                src="${pageContext.request.contextPath}/js/project.js"></script>
        <title>Title</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basic_style.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/input.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">
    </head>
    <body>
    <jsp:include page="navbar.jsp"/>
    <h1 class="m-5"><fmt:message key="redact"/></h1>
    <form action="userServlet" name="redact" method="post">
        <input type="hidden" name="command" value="redact_user_profile">
        <p><input class="bg-dark m-3" type="text" value="${username}" name="username" pattern="\w{4,29}"
                  onkeypress="this.style.height = ((this.value.height + 1) * 8) + 'px';" required="required"
                  title="<fmt:message key="username_password_characters"/>"></p>
        <p><textarea style="color: whitesmoke" rows="10" cols="80" class="bg-dark m-3 text-color-white" type="text"
                     placeholder="<fmt:message key="about_me"/>"
                     name="description">${description}</textarea></p>
        <p><input class="bg-dark m-3" type="submit" value=
            <fmt:message key="redact"/> name="redact"/></p>
    </form>
    <p>${message}</p>
    </body>
</fmt:bundle>
</html>
