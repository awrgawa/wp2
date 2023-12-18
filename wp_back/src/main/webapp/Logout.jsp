<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Logout</title>
</head>
<body>
<%
    
    if (session.getAttribute("userid") != null) {
        // 세션을 무효화시킵니다.
        session.invalidate();
        response.getWriter().println("<script>\r\n"
    	        + "      alert(\"로그아웃 되었습니다.\");\r\n"
    	        + "      window.location.href = 'mainpage.jsp';\r\n"
    	        + "      </script>");
    }
response.getWriter().println("<script>\r\n"
        + "      window.location.href = 'mainpage.jsp';\r\n"
        + "      </script>");
    // 로그아웃 후 리디렉션하거나 메시지를 표시할 수 있습니다.
    //response.sendRedirect("mainpage.jsp"); // 로그인 페이지 또는 홈페이지로 리디렉션
    // 또는
    // out.println("<p>You have been successfully logged out.</p>");
%>
</body>
</html>