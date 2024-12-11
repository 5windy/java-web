<%@page import="user.model.UserDao"%>
<%@page import="user.model.UserRequestDto"%>
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
request.setCharacterEncoding("UTF-8");

String username = request.getParameter("username");
String password = request.getParameter("password");
String email = request.getParameter("email");
String name = request.getParameter("name");
String birth = request.getParameter("birth");
String telecom = request.getParameter("telecom");
String gender = request.getParameter("gender");
String country = request.getParameter("country");
String phone = request.getParameter("phone");
String agree = request.getParameter("agree");

// 사용자의 입력한 정보를 서버로 전달하여
// 회원가입 처리 (DB에 INSERT : Transaction)

// 생성할 객체는 UserRequestDto 
UserRequestDto userDto = new UserRequestDto(username, password, email, name, birth, telecom, gender, country, phone, agree);

UserDao userDao = UserDao.getInstance();
userDao.createUser(userDto);

%>
</body>
</html>