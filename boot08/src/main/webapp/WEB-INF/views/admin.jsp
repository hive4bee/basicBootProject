<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Admin Page</h1>
	<hr>
	<sec:authentication property="principal" var="pinfo"/>
	<sec:authorize access="isAuthenticated()">
		${pinfo.username}
	</sec:authorize>
	<sec:authorize access="isAnonymous()">
		....
	</sec:authorize>
</body>
</html>