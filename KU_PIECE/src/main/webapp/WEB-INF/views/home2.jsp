<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>main</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/content.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.3.min.js"></script>
		<script type="text/javascript">
			function loginForm() {				
				$("#loginForm").show();
				$("#loginForm").find("#midError").text("");
				$("#loginForm").find("#mpasswordError").text("");
				$("#joinForm").hide();				
			}
			
			function login() {
				
				var userEmail = $("#loginForm").find("#userEmail").val().trim();
				if(userEmail == "") { $("#loginForm").find("#midError").text("필수"); }
				else { $("#loginForm").find("#midError").text(""); }
				
				var userPassword = $("#loginForm").find("#userPassword").val().trim();
				if(userPassword == "") { $("#loginForm").find("#mpasswordError").text("필수"); }
				else { $("#loginForm").find("#mpasswordError").text(""); }
				
				if(userEmail!="" && userPassword!="") {
					function testAlertBox() {
					    alert(userEmail+'&'+userPassword);
					  }
					$.ajax({
						url: "/konkuk/user/login.do",
						method:"POST",
						data:$("#loginForm").serialize(),
						success:function(data) {
							if(data.result == "success") {
								$("#loginForm").hide();
								$("#userEmail", window.parent.document).text(mid);								
								$("#sec02_result").html('<a class="button" href="javascript:logout()">로그아웃</a>');
							} else if(data.result == "fail-mid") {
								$("#sec02_result").html("<span class='error'>아이디가 존재하지 않습니다.</span>");
							} else if(data.result == "fail-mpassword") {
								$("#sec02_result").html("<span class='error'>패스워드가 틀립니다.</span>");
							}
						},
						error: function() {
							$("#sec02_result").html("<span class='error'>로그인 실패</span>");
						}
					});
				}
			}
			
			function logout() {
				$.ajax({
					url:"logout",
					success:function(data) {
						if(data.result == "success") {
							$("#loginForm").show();
							$("#sec02_result").empty();
							$("#mid", window.parent.document).text("Who are you?");
						}
					}
				});
			}
			
			function joinForm() {
				$("#loginForm").hide();
				$("#joinForm").show();
				$("#joinForm").find("#midError").text("");
				$("#joinForm").find("#mnameError").text("");
				$("#joinForm").find("#mpasswordError").text("");
			}
			
			function join() {
			/* 	var mid = $("#joinForm").find("#mid").val().trim();
				if(mid == "") { $("#joinForm").find("#midError").text("필수"); }
				else { $("#joinForm").find("#midError").text(""); }
				
				var mname = $("#joinForm").find("#mname").val().trim();
				if(mname == "") { $("#joinForm").find("#mnameError").text("필수"); }
				else { $("#joinForm").find("#mnameError").text(""); }				
				
				var mpassword = $("#joinForm").find("#mpassword").val().trim();
				if(mpassword == "") { $("#joinForm").find("#mpasswordError").text("필수"); }
				else { $("#joinForm").find("#mpasswordError").text(""); }
				 */
				if(true) {
					$.ajax({
						url:"user/register.do",
						method:"POST",
						data:$("#joinForm").serialize(),
						success:function(data) {
							if(data.result == "success") {
								$("#loginForm").show();
								$("#joinForm").hide();
								$("#sec02_result").html("<span class='error'>회원가입 성공, 로그인하세요.</span>");
							} 
						},
						error: function() {
							$("#sec02_result").html("<span class='error'>회원가입 실패</span>");
						}
					});
				}
			}
		</script>
	</head>
	<body>
		<div id="header">
			<h3>데이터베이스 연동</h3>
		</div>
		
		
			
			<div class="sector">
				<div class="sector_title"><h4><span class="no">Sec02. </span>로그인</h4></div>
				<div class="sector_content">
					<form id="loginForm" method="POST"<c:if test="${sessionUserEmail!=null}">style="display:none"</c:if>>
						<table style="width:auto">
							<tr>
								<th style="width:100px">유저아이디</th>
								<td style="width:220px;text-align:left;"><input id="userEmail" type="text" name="userEmail"/><span id="midError" class="error"></span></td>
							</tr>
							<tr>
								<th style="width:100px">유저비밀번호</th>
								<td style="width:220px;text-align:left;"><input id="userPassword" type="password" name="userPassword"/><span id="mpasswordError" class="error"></span></td>
							</tr>
							
						</table>
						<!-- <a href="/konkuk/user/login.do" class="button">로그인</a> -->
						<a href="javascript:login()" class="button">유저로그인</a>
						<a href="javascript:joinForm()" class="button">유저회원가입</a>						
					</form>
					<form id="joinForm" style="display:none">
						<table style="width:auto">
							<tr>
								<th style="width:100px">유저이메일</th>
								<td style="width:220px;text-align:left;"><input id="userEmail" type="text" name="userEmail"/><span id="midError" class="error"></span></td>							
							</tr>
							<tr>
								<th style="width:100px">유저이름</th>
								<td style="width:220px;text-align:left;"><input id="userName" type="text" name="userName"/><span id="mnameError" class="error"></span></td>
							</tr>
							<tr>
								<th style="width:100px">유저비밀번호</th>
								<td style="width:220px;text-align:left;"><input id="userPassword" type="text"  name="userPassword"/><span id="mpasswordError" class="error"></span></td>
							</tr>
								<tr>
								<th style="width:100px">유저성별</th>
								<td style="width:220px;text-align:left;"><input id="userTel" type="text"  name="userTel"/><span id="mpasswordError" class="error"></span></td>
							</tr>
								<tr>
								<th style="width:100px">유저주소</th>
								<td style="width:220px;text-align:left;"><input id="userAddress type="text" name="userAddress"/><span id="mpasswordError" class="error"></span></td>
							</tr>
								<tr>
								<th style="width:100px">유저자기소개</th>
								<td style="width:220px;text-align:left;"><input id="userIntro type="text" name="userIntro"/><span id="mpasswordError" class="error"></span></td>
							</tr>
								<tr>
								<th style="width:100px">유저프로필이미지</th>
								<td style="width:220px;text-align:left;"><input id="userImage" type="text" name="userImage"/><span id="mpasswordError" class="error"></span></td>
							</tr>
								<tr>
								<th style="width:100px">유저식별코드</th>
								<td style="width:220px;text-align:left;"><input id="userCode" type="text" name="userCode"/><span id="mpasswordError" class="error"></span></td>
							</tr>
						</table>
						<a href="javascript:join()" class="button">회원가입</a>	
						<a href="javascript:loginForm()" class="button">취소</a>
					</form>
				</div>
				<div id="sec02_result" class="sector_result">
					<c:if test="${sessionMid!=null}">
						<script>$("#mid", window.parent.document).text("${sessionMid}");</script>
						<a class="button" href="javascript:logout()">로그아웃</a>
					</c:if>
				</div>
			</div>
			
			<div class="sector">
				<div class="sector_title"><h4><span class="no">Sec03. </span>게시판</h4></div>
				<div class="sector_content">
					<a class="button" href="boardList">게시물 목록</a>
				</div>
				<div id="sec03_result" class="sector_result"></div>
			</div>	
		</div>
	</body>
</html>