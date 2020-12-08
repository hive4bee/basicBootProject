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
		<div class="panel-heading">List Page</div>
		<div class="panel-body">
			<%-- 
			<p>${result}</p>
			<ul class="list-group">
				<c:choose>
					<c:when test="${empty list}">
						<li>등록된 글이 없습니다.</li>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${!empty list}">
						<c:forEach var="board" items="${list.content}">
							<li class="list-group-item">${board}</li>
						</c:forEach>
					</c:when>
				</c:choose>
			</ul>
			 --%>
			<c:set var="list" value="${result.result}"/>
			<table class="table table-striped table-bordered table-hover" id="dataTables-examle">
				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일자</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty list}">
						<tr class="odd gradeX">
							<td colspan="4">등록된 글이 없습니다.</td>
						</tr>
						</c:when>
						<c:when test="${!empty list}">
							<c:forEach var="board" items="${list.content}">
							<tr class="odd gradeX">
								<td>${board.bno}</td>
								<td><a href="${board.bno}" class="boardLink">${board.title}</a></td>
								<td>${board.writer}</td>
								<td class="center"><fmt:formatDate type="date" value="${board.regdate}"></fmt:formatDate></td>
							</tr>
							</c:forEach>
						</c:when>
					</c:choose>
				</tbody>
			</table>
			
			<div>
				<select id="searchType">
					<option>--</option>
					<option value="t" >제목</option>
					<option value="c">내용</option>
					<option value="w">작성자</option>
				</select>
				<input type="text" id="searchKeyword">
				<button id="searchBtn">검색</button>
			</div>
			
			<!-- paging -->
			<nav>
			
				<div>
					<ul class="pagination">
						<c:if test="${!empty result.prevPage}">
							<li class="page-item">
								<a href="${result.prevPage.pageNumber+1}">PREV ${result.prevPage.pageNumber + 1}</a>
							</li>
						</c:if>
						<c:forEach var="num" items="${result.pageList}">
							<li class="page-item ${num.pageNumber+1 == result.currentPageNum ? 'active' : ''} ">
								<a href="${num.pageNumber+1}">${num.pageNumber + 1}</a>
							</li>
						</c:forEach>
						<c:if test="${!empty result.nextPage}">
							<li class="page-item">
								<a href="${result.nextPage.pageNumber+1}">NEXT ${result.nextPage.pageNumber +1}</a>
							</li>
						</c:if>
					</ul>
				</div>
			
			</nav>
		</div>
	</div>
	<form id="f1" action="/boards/list" method="get">
		<input type="hidden" name="page" value="${result.currentPageNum}">
		<input type="hidden" name="size" value="${result.currentPage.pageSize }">
		<input type="hidden" name="type" value="${pageVO.type}">
		<input type="hidden" name="keyword" value="${pageVO.keyword}">
	</form>
	<%@ include file="../include/footer.jsp" %>
	<script>
		$(window).load(function(){
			var msg = '<c:out value="${msg}" />';
			if(msg == 'success'){
				alert("정상적으로 처리되었습니다.");
				var stateObj={msg:''};
			}
		});
		$(document).ready(function(){
			//To set both value if type and keyword is given...
			var type = '<c:out value="${pageVO.type}" />';
			var keyword = '<c:out value="${pageVO.keyword}" />';
			console.log("type: " + type + " / keyword: " + keyword);
			if(type){
				$("option[value='"+type+"']").prop("selected", true);
			}
			if(keyword){
				$("#searchKeyword").val(keyword);
			}
			
			//To paginate 
			var formObj = $("#f1");
			$(".pagination a").click(function(e){
				e.preventDefault();
				var page = $(this).attr("href");
				console.log("page: " + page);
				formObj.find("input[name='page']").val(page);
				formObj.submit();
			});
			
			//To search keyword with type 
			$("#searchBtn").on("click", function(e){
				var typeStr = $("#searchType").find(":selected").val();
				var keywordStr = $("#searchKeyword").val();
				console.log("typeStr: " + typeStr + " / keywordStr: " + keywordStr);
				formObj.find("[name='type']").val(typeStr);
				formObj.find("[name='keyword']").val(keywordStr);
				formObj.find("[name='page']").val("1");
				formObj.submit();
			});
			
			//To move its content when user click the title.
			$(".boardLink").click(function(e){
				e.preventDefault();
				var bno = $(this).attr("href");
				
				formObj.attr("action", "/boards/view");
				formObj.append("<input type='hidden' name='bno' value='"+bno+"'>");
				formObj.submit();
			});
		});
	</script>
</body>
</html>