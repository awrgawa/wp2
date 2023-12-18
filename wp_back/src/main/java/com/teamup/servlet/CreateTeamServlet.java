package com.teamup.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teamupDB.dao.teamupDAO;
import com.teamupmodels.beans.*;
/**
 * Servlet implementation class CreateTeamServlet
 */
@WebServlet("/CreateTeamServlet")
public class CreateTeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTeamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	HttpSession session = request.getSession(false); // false: 새 세션 생성 방지
    	String masteruserId = null;

    	    if (session != null) {
    	        masteruserId = (String) session.getAttribute("id");
    	    }

    	    if (masteruserId == null) {
    	        // 로그인하지 않은 경우 처리
    	        response.sendRedirect("login.jsp"); // 로그인 페이지로 리디렉션
    	        return; // 메소드 종료
    	    }

        // 폼에서 나머지 팀 데이터 추출
        String className = request.getParameter("class_name_input");
        String introduction = request.getParameter("team_introduction_input");
        String requirement = request.getParameter("team_requirement_input");
        int total = Integer.parseInt(request.getParameter("team_num_input"));

        // DAO를 통해 데이터베이스에 팀 추가
        teamupDAO dao = new teamupDAO();
        
        // Team 객체 생성 및 데이터 설정
        boolean result = dao.addTeam(className, masteruserId, introduction, requirement, 1, total);
        
        if (result) {
        	PrintWriter out = response.getWriter();
        	out.println("<script>\r\n"
        			+ "      alert(\"성공\");\r\n"
        			+ "       location.href= \"mainpage.jsp\";\r\n"
        			+ "      </script>");
            
        } else {
            // 팀 생성 실패 처리: 에러 메시지 출력
            response.sendRedirect("MakeTeam.jsp"); 
        }
    }

}
