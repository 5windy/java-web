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
// JSP 내장객체의 유효기간 (생명주기 = Scope)
// 1) page			: 하나의 페이지 단위로 처리 (페이지 변경 시 소멸)
// 2) request		: 하나의 요청 단위로 처리 (요청이 끝나면 소멸)
// 3) session		: 하나의 브라우저 단위로 처리 (브라우저가 닫히면 소멸)
// 4) application 	: 하나의 애플리케이션 단위로 처리 (서버가 종료되면 소멸)

pageContext.setAttribute("pageData", "페이지 데이터");
request.setAttribute("requestData", "요청 데이터");
session.setAttribute("sessionData", "세션 데이터");
application.setAttribute("applicationData", "앱 데이터");

// pageContext.setAttribute("data", "페이지 데이터");
request.setAttribute("data", "요청 데이터");
session.setAttribute("data", "세션 데이터");
application.setAttribute("data", "앱 데이터");

// request.getRequestDispatcher("/scope2.jsp").forward(request, response);

%>

<p>pageData : <%=pageContext.getAttribute("pageData") %></p>
<p>requestData : <%=request.getAttribute("requestData") %></p>
<p>sessionData : <%=session.getAttribute("sessionData") %></p>
<p>applicationData : <%=application.getAttribute("applicationData") %></p>

<%--
Expression Language Tag (EL Tag)
${} 영역 안에 변수 등을 작성 

*주의사항: 
Javascript에서 사용은 Tag와 동일하기 때문에, 
JSP 문서에서는 <script></script>태그를 직접 작성하지 않는다!
--%>

<br>
<p>${pageScope.pageData}</p>
<p>${requestScope.requestData}</p>
<p>${sessionScope.sessionData}</p>
<p>${applicationScope.applicationData}</p>

<br>
<p>${pageData}</p>
<p>${requestData}</p>
<p>${sessionData}</p>
<p>${applicationData}</p>

<br>
<p>data : ${data} (생명주기가 짧은 순서대로 참조의 우선순위가 발생함)</p>


<br>
<p>파라미터 값 가져오기 : ${param.username}</p>

<%=request.getParameter("username") %>

</body>
</html>