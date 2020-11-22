<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pt" uri="paginationTag" %>
<%@ taglib prefix="ct" uri="paginationTag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:bundle basename="property.text">
    <head>
        <script type="text/javascript"
                src="${pageContext.request.contextPath}/js/project.js"></script>
        <title>Title</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table_outlook.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">
    </head>
    <body>
    <jsp:include page="navbar.jsp"/>
        ${message}
    <h1 class="m-left-200 m-top-30"><fmt:message key="user_list"/></h1>
    <div class="d-inline-block">
        <form action="userServlet" name="find_user" method="post">
            <input type="hidden" name="command" value="find_users">
            <input class="m-left-200 bg-dark m-top-30" type="text" name="username" placeholder=<fmt:message
                    key="username"/>>
        </form>
        <form class="m-left-200 m-top-30" action="userServlet" name="sort_users" method="post">
            <input type="hidden" name="command" value="sort_users">
            <select class="bg-dark" onchange="this.form.submit()" name="field_to_compare">
                <option><fmt:message key="sort"/></option>
                <option value="name"><fmt:message key="username"/></option>
                <option value="is_active"><fmt:message key="activity"/></option>
                <c:if test="${user_role.toString().equals(\"ADMIN\")}">
                    <option value="type_id"><fmt:message key="user_role"/></option>
                </c:if>
            </select>
        </form>
    </div>
    <div align="center" class="m-top-30">
        <table border="0" cellpadding="5">
            <tr>
                <th><fmt:message key="username"/></th>
                <th><fmt:message key="is_active"/></th>
                <c:choose>
                    <c:when test="${user_role.toString().equals(\"ADMIN\")}">
                        <th><fmt:message key="user_role"/></th>
                        <th><fmt:message key="set_activity"/></th>
                        <th><fmt:message key="change_user_role"/></th>
                    </c:when>
                </c:choose>
            </tr>
            <ct:pagination-users startingIndex="${starting_index}" endingIndex="${ending_index}"/>
        </table>
        <nav>
            <ul class="pagination justify-content-center">
                <c:forEach var="i" begin="1" end="${pages_amount}" step="1">
                    <li class="page-item">
                        <form action="userServlet" method="post">
                            <input type="hidden" name="command" value="change_page_index">
                            <input class="m-left-30 bg-dark m-top-30" type="submit" name="current_page_number"
                                   value="<c:out value="${i}"/>">
                        </form>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </div>
    <jsp:include page="footer.jsp"/>
    </body>
</fmt:bundle>
</html>
