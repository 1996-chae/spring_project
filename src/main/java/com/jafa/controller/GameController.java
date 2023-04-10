package com.jafa.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jafa.domain.BaseballGameVO;
import com.jafa.service.GameService;

@Controller
@RequestMapping("/game")
public class GameController {

	@Autowired
	GameService gameService;
	
	@RequestMapping("/list")
	public String list() {
		return "/game/list";
	}
	
	@GetMapping(value = "/baseball")
	public String baseballStart() {
		return "/game/baseball/start";
	}
	
	@PostMapping(value = "/baseball/start")
	@ResponseBody
	public BaseballGameVO start() {
		BaseballGameVO vo = new BaseballGameVO();
		vo.setAnswer(gameService.start());
		vo.setRound(1);
		vo.setResults(new ArrayList<String>());
		System.out.println(vo);
		return vo;
	}
	
	@PostMapping(value = "/baseball/turn")
	@ResponseBody
	public BaseballGameVO turn(@RequestBody BaseballGameVO vo) {
		System.out.println("현제 라운드 : " + vo.getRound());
		return gameService.playRound(vo);
	}
}
