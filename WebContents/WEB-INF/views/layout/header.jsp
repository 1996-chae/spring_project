<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" href="${contextPath}/resources/css/style.css">

<meta name="_csrf_token" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<meta name="_csrf_parameter" content="${_csrf.parameterName}"/>

<script>
	const contextPath = "${contextPath}";
</script>
<script src="${contextPath}/resources/js/main.js"></script>
</head>
<body>


<nav class="container navbar navbar-expand-sm bg-dark navbar-dark text-center">
	<div class="jumbotron">
		<h2>서향림</h2>
	</div>
	<div>
		<ul class="nav nav-tabs">
			<li class="nav-item">
				<a class="nav-link" href="${contextPath}/board/list">게시판</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${contextPath}/member/all">모든</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${contextPath}/member/admin">운영자존</a>
			</li>
			<li>
				<a class="nav-link" href="${contextPath}">홈페이지</a>
			</li>
		</ul>
	</div>
	<div>
	<sec:authorize access="isAnonymous()"><!-- 권한이 없는 경우  -->
	<c:if test="${requestScope['javax.servlet.forward.request_uri'] ne '/book_board/member/login'}" >
		<form class="user_form">
			<input type="text" name="loginId" class="form-control" value="${loginId}" 
			placeholder="아이디 입력" onfocus="this.placeholder=''" onblur="this.placeholder='아이디 입력'">
			<input type="text" name="loginPwd" class="form-control" placeholder="비밀번호 입력" 
			onfocus="this.placeholder=''" onblur="this.placeholder='비밀번호 입력'">
			로그인 상태 유지 : <input type="checkbox" name="remember-me"><br>
			<button class="btn btn-success user_login">로그인</button>
			<a class="btn btn-success" href="${contextPath}/member/join">회원가입</a>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		</form>
	</c:if>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()"> <!-- 권한이 있는 경우  -->
		<p> <!-- 로그인 정보-->
			아이디 : <sec:authentication property="principal.username"/><br>
			닉네임 : <sec:authentication property="principal.memberVO.nickname"/>
		</p>
		<form class="user_form"> <!-- 기본값 : /logout -->
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<button class="btn btn-primary user_logout">로그아웃</button>
		</form>
		<sec:authorize access="hasAnyRole('ROLE_MEMBER')">
			<a class="btn btn-primary" href="${contextPath}/member/mypage">나의정보보기</a><br>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUB_ADMIN')">
			<a class="btn btn-primary" href="${contextPath}/member/admin">관리자만 접근 가능</a><br>
		</sec:authorize>
	</sec:authorize>
	</div>
</nav>