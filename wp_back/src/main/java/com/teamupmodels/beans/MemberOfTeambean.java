package com.teamupmodels.beans;

public class MemberOfTeambean {
	private String id;
	private String team_id;
	private String name;
	private String phone;
	private String intro;
	private String grade;
	private String studentNum;
	private String major;
	
	
	public MemberOfTeambean() {}
	public MemberOfTeambean(String id, String team_id, 
			String name, String phone, 
			String intro,String grade, 
			String studentNum, String major) {
		this.id = id;
		this.team_id = team_id;
		this.name = name;
		this.phone = phone;
		this.intro = intro;
		this.grade = grade;
		this.studentNum = studentNum;
		this.major = major;
	}
	
	//Getter
	public String getMem_id() { return id;}
	public String getMem_teamid() { return team_id;}
	public String getMem_name() { return name;}
	public String getMem_phone() { return phone;}
	public String getMem_intro() { return intro;}
	public String getMem_Grade() { return grade;}
	public String getMem_studentNum() { return studentNum;}
	public String getMem_Major() {return major;}
	
	//Setter
	public void setMem_id(String id) {this.id = id;}
	public void setMem_team_id(String team_id) {this.team_id = team_id;}
	public void setMem_name(String name) {this.name = name;}
	public void setMem_phone(String phone) {this.phone = phone;}
	public void setMem_intro(String intro) {this.intro = intro;}
	public void setMem_Grade(String grade) {this.grade = grade;}
	public void setMem_studentNum(String studentNum) {this.studentNum = studentNum;}
	public void setMem_Major(String major) {this.major = major;}
}