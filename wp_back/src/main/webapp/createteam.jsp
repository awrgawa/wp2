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

String masteruserId = (String)session.getAttribute("userid");


// 폼에서 나머지 팀 데이터 추출
String className = request.getParameter("class_name_input");
String introduction = request.getParameter("team_introduction_input");
String requirement = request.getParameter("team_requirement_input");
int total = Integer.parseInt(request.getParameter("team_num_input"));

// DAO를 통해 데이터베이스에 팀 추가
teamupDAO dao = new teamupDAO();

// Team 객체 생성 및 데이터 설정
boolean result = dao.addTeam(new Teambean(className, masteruserId, introduction, requirement, 1, total));
dao.addMatch(new Matchbean(className+masteruserId,masteruserId,introduction));

if (result) {

	out.println("<script>\r\n"
			+ "      alert(\"성공\");\r\n"
			+ "       location.href= \"mainpage.jsp\";\r\n"
			+ "      </script>");
    
} else {
    // 팀 생성 실패 처리: 에러 메시지 출력
    out.println("<script>\r\n"
			+ "      alert(\"실패\");\r\n"
			+ "       location.href= \"MakeTeam.jsp\";\r\n"
			+ "      </script>");

}

%>
</body>
</html>