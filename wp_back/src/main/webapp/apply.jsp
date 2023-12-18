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

String name_input = request.getParameter("name_input");
String major_input = request.getParameter("major_input");
String student_num = request.getParameter("student_num_input");
String grade_input = request.getParameter("grade_input");
String self_introduction = request.getParameter("self_introduction_input");
String team_id = request.getParameter("team_id");
String current_page = request.getParameter("page");
String classname = request.getParameter("search_name");

boolean isappliable = false;


// 세션에서 사용자 아이디 가져오기
String userId = (String) session.getAttribute("userid");

// userId가 null이 아닌 경우, 사용자가 로그인한 것으로 간주
if (userId != null) {
    Applybean ap = new Applybean(team_id,userId,self_introduction);
    isappliable = teamDAO.addApply(ap);
    if(isappliable) {
    Userbean user = teamDAO.getUserById(userId);
    
    MemberOfTeambean temp = new MemberOfTeambean(userId, team_id, name_input,
    		user.getPhone(),self_introduction, 
    		grade_input, student_num,major_input);
    teamDAO.addMemberOfTeam(temp);
    }
    else{
    	response.getWriter().println("<script>\r\n"
    	        + "      alert(\"이미 해당 수업의 다른 팀에 참여하고 있습니다\");\r\n"
    	        + "      window.location.href = 'mainpage.jsp';\r\n"
    	        + "      </script>");
    }
} 

// 결과를 요청 속성에 저장



// JSP 페이지로 요청 전달
RequestDispatcher dispatcher = request.getRequestDispatcher("getteambyclassname.jsp");
dispatcher.forward(request, response);


%>
</body>
</html>