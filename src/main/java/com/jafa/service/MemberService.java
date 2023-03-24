package com.jafa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jafa.domain.AuthVO;
import com.jafa.domain.MemberGrade;
import com.jafa.domain.MemberVO;
import com.jafa.repository.AuthRepository;
import com.jafa.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	AuthRepository authRepository; 
	
	// 비밀번호 암호화 
	@Autowired
	PasswordEncoder passwordEncoder;
	
	// 회원가입
	@Transactional
	public void join(MemberVO vo) {
		vo.setPwd(passwordEncoder.encode(vo.getPwd())); // 비밀번호 암호화 
		memberRepository.save(vo);
		AuthVO authVO = AuthVO.builder()
				.id(vo.getId())
				.memberGrade(MemberGrade.ROLE_MEMBER)
				.ordinal(MemberGrade.ROLE_MEMBER.ordinal())
				.build();
		authRepository.save(authVO);
	}
	
	// 회원 등급변경 
	@Transactional
	public void updateMemberType(AuthVO authVO) {
		authRepository.remove(authVO.getId()); // 모든등급삭제 
		MemberGrade memberType = authVO.getMemberGrade(); // 변경할 회원등급 
		MemberGrade[] types = MemberGrade.values(); // 전체 회원등급
		for(int i=memberType.ordinal(); i<types.length;i++) {
			AuthVO vo = AuthVO.builder()
				.id(authVO.getId())
				.memberGrade(types[i])
				.ordinal(types[i].ordinal())
				.build();
			authRepository.save(vo);
		}
	}

	public List<MemberVO> memberList() {
		return memberRepository.memberList();
	}
}
