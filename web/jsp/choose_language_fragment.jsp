<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:bundle basename="html.text">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
        <title>Title</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dropdown_menu.css"/>
    </head>
    <body>
    <form action="userServlet" name="select language" method="post">
        <input type="hidden" value="change_language" name="command">
        <select class="dark-navbar" onchange="this.form.submit()" name="language">
            <option><fmt:message key="choose_language"/></option>
            <option value="en_US"><fmt:message key="english"/></option>
            <option value="ru_RU"><fmt:message key="russian"/></option>
        </select>
    </form>
    </body>
</fmt:bundle>
</html>
