<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../../layout/header.jsp" %>
<div class="container text-center">
    <h1>1~9 숫자 야구 게임 - 게임 종료</h1>
    <p>정답: ${game.answer}</p>
    <p>당신의 시도: ${game.round}</p>
    <ul class="list-group">
        <c:forEach var="result" items="${game.results}">
            <li class="list-group-item">${result}</li>
        </c:forEach>
    </ul>
    <form method="get" action="${contextPath}/game/baseball">
        <button type="submit">다시 시작</button>
    </form>
</div>
<%@ include file="../../layout/footer.jsp" %>