<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>

</head>
<body>
	<div id="center-content">
		
		<!-- 메인 해더 -->
	
		<jsp:include page="/WEB-INF/views/includes/main-header.jsp"></jsp:include>

		<div>		
			<form id="joinForm" method="post" action="${pageContext.request.contextPath}/user/join">
				<table>
			      	<colgroup>
						<col style="width: 100px;">
						<col style="width: 170px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td><label for="txtId">아이디</label></td>
		      			<td><input id="txtId" type="text" name="id"></td>
		      			<td><button id="btnIdCheck" type="button">아이디체크</button></td>
		      		</tr>
		      		<tr>
		      			<td></td>
		      			<td id="tdMsg" colspan="2"></td>
		      		</tr> 
		      		<tr>
		      			<td><label for="txtPassword">패스워드</label> </td>
		      			<td><input id="txtPassword" type="password" name="password"  value=""></td>   
		      			<td></td>  			
		      		</tr> 
		      		<tr>
		      			<td><label for="txtUserName">이름</label> </td>
		      			<td><input id="txtUserName" type="text" name="userName"  value=""></td>   
		      			<td></td>  			
		      		</tr>  
		      		<tr>
		      			<td><span>약관동의</span> </td>
		      			<td colspan="3">
		      				<input id="chkAgree" type="checkbox" name="agree" value="y">
		      				<label for="chkAgree">서비스 약관에 동의합니다.</label>
		      			</td>   
		      		</tr>   		
		      	</table>
					<button id="btnJoin" class="btn" type="submit" >회원가입</button>
				
				</form>
			</div>
	      		
			
			
		</div>
		
		
		<!-- 메인 푸터  자리-->
		
		<jsp:include page="/WEB-INF/views/includes/main-footer.jsp"></jsp:include>


</body>
<script type="text/javascript">
	$("#btnIdCheck").on("click", function(){
		console.log("버튼클릭");
		var uId = $("#txtId").val();
		
		console.log(uId);
	
		
		
		$.ajax({
			url : "${pageContext.request.contextPath }/user/idcheck",		
			
			type : "post",
			//contentType : "application/json",
			data : {userId: uId},
			
			dataType : "json",
			success : function(result){
				console.log(result);
				/*성공시 처리해야될 코드 작성*/
				if(result == true){
					$("#tdMsg").text("사용가능");
				}else{
					$("#tdMsg").text("사용불가");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
		
		
	} );
	
	$("#btnJoin").on("click",function(){
		
		var pass = $("#txtPassword").val();
		var name = $("#txtUserName").val();
		var agree = $("#chkAgree").val();
		var uerInfo ={
			userId: uId
		}
		if(uId ==""){
			alert('아이디를 입력해주세요.');
		}if(pass==""){
			alert('비밀번호를 입력해주세요.');
		}if(name==""){
			alert('이름를 입력해주세요.');
		}if(agree!="y"){
			alert('서비스 약관에 동의해주세요.');
		}
	});
</script>


</html>