<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
	<div class="jumbotron">
		<h1>나의 정보 보기</h1>
	</div>
	<sec:authorize access="isAuthenticated()"> <!-- 권한이 있는 경우  -->
	<div>
		확인 : <sec:authentication property="principal.HelloVO"/>
	</div>
	<sec:authentication property="principal.MemberVO" var="me"/>
	<table class="table">
		<tr>
			<th colspan="1">아이디</th>
			<td colspan="5">${me.id}</td>
		</tr>
		<tr>
			<th>닉네임</th>
			<td>${me.nickname}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${me.name}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${me.email}</td>
		</tr>
		<tr>
			<th>가입일</th>
			<td>${me.joinDate}</td>
		</tr>
		<tr>
			<th>상태</th>
			<td>${me.enabled}</td>
		</tr>
		<tr>
			<th>등급</th>
			<td>${me.authList[0].memberGrade}</td>
		</tr>
	</table>
	</sec:authorize>
</div>
<%@ include file="../layout/footer.jsp" %>


