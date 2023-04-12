<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<script src="${contextPath}/resources/js/member/join.js"></script>
<div class="container">
	<div class="jumbotron">
		<h1>회원 가입</h1>
	</div>
	<form class="join_form" action="${contextPath}/member/join" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<input type="hidden" name="returnUrl" value="${returnUrl}">
		<div class="form-group">
			<input type="text" name="id" class="form-control" placeholder="아이디">
			<button type="button" class="user_id_check">아이디 중복 체크</button>
			<p class="user_id_message" style="color: red;"/>
		</div>
		<div class="form-group">
			<input type="text" name="pwd" class="form-control" placeholder="비밀번호">
		</div>
		<div class="form-group">
			<input type="text" name="pwd_check" class="form-control" placeholder="비밀번호확인">
		</div>
		<div class="form-group">
			<input type="text" name="name" class="form-control" placeholder="이름">
		</div>
		<div class="form-group">
			<input type="text" name="email" class="form-control" placeholder="이메일">
		</div>
		<div class="form-group">
			<input type="text" name="nickname" class="form-control" placeholder="닉네임">
			<button type="button" class="user_nick_check">닉네임 중복 체크</button>
			<p class="user_nick_message" style="color: red;"/>
		</div>
		<button class="btn btn-primary user_join">가입하기</button>
	</form>
</div>
<%@ include file="../layout/footer.jsp" %>


