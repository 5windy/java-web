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

System.out.println("username :" + username);
System.out.println("password :" + password);
System.out.println("email :" + email);
System.out.println("name :" + name);
System.out.println("birth :" + birth);
System.out.println("telecom :" + telecom);
System.out.println("gender :" + gender);
System.out.println("country :" + country);
System.out.println("phone :" + phone);
System.out.println("agree :" + agree);
%>
</body>
</html>