<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<% request.setCharacterEncoding("utf-8");%>
<meta charset="UTF-8">
<title>신청서 작성</title>
<link rel="stylesheet" href="SendUserInfo.css?after">
</head>
<body>

<%
	String current_page = request.getParameter("page");
	String team_id=request.getParameter("team_id");
	String search_name = (String)request.getAttribute("search_name");
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
			<a href="pre_page.jsp">마이페이지</a>
		</div>	
		<div class="bar">
			<a href="Logout.jsp">로그아웃</a>
		</div>
		<div class="bar">
			<button id="contact_us_button" type="button" onclick="alert('전화번호 : 000-0000-0000')">Contact us</button>
		</div>
	</nav>
</header>
<main>
	<p><h2>Request</h2></p>
	<form action="apply.jsp" method="post" class="formcontainer">
		<div class="name">
			<p>이름</p>
			<input type="text" name="name_input" id="name_input">
		</div>
		<div class="major">
			<p>학과</p>
			<input type="text" name="major_input" id="major_input">
		</div>
		<div class="student_num">
			<p>학번</p>
			<input type="text" name="student_num_input" id="student_num_input">
		</div>
		<div class="grade">
			<p>학년</p>
			<input type="text" name="grade_input" id="grade_input">
		</div>

		<div class="self_introduction">
			<p>자기 소개</p>
			<textarea name="self_introduction_input" id="self_introduction_input"></textarea>
		</div>
		
		<input type="hidden" name="team_id" value="<%= team_id%>">
		<input type="hidden" value="<%=current_page%>" name="page">
		<input type="hidden" value="<%=search_name%>" name="search_input">
		<input type="submit" value="가입 신청" id="SubmitMakeTeam">
	</form>
</main>

</body>
</html>