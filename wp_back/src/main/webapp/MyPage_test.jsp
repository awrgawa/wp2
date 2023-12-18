<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.util.*" %>
<%@ page import="com.teamupmodels.beans.*" %>
<% request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	
	Teambean[] infoa= new Teambean[3];
	infoa[0] = new Teambean();
	
	

	request.setAttribute("info",infoa);

%>
<jsp:forward page="MyPage.jsp"></jsp:forward>
</body>
</html>