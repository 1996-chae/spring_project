package com.jafa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jafa.domain.BaseballGameVO;
import com.jafa.model.BaseballModel;

@Service
public class GameService {
	
	@Autowired
	BaseballModel baseballModel;
	
	public String start() {
		return baseballModel.generateRandomNumber();
	}
	
	public BaseballGameVO playRound(BaseballGameVO vo) {
		return baseballModel.playRound(vo);
	}
	
}
