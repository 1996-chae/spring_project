package com.jafa.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardVO {
	private Long bno;
	private Long rno;
	private String writeType;
	private String title;
	private String short_ti;
	private String content;
	private String id;
	private String nickname;
	private Date writeDate;
	private Integer hitsCnt; // 조회 개수
	private Integer replyCnt; // 댓글 개수
	private Integer likeCnt; // 좋아요 개수
	private Integer attachFileCnt; // 첨부파일 개수
	
}
