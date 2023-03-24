<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="../../layout/header.jsp" %>

<div class="container">
	<h1>메인</h1>
	
	<h1>1~9 숫자 야구 게임</h1>
    <p>게임을 시작하려면 아래의 버튼을 클릭하세요.</p>
    <form method="get" action="/game/baseball">
		<button type="submit">게임 시작</button>
    </form>
	
</div>
<%@ include file="../../layout/footer.jsp" %>

<script>
let loginOn = "${loginOn}"
if(loginOn!='') alert(loginOn);
</script>
