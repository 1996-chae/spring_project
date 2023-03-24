<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>

<div class="container">
	<h1>메인</h1>
	<div>
		<a href="${contextPath}/board/list">게시판 가기</a>
		<a href="${contextPath}/game/baseball">야구 게임 가기</a>
	</div>
	<div>
		<sec:authorize access="isAnonymous()"><!-- 권한이 없는 경우  -->
			<a href="${contextPath}/member/join">회원가입</a><br>
			<a href="${contextPath}/member/login">로그인</a><br>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()"> <!-- 권한이 있는 경우  -->
			<p>로그인 중 : <sec:authentication property="principal.username"/></p> <!-- 로그인 id-->
			<sec:authentication property="principal.memberVO.nickname" var="u_nickname"/>
			<sec:authentication property="principal.helloVO.welcome" var="welcome"/>
			<spring:message code="main.greeting"  arguments="${u_nickname},${welcome}"/>
			<form action="${contextPath}/member/logout" method="post"> <!-- 기본값 : /logout -->
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<button class="btn btn-primary">로그아웃</button>
			</form>
		</sec:authorize>
		<a href="${contextPath}/member/all">모든 사용자 접근 가능</a><br>
		<sec:authorize access="hasAnyRole('ROLE_REGULAR_MEMBER','ROLE_ASSOCIATE_MEMBER')">
			<a href="${contextPath}/member/mypage">나의정보보기</a><br>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUB_ADMIN')">
			<a href="${contextPath}/member/admin">관리자만 접근 가능</a><br>
		</sec:authorize>
	</div>
	<div>
		<h1>1~9 숫자 야구 게임</h1>
	    <p>게임을 시작하려면 아래의 버튼을 클릭하세요.</p>
	    <form method="get" action="${contextPath}/game/baseball">
			<button type="submit">게임 시작</button>
	    </form>
	</div>
</div>

<script>
let loginOn = "${loginOn}"
if(loginOn!='') alert(loginOn);
</script>

<%@ include file="layout/footer.jsp" %>
