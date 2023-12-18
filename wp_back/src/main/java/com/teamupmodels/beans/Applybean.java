package com.teamupmodels.beans;

public class Applybean {
	 private String teamId;
	    private String userId;
	    private String intro; //팀에 지원할 때의 소개 및 각오
	    // 기본 생성자
	    public Applybean() {}
	    public Applybean(String teamId, String userId,String intro) {
	    	this.teamId = teamId;
	    	this.userId = userId;
	    	this.intro = intro;
	    }
	    // Getter와 Setter 메소드
	    public String getTeamId() {
	        return teamId;
	    }

	    public void setTeamId(String teamId) {
	        this.teamId = teamId;
	    }

	    public String getUserId() {
	        return userId;
	    }

	    public void setUserId(String userId) {
	        this.userId = userId;
	    }
	    
	    public String getIntro() {
	        return intro;
	    }

	    public void setIntro(String intro) {
	        this.intro = intro;
	    }
}