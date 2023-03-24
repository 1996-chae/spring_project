package com.jafa.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String parameter = request.getParameter("returnUrl");
		String header = request.getHeader("referer"); // 접속 경로
		System.out.println("겟 헤더"+header);
		System.out.println(authentication.getAuthorities()); // 회원등급
		System.out.println(request.getRequestURL()); // 요청주소
		System.out.println(authentication.getName()); // 회원아이디
		System.out.println(parameter);
		if(parameter != null && !parameter.equals("")) {
			System.out.println("url 계속 존재");
			response.sendRedirect(parameter);
		} else {
			System.out.println("url 계속 없음");
			response.sendRedirect(request.getContextPath());
		}
	}
	
}
