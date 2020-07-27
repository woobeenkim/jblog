<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn selected"><a
					href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn"><a
					href="${pageContext.request.contextPath}/${authUser.id}/admin/cate">카테고리</a></li>
				<li class="tabbtn"><a
					href="${pageContext.request.contextPath}/${authUser.id}/admin/wform">글작성</a></li>
			</ul>
			<!-- //admin-menu -->

			<div id="admin-content">

				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>카테고리명</th>
							<th>포스트 수</th>
							<th>설명</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody id="cateList">
						<!-- 리스트 영역 -->

						<!-- 리스트 영역 -->
					</tbody>
				</table>

				<table id="admin-cate-add">
					<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
					<tr>
						<td class="t">카테고리명</td>
						<td><input id="name" type="text" name="name"></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input id="desc" type="text" name="desc"></td>
					</tr>
				</table>

				<div id="btnArea">
					<button id="btnAddCate" class="btn_l" type="submit">카테고리추가</button>
				</div>

			</div>
			<!-- //admin-content -->
		</div>
		<!-- //content -->


		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>


	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">


	$(document).ready(function(){
	
		fetchList();
	});

	$("#btnAddCate").on("click",function(){
		
		console.log("클릭.");
		var Name = $("#name").val();
		var desc = $("#desc").val();
		
		var categoryvo = {cateName:Name,
						  description:desc};
		
		console.log(categoryvo);
	
		$.ajax({
					
					url : "${pageContext.request.contextPath }/${id}/admin/insertcate",		
					type : "post",
					//contentType : "application/json",
					data :  categoryvo,
					
					//contentType : "application/json",
					//data :  JSON.stringify(personvo),
					
					//json방식 사용할땐 contenttype과 data의 형식을 맞춰줘야 한다.
					
					dataType : "json",
					success : function(categoryvo){
						console.log(categoryvo);
						render(categoryvo);
					},
					error : function(XHR, status, error) {
						console.error(status + " : " + error);
					}
				});
	

	});

$("#cateList").on("click",function(){
	
	console.log("클릭");
	
	var no = $(this).data("cateNo");
	console.log(no);
	
	$.ajax({
		
		url : "${pageContext.request.contextPath }/{id}/admin/catedelete",		
		type : "post",
		//contentType : "application/json",
		data : {cateNo:no},
		
		//비교값:실제가져온 값
		dataType : "json",
		success : function(count){
			console.log(count);
			if(count < 1){
				 cateNo.remove();
			}else{
				alert('삭제할 수 없습니다.');
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
})
	function fetchList() {
		$.ajax({

			url : "${pageContext.request.contextPath}/list",
			type : "post",
			//contentType : "application/json",
			//data : {name: "홍길동"},

			dataType : "json",
			success : function(cateList) {
				console.log(cateList);
				for (var i = 0; i < cateList.length; i++) {
					render(cateList[i]);
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}

	function render(categoryvo) {
		var str = "";
		str += "<tr>";
		str += "<td>"+categoryvo.cateNo+"</td>";
		str += "<td>"+categoryvo.cateName+"</td>";
		str += "<td>"+categoryvo.count+"</td>";
		str += "<td>"+categoryvo.description+"</td>";
		str += "<td class='text-center'>";
		str += "<img class='btnCateDel' data-cateNo = "+categoryvo.cateNo+" src=${pageContext.request.contextPath}/assets/images/delete.jpg>";
		str += "	</td>";
		str += "</tr>";

		$("#cateList").append(str);
	}
</script>


</html>