<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.teamupmodels.beans.*" %>
<% request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>팀 찾기</title>
<link rel="stylesheet" href="FindTeam.css?after">

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
	Teambean[] info=(Teambean[])request.getAttribute("info");
	
	

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
		<form action="getteambyclassname.jsp" method="get" id="search_bar_form">
			<input type="hidden" name="page" value="0">
			<input type="text" name="search_input" id="search_input" value="">
			<input type="submit" id="search_submit"  value="">
		</form>
	</div>
	<div class="select_teams">
	<%
	if(info!=null){
		for(int i=0;i<info.length;i++){
			if(i<info.length){
	%>
	
		<div class="select_team">
			<p class="team_num" style="color: #5F5E61; font-family: 'Poppins';"><%=info[i].getCount() %>/<%=info[i].getTotal() %></p>
			<p class="class_name" style="color: #5F5E61; font-family: 'Poppins';"><%=info[i].getClassName() %><p/>
			<p style="color: #5F5E61; font-family: 'Poppins';">팀 소개</p>
			<div class="print_team_introduction"><%=info[i].getIntro() %></div>
			<p style="color: #5F5E61; font-family: 'Poppins';">팀원 조건</p>
			<div class="print_team_requirement"><%=info[i].getReq() %></div>
			<%if(info[i].getCheck()){//check=false면 신청불가%>
			<form action="SendUserInfo.jsp" method="post" class="select_team_form">
				<input type="hidden" value="<%=info[i].getTeamId() %>" name="team_id">
				<input type="submit" class="select_team_submit" value="">
			</form>
			<%
				}
			else{
			%>
			<div class="select_complete">신청 불가</div>
			<%
			}
			
			%>
			
		</div>	
	<%
			}
		}
	
	%>

		<%} %>
</main>

</body>
</html>