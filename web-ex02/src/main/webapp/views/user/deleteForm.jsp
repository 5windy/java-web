<%@page import="user.model.User"%>
<%@page import="java.sql.Date"%>
<%@page import="user.model.UserRequestDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/style/form.css">
<script type="module" src="/resources/script/validation-login.js"></script>
<title>회원탈퇴</title>
</head>
<body>
<%
User user = (User)session.getAttribute("log");
if(user == null)
	response.sendRedirect("/login");
%>

	<h1>회원탈퇴</h1>
	<form method="POST" action="/user/delete">
		<div>
			<input type="text" id="username" name="username" placeholder="아이디" value="${log.username}" disabled>
			<input type="password" id="password" name="password" placeholder="비밀번호">
		</div>
		<ul class="error-msg-group">
			<li id="error-msg-password-empty" class="error-msg">비밀번호: 필수 정보입니다.</li>
			<li id="error-msg-password-pattern" class="error-msg">비밀번호: 유효하지 않은 값입니다.</li>
		</ul>
	
		<input type="submit" value="탈퇴">
	</form>

</body>
</html>