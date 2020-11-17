<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:bundle basename="html.text">
    <head>
        <title>Title</title>
        <script type="text/javascript"
                src="${pageContext.request.contextPath}/js/project.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbar.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">
    </head>
    <body>
    <nav class="navbar navbar-expand-lg navbar-light dark-navbar">
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="m-left-130">
                    <form action="userServlet" name="view main" method="post">
                        <input type="hidden" name="command" value="view_main_page">
                        <input type="submit" class="mx-3 dark-navbar nav-input" value=
                            <fmt:message key="gym"/>>
                    </form>
                </li>
                <jstl:if test="${user_role.toString().equals(\"GUEST\")}">
                    <li class="m-left-30">
                        <form action="userServlet" name="register" method="post">
                            <input type="hidden" name="command" value="go_to_registration">
                            <input type="submit" class="mx-3 dark-navbar nav-input" value=
                                <fmt:message key="register"/>>
                        </form>
                    </li>
                    <li class="m-left-30">
                        <form action="userServlet" name="login" method="post">
                            <input type="hidden" name="command" value="go_to_login">
                            <input type="submit" class="mx-3 dark-navbar nav-input" value=
                                <fmt:message key="login"/>>
                        </form>
                    </li>
                </jstl:if>
                <jstl:if test="${user_role.toString().equals(\"CLIENT\") or user_role.toString().equals(\"TRAINER\")}">
                    <li class="m-left-30">
                        <form action="userServlet" name="profile" method="post">
                            <input type="hidden" name="command" value="view_user_profile">
                            <input type="submit" class="mx-3 dark-navbar nav-input" value=
                                <fmt:message key="view_profile"/>>
                        </form>
                    </li>
                </jstl:if>
                <jstl:if test="${user_role.toString().equals(\"CLIENT\") or user_role.toString().equals(\"ADMIN\")
                                    or user_role.toString().equals(\"TRAINER\")}">
                    <li class="m-left-30">
                        <form action="userServlet" name="logout" method="post">
                            <input type="hidden" name="command" value="logout">
                            <input type="submit" class="mx-3 dark-navbar nav-input" value=
                                <fmt:message key="logout"/>>
                        </form>
                    </li>
                </jstl:if>
                <jstl:if test="${user_role.toString().equals(\"ADMIN\") or user_role.toString().equals(\"TRAINER\")}">
                    <li class="m-left-30">
                        <form action="userServlet" name="view users" method="post">
                            <input type="hidden" name="command" value="view_users">
                            <input type="submit" class="mx-3 dark-navbar nav-input" value=
                                <fmt:message key="view_users"/>>
                        </form>
                    </li>
                </jstl:if>
                <li class="m-left-30">
                    <jsp:include page="choose_language_fragment.jsp"/>
                </li>
                <jstl:if test="${user_role.toString().equals(\"CLIENT\")}">
                    <li class="m-left-30">
                        <div class="text-color-white">
                                ${sessionScope.balance} BYN
                        </div>
                    </li>
                    <li>
                        <form action="userServlet" name="profile" method="post">
                            <input type="hidden" name="command" value="go_to_replenish_money_account">
                            <input type="submit" class="mx-3 dark-navbar nav-input" value=
                                <fmt:message key="add_money"/>>
                        </form>
                    </li>
                </jstl:if>
            </ul>
        </div>
    </nav>
    </body>
</fmt:bundle>
</html>
