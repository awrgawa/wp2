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


// 세션에서 사용자 아이디 가져오기
String userId = (String) session.getAttribute("userid");
	// 요청으로부터 매개변수 추출
        
String className = request.getParameter("search_input");

    // TeamUpDAO를 사용하여 Team 배열을 가져오는 로직
Teambean[] teams = null;
try {
		teams = teamDAO.getTeamsByClassName(className);
	} catch (Exception e) {
			// TODO Auto-generated catch block
	e.printStackTrace();
			
}
		if(teams!=null){
		//팀 배열들 중에서 사용자가 이미 참여 중인 팀이면은 그 index의 팀의 check = false;
		
			for(int i = 0; i< teams.length; i++) {
				if(teamDAO.getMatch(teams[i].getTeamId(), userId) != null)
					teams[i].setCheck(false);
				if(teamDAO.getApply(teams[i].getTeamId(), userId) != null)
					teams[i].setCheck(false);
			}
		
		}
		

        // 결과를 요청 속성에 저장
        request.setAttribute("info", teams);

        // JSP 페이지로 요청 전달
        RequestDispatcher dispatcher = request.getRequestDispatcher("FindTeam.jsp");
        dispatcher.forward(request, response);
    


%>
</body>
</html>