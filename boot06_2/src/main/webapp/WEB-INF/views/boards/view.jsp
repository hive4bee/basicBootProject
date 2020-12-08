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
			<hr>
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>댓글번호</th>
						<th>답글</th>
						<th>작성자</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody id="replyTable"></tbody>
			</table>
			<div class="pull-right">
				<button class="btn" id="addReplyBtn">Add Reply</button>
			</div>
			
		</div>
	</div>
	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content -->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Modal Header</h4>
				</div>
				<div class="modal-body">
					<label>Reply Text</label>
					<input type="text" class="form-control" name="replyText">
					<label>Replyer</label>
					<input type="text" class="form-control" name="replyer">
				</div>
				<div class="modal-footer">
					<button id="delModalBtn" class="btn btn-danger">Delete</button>
					<button id="modalBtn" class="btn btn-info">Save</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../include/footer.jsp" %>
	<script src="/resources/js/reply.js"></script>
	<script>
		var bno = '<c:out value="${vo.bno}" />';
		var rno;
		//리스트 불러오는 메서드
		function showList(page){
			replyManager.getAll({bno:bno, page:page||1}, function(list){
				var str = "";
				for(var i = 0; i<list.length; i++){
					str+="<tr>";
					str+="<td>"+list[i].rno+"</td>";
					str+="<td>"+list[i].replyText+"</td>";
					str+="<td>"+list[i].replyer+"</td>";
					str+="<td>"+formatDate(list[i].regdate)+"</td>";
					str+="</tr>";
				}
				$("#replyTable").html(str);
			});
		}
		//날짜 포멧 
		function formatDate(timeValue){
			var date = new Date(timeValue);
			var yy = date.getFullYear();
			var mm = date.getMonth()+1 >= 10 ? date.getMonth()+1 : '0'+(date.getMonth()+1);
			var dd = date.getDate();
			return yy+"-"+mm+"-"+dd;
		}
		
		///////////////////////////////////////////////////////////////////////
		$(document).ready(function(){
			//var bno = '<c:out value="${vo.bno}" />';
			showList(1);
			var mode;
			//게시글을 열람한 후 Add Reply 버튼을 클릭하면 댓글작성 모달을 불러온다. 
			$("#addReplyBtn").on("click", function(){
				$("#myModal").modal("show");
				$(".modal-title").text("Add Reply");
				
				mode="ADD";
			});
			
			//모달을 불러와서 댓글을 작성한 후에 제출하는 버튼을 누른다.
			$("#modalBtn").click(function(){
				var replyTextObj = $("input[name='replyText']");
				var replyerObj = $("input[name='replyer']");
				
				if(mode == 'ADD'){
					
					var obj = {replyText:replyTextObj.val(), replyer:replyerObj.val(), bno:bno};
					
					replyManager.add(obj, function(){
						showList(1);
						alert("새로운 댓글이 추가되었습니다.");
						$("#myModal").modal("hide");
						replyTextObj.val("");
						replyerObj.val("");
					})
				}else if(mode == 'MOD'){
					var obj = {replyText:replyTextObj.val(), bno:bno, rno:rno};
					replyManager.update(obj, function(){
						alert("댓글이 수정되었습니다.");
						$("#myModal").modal("hide");
						replyTextObj.val("");
						replyerObj.val("");
						showList(1);
					})
				}
				
			});
			
			//글을 열람한 후에 원하는 수정하거나 삭제하고 싶은 댓글 부분을 눌렀을 때 모달을 불러온다.
			//when clicking a reply that you want to modify or delete, modal poped up.
			$("#replyTable").on("click", "tr", function(e){
				var tds = $(this).find("td");
				rno = tds[0].innerHTML;
				mode='MOD';
				
				var replyTextObj = $("input[name='replyText']");
				var replyerObj = $("input[name='replyer']");
				replyTextObj.val(tds[1].innerHTML);
				replyerObj.val(tds[2].innerHTML);
				$("#myModal").modal("show");
				$(".modal-title").text("Modify/Delete Reply");
			});
			
			//삭제하기 버튼을 클릭했을 때 삭제처리한다.
			$("#delModalBtn").on("click", function(){
				var obj = {bno:bno, rno:rno};
				replyManager.remove(obj, function(){
					showList(1);
					alert("댓글이 삭제되었습니다.");
					$("#myModal").modal("hide");
					$("input[name='replyText']").val("");
					$("input[name='replyer']").val("");
					
				});
			});
			
			
			
			
		});//end $(document).ready(function(){});
		///////////////////////////////////////////////////////////////////////
		
	</script>
</body>
</html>