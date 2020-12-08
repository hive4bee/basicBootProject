<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post">
		<p>
			<label>UID</label>
			<input type="text" name="uid" value="newbie" />
		</p>
		<p>
			<label>UPW</label>
			<input type="password" name="upw" value="newbie" />
		</p>
		<p>
			<label>UNAME</label>
			<input type="text" name="uname" value="홍길동" />
		</p>
		<p>
			<input type="checkbox" name="roles[0].roleName" value="BASIC" checked>BASIC
			<input type="checkbox" name="roles[1].roleName" value="MANAGER" checked>MANAGER
			<input type="checkbox" name="roles[2].roleName" value="ADMIN" checked>ADMIN
		</p>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<button type="submit" class="btn">JOIN</button>
	</form>
</body>
</html>