<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>
		
		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">
					<c:if test="${blogvo.logoFile == 'default'}">
						<img id="proImg" src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
					</c:if>
					<c:if test="${blogvo.logoFile != 'default'}">
						<img id="proImg" src="${pageContext.request.contextPath}/upload/${blogvo.logoFile}">
					</c:if>
					<div id="nick">${blogvo.userName}(${blogvo.id})님</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
						<c:forEach items="${cateList}" var="catevo">
							<li><a href="${pageContext.request.contextPath}/${blogvo.id}?CateNo=${catevo.cateNo}">${catevo.cateName}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<!-- profilecate_area -->
			
			<div id="post_area">
				<c:if test="${not empty postvo}">
					<div id="postBox" class="clearfix">
							<div id="postTitle" class="text-left"><strong>${postvo.postTitle}</strong></div>
							<div id="postDate" class="text-left"><strong>${postvo.regDate}</strong></div>
							<div id="postNick">래미(iremys)님</div>
					</div>
					<!-- //postBox -->
				
					<div id="post" >
						${fn:replace(postvo.postContent, newLine, "<br>") }
					</div>
					<!-- //post -->
				</c:if>
				
				<c:if test="${empty postvo}">
					<div id="postBox" class="clearfix">
							<div id="postTitle" class="text-left"><strong>등록된 글이 없습니다.</strong></div>
							<div id="postDate" class="text-left"><strong></strong></div>
							<div id="postNick"></div>
					</div>
					<!-- //postBox -->
				
					<div id="post" >
					</div>
					<!-- //post -->
				</c:if>
				
				<div id="list">
					<div id="listTitle" class="text-left"><strong>카테고리의 글</strong></div>
					<table>
						<colgroup>
							<col style="">
							<col style="width: 20%;">
						</colgroup>
						<c:forEach items="${pList}" var="postvo">
							<tr>
								<td class="text-left"><a href="${pageContext.request.contextPath}/${blogvo.id}?CateNo=${postvo.cateNo}&postNo=${postvo.postNo}&postTitle=${postvo.postTitle}&postContent=${postvo.postContent}&regDate=${postvo.regDate}">${postvo.postTitle}</a></td>
								<td class="text-right">${postvo.regDate}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->
			
			
			
		</div>	
		<!-- //content -->
		<div class=></div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		
	
	
	</div>
	<!-- //wrap -->

</body>
<script type="text/javascript">

	$("#postbox").on("click",function(){
		console.log("클릭");
	})
	
	function render(postvo){
		var str = "";
		str +="<c:if test='${not empty postvo}'>";
		str +="<div id='postbox' class='clearfix'";		
		str +="<div id='postTitle' class='text-left'><strong>"+postvo.postTitle+"</strong></div>";		
		str +="<div id='postDate' class='text-left'><strong>"+postvo.regDate+"</strong></div>";		
		str +="<div id='postNick'>래미(iremys)님</div>";		
		str +="</div>";		
		str +="<div id='post' >fn:replace("+postvo.postContent+", newLine, '<br>') +</div>";		
		str +="</c:if>";		
		str +="<c:if test="+empty postvo+">";		
		str +="<div id='postBox' class='clearfix'>";		
		str +="<div id='postTitle' class='text-left'><strong>등록된 글이 없습니다.</strong></div>";		
		str +="<div id='postDate' class='text-left'><strong></strong></div>";		
		str +="<div id='postNick'></div>";		
		str +="</div>";		
		str +="<div id='post'>";		
		str +="</div>";		
		str +="</c:if>";	
		
		$("#post_Area").append(str);
	
		}


	
	

</script>

</html>