package com.jafa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthVO {
	private String id;
	private MemberGrade memberGrade;
	private int ordinal;
	
	public AuthVO(String id, MemberGrade memberGrade) {
		this.id = id;
		this.memberGrade = memberGrade;
	}
	
	
}