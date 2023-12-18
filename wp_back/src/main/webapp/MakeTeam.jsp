<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팀 만들기</title>
<link rel="stylesheet" href="MakeTeam.css">

</head>

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
<body>

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
			<a href="Logout.jsp">로그아웃</a>
		</div>
		<div class="bar">
			<button id="contact_us_button" type="button" onclick="alert('전화번호 : 000-0000-0000')">Contact us</button>
		</div>
	</nav>
</header>
<main>
	<form action="createteam.jsp" method="post" class="formcontainer">
		<div class="class_name">
			<p>수업명</p>
			<input type="text" name="class_name_input" id="class_name_input" placeholder="공백 없이, 영어는 소문자로 입력, 분반은 수업명 뒤에 바로 붙여서 작성">
		</div>
		<div class="team_num">
			<p>팀 인원</p>
			<select name="team_num_input" id="team_num_input">
				<option value="2">2명</option>
				<option value="3">3명</option>
				<option value="4">4명</option>
				<option value="5">5명</option>
				<option value="6">6명</option>
				<option value="7">7명</option>
				<option value="8">8명</option>
				<option value="9">9명</option>
			</select>
		</div>
		<div class="team_introduction">
			<p>팀 소개</p>
			<textarea name="team_introduction_input" id="team_introduction_input" placeholder="팀에 대한 간단한 소개를 작성"></textarea>
		</div>
		<div class="team_requirement">
			<p>팀원 조건</p>
			<textarea name="team_requirement_input" id="team_requirement_input" placeholder="구하고 싶은 팀원의 조건을 작성"></textarea>
		</div>
		<input type="submit" value="파티 만들기" id="SubmitMakeTeam">
	</form>
</main>

</body>
</html>