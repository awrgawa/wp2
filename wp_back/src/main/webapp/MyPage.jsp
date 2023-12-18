<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.teamupmodels.beans.*" %>

<% request.setCharacterEncoding("utf-8");%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>마이 페이지</title>
<link rel="stylesheet" href="MyPage.css?after">
</head>

<%
	Teambean[] team = (Teambean[])request.getAttribute("teams");
	String[] leader=(String[])request.getAttribute("leader");
	String[] leader_phone=(String[])request.getAttribute("leader_phone");
%>

<body>


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

<%
	for(int i=0; i < team.length;i++){

%>
	<div class="team">
		<div class="class_name"><%=team[i].getClassName() %></div>
		<div class="exit">
			<form action="quitteam.jsp" method="post" class="exit_form">
				<input type="hidden" value="<%=team[i].getTeamId() %>" name="team_id">
				<input type="submit" value="탈퇴" name="exit" class="exit_button">
			</form>	
		</div>
		<br><br>
		<div class="for_flex">
			<div class="team_total_member">
				<div class="team_leader">팀장 : <%=leader[i] %> hp : <%=leader_phone[i]%></div>
				<br>
				<div class="team_members">

				<% for(int j=1; j< team[i].getMembers().length ; j++){ %>
					<div class="team_member">팀원 <%=j %> : <a href="SeeUserInfo.jsp?name=<%=team[i].getMembers()[j].getMem_name() %>&major=<%=team[i].getMembers()[j].getMem_Major() %>&student_num=<%=team[i].getMembers()[j].getMem_studentNum() %>&grade=<%=team[i].getMembers()[j].getMem_Grade() %>&self_introduction=<%=team[i].getMembers()[j].getMem_intro() %>"><%=team[i].getMembers()[j].getMem_name() %></a> hp : <%=team[i].getMembers()[j].getMem_phone() %></div>
				<%} %>

				</div>
			</div>
			<div class="accept_team">
				<div class="title">신청자</div>
				<br>
				<div class="want_team_boader">

			<%for(int j=0; j<team[i].getApplicants().length;j++){ %>
					<div class="want_team">
						<div class="want_id">신청자<%=j+1 %> : <a href="SeeUserInfo.jsp?name=<%=team[i].getApplicants()[j].getMem_name() %>&major=<%=team[i].getApplicants()[j].getMem_Major() %>&student_num=<%=team[i].getApplicants()[j].getMem_studentNum() %>&grade=<%=team[i].getApplicants()[j].getMem_Grade() %>&self_introduction=<%=team[i].getApplicants()[j].getMem_intro() %>"><%=team[i].getApplicants()[j].getMem_name() %></a> hp : <%=team[i].getApplicants()[j].getMem_phone() %></div>
						<form action="acceptorreject.jsp" method="get" class="want_form">
							<input type="hidden" value="<%=team[i].getApplicants()[j].getMem_id() %>" name="want_join_id">
							<input type="hidden" value="<%=team[i].getTeamId() %>" name="team_id">
							<input type="submit" value="거절" name="choice" class="choice_button">
							<input type="submit" value="수락" name="choice" class="choice_button">
						</form>
					</div>
				<%} %>	
				</div>
			</div>
		</div>
		<br>
	</div>
	<%
	}
	%>

</main>

</body>
</html>