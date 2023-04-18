<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<script src="${contextPath}/resources/js/member/join.js"></script>
<div class="container">
	<div class="jumbotron">
		<h1>회원 가입</h1>
	</div>
	<form class="join_form" action="${contextPath}/member/join" method="post">
		<spring:hasBindErrors name="memberDTO"/> <!-- name속성 : 커맨드객체 이름 -->
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<input type="hidden" name="returnUrl" value="${returnUrl}">
		<div class="form-group">
			<label for="id">아이디</label>
			<input type="text" id="id" name="id" class="form-control" placeholder="아이디입력해주세요" value="${dto.id}">
			<button type="button" class="user_id_check">아이디 중복 체크</button>
			<p class="user_id_message" style="color: red;">
				<form:errors path="memberDTO.id" element="div" class="input_errors" />
			</p>
		</div>
		<div class="form-group">
			<label for="pwd">비밀번호</label>
			<input type="text" id="pwd" name="pwd" class="form-control" placeholder="비밀번호입력해주세요" value="${dto.pwd}">
			<form:errors path="memberDTO.pwd" element="div" class="input_errors" />
		</div>
		<div class="form-group">
			<label for="pwd_check">비밀번호 확인</label>
			<input type="text" id="pwd_confirm" name="pwd_confirm" class="form-control" placeholder="비밀번호확인" value="${dto.pwd_confirm}">
			<form:errors path="memberDTO.pwd_confirm" element="div" class="input_errors" />
		</div>
		<div class="form-group">
			<label for="name">이름</label>
			<input type="text" id="name" name="name" class="form-control" placeholder="이름입력해주세요" value="${dto.name}">
			<form:errors path="memberDTO.name" element="div" class="input_errors" />
		</div>
		<div class="form-group">
			<label for="email">이메일</label>
			<input type="text" id="email" name="email" class="form-control" placeholder="이메일입력해주세요" value="${dto.email}">
			<form:errors path="memberDTO.email" element="div" class="input_errors" />
		</div>
		<div class="form-group">
			<label for="nickname">닉네임</label>
			<input type="text" id="nickname" name="nickname" class="form-control" placeholder="닉네임입력해주세요" value="${dto.nickname}">
			<button type="button" class="user_nick_check">닉네임 중복 체크</button>
			<p class="user_nick_message" style="color: red;"/>
		</div>
		<button class="btn btn-primary user_join">가입하기</button>
	</form>
</div>
<%@ include file="../layout/footer.jsp" %>


