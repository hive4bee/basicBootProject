<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>CUSTOM LOGOUT PAGE</h2>
	<form method="post">
		<h3>Logout</h3>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<button type="submit" class="btn">Logout</button>
	</form>
</body>
</html>