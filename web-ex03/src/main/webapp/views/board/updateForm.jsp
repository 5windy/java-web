<%@page import="java.sql.Date"%>
<%@page import="user.model.UserRequestDto"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/style/form.css">
<script src="/resources/script/board.js"></script>
<title>${board.title}</title>
</head>
<body>
	<c:if test="${empty board }">
		<c:redirect url="/service?command=list" />
	</c:if>
	<h1>${board.title}</h1>
	<form method="POST" action="/service">
		<input type="hidden" name="command" value="update-board">
		<input type="hidden" name="no" value="${board.code }">
		<div>
			<input type="text" id="title" name="title" value="${board.title}" ${log.username eq board.username ? "" : "disabled" }>
			<input type="hidden" name="username" value="${board.username }">
			<input type="text" id="name" name="name" value="${board.name}" disabled }>
			<input type="datetime-local" id="reg-date" name="reg-date" value="${board.regDate}" disabled }>
			<textarea id="content" name="content" ${log.username eq board.username ? "" : "disabled"}>${board.content}</textarea>
			<c:if test="${not empty log}">
				<select id="status" name="status">
					<option value="show" selected>게시</option>
					<option value="hide">숨김</option>
					<option value="reserv">예약</option>
				</select>
				<input type="datetime-local" id="postDate" name="postDate" value="${empty board.postDate ? board.regDate : board.postDate}"> 
			</c:if>
		</div>
		<c:if test="${log.username eq board.username}">
			<div id="btn-group">
				<input type="submit" value="글수정">
				<input type="button" value="삭제" onclick="deleteBoard(${board.code})">
			</div>
		</c:if>
	</form>
	
</body>
</html>