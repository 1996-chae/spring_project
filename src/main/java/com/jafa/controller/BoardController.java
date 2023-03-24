
package com.jafa.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.domain.AttachVO;
import com.jafa.domain.BoardVO;
import com.jafa.domain.Criteria;
import com.jafa.domain.MemberDetail;
import com.jafa.domain.MemberVO;
import com.jafa.domain.Pagination;
import com.jafa.service.BoardService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@Log4j
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = {"list","/list/{page}"})
	public String list(@PathVariable(required = false) Long page, 
			@ModelAttribute("cri") Criteria criteria, Model model ) {
		model.addAttribute("list",boardService.boardList(criteria));
		model.addAttribute("p", new Pagination(criteria, boardService.listCount(criteria)));
		model.addAttribute("typeAll", boardService.allType());
		return "/board/list";
	}
	
	@RequestMapping("/detail/{bno}")
	public String detail(Model model, @PathVariable Long bno, 
			@ModelAttribute("cri")Criteria criteria) {
		BoardVO vo = boardService.detail(bno);
		model.addAttribute("board",vo);
		if(vo.getAttachFileCnt()>0) {
			List<AttachVO> attachList = boardService.attachList(bno);
			model.addAttribute("attachList", attachList);
		}
		model.addAttribute("replyList", boardService.replyList(bno));
		return "/board/detail";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/write")
	public String boardAdd(Model model, 
			@ModelAttribute("cri")Criteria criteria) {
		model.addAttribute("typeAll", boardService.allType());
		return "/board/write";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/write")
	public String boardAdd(Model model, @ModelAttribute("cri")Criteria criteria, 
			BoardVO vo, String selboxDirect, RedirectAttributes rttr,
			@RequestParam("attachFile") MultipartFile[] multipartFiles, Authentication auth) {
		MemberDetail principal = (MemberDetail) auth.getPrincipal();
		MemberVO memberVO = principal.getMemberVO();
		vo.setId(memberVO.getId());
		vo.setNickname(memberVO.getNickname());
		if(vo.getWriteType().equals("direct")) {
			vo.setWriteType(selboxDirect);
		}
		Long addBoard = boardService.addBoard(vo, multipartFiles);
		
		return "redirect:/board/detail/"+addBoard;
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{bno}")
	public String modify(Authentication auth, @PathVariable Long bno,
			@ModelAttribute("cri")Criteria criteria, Model model, HttpServletRequest request) {
		
		BoardVO vo = boardService.detail(bno);
		if(vo.getId().equals(((MemberDetail)auth.getPrincipal()).getUsername())) {
			model.addAttribute("board",vo);
			if(vo.getAttachFileCnt()>0) {
				List<AttachVO> attachList = boardService.attachList(bno);
				model.addAttribute("attachList", attachList);
				model.addAttribute("typeAll", boardService.allType());
			}
			return "/board/modify";
		}
		return "redirect:/board/detail/"+bno;
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify")
	public String modify(Authentication auth, BoardVO vo, 
			@RequestParam(value = "attachFile") MultipartFile[] multipartFiles,
			@RequestParam(value = "delFileList") List<Long> delAttachList) {
		if(vo.getId().equals(((MemberDetail)auth.getPrincipal()).getUsername())) {
			boardService.modify(vo, delAttachList, multipartFiles);
		}
		return "redirect:/board/list";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/delete")
	public String boardDel(Long bno, @ModelAttribute("cri")Criteria criteria) {
		boardService.delBoard(bno);
		return "redirect:/board/list";
	}
	
	
}
