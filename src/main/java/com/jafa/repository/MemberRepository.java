package com.jafa.repository;

import java.util.List;

import com.jafa.domain.MemberVO;

public interface MemberRepository {
	
	MemberVO read(String id);

	void save(MemberVO vo);

	int idCheck(String id);
	int nickCheck(String nickname);
	
	List<MemberVO> memberList();

	
}

