package com.jafa.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BaseballGameVO {

	private final int MAX_ROUNDS;
	private final int NUMBER_LENGTH;

    private int round;
    private boolean gameOver;
    private List<String> results;
    private String answer;
    private String guess;


    public BaseballGameVO() {
    	this(9,3);
    }
    
    public BaseballGameVO(int MAX_ROUNDS, int NUMBER_LENGTH) {
		this.MAX_ROUNDS = MAX_ROUNDS;
		this.NUMBER_LENGTH = NUMBER_LENGTH;
	}




    
    
}

