package com.jafa.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.domain.AuthListDTO;
import com.jafa.domain.AuthVO;
import com.jafa.domain.MemberGrade;
import com.jafa.domain.MemberVO;
import com.jafa.repository.TestRepository;
import com.jafa.service.MemberService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/member")
public class SecurityExamController {
	
	@Autowired
	MemberService memberService; 
	
	@Autowired
	TestRepository testRepository;
	
	@GetMapping("/all")
	public void doAll(Model model) {
		log.info("모두 접근 허용");
		Date date1 = testRepository.getDate1();
		Date date2 = testRepository.getDate2();
		System.out.println(date1);
		System.out.println(date2);
		model.addAttribute("time1", date1);
		model.addAttribute("time2", date2);
	}
	
	@GetMapping("/accessError")
	public void accessError() {
		log.info("접근이 거부됨.");
		// member/accessError.jsp 생성
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/mypage")
	public String myPage() {
		return "member/mypage";
	}
	
	/* 
	// ajax를 이용한 방법을 이용하여 상시 닉네임을 불러오고 싶었지만 securityDetail을 통해 쉽게 받을 수 있는 방법을 찾음
	// 상시 헤더 닉네임 및 정보
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "myNickname", produces="application/text;charset=utf8", method = RequestMethod.GET)
	@ResponseBody
	public String myNickname(Authentication auth) {
		MemberDetail principal = (MemberDetail) auth.getPrincipal();
		String nickname = principal.getMemberVO().getNickname();
		System.out.println("ajax 실행 : "+ principal.getMemberVO().getNickname());
		return nickname;
	}
	 */
	
	// 로그인 페이지 
	@RequestMapping("/login")
	public String login(Authentication authentication, RedirectAttributes rttr, Model model, HttpServletRequest request) {
		String returnUrl = request.getParameter("returnUrl");
		String parameter = request.getParameter("loginId");
		String header = request.getHeader("referer"); // 접속 경로
		if(!header.equals("http://localhost:8090/book_board/member/login")) {
			model.addAttribute("returnUrl", header);
		}
		System.out.println("controller 입니다 url은 : " + returnUrl + " ==== id : "+parameter);
		System.out.println("getHeader'referer' 접속경로 : " + header);
		if(authentication!=null && authentication.isAuthenticated()) {
			rttr.addFlashAttribute("loginOn", "이미 로그인한 상태입니다.");
			return "redirect:/";
		}
		return "/member/login";
	}
	
	// 회원가입폼
	@PreAuthorize("isAnonymous()")
	@GetMapping("/join")
	public void join() {
	}
	
	@RequestMapping(value = "idCheck", method = RequestMethod.GET)
	@ResponseBody
	public int idCheck(String id) {
		return memberService.idCheck(id);
	}
	
	@RequestMapping(value = "nickCheck", method = RequestMethod.GET)
	@ResponseBody
	public int nickCheck(String nickname) {
		return memberService.nickCheck(nickname);
	}
	
	//회원가입처리
	@PostMapping("/join")
	public String join(MemberVO vo, RedirectAttributes rttr) {
		memberService.join(vo);
		return "redirect:/";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUB_ADMIN')")
	@GetMapping("/admin")
	public void doAdmin(Model model) {
		// 관리자만 허용
		List<MemberVO> memberList = memberService.memberList(); 
		model.addAttribute("list", memberList);
		model.addAttribute("mGrade", MemberGrade.values());
	}
	
	// 회원등급변경
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUB_ADMIN')")
	@PostMapping("/upadteMemberType")
	public String updateMemberType(AuthListDTO authListDTO, RedirectAttributes rttr) {
		log.info(authListDTO.getAuthList());
		List<AuthVO> authList = authListDTO.getAuthList();
		for(AuthVO vo : authList) {
			if(vo.getId()!=null&&vo.getMemberGrade()!=null){
				memberService.updateMemberType(vo);
			}
		}
		rttr.addFlashAttribute("updateMember", "등급변경");
		return "redirect:/member/admin";
	}
}
