<%@page import="java.sql.Date"%>
<%@page import="user.model.UserRequestDto"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/style/form.css">
<title>글작성</title>
</head>
<c:import url="/header" />
<body>
<c:if test="${empty log }">
	<c:redirect url="/login" />
</c:if>
<div class="content-container">
	<h2>글작성</h2>
	<form method="POST" action="/service/boards" enctype="multipart/form-data">
		<input type="hidden" name="command" value="write">
		<div>
			<input type="text" id="title" name="title" placeholder="제목" autofocus>
			<textarea id="content" name="content"placeholder="내용"></textarea>
			<input type="file" id="file" name="file">
			<select id="status" name="status">
				<option value="show" selected>게시</option>
				<option value="hide">숨김</option>
				<option value="reserv">예약</option>
			</select>
			<input type="datetime-local" id="postDate" name="postDate"> 
		</div>
		<input type="submit" value="글작성">
	</form>
</div>
</body>
<c:import url="/footer" />
</html>