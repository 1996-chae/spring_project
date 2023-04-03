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


<nav class="container">
	<ul class="nav justify-content-center">
		<a class="navbar-brand" href="${contextPath}">
			<h2>서향림</h2>
		</a>
	</ul>
	<ul class="nav justify-content-end">
		
		<sec:authorize access="isAnonymous()"><!-- 권한이 없는 경우  -->
		<c:if test="${requestScope['javax.servlet.forward.request_uri'] ne '/book_board/member/login'}" > <!-- 현제 주소(uri) 비교 -->
			<li class="nav-item">
				<form class="user_form form-inline my-2 my-lg-0">
					
					<input type="text" name="loginId" class="form-control form-control-sm mr-sm2" value="${loginId}" 
					placeholder="아이디 입력" onfocus="this.placeholder=''" onblur="this.placeholder='아이디 입력'">
					<input type="text" name="loginPwd" class="form-control form-control-sm mr-sm2" placeholder="비밀번호 입력" 
					onfocus="this.placeholder=''" onblur="this.placeholder='비밀번호 입력'">
					<button class="btn btn-success user_login">로그인</button>
					<input type="checkbox" name="remember-me">로그인 유지
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				</form>
				<a class="border" href="${contextPath}/member/join"><b>회원가입</b></a>
				<a class="border" href="${contextPath}/member/join"><b>아이디ㆍ비밀번호 찾기</b></a>
			</li>
		</c:if>
		</sec:authorize>
		
		<sec:authorize access="isAuthenticated()"> <!-- 권한이 있는 경우  -->
			<li class="nav-item">
				<!-- 로그인 정보-->
				<sec:authentication property="principal.memberVO.nickname"/>님
			</li>
			<li class="nav-item dropdown">
				<form class="user_form"> <!-- 기본값 : /logout -->
					<a class="nav-link dropdown-toggle btn btn-primary" data-toggle="dropdown" href="#">↓</a>
				    <div class="dropdown-menu">
						<a class="dropdown-item btn btn-primary user_logout">
				      		로그아웃
						</a>
						<a class="dropdown-item" href="${contextPath}/member/mypage">
				      		나의정보보기
						</a>
						<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUB_ADMIN')">
							<a class="dropdown-item" href="${contextPath}/member/admin">
					      		관리자 기능
							</a>
						</sec:authorize>
				    </div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				</form>
			</li>
		</sec:authorize>
	</ul>
	<ul class="nav nav-tabs nav-justified">
		<li class="nav-item">
			<a class="nav-link" href="${contextPath}/board/list">게시판</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="${contextPath}/board/list">게시판2</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="${contextPath}/board/list">게시판3</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="${contextPath}/board/list">게시판4</a>
		</li>
	</ul>
</nav>

</head>
<body>


