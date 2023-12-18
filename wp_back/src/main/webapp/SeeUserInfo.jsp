<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<% request.setCharacterEncoding("utf-8");%>
<meta charset="UTF-8">
<title>신청서 보기</title>
<link rel="stylesheet" href="SendUserInfo.css?after">
</head>
<body>
<%
	if(session.getAttribute("userid")==null){
		%>
		<script>
		alert("로그인을 먼저 해주세요");
 		location.href= "mainpage.jsp";
		</script>
		
		<%
	}
%>

<%

%>
<header>
	<nav>
		<div class="logo">
			<a href="mainpage.jsp"><img id="logo" src="image/logo2.png"></a>
		</div>
		<div class="bar">
			<a href="MakeTeam.jsp">파티 만들기</a>
		</div>
		<div class="bar">
			<a href="FindTeam_search.jsp">파티찾기</a>
		</div>
		<div class="bar">
			<a href="pre_mypage.jsp">마이페이지</a>
		</div>	
		<div class="bar">
			<button id="contact_us_button" type="button" onclick="alert('전화번호 : 000-0000-0000')">Contact us</button>
		</div>
	</nav>
</header>
<main>
	<p><h2>Request</h2></p>
	<form action="pre_mypage.jsp" method="post" class="formcontainer">
		<div class="name">
			<p>이름</p>
			<input type="text" name="name_input" id="name_input" value="<%=request.getParameter("name") %>" disabled>
		</div>
		<div class="major">
			<p>학과</p>
			<input type="text" name="major_input" id="major_input" value="<%=request.getParameter("major") %>" disabled>
		</div>
		<div class="student_num">
			<p>학번</p>
			<input type="text" name="student_num_input" id="student_num_input" value="<%=request.getParameter("student_num") %>" disabled>
		</div>
		<div class="grade">
			<p>학년</p>
			<input type="text" name="grade_input" id="grade_input" value="<%=request.getParameter("grade") %>" disabled>
		</div>

		<div class="self_introduction">
			<p>자기 소개</p>
			<textarea name="self_introduction_input" id="self_introduction_input" disabled><%=request.getParameter("self_introduction") %></textarea>
		</div>
		

		<input type="submit" value="돌아가기" id="SubmitMakeTeam">
	</form>
</main>

</body>
</html>