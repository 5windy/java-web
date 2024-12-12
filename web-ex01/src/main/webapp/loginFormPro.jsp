<%@page import="user.model.UserDao"%>
<%@page import="user.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String username = request.getParameter("username");
String password = request.getParameter("password");

UserDao userDao = UserDao.getInstance();
User user = userDao.findUserByUsername(username);

String url = "";

if(user != null && user.checkPassword(password)) {
	// 내장 객체 중, session에 로그인 유저 정보를 저장 
	// mypage 로 이동
	// ㄴ ***님 환영합니다.
	session.setAttribute("log", user);
	url = "/myPage.jsp";
} else {
	// login form 으로 이동
	url = "/loginForm.jsp";
}

response.sendRedirect(url);

%>
</body>
</html>