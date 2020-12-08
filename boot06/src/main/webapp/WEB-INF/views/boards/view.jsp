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
		<div class="panel-heading">View Page</div>
		<div class="panel-body">
			<div class="form-group">
				<label>글번호</label> <input class="form-control" name="bno" value="${vo.bno}" readonly />
			</div>
			<div class="form-group">
				<label>제목</label> <input class="form-control" name="title" value="${vo.title}" readonly />
				<p class="help-block">Title text here.</p>
			</div>
			<div class="form-group">
				<label>내용</label>
				<textarea class="form-control" rows="3" name="content" readonly>${vo.content}</textarea>
			</div>
			<div class="form-group">
				<label>작성자</label> <input class="form-control" name="writer" value="${vo.writer}" readonly />
			</div>
			<div class="form-group">
				<label>작성일</label> <input class="form-control" name="regDate" value='<fmt:formatDate type="date" value="${vo.regdate}" />' readonly>
			</div>
			<div class="put-right">
				<a href="/boards/modify?page=${pageVO.page}&size=${pageVO.size}&type=${pageVO.type}&keyword=${pageVO.keyword}&bno=${vo.bno}" class="btn btn-default">Modify/Delete</a>
				<a href="/boards/list?page=${pageVO.page}&size=${pageVO.size}&type=${pageVO.type}&keyword=${pageVO.keyword}&bno=${vo.bno}" class="btn btn-primary">Go List</a>
			</div>
			
		</div>
	</div>
	<%@ include file="../include/footer.jsp" %>
</body>
</html>