
package com.jafa.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.jafa.domain.ReplyVO;
import com.jafa.service.ReplyService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/reply")
@Log4j
public class ReplyController {
	
	@Autowired
	ReplyService replyService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String replyList(Long bno) throws Exception {
		Gson gson = new Gson();
		return gson.toJson(replyService.replyList(bno));
	}
	
	@RequestMapping(value = "write", method = RequestMethod.POST)
	@ResponseBody
	public List<ReplyVO> replyWrite(ReplyVO vo) {
		replyService.write(vo);
		return replyService.replyList(vo.getBno());
	}
	
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	@ResponseBody
	public List<ReplyVO> replyModify(ReplyVO vo) {
		replyService.modify(vo);
		return replyService.replyList(vo.getBno());
	}
	
	@RequestMapping(value = "remove", method = RequestMethod.POST)
	@ResponseBody
	public List<ReplyVO> replyRemove(ReplyVO vo) {
		replyService.write(vo);
		return replyService.replyList(vo.getBno());
	}
	
	
	
}
