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
	teamupDAO teamDAO;
	teamDAO = new teamupDAO(); 
	
	String id = request.getParameter("id");
    String password = request.getParameter("password");
    
    if(teamDAO.login(id, password)) {
    	
    	session.setAttribute("userid", id);
        session.setAttribute("userpw", password);
        Cookie cookie = new Cookie("userid", id);
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        response.addCookie(cookie);
        String name = teamDAO.getUserById((String)session.getAttribute("userid")).getName();
        
        response.getWriter().println("<script>\r\n"
    	        + "      alert(\"로그인 성공! "+name+"님 환영합니다!\");\r\n"
    	        + "      window.location.href = 'mainpage.jsp';\r\n"
    	        + "      </script>");
    }
    else {
    	response.getWriter().println("<script>\r\n"
    	        + "      alert(\"유효하지 않은 ID 혹은 비밀번호입니다!\");\r\n"
    	        + "      window.location.href = 'mainpage.jsp';\r\n"
    	        + "      </script>");
    }
%>
</body>
</html>