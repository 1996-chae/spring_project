
package com.jafa.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jafa.domain.AuthVO;
import com.jafa.domain.MemberDetail;
import com.jafa.domain.MemberVO;
import com.jafa.domain.ReplyVO;
import com.jafa.service.ReplyService;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	ReplyService replyService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<ReplyVO> replyList(Long bno) throws Exception {
		System.out.println(replyService.replyList(bno));
		return replyService.replyList(bno);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "write", method = RequestMethod.POST)
	@ResponseBody
	public List<ReplyVO> replyWrite(@RequestBody ReplyVO replyVO, Authentication auth) {
		MemberDetail principal = (MemberDetail) auth.getPrincipal();
		MemberVO memberVO = principal.getMemberVO();
		replyVO.setId(memberVO.getId());
		replyVO.setNickname(memberVO.getNickname());
		replyService.write(replyVO);
		return replyService.replyList(replyVO.getBno());
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	@ResponseBody
	public List<ReplyVO> replyModify(@RequestBody ReplyVO replyVO, Authentication auth) {
		MemberDetail principal = (MemberDetail) auth.getPrincipal();
		MemberVO memberVO = principal.getMemberVO();
		if(memberVO.getId().equals(replyVO.getId())) {
			replyService.modify(replyVO);
		}
		return replyService.replyList(replyVO.getBno());
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "remove", method = RequestMethod.POST)
	@ResponseBody
	public List<ReplyVO> replyRemove(@RequestBody ReplyVO replyVO, Authentication auth) {
		MemberDetail principal = (MemberDetail) auth.getPrincipal();
		MemberVO memberVO = principal.getMemberVO();
		System.out.println(principal.getAuthorities());
		if(memberVO.getId().equals(replyVO.getId()) || memberVO.getAuthList().get(0).getOrdinal()<=1) {
			replyService.remove(replyVO);
		}
		return replyService.replyList(replyVO.getBno());
	}
	
	
	
}
