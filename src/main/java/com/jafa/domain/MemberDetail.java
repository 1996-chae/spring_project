package com.jafa.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class MemberDetail extends User {
	private static final long serialVersionUID = -2347344875567152317L;
	
	MemberVO memberVO;
	HelloVO helloVO;
	
	public MemberDetail(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public MemberDetail(MemberVO vo, HelloVO helloVO) {
		super(vo.getId(), vo.getPwd(),
				vo.getAuthList().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getMemberGrade().toString()))
				.collect(Collectors.toList()));
		this.memberVO = vo;
		this.helloVO = helloVO;
	}
	
}
 