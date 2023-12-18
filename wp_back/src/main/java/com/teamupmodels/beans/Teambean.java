package com.teamupmodels.beans;

public class Teambean {
	private String team_id;	//팀 고유 id
	private String class_name;	//팀의 수업이름
	private String masteruser_id; 	//팀장의 아이디. user테이블의 id필드
	private String introduction;	//팀 소개
	private String requirement;	//팀원 조건
	private int count; 		//현재 팀 인원
	private int total;		//최대 파티 인원
	
	private MemberOfTeambean[] members;	//팀에 속해있는 팀장을 제외한 팀원들
	private MemberOfTeambean[] applicants;//팀에 지원하여 수락을 대기하는 사람들
	private int apply_index;
	private boolean check;
	
    public Teambean() {}
    public Teambean(String class_name, 
    		String masteruser_id, String introduction, String requirement,
    		int count, int total) {
    	this.team_id = class_name + masteruser_id;
    	this.class_name = class_name;
    	this.masteruser_id = masteruser_id;
    	this.introduction = introduction;
    	this.requirement = requirement;
    	this.count = count;
    	this.total = total;
    	members = null;
    	applicants = null;
    	apply_index = 0;
    	check = true;
    }
    // Getter와 Setter 메소드
    public String getTeamId() { return team_id; }

    public void setTeamId(){this.team_id =class_name + masteruser_id;}

    public String getClassName() {return class_name;}
    
    public void setClassName(String class_name) {this.class_name = class_name;}

    public String getMasteruser() {return masteruser_id;}

    public void setMasteruser(String masteruser_id) {
        this.masteruser_id = masteruser_id;
    }
    
    public String getIntro() {return introduction;}

    public void setIntro(String introduction) {
        this.introduction = introduction;
    }
    
    public String getReq() {return requirement;}

    public void setReq(String req) {
        this.requirement = req;
    }
    
    public int getCount() {return count;}
    
    public void setCount(int count) {this.count = count;}
    
    public int getTotal() {return total;}
    
    public void setTotal(int tot) {this.total = tot;}
    
    public MemberOfTeambean[] getApplicants() {return applicants;}
    
    public void setApplicants(MemberOfTeambean[] applicant) {this.applicants = applicant;}
    
    public MemberOfTeambean[] getMembers() {return members;}
    
    public void setMembers(MemberOfTeambean[] member) {this.members  = member;}

    public boolean getCheck() {return check;}
    
    public void setCheck(boolean check) {this.check = check;} 
}
