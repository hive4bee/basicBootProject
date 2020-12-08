<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>CUSTOM LOGIN PAGE</h2>
	<c:if test="${!empty param.error}">
		<h2>Invalid Username or password</h2>
		<h2>${param.error}</h2>
	</c:if>
	<c:if test="${!empty param.logout}">
		<h2>You have been logged out.</h2>
	</c:if>
	
	<form method="post">
		<p>
			<label for="username">Username</label> <input type="text" id="username" name="username" value="user88"/>
		</p>
		<p>
			<label for="password">Password</label> <input type="password" id="password" name="password" value="pw88" />
		</p>
		<p>
			<label for="text">Remember-me</label> <input type="checkbox" id="remember-me" name="remember-me"/>
		</p>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<button type="submit" class="btn">Log in</button>
	</form>
</body>
</html>