package com.jafa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jafa.domain.ReplyVO;
import com.jafa.repository.BoardRepository;
import com.jafa.repository.ReplyRepository;

@Service
public class ReplyService {

	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	ReplyRepository replyRepository;
	
	// 댓글 리스트 불러오기 원하는 만큼 불러오기
	public List<ReplyVO> replyList(Long bno) {
		return replyRepository.list(bno);
	}
	
	@Transactional
	public void write(ReplyVO vo) {
		replyRepository.write(vo);
		boardRepository.updateReplyCnt(vo.getBno());
	}

	@Transactional
	public void modify(ReplyVO vo) {
		replyRepository.modify(vo);
	}
	
	@Transactional
	public void remove(ReplyVO vo) {
		replyRepository.remove(vo.getRno());
		boardRepository.updateReplyCnt(vo.getBno());
	}

 	
}
