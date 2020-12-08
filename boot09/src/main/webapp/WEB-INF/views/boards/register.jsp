<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<%@ include file="../include/header.jsp" %>
</head>
<body>
	<div class="page-header">
		<h1>
			Boot06 Project <small>for Spring MVC + JPA</small>
		</h1>
	</div>
	<div class="panel panel-default">
	<div class="panel-heading">Register Page</div>
	<div class="panel-body">
		<form action="/boards/register" method="post">
			<div class="form-group">
				<label>Title</label> <input class="form-control" name="title" value='<c:out value="${vo.title}" />' >
				<p class="help-block">Title text here.</p>
			</div>
			<div class="form-group">
				<label>Content</label>
				<textarea class="form-control" rows="3" name="content" ><c:out value="${vo.content}"/></textarea>
			</div>
			<div class="form-group">
				<sec:authentication property="principal" var="pinfo" />
				<label>Writer</label> <input class="form-control" name="writer" value='<c:out value="${pinfo.username}" />' readonly/>
			</div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button type="submit" class="btn btn-default">등록</button>
			<button type="reset" class="btn btn-primary">취소</button>
		</form>
		
	</div>
	</div>
</body>
</html>