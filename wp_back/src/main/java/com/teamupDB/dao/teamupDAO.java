package com.teamupDB.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.teamupmodels.beans.*;

public class teamupDAO {
	
	//DB URL
	private String dbUrl = "jdbc:mysql://localhost:3306/TeamUp_DB?serverTimezone=UTC";

	//CONNECT TO DB 
	public Connection getConnection() {
		 try {
	         Class.forName("com.mysql.jdbc.Driver");
	         return DriverManager.getConnection(dbUrl,
	        		 "root", "0000");
	     } catch (SQLException | ClassNotFoundException e) {
	         e.printStackTrace();
	         return null;
	     }
	}
    
	/* USER TABLE CRUD */
	//(CREATE) 새로운 user 등록
	public boolean addUser(Userbean user) {
	    String checkDuplicationQuery = "SELECT COUNT(*) FROM User WHERE id = '"+user.getId()+"'";

	    try  {
	        
	        // 사용자 ID 중복 확인
	    	 Class.forName("com.mysql.jdbc.Driver");
	    	 Connection conn=DriverManager.getConnection(dbUrl,
	        		 "root", "0000");
	    	 Statement pstmtCheck = conn.createStatement();
	     
	        ResultSet rs = pstmtCheck.executeQuery(checkDuplicationQuery);
	        
	        if (rs.next() && rs.getInt(1) > 0) {
	            return false; // 중복되는 ID가 있으면 false 반환
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	        
	        // 중복되는 ID가 없으면 사용자 추가(id, password, name, phone)
	    

	      
	        try  {
	        	
	        	Connection conn = getConnection();

	        	Statement pstmt =conn.createStatement();
	        	String sss = "INSERT INTO User VALUES ('"+user.getId()+"','"+ user.getPassword()+"','" +user.getName()+"','"+ user.getPhone()+"')";
	    
	            int affectedRows = pstmt.executeUpdate(sss);
	            return true;
	        } catch (Exception e1) {
		        e1.printStackTrace();
		        return false;
	        }
	       

	    
	  
	}

    
    //(READ) return user by id
    public Userbean getUserById(String userId) {
        String sql = "SELECT * FROM User WHERE id = '"+userId+"'";
        try  {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection conn = getConnection();
            Statement pstmt = conn.createStatement();

            ResultSet rs = pstmt.executeQuery(sql);
            if (rs.next()) {
                Userbean user = new Userbean(rs.getString("id"),
                		rs.getString("password"),
                		rs.getString("name"),
                		rs.getString("phone"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //(UPDATE) User 정보 업데이트
    public boolean updateUser(Userbean user) {
        String sql = "UPDATE User SET password = ?, name = ?, phone = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPhone());
            pstmt.setString(4, user.getId());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //(DELETE) id로 User 삭제
    public boolean deleteUser(String userId) {
        String sql = "DELETE FROM User WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /* USER TABLE CRUD END */
    
    /* TEAM TABLE CRUD */
    // (CREATE) create team
    public boolean addTeam(Teambean team) {
        String sql = "INSERT INTO Team VALUES ('"+team.getTeamId()+"', '"+team.getClassName()+"', '"+team.getMasteruser()+"', '"+team.getIntro()+"', '"+team.getReq()+"', "+team.getCount()+", "+team.getTotal()+")";
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection conn = getConnection();
            Statement pstmt = conn.createStatement();

            int affectedRows = pstmt.executeUpdate(sql);
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // team객체가아닌 string들로 team 생성
    public boolean addTeam(String class_name, String masteruser_id, String introduction, 
		      String requirement, int count, int total) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
		conn = getConnection(); // 데이터베이스 연결
		
		// 3단계: masteruser_id와 class_name을 바탕으로 makeMatch 함수 수행
		Matchbean match = new Matchbean(class_name + masteruser_id, masteruser_id, introduction);
		if (addMatch(match)) {
		// 4단계: makematch가 true이면 새로운 팀을 만들고 데이터베이스에 저장
		String insertQuery = "INSERT INTO Team (class_name, masteruser_id, introduction, requirement, count, total) VALUES (?, ?, ?, ?, ?, ?)";
		pstmt = conn.prepareStatement(insertQuery);
		pstmt.setString(1, class_name);
		pstmt.setString(2, masteruser_id);
		pstmt.setString(3, introduction);
		pstmt.setString(4, requirement);
		pstmt.setInt(5, count);
		pstmt.setInt(6, total);
		int result = pstmt.executeUpdate();
		return result > 0; // 팀 생성 성공 여부 반환
		}
		return false; // 이미 다른 팀에 속해있어 팀을 만들 수 없는 경우
		} catch (SQLException e) {
		e.printStackTrace();
		return false;
		} finally {
		// 자원 정리
		try {
		if (pstmt != null) pstmt.close();
		if (conn != null) conn.close();
		} catch (SQLException e) {
		e.printStackTrace();
		}
	}
	}
    
    // (READ) return team by id
    public Teambean getTeamById(String teamId) {
        String sql = "SELECT * FROM Team WHERE team_id = '"+teamId+"'";
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection conn = getConnection();
            Statement pstmt = conn.createStatement();
            ResultSet rs = pstmt.executeQuery(sql);
            if (rs.next()) {
                Teambean team = new Teambean(
                		rs.getString("class_name"),
                		rs.getString("masteruser_id"),
                		rs.getString("introduction"),
                		rs.getString("requirement"),
                		rs.getInt("count"),rs.getInt("total"));
                return team;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // (UPDATE) TEAM INFO UPDATE
    public boolean updateTeam(Teambean team) {
        String sql = "UPDATE Team SET class_name = '"+team.getClassName()+"', masteruser_id = '"+team.getMasteruser()+"', introduction = '"+team.getIntro()+"', requirement = '"+team.getReq()+"', count = "+team.getCount()+", total = "+team.getTotal()+" WHERE team_id = '"+team.getTeamId()+"'";
        try  {
        	Connection conn = getConnection();
            Statement pstmt = conn.createStatement();

            int affectedRows = pstmt.executeUpdate(sql);
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // (DELETE) teamid로 Team 정보 삭제
    public boolean deleteTeam(String teamId) {
        String sql = "DELETE FROM Team WHERE team_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, teamId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /* TEAM TABLE CRUD END */
    
    /* MATCH TABLE CRUD */
    // (CREATE) add match
    public boolean addMatch(Matchbean match) {
        Connection conn = null;
        Statement pstmt = null;
        ResultSet rs = null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection(); // 데이터베이스 연결

            // 1단계: team_id를 바탕으로 class_name 추출
            String classQuery = "SELECT class_name FROM Team WHERE team_id = '"+match.getTeamId()+"'";
            pstmt = conn.createStatement();

            rs = pstmt.executeQuery(classQuery);
            if (rs.next()) {
                String className = rs.getString("class_name");

                // 2단계: user_id와 class_name을 바탕으로 isUserInClassTeam 함수를 이용해 검사
                if (!isUserInClassTeam(match.getUserId(), className)) {
                    // 3단계: false일 경우 매치 생성
                    String insertQuery = "INSERT INTO `Match` (team_id, id, intro) VALUES ('"+match.getTeamId()+"', '"+match.getUserId()+"', '"+match.getIntro()+"')";
                    Statement pstmtInsert = conn.createStatement();
                    int result = pstmtInsert.executeUpdate(insertQuery);
                    return result > 0; // 매치 생성 성공 여부 반환
                }
            }
            return false; // 이미 다른 팀에 속해있거나, class_name을 찾을 수 없는 경우
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            // 자원 정리
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // (READ) Return MATCH by teamId and userId
    public Matchbean getMatch(String teamId, String userId) {
        String query = "SELECT * FROM `Match` WHERE team_id = '"+teamId+"' AND id = '"+userId+"'";
        try  {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection conn = getConnection();
            Statement pstmt = conn.createStatement();
            try (ResultSet rs = pstmt.executeQuery(query)) {
                if (rs.next()) {
                    return new Matchbean(rs.getString("team_id"), rs.getString("id"),
                                     rs.getString("intro"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Matchbean[] getMatchesByUserId(String userId) {
        String query = "SELECT * FROM `Match` WHERE id = '"+userId+"'";
        List<Matchbean> matches = new ArrayList<>();

        try  {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection conn = getConnection();
            Statement pstmt = conn.createStatement();


            try (ResultSet rs = pstmt.executeQuery(query)) {
                while (rs.next()) {
                    Matchbean match = new Matchbean(rs.getString("team_id"), rs.getString("id"),
                                                    rs.getString("intro"));
                    matches.add(match);
                }
                rs.close();
            }
            pstmt.close();
            conn.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        // Convert the list to an array and return
        return matches.toArray(new Matchbean[0]);
    }
    
    public Matchbean[] getMatchesByTeamId(String teamId) {
        String query = "SELECT * FROM `Match` WHERE team_id = '"+teamId+"'";
        List<Matchbean> matches = new ArrayList<>();

        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection conn = getConnection();
            Statement pstmt = conn.createStatement();

            try (ResultSet rs = pstmt.executeQuery(query)) {
                while (rs.next()) {
                    Matchbean match = new Matchbean(rs.getString("team_id"), rs.getString("id"),
                                                    rs.getString("intro"));
                    matches.add(match);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Convert the list to an array and return
        return matches.toArray(new Matchbean[0]);
    }
    // (UPDATE) MATCH INFO UPDATE
    public boolean updateMatch(Matchbean match) {
        String query = "UPDATE `Match` SET intro = ? WHERE team_id = ? AND id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, match.getIntro());
            pstmt.setString(2, match.getTeamId());
            pstmt.setString(3, match.getUserId());
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // (DELETE) teamid와 userid로 match 삭제
    public boolean deleteMatch(String teamId, String userId) {
        String query = "DELETE FROM `Match` WHERE team_id = '"+teamId+"' AND id = '"+userId+"'";
        try  {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection conn = getConnection();
            Statement pstmt = conn.createStatement();

            int result = pstmt.executeUpdate(query);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /* MATCH TABLE CRUD END */
    
    /* APPLY TABLE CRUD */
    // (CREATE) ADD APPLY
    public boolean addApply(Applybean apply) {
        Connection conn = null;
        Statement pstmt = null;
        ResultSet rs = null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection(); // 데이터베이스 연결

            // 1단계: team_id를 바탕으로 class_name 추출
            String classQuery = "SELECT class_name FROM Team WHERE team_id = '" + apply.getTeamId()+"'";
            pstmt = conn.createStatement();

            rs = pstmt.executeQuery(classQuery);
            if (rs.next()) {
                String className = rs.getString("class_name");

                // 2단계: user_id와 class_name을 바탕으로 isUserInClassTeam 함수를 이용해 검사
                if (!isUserInClassTeam(apply.getUserId(), className)) {
                
                    // 3단계: false일 경우 매치 생성
                    String insertQuery = "INSERT INTO Apply (team_id, id, intro) VALUES ('"+apply.getTeamId()+"','"+ apply.getUserId()+"','"+apply.getIntro()+"')";
                    Statement pstmtInsert = conn.createStatement();
                   
                    int result = pstmtInsert.executeUpdate(insertQuery);
                    return result > 0; // 매치 생성 성공 여부 반환
                }
            }
            return false; // 이미 다른 팀에 속해있거나, class_name을 찾을 수 없는 경우
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            // 자원 정리
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // (READ) GET APPLY BY TEAMID AND USERID
    public Applybean getApply(String teamId, String userId) {
        String query = "SELECT * FROM Apply WHERE team_id = '"+teamId+"' AND id = '"+userId+"'";
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection conn = getConnection();
             Statement pstmt = conn.createStatement();
   
            try (ResultSet rs = pstmt.executeQuery(query)) {
            	
                if (rs.next()) {
                    return new Applybean(rs.getString("team_id"), rs.getString("id"),
                                     rs.getString("intro"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Applybean[] getApplyByTeamId(String userId) {
        String query = "SELECT * FROM Apply WHERE team_id = '"+userId+"'";
        List<Applybean> applies = new ArrayList<>();

        try  {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection conn = getConnection();
            Statement pstmt = conn.createStatement();


            try (ResultSet rs = pstmt.executeQuery(query)) {
                while (rs.next()) {
                    Applybean match = new Applybean(rs.getString("team_id"), rs.getString("id"),
                                                    rs.getString("intro"));
                    applies.add(match);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Convert the list to an array and return
        return applies.toArray(new Applybean[0]);
    }
    
    // (UPDATE) APPLY INFO UPDATE
    public boolean updateApply(Applybean apply) {
        String query = "UPDATE Apply SET intro = ? WHERE team_id = ? AND id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, apply.getIntro());
            pstmt.setString(2, apply.getTeamId());
            pstmt.setString(3, apply.getUserId());
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // (DELETE) DELETE APPLY
    public boolean deleteApply(String teamId, String userId) {
        String query = "DELETE FROM Apply WHERE team_id = '"+teamId+"' AND id = '"+userId+"'";
        try  {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection conn = getConnection();
            Statement pstmt = conn.createStatement();
            int result = pstmt.executeUpdate(query);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /* APPLY TABLE CRUD END */
    
    /* MEMBEROFTEAM TABLE CRUD */
    // (CREATE) 
    public boolean addMemberOfTeam(MemberOfTeambean member) {
        String query = "INSERT INTO MemberOfTeam (id, team_id, name, phone, intro, grade, studentNum, major) VALUES ('"
    +member.getMem_id()+"', '"+member.getMem_teamid()+
    "', '"+member.getMem_name()+"', '"+
    member.getMem_phone()+"', '"+
    member.getMem_intro()+"', '"+member.getMem_Grade()+
    "', '"+member.getMem_studentNum()+"', '"+
    member.getMem_Major()+"')";
        try  {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection conn = getConnection();
            Statement pstmt = conn.createStatement();
       
            int result = pstmt.executeUpdate(query);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // (READ) RETURN MEMOFTEAM BY ID AND TEAMID
    public MemberOfTeambean getMemberOfTeam(String id, String teamId) {
        String query = "SELECT * FROM MemberOfTeam WHERE id = ? AND team_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.setString(2, teamId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new MemberOfTeambean(rs.getString("id"), rs.getString("team_id"),
                                            rs.getString("name"), rs.getString("phone"),
                                            rs.getString("intro"), rs.getString("grade"),
                                            rs.getString("studentNum"), rs.getString("major"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
 // (READ 2) Return all MemberOfTeam records for a given userId as an array
    public MemberOfTeambean getMembersOfTeamByUserId(String userId) {
        String query = "SELECT * FROM MemberOfTeam WHERE id = '"+userId+"'";
        MemberOfTeambean members = new MemberOfTeambean();

        try  {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection conn = getConnection();
            Statement pstmt = conn.createStatement();


            try (ResultSet rs = pstmt.executeQuery(query)) {
                while (rs.next()) {
                    MemberOfTeambean member = new MemberOfTeambean(rs.getString("id"), rs.getString("team_id"),
                                                                  rs.getString("name"), rs.getString("phone"),
                                                                  rs.getString("intro"), rs.getString("grade"),
                                                                  rs.getString("studentNum"), rs.getString("major"));
                    members = member;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Convert the list to an array and return
        return members;
    }
    // (UPDATE) UPDATE MEMBEROFTEAM INFO
    public boolean updateMemberOfTeam(MemberOfTeambean member) {
        String query = "UPDATE MemberOfTeam SET team_id = ?, name = ?, phone = ?, intro = ?, grade = ?, studentNum = ?, major = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, member.getMem_teamid());
            pstmt.setString(2, member.getMem_name());
            pstmt.setString(3, member.getMem_phone());
            pstmt.setString(4, member.getMem_intro());
            pstmt.setString(5, member.getMem_Grade());
            pstmt.setString(6, member.getMem_studentNum());
            pstmt.setString(7, member.getMem_Major());
            pstmt.setString(8, member.getMem_id());
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // (DELETE) DELETE MEMBEROFTEAM INFO
    public boolean deleteMemberOfTeam(String id, String teamId) {
        String query = "DELETE FROM MemberOfTeam WHERE id = '"+id+"' AND team_id = '"+teamId+"'";
        try  {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection conn = getConnection();
            Statement pstmt = conn.createStatement();

            int result = pstmt.executeUpdate(query);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /* MEMBEROFTEAM TABLE CRUD END */
    
    /***************************************************************************************************/
    
    /* 기타 연산 */
    
    /***************************************************************************************************/
    
    //수업명을 바탕으로 해당 수업의 팀 배열 리턴
    public Teambean[] getTeamsByClassName(String className) throws SQLException {
        List<Teambean> teamList = new ArrayList<>();
        String sql = "SELECT * FROM Team WHERE class_name = '"+className+"'";

        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection conn = getConnection();
            Statement pstmt = conn.createStatement();

            try (ResultSet rs = pstmt.executeQuery(sql)) {
                while (rs.next()) {
                    Teambean team = new Teambean(rs.getString("class_name"),rs.getString("masteruser_id"),rs.getString("introduction"),rs.getString("requirement"),rs.getInt("count"),rs.getInt("total"));
                    
                    teamList.add(team);
                }
            }
        }
        catch(Exception e) {
        	e.printStackTrace();
            return null;
        }
        // 리스트를 배열로 변환
        Teambean[] teams = new Teambean[teamList.size()];
        teams = teamList.toArray(teams);

        return teams;
    }

    //해당 유저가 그 수업의 어떤 팀에라도 속해있는지를 검사
    public boolean isUserInClassTeam(String userId, String className) {
        Connection conn = null;
        Statement pstmt = null;
        ResultSet rs = null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection(); // 데이터베이스 연결 메소드 호출
            // 1단계: class_name을 바탕으로 Team 테이블에서 해당 수업의 팀들을 조회
            String teamQuery = "SELECT team_id FROM Team WHERE class_name = '"+className+"'";
            pstmt = conn.createStatement();            
            rs = pstmt.executeQuery(teamQuery);
            while (rs.next()) {
                String teamId = rs.getString("team_id");

                // 2단계: 조회된 team_id와 넘겨받은 user_id를 이용하여 Match 테이블에서 확인
                String matchQuery = "SELECT COUNT(*) FROM `Match` WHERE team_id = '"+teamId+"' AND id = '"+userId+"'";
                Statement pstmtMatch = conn.createStatement();

                ResultSet rsMatch = pstmtMatch.executeQuery(matchQuery);
                if (rsMatch.next() && rsMatch.getInt(1) > 0) {
                    return true; // 사용자가 해당 클래스의 팀에 속해 있음
                }
            }
            return false; // 사용자가 해당 클래스의 어떤 팀에도 속해있지 않음
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            // 자원 정리
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    //로그인 성공 여부(id에 따른 비밀번호 일치 여부 검사)
    public boolean login(String userId, String password) {
        String query = "SELECT password FROM User WHERE id = '"+userId+"'";
        try  {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection conn = getConnection();
            Statement pstmt = conn.createStatement();

            ResultSet rs = pstmt.executeQuery(query);

            if (rs.next()) {
                String dbPassword = rs.getString("password");
                return dbPassword.equals(password); // 비밀번호 비교
            } else {
                return false; // 사용자 ID가 없음
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
   
}


