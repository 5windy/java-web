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
<body>
	<c:if test="${empty log }">
		<c:redirect url="/login" />
	</c:if>
	<h1>글작성</h1>
	<form method="POST" action="/service">
		<input type="hidden" name="command" value="write">
		<div>
			<input type="text" id="title" name="title" placeholder="제목" autofocus>
			<textarea id="content" name="content"placeholder="내용"></textarea>
			<select id="status" name="status">
				<option value="show" selected>게시</option>
				<option value="hide">숨김</option>
				<option value="reserv">예약</option>
			</select>
			<input type="datetime-local" id="postDate" name="postDate"> 
		</div>
		<input type="submit" value="글작성">
	</form>
</body>
</html>