package com.jafa.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class Criteria {
	
	private int page; 
	private int perPageNum;
	private String category;
	
	private String sequence;
	private Integer limit;
	
	private String type; 
	private String keyword;
	
	public Criteria() {
		this(1,20);
		System.out.println("Criteria!!!");
	}

	public Criteria(int page, int perPageNum) {
		this.page = page;
		this.perPageNum = perPageNum;
	}
	
	// #{maxRow}
	public int getMaxRow() {
		return this.page*this.perPageNum;
	}
	// #{minRow}
	public int getMinRow() {
		return (this.page-1)*this.perPageNum;
	}
}
