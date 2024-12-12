<%@page import="user.model.UserRequestDto"%>
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
String username = user.getUsername();

String password = request.getParameter("password");
String newPassword = request.getParameter("new-password");
String email = request.getParameter("email");
String country = request.getParameter("country");
int telecom = Integer.parseInt(request.getParameter("telecom"));
String phone = request.getParameter("phone");

UserDao userDao = UserDao.getInstance();

UserRequestDto userDto = new UserRequestDto();

userDto.setUsername(username);
userDto.setCountry(country);
userDto.setTelecom(telecom);

if(!email.equals("") && !email.equals(user.getEmail())) {
	User target = userDao.findUserByEmail(email);
	if(target == null)
		userDto.setEmail(email);
}

if(!newPassword.equals("") && !password.equals(newPassword)) {
	if(user.checkPassword(password))
		userDto.setPassword(newPassword);
}

if(!user.getPhone().equals(phone)) {
	User target = userDao.findUserByPhone(phone);
	if(target == null) 
		userDto.setPhone(phone);
}

user = userDao.updateUser(userDto);
session.setAttribute("log", user);

response.sendRedirect("/myPage.jsp");

%>
</body>
</html>