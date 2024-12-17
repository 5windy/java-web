<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/style/list.css">
<title>게시판</title>
</head>
<body>
<h1>게시판</h1>
<c:set var="page" value="${empty param.page ? 1 : param.page }"></c:set>
<div id="list-container">
	<div>
		<span class="no">번호</span>
		<span class="title">제목</span>
		<span class="author">작성자</span>
		<span class="reg-date">등록일</span>
		<span class="view-cnt">조회수</span>
		<span class="like-cnt">좋아요</span>
	</div>
	<c:forEach var="board" varStatus="status" items="${list }">
		<div>
			<span class="no">${size - status.index - ((page - 1) * 10)}</span> 
			<span class="title"><a href="/boards/${board.code }">${board.title}</a></span>
			<span class="author">${board.name }</span>
			<span class="reg-date">${fn:substringBefore(board.regDate, " ")}</span>
			<span class="view-cnt">${board.viewCnt }</span>
			<span class="like-cnt">${board.likeCnt }</span>
		</div>
	</c:forEach>
</div>
<div id="page-nav">
	<c:forEach var="page" begin="1" end="${size % 10 == 0 ? size / 10 : size / 10 + 1 }">
		<span><a href="/boards?page=${page }">${page }</a></span>
	</c:forEach>
</div>
</body>
</html>