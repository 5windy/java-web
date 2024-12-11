<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index page</title>
</head>
<body>
<h1>Index Page</h1>

<%
// JNDI : Java Naming and Directory Interface

// Database 연동 
Connection conn = null;

Context initCtx = new InitialContext();
Context envCtx = (Context) initCtx.lookup("java:comp/env");

DataSource ds = (DataSource) envCtx.lookup("jdbc/SampleDB");
conn = ds.getConnection();

if(conn != null) {
	System.out.println("Sample DB 연동성공!");
} else {
	System.err.println("Sample DB 연동실패...");	
}

%>
</body>
</html>