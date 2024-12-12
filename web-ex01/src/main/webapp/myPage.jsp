<%@page import="user.model.User"%>
<%@page import="user.model.UserDao"%>
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
if(session.getAttribute("log") == null) {
	response.sendRedirect("/loginForm.jsp");
}
%>

<h2>${log.username}님 환영합니다!</h2>

<%-- 
Update Form 만들기 
action : /updateFormPro.jsp
button :
ㄴ 수정 -> submit 
ㄴ 탈퇴 -> /deleteForm.jsp 로 이동 (비밀번호 검증 후 처리)
ㄴ 로그아웃 -> /logoutPro.jsp 로 이동

부분적으로 수정가능하며 (username, name, birth, gender disabled)
email, phone의 경우에는 unique 검증
--%>
</body>
</html>