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
User user = (User)session.getAttribute("log");
String password = request.getParameter("password");

String url = "";

if(user.checkPassword(password)) {
	UserDao userDao = UserDao.getInstance();
	userDao.deleteUserByUsername(user.getUsername());
	url = "/";
	
} else {
	url = "/deleteForm.jsp";
}
response.sendRedirect(url);
%>
</body>
</html>