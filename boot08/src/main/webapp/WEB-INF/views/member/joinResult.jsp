<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>
		<label>UID</label>
		<input type="text" name="uid" value="${member.uid}" readonly />
	</p>
	<p>
		<label>UPW</label>
		<input type="text" name="upw" value="${member.upw}" readonly />
	</p>
	<p>
		<label>UNAME</label>
		<input type="text" name="uname" value="${member.uname}" readonly />
	</p>
</body>
</html>