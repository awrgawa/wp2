package com.teamup.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teamupDB.dao.teamupDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 private teamupDAO teamDAO; // TeamUpDAO 인스턴스
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.teamDAO = new teamupDAO(); 
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 // 폼에서 데이터 추출
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        
        if(teamDAO.login(id, password)) {
        	HttpSession session = request.getSession();
        	
        	session.setAttribute("userid", id);
            session.setAttribute("userpw", password);
            Cookie cookie = new Cookie("userid", id);
            cookie.setMaxAge(3600);
            cookie.setPath("/");
            response.addCookie(cookie);
            System.out.println(session.getAttribute("userid"));
            response.getWriter().println("<script>\r\n"
        	        + "      alert(\"유효하지 않은 ID 혹은 비밀번호입니다!\");\r\n"
        	        + "      window.location.href = 'MainPage.jsp';\r\n"
        	        + "      </script>");
        }
        else {
        	response.getWriter().println("<script>\r\n"
        	        + "      alert(\"유효하지 않은 ID 혹은 비밀번호입니다!\");\r\n"
        	        + "      window.location.href = 'MainPage.jsp';\r\n"
        	        + "      </script>");
        }
	}
}
