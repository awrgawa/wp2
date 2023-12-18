package com.teamup.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teamupDB.dao.teamupDAO;
import com.teamupmodels.beans.*;

/**
 * Servlet implementation class AcceptOrRejectServlet
 */
@WebServlet("/AcceptOrRejectServlet")
public class AcceptOrRejectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private teamupDAO teamDAO; // TeamUpDAO 인스턴스

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptOrRejectServlet() {
        super();
        this.teamDAO = new teamupDAO(); 
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청으로부터 매개변수 추출
    	HttpSession session = request.getSession(false);
    	
    	String applicant_id = request.getParameter("want_join_id");
    	String team_id = request.getParameter("team_id");
    	String choice = request.getParameter("choice");
 	    
    	if(teamDAO.getTeamById(team_id).getMasteruser() != 
    			session.getAttribute("userid")) {
    		response.getWriter().println("<script>\r\n"
        	        + "      alert(\"권한이 없습니다!\");\r\n"
        	        + "      window.location.href = '프리마이페이지서블릿';\r\n"
        	        + "      </script>");
    	}
        
    	//수락이라면 팀 명단에 추가
    	if(choice == "수락") {
        	Matchbean mb = new Matchbean(team_id, applicant_id, 
        			teamDAO.getApply(team_id, applicant_id).getIntro());
        	//team_id와 user_id로 Match 생성
        	teamDAO.addMatch(mb);
        }
        
    	//team_id와 user_id가진 Apply 삭제 
        teamDAO.deleteApply(team_id, applicant_id);
        
        response.sendRedirect("프리마이페이지서블릿");
    }
}


