package com.jafa.repository;

import java.util.List;

import com.jafa.domain.ReplyVO;

public interface ReplyRepository {
	
	//list 뽑기
	List<ReplyVO> list(Long bno);

	//댓글생성
	void write(ReplyVO vo);

	//수정
	void modify(ReplyVO vo);
	
	// 개개별 삭제
	void remove(Long rno);
	
	// 전체 삭제
	void delete(Long bno);

	
}
