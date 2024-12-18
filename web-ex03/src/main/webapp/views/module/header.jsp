<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/style/grid.css"> 
</head>
<body>
<header>
<h1><a href="/">JSP Web Project</a></h1>
<nav>
	<ul>
		<li><a href="/">홈</a></li>
		<li><a href="/list">게시판</a></li>
		<li><a href="/mypage">마이페이지</a></li>
		<c:choose>
			<c:when test="${empty log }">
				<li><a href="/login">로그인</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="/service/users?command=logout">로그아웃</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</nav>
</header>
</body>
</html>