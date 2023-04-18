package com.jafa.domain;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.jafa.validation.FieldMatch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldMatch(first = "pwd", second = "pwd_confirm", message = "비밀번호와 비밀번호확인이 일치하지 않음")
public class MemberDTO {
	
	@NotBlank(message = "아이디는 필수 항목입니다.")
	@Length(min = 4, max = 16, message = "아이디는 4~16자이어야 합니다.")
	private String id; 
	
	@NotBlank(message = "닉네임을 입력하세요")
	private String nickname;
	
	@NotBlank(message = "이름을 입력하세요")
	private String name; 
	
	@Pattern(regexp = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$", message = "8~15자리 숫자,문자,특수문자를 필수로 입력하세요.")
	private String pwd; // 비밀번호
	private String pwd_confirm; // 비밀번호확인
	
	@Email(message = "올바른 이메일 형식이 아닙니다.")
	@NotBlank(message = "이메일은 필수항목 입니다.")
	private String email;
	
	private List<AuthVO> authList;
}
