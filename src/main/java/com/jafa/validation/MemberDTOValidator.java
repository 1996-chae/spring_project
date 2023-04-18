package com.jafa.validation;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jafa.domain.MemberDTO;

public class MemberDTOValidator implements Validator{

	// 검증 대상의 객체 타입 정의 
	@Override
	public boolean supports(Class<?> clazz) {
		return MemberDTO.class.isAssignableFrom(clazz);
	}

	// 검증 작업 수행 
	@Override
	public void validate(Object target, Errors errors) {
		MemberDTO dto = (MemberDTO) target;
		// 이름
		if(dto.getName()==null || dto.getName().isBlank()) { // 이름을 입력하지 않았을 때 
			errors.rejectValue("name", "required.name");
		} else { // 값이 있는 경우 
			String memberName = dto.getName();
			if(memberName.length()<2 || memberName.length()>6) { // 이름은 2~6자
				errors.rejectValue("name", "length.name");
			} else if(!Pattern.matches("^[가-힣|a-z|A-Z]*$", memberName)) { // 한글 또는 영문
				errors.rejectValue("name", "nameChar.name");
			}
			
		}
		
		// 아이디 
		if(dto.getId()==null || dto.getId().isBlank()) {  
			errors.rejectValue("id", "required.memberId");
		} else {
			// 4~16자, 영문 또는 숫자만 허용 ^[a-z|A-Z|0-9]*$
			String memberId = dto.getId();
			if(memberId.length()<4 | memberId.length()>16) {
				errors.rejectValue("id", "length.memberId");
			} else if(!Pattern.matches("^[a-z|A-Z|0-9]*$", memberId)) {
				errors.rejectValue("id", "idChar.memberId");
			}
		}
		
		// 비밀번호
		if(dto.getPwd()==null || dto.getPwd().isBlank()) {  
			errors.rejectValue("pwd", "required.password");
		} else {
			String password = dto.getPwd();
			if(!Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&=])[A-Za-z\\d@$!%*#?&=]{8,}$", password)) {
				errors.rejectValue("pwd", "limit.password");
			} else if(!password.equals(dto.getPwd_confirm())) { 
				// 비밀번호와 비밀번호 확인이 일치 하지않을 경우 
				errors.rejectValue("pwd", "notMatching.password");
				errors.rejectValue("pwd_confirm", "notMatching.password");
			}
		}
		
		// 이메일 
		if(dto.getEmail()==null || dto.getEmail().isBlank()) {  
			errors.rejectValue("email", "required.email");
		}
		
//		// 휴대폰번호
//		if(dto.getPhoneNumber()==null || dto.getPhoneNumber().isBlank()) {  
//			errors.rejectValue("phoneNumber", "required.phoneNumber");
//		}
		
		
	}
}

