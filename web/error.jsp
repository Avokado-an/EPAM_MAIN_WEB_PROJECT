<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:bundle basename="resources.html">
<head>
    <title>Title</title>
</head>
<body>
<fmt:message key="error_message"/>
</body>
</fmt:bundle>
</html>
