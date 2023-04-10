<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="container text-center">
	<h1>1~9 숫자 야구 게임 - 라운드 ${game.round}</h1>
	<p>1~9 중 중복되지 않는 3개의 숫자를 입력하세요. 예) 123</p>
	<form class="ssgg">
		<input type="text" name="guess" pattern="[1-9]{3}" maxlength="3" required autofocus>
		<button type="button" class="baseballl_turn_end">제출</button>
	</form>
	<p>결과:</p>
	<ul class="list-group">
		<c:forEach var="result" items="${game.results}">
		<li class="list-group-item border">${result}</li>
		</c:forEach>
	</ul>
</div>