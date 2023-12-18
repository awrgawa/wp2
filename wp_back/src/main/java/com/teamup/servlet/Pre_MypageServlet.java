package com.teamup.servlet;

import java.io.IOException;

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
 * Servlet implementation class Pre_MypageServlet
 */
@WebServlet("/Pre_MypageServlet")
public class Pre_MypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private teamupDAO teamDAO; // TeamUpDAO 인스턴스

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pre_MypageServlet() {
        super();
        this.teamDAO = new teamupDAO(); // TeamUpDAO 초기화
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false); // false: 새 세션 생성 방지
    	String user_Id = null;

    	if (session != null) {
    	       user_Id = (String) session.getAttribute("id");
    	}

    	if (user_Id == null) {
    	   // 로그인하지 않은 경우 처리
    	   response.sendRedirect("login.jsp"); // 로그인 페이지로 리디렉션
    	   return; // 메소드 종료
    	}
    	String[] masters = null; //팀장의 이름들
    	Matchbean[] matches = null;
    	Teambean[] teams = null; // 로그인한 사용자가 참가 중인 팀들
    	String[] phones = null;
		//Need to be implemented..!!
    	//로그인한 사용자가 가입한 팀들 리턴 + 해당 팀들의 팀장 id
    	matches = teamDAO.getMatchesByUserId(user_Id);
    	teams = new Teambean[matches.length];
    	masters = new String[matches.length];
    	phones = new String[matches.length];
    	
    	for(int i = 0;i < matches.length;i++) {
    		
    		teams[i]= teamDAO.getTeamById(matches[i].getTeamId());
    		Applybean[] applies = teamDAO.getApplyByTeamId(teams[i].getTeamId());
    		MemberOfTeambean[] mots = new MemberOfTeambean[applies.length];
    		for(int j = 0; j < applies.length; j++) {
    			mots[j]=teamDAO.getMembersOfTeamByUserId(applies[j].getUserId());
    		}
    		teams[i].setApplicants(mots);
    		
    		Matchbean[] mch = teamDAO.getMatchesByTeamId(teams[i].getTeamId());
    		MemberOfTeambean[] mots2 = new MemberOfTeambean[mch.length];
    		for(int j = 0; j < mots2.length; j++) {
    			mots2[j]=teamDAO.getMembersOfTeamByUserId(mch[j].getUserId());
    		}
    		teams[i].setMembers(mots2);
    		
    		masters[i] = teams[i].getMasteruser();
    		phones[i] = teamDAO.getUserById(teams[i].getMasteruser()).getPhone();
    	}
    	
        // 결과를 요청 속성에 저장
        request.setAttribute("leader", masters);
        request.setAttribute("teams", teams);
        request.setAttribute("leader_phone", phones);
        // JSP 페이지로 요청 전달
        RequestDispatcher dispatcher = request.getRequestDispatcher("MyPage.jsp");
        dispatcher.forward(request, response);    
	}

}
