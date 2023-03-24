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
	private String content;
	private String id;
	private String nickname;
	private Date writeDate;
	private Integer attachFileCnt; // 첨부파일 개수
}
