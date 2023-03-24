package com.jafa.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jafa.common.FileUploadUtils;
import com.jafa.domain.AttachVO;
import com.jafa.domain.BoardVO;
import com.jafa.domain.Criteria;
import com.jafa.domain.ReplyVO;
import com.jafa.repository.AttachRepository;
import com.jafa.repository.BoardRepository;
import com.jafa.repository.ReplyRepository;

@Service
public class BoardService {
	
	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	AttachRepository attachRepository;
	
	@Autowired
	ReplyRepository replyRepository;
	
	@Autowired
	FileUploadUtils fileUploadUtils;

	
	public List<BoardVO> boardList(Criteria criteria) {
		return boardRepository.list(criteria); 
	}
	
	public int listCount(Criteria criteria) {
		return boardRepository.getTotalCount(criteria);
	}

	// 게시판 상세 보기
	public BoardVO detail(Long bno) {
		return boardRepository.detail(bno);
	}

	public List<String> allType() {
		return boardRepository.allType();
	}

	// 글쓰기 (파일도 같이 전송)
	@Transactional
	public Long addBoard(BoardVO vo, MultipartFile[] multipartFiles) {
		System.out.println(vo);
		// 게시물정보 저장 
		boardRepository.insertBoard(vo);
		// 첨부파일 업로드
		System.out.println(vo);
		List<AttachVO> attachList = fileUploadUtils.getAttachVOAndUpload(vo.getBno(), multipartFiles);
		System.out.println(attachList);
		// 첨부파일 정보 저장
		if(!attachList.isEmpty()) { // 첨부파일이 있으면 
			attachRepository.save(attachList); // 첨부파일 정보 저장 
			boardRepository.updateAttachFileCnt(vo.getBno()); // 첨부파일 개수 업데이트 
		}
		return vo.getBno();
	}

	public List<AttachVO> attachList(Long bno) {
		return attachRepository.list(bno);
	}

	public List<ReplyVO> replyList(Long bno) {
		return replyRepository.list(bno);
	}

	// 수정
	@Transactional
	public void modify(BoardVO vo, List<Long> delFileList, MultipartFile[] multipartFiles) {
		// 삭제할 파일 있다면 파일삭제
		if(!delFileList.isEmpty()) { // 1,2,3
			for(Long ano : delFileList) {
				AttachVO attachVO = attachRepository.get(ano);
				new File(attachVO.getFilePath()).delete(); // 파일 삭제 
				attachRepository.remove(ano);
			}
		}
		// 테이블 수정
		boardRepository.modify(vo);
		// 새로운 파일을 업로드해야하는 경우 
		List<AttachVO> attachList = fileUploadUtils.getAttachVOAndUpload(vo.getBno(),multipartFiles);
		if(!attachList.isEmpty()) { // 첨부파일이 있으면 
			attachRepository.save(attachList); // 첨부파일 정보 저장 
		}
		boardRepository.updateAttachFileCnt(vo.getBno()); // 첨부파일 개수 업데이트 
	}
	
	// 삭제
	@Transactional
	public void delBoard(Long bno) {
		List<AttachVO> list = attachRepository.list(bno);
		if(!list.isEmpty()) { //첨부파일이 있다면
			// 모든 첨부파일 삭제 
			fileUploadUtils.deleteAllFile(bno);
		}
		attachRepository.delete(bno);
		replyRepository.delete(bno);
		boardRepository.delete(bno);
	}
	
	
}
