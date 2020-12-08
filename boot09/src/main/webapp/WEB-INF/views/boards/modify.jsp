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
		<div class="panel-heading">Modify Page</div>
		<div class="panel-body">
			<form id="f1">
				<div class="form-group">
					<label>BNO</label> <input class="form-control" name="bno" value='<c:out value="${vo.bno}" />' readonly />
				</div>
				<div class="form-group">
					<label>Title</label> <input class="form-control" name="title" value='<c:out value="${vo.title}" />'>
					<p class="help-block">Title text here.</p>
				</div>
				<div class="form-group">
					<label>Content</label>
					<textarea class="form-control" rows="3" name="content"><c:out value="${vo.content}"/></textarea>
				</div>
				<div class="form-group">
					<label>Writer</label> <input class="form-control" name="writer" value='<c:out value="${vo.writer}" />' readonly>
				</div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="hidden" name="page" value="${pageVO.page}">
				<input type="hidden" name="size" value="${pageVO.size}">
				<input type="hidden" name="type" value="${pageVO.type}">
				<input type="hidden" name="keyword" value="${pageVO.keyword}">
			</form>
			<div class="form-group">
				<label>RegDate</label> <input class="form-control" name="regDate" value='<fmt:formatDate value="${vo.regdate}" />' readonly>
			</div>
			
			<div class="pull-right">
				<a href="#" class="btn btn-warning modbtn">Modify</a>
				<a href="#" class="btn btn-danger delbtn">Delete</a>
				<a href="/boards/list?page=${pageVO.page}&size=${pageVO.size}&type=${pageVO.type}&keyword=${pageVO.keyword}&bno=${vo.bno}" class="btn btn-primary">Cancel & Go List</a>
			</div>
		</div>
	</div>
	<%@ include file="../include/footer.jsp" %>
	<script>
		$(document).ready(function(){
			var formObj = $("#f1");
			$(".delbtn").on("click", function(e){
				e.preventDefault();
				if(confirm("정말로 지우시겠습니까?")){
					formObj.attr("action", "/boards/delete");
					formObj.attr("method", "post");
					
					formObj.submit();
				}
			});
			
			$(".modbtn").on("click", function(){
				formObj.attr("action", "modify");
				formObj.attr("method", "post");
				
				formObj.submit();
			});
			
		});
	</script>
</body>
</html>