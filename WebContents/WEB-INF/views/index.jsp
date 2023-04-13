<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>

<div class="container">
	<div class="text-center">
		<img class="main_img" src="${contextPath}/resources/images/b_bump.jpg">
	</div>
	<div class="row">
		<div class="col-sm-4">
			<table class="table table-hover">
				<thead>
					<h3>최신글</h3>
					<tr>
					  <th>분류</th>
					  <th>제목</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list_day}" var="board">
						<tr>
							<td>
								<a href="${contextPath}/board/detail/${board.bno}">
									${board.writeType}
								</a>
							</td>
							<td>
								<a href="${contextPath}/board/detail/${board.bno}">
									${board.short_ti}
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="col-sm-4">
			<table class="table table-hover">
				<thead>
					<h3>인기글</h3>
					<tr>
					  <th>분류</th>
					  <th>제목</th>
					  <th>추천수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list_hits}" var="board">
						<tr>
							<td>
								<a href="${contextPath}/board/detail/${board.bno}">
									${board.writeType}
								</a>
							</td>
							<td>
								<a href="${contextPath}/board/detail/${board.bno}">
									${board.short_ti}
								</a>
							</td>
							<td>${board.hitsCnt}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="col-sm-4">
			<table class="table table-hover">
				<thead>
					<h3>추천글</h3>
					<tr>
					  <th>분류</th>
					  <th>제목</th>
					  <th>추천수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list_like}" var="board">
						<tr>
							<td>
								<a href="${contextPath}/board/detail/${board.bno}">
									${board.writeType}
								</a>
							</td>
							<td>
								<a href="${contextPath}/board/detail/${board.bno}">
									${board.short_ti}
								</a>
							</td>
							<td>${board.likeCnt}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
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
