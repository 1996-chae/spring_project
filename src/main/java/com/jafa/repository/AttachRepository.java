package com.jafa.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jafa.domain.AttachVO;

public interface AttachRepository {
	
	// 게시글의 전체 파일 불러오기
	List<AttachVO> list(Long bno);
	
	void save(@Param("attachList")List<AttachVO> attachList);

	// 게시글의 전채 파일 삭제
	void delete(Long bno);

	// 개별 가져오기
	AttachVO get(Long ano);
	// 파일 개별 삭제
	void remove(Long ano);




}
