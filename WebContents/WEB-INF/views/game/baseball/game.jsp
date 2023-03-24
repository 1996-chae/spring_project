<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../layout/header.jsp" %>
<script>
var results = [];
<c:forEach var="result" items="${game.results}">
	results.push("${result}");
</c:forEach>
let game = {
	round : "${game.round}",
	gameOver : "${game.gameOver}",
	results : results,
	answer : "${game.answer}",
	guess : "${game.guess}"
};
</script>
<script src="${contextPath}/resources/js/game/baseball.js"></script>

<div class="container">
	<h1>1~9 숫자 야구 게임 - 라운드 ${game.round}</h1>
	<p>1~9 중 중복되지 않는 3개의 숫자를 입력하세요. 예) 123</p>
	<form class="ssgg">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<input type="text" name="guess" pattern="[1-9]{3}" maxlength="3" required autofocus>
		<button>제출</button>
	</form>
	<p>결과:</p>
	<ul>
		<c:forEach var="result" items="${game.results}">
			<li>${result}</li>
		</c:forEach>
	</ul>
</div>

<%@ include file="../../layout/footer.jsp" %>