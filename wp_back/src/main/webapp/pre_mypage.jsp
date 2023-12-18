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

	String user_Id = (String)session.getAttribute("userid");

	String[] masters = null; //팀장의 이름들
	Matchbean[] matches = null;
	Teambean[] teams = null; // 로그인한 사용자가 참가 중인 팀들
	String[] phones = null;
//Need to be implemented..!!
//로그인한 사용자가 가입한 팀들 리턴 + 해당 팀들의 팀장 id
	System.out.print(user_Id+"ㅁㄴㅇㄹ");
	//로그인한 사용자가 속한 팀들의 정보(match)
	matches = teamDAO.getMatchesByUserId(user_Id);
	//match마다, 즉 한 팀 마다 구성원 팀원 배열 존재
	teams = new Teambean[matches.length];
	//위의 팀 배열에 대해 팀장을 알아야 한다.
	masters = new String[matches.length];
	//각 팀의 팀장의 폰 번호
	phones = new String[matches.length];
	System.out.print(matches.length + "ㅇㅇㅇ");
	for(int i = 0;i < matches.length;i++) {
		//match의 팀id를 바탕으로 해당 팀을 찾는다.
		System.out.print("들어왔다.");
		teams[i]= teamDAO.getTeamById(matches[i].getTeamId());
		//찾은 팀의 id를 바탕으로 apply를 구한다.
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
%>
</body>
</html>