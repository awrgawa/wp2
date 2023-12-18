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
	teamupDAO teamDAO=new teamupDAO();
	String team_id = request.getParameter("team_id");
 
// 세션 가져오기

// 세션에서 사용자 아이디 가져오기
	String userId = (String) session.getAttribute("userid");

//탈퇴하려는 사람이 팀장이면 안 된다.
	if(teamDAO.getTeamById(team_id).getMasteruser().equals(userId)) {
		out.println("<script>\r\n"
			+ "      alert(\"팀장은 탈퇴할 수 없습니다!\");\r\n"
			+ "      </script>");
		
	}
	else {
		teamDAO.deleteMatch(team_id, userId);
		teamDAO.deleteMemberOfTeam(userId, team_id);
		Teambean t = teamDAO.getTeamById(team_id);
		t.setCount(t.getCount()-1);
		teamDAO.updateTeam(t);
	}

	//
	out.print("<script>\r\n"
			+ "      window.location.href = 'pre_mypage.jsp';\r\n"
			+ "      </script>");

%>
</body>
</html>