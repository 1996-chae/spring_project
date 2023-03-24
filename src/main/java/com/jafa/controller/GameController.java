package com.jafa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jafa.domain.BaseballGameVO;
import com.jafa.service.GameService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/game")
@Log4j
public class GameController {

	@Autowired
	GameService gameService;
	
	@RequestMapping("/list")
	public String list() {
		
		return "/game/list";
	}
	
	@RequestMapping(value = {"/baseball","/baseball?"})
	public String abc(Model model, @ModelAttribute BaseballGameVO vo) {
		System.out.println(vo);
		if(vo.getRound() == 0) {
			vo = new BaseballGameVO();
			vo.setAnswer(gameService.start());
			vo.setRound(1);
			vo.setResults(new ArrayList<String>());
			System.out.println(vo);
			model.addAttribute("game",vo);
			
			System.out.println("============");
			System.out.println("게임 재시작");
			return "/game/baseball/game";
		}
		System.out.println("라운드 : " + vo.getRound());
		BaseballGameVO playRound = gameService.playRound(vo);
		System.out.println(playRound);
		model.addAttribute("game", playRound);
		if(playRound.isGameOver()) {
			return "/game/baseball/over";
		}
		return "/game/baseball/game";
	}
}
