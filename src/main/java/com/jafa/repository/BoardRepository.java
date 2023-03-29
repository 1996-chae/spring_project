package com.jafa.repository;

import java.util.List;

import com.jafa.domain.BoardVO;
import com.jafa.domain.Criteria;

public interface BoardRepository {
	
	List<BoardVO> list(Criteria criteria);
	
	int getTotalCount(Criteria criteria);

	BoardVO detail(Long bno);

	List<String> allType();

	void insertBoard(BoardVO vo);

	void updateAttachFileCnt(Long bno);
	void updateReplyCnt(Long bno);

	void delete(Long bno);

	void modify(BoardVO vo);

	
}
