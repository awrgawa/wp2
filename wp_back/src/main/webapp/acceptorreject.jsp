<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <%@ page import="com.teamupDB.dao.*" %>
<%@ page import="com.teamupmodels.beans.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% request.setCharacterEncoding("utf-8");%>
<%

	teamupDAO teamDAO = new teamupDAO();


String applicant_id = request.getParameter("want_join_id");
String team_id = request.getParameter("team_id");
String choice = request.getParameter("choice");
 
if(teamDAO.getTeamById(team_id).getMasteruser() != 
		session.getAttribute("userid")) {
	response.getWriter().println("<script>\r\n"
	        + "      alert(\"권한이 없습니다!\");\r\n"
	        + "      window.location.href = 'pre_mypage.jsp';\r\n"
	        + "      </script>");
}

//수락이라면 팀 명단에 추가
if(choice.equals("수락")) {
	if(teamDAO.getTeamById(team_id).getCount()<teamDAO.getTeamById(team_id).getTotal()){
	System.out.print("수락 들어옴");
	Matchbean mb = new Matchbean(team_id, applicant_id, 
			teamDAO.getApply(team_id, applicant_id).getIntro());
	//team_id와 user_id로 Match 생성
	teamDAO.addMatch(mb);
	Teambean t = teamDAO.getTeamById(team_id);
	t.setCount(t.getCount()+1);
	teamDAO.updateTeam(t);
	}
}

//team_id와 user_id가진 Apply 삭제 
teamDAO.deleteApply(team_id, applicant_id);

response.sendRedirect("pre_mypage.jsp");
%>
</body>
</html>