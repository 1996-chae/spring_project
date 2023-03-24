package com.jafa.domain;

public enum MemberGrade {
	
	ROLE_ADMIN("관리자"), //0
	ROLE_SUB_ADMIN("부관리자"), //1
	ROLE_MEMBER("회원"); //2
	
	private final String name;
	
	MemberGrade(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	
}
