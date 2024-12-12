<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>pageData : <%=pageContext.getAttribute("pageData") %></p>
<p>requestData : <%=request.getAttribute("requestData") %></p>
<p>sessionData : <%=session.getAttribute("sessionData") %></p>
<p>applicationData : <%=application.getAttribute("applicationData") %></p>
</body>
</html>