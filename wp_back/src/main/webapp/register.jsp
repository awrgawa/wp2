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
String id = request.getParameter("username");
String password = request.getParameter("password");
String confirmPassword = request.getParameter("confirmPassword");
String name = request.getParameter("username");
String phone = request.getParameter("phone");

// 비밀번호 일치 확인
if (!password.equals(confirmPassword)) {
    // 비밀번호가 일치하지 않을 경우의 처리
	response.getWriter().println("<script>\r\n"
	        + "      alert(\"패스워드가 일치하지 않습니다!\");\r\n"
	        + "      window.location.href = 'mainpage.jsp';\r\n"
	        + "      </script>");
}

// User 객체 생성 및 데이터 설정
Userbean newUser = new Userbean(id, password, name, phone);

// DAO를 통해 데이터베이스에 사용자 추가
teamupDAO dao = new teamupDAO();
boolean result = dao.addUser(newUser);

if (result) {
    // 회원가입 성공 처리: 로그인 페이지나 메인 페이지로 리디렉션
    response.getWriter().println("<script>\r\n"
	        + "      alert(\"회원가입에 성공했습니다!\");\r\n"
	        + "      window.location.href = 'mainpage.jsp';\r\n"
	        + "      </script>");
    
} else {
    // 회원가입 실패 
    response.getWriter().println("<script>\r\n"
	        + "      alert(\"이미 존재하는 ID입니다!\");\r\n"
	        + "      window.location.href = 'mainpage.jsp';\r\n"
	        + "      </script>");
}


%>

</body>
</html>