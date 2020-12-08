<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<%@ include file="../include/header.jsp" %>
	<link rel="stylesheet" href="https://openlayers.org/en/v4.6.5/css/ol.css" type="text/css">
    <!-- The line below is only needed for old environments like Internet Explorer and Android 4.x -->
    <script src="https://cdn.polyfill.io/v2/polyfill.min.js?features=requestAnimationFrame,Element.prototype.classList,URL"></script>
    <script src="https://openlayers.org/en/v4.6.5/build/ol.js"></script>
    <style>
      a.skiplink {
        position: absolute;
        clip: rect(1px, 1px, 1px, 1px);
        padding: 0;
        border: 0;
        height: 1px;
        width: 1px;
        overflow: hidden;
      }
      a.skiplink:focus {
        clip: auto;
        height: auto;
        width: auto;
        background-color: #fff;
        padding: 0.3em;
      }
      #map{
      	height:300px;
      }
      #map:focus {
        outline: #4A74A8 solid 0.15em;
      }
    </style>
    <style>
      .ol-dragbox {
        background-color: rgba(255,255,255,0.4);
        border-color: rgba(100,150,0,1);
      }
    </style>
</head>
<body>
	<a class="skiplink" href="#map">Go to map</a>
    <div id="map" class="map" tabindex="2"></div>
    <div id="info">No countries selected</div>
    <script>
      var vectorSource = new ol.source.Vector({
        url: 'https://openlayers.org/en/v4.6.5/examples/data/geojson/countries.geojson',
        format: new ol.format.GeoJSON()
      });


      var map = new ol.Map({
        layers: [
          new ol.layer.Tile({
            source: new ol.source.OSM()
          }),
          new ol.layer.Vector({
            source: vectorSource
          })
        ],
        target: 'map',
        view: new ol.View({
          center: [0, 0],
          zoom: 3
        })
      });

      // a normal select interaction to handle click
      var select = new ol.interaction.Select();
      map.addInteraction(select);

      var selectedFeatures = select.getFeatures();

      // a DragBox interaction used to select features by drawing boxes
      var dragBox = new ol.interaction.DragBox({
        condition: ol.events.condition.platformModifierKeyOnly
      });

      map.addInteraction(dragBox);

      dragBox.on('boxend', function() {
        // features that intersect the box are added to the collection of
        // selected features
        var extent = dragBox.getGeometry().getExtent();
        vectorSource.forEachFeatureIntersectingExtent(extent, function(feature) {
          selectedFeatures.push(feature);
        });
      });

      // clear selection when drawing a new box and when clicking on the map
      dragBox.on('boxstart', function() {
        selectedFeatures.clear();
      });

      var infoBox = document.getElementById('info');

      selectedFeatures.on(['add', 'remove'], function() {
        var names = selectedFeatures.getArray().map(function(feature) {
          return feature.get('name');
        });
        if (names.length > 0) {
          infoBox.innerHTML = names.join(', ');
        } else {
          infoBox.innerHTML = 'No countries selected';
        }
      });
    </script>
    
	<div class="page-header">
		<h1>
			Boot06 Project <small>for Spring MVC + JPA</small>
		</h1>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">List Page</div>
		<div class="pull-right">
			<h3><a class="label label-default goRegBtn" href="/boards/register">Register</a></h3>
		</div>
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
								<%-- 
								<td>${board.bno}</td>
								<td><a href="${board.bno}" class="boardLink">${board.title}</a></td>
								<td>${board.writer}</td>
								<td class="center"><fmt:formatDate type="date" value="${board.regdate}"></fmt:formatDate></td>
								 --%>
								<td>${board[0]}</td>
								<td>
									<a href="${board[0]}" class="boardLink">${board[1]}</a>
									<span class="badge">${board[2]}</span>
								</td>
								<td>${board[3]}</td>
								<td class="center"><fmt:formatDate type="date" value="${board[4]}"></fmt:formatDate></td>
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
			
			
			var uid = "";
			<sec:authorize access="isAuthenticated()">
				uid = '<sec:authentication property="principal.username"/>';
			</sec:authorize>
			$(".goRegBtn").on("click", function(e){
				e.preventDefault();
				if(!uid){
					if(confirm("로그인할까요?")){
						self.location="/login";
					}
				}else{
					self.location=$(this).attr("href");
				}
			});
		});

	</script>
	
</body>
</html>