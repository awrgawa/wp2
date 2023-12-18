package com.teamup.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teamupDB.dao.teamupDAO;
import com.teamupmodels.beans.*;

/**
 * Servlet implementation class GetTeamsByClassNameServlet
 */
@WebServlet("/GetTeamsByClassNameServlet")
public class GetTeamsByClassNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private teamupDAO teamDAO; // TeamUpDAO 인스턴스   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTeamsByClassNameServlet() {
        super();
        this.teamDAO = new teamupDAO(); // TeamUpDAO 초기화
   }

	

	
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	
	// 세션 가져오기
    HttpSession session = request.getSession();

    // 세션에서 사용자 아이디 가져오기
    String userId = (String) session.getAttribute("userId");
		// 요청으로부터 매개변수 추출
	        
	String className = request.getParameter("search_input");
	String pageinfo = request.getParameter("page");
	    // TeamUpDAO를 사용하여 Team 배열을 가져오는 로직
	Teambean[] teams = null;
	try {
			teams = teamDAO.getTeamsByClassName(className);
		} catch (SQLException e) {
				// TODO Auto-generated catch block
		e.printStackTrace();
	}
			
			//팀 배열들 중에서 사용자가 이미 참여 중인 팀이면은 그 index의 팀의 check = false;
			for(int i = 0; i< teams.length; i++) {
				if(teamDAO.getMatch(teams[i].getTeamId(), userId) != null)
					teams[i].setCheck(false);
				if(teamDAO.getApply(teams[i].getTeamId(), userId) != null)
					teams[i].setCheck(false);
			}
			

	        // 결과를 요청 속성에 저장
	        request.setAttribute("info", teams);
	        request.setAttribute("page", pageinfo);
	        request.setAttribute("search_input",className);
	        // JSP 페이지로 요청 전달
	        RequestDispatcher dispatcher = request.getRequestDispatcher("FindTeam.jsp");
	        dispatcher.forward(request, response);
	    }
}

	

