package com.jafa.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jafa.domain.Criteria;
import com.jafa.repository.TestRepository;
import com.jafa.service.BoardService;
import com.jafa.service.TestService;

@Controller
public class HomeController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private TestService testService;
	
	@GetMapping("/")
	public String home(Model model) {
		Criteria criteria = new Criteria(1,5);
		Date date1 = testService.date1();
		date1.setDate(date1.getDate()-1);
		model.addAttribute("list",boardService.boardList(criteria));
		
		
		return "index";
	}
}