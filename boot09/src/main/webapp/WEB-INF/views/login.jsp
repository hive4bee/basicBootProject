<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<%@ include file="./include/header.jsp" %>
</head>
<body>
	<div class="page-header">
		<h1>
			Boot06 Project <small>for Spring MVC + JPA</small>
		</h1>
	</div>
	<div class="panel panel-default">

		<div class="panel-heading">Login Page</div>
		<div class="panel-body">
			<c:choose>
				<c:when test="${!empty param.error}">
					<h2>Invalid Username or password</h2>
					<h2>${param.error}</h2>
				</c:when>
				<c:when test="${!empty param.logout}">
					<h2>You have been logged out.</h2>
				</c:when>
				<c:otherwise>
					<div class="container">
						<form method="post">
							<p>
								<label for="username">Username</label> <input type="text" id="username" name="username"/>
							</p>
							<p>
								<label for="password">Password</label> <input type="password" id="password" name="password" />
							</p>
							<p>
								<label for="text">Remember-Me</label> <input type="checkbox" id="remember-me" name="remember-me" />
							</p>
							
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<button type="submit" class="btn">Log in</button>
						</form>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<%@ include file="./include/footer.jsp" %>
</body>
</html>