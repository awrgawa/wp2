<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>팀 찾기</title>
<link rel="stylesheet" href="FindTeam.css">
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
	//탐찾기에 들어가면 이 페이지를 거치고 FindTeam.jsp로

%>
<header>
	<nav>
		<div class="logo">
			<a href="mainpage.jsp"><img id="logo" src="./image/logo2.png"></a>
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
			<a href="Logout.jsp">로그아웃</a>
		</div>
		<div class="bar">
			<button id="contact_us_button" type="button" onclick="alert('전화번호 : 000-0000-0000')">Contact us</button>
		</div>
	</nav>
</header>
<main>
	<div class="search_bar">
		<form action="getteambyclassname.jsp" method="post" id="search_bar_form">

			<input type="text" name="search_input" id="search_input">
			<input type="submit" id="search_submit"  value="">
		</form>
	</div>
</main>

</body>
</html>