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
/**
 * Servlet implementation class QuitTeamServlet
 */
@WebServlet("/QuitTeamServlet")
public class QuitTeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private teamupDAO teamDAO; // TeamUpDAO 인스턴스
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuitTeamServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.teamDAO = new teamupDAO();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String team_id = request.getParameter("team_id");
 	   
		// 세션 가져오기
        HttpSession session = request.getSession();

        // 세션에서 사용자 아이디 가져오기
        String userId = (String) session.getAttribute("userId");
        
        //탈퇴하려는 사람이 팀장이면 안 된다.
        if(teamDAO.getTeamById(team_id).getMasteruser() != userId) {
        teamDAO.deleteMatch(team_id, userId);
        teamDAO.deleteMemberOfTeam(userId, team_id);
        }
        else {
        	PrintWriter out = response.getWriter();
        	out.println("<script>\r\n"
        			+ "      alert(\"팀장은 탈퇴할 수 없습니다!\");\r\n"
        			+ "      </script>");
        }
        response.sendRedirect("Pre_MypageServlet.java");
	}

}
