package com.jafa.repository;

import java.util.List;

import com.jafa.domain.MemberVO;

public interface MemberRepository {
	
	MemberVO read(String id);

	void save(MemberVO vo);

	List<MemberVO> memberList();
	
}

