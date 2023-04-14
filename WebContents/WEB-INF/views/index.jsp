<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>

<div class="container">
	<div id="demo" class="carousel slide" data-ride="carousel">
		<ul class="carousel-indicators">
			<li data-target="#demo" data-slide-to="0" class="active"></li>
			<li data-target="#demo" data-slide-to="1"></li>
			<li data-target="#demo" data-slide-to="2"></li>
		</ul>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="${contextPath}/resources/images/b_bump.jpg" width="1100" height="500">
				<div class="carousel-caption">
					<h3>실내</h3>
					<p>누구든지 할 수 있는 클라이밍 도전하세요!!</p>
				</div>   
			</div>
			<div class="carousel-item">
				<img src="${contextPath}/resources/images/이미지07.jpg" width="1100" height="500">
				<div class="carousel-caption">
					<h3>야외</h3>
					<p>야외에서 즐기는 클라이밍을 경험하세요!</p>
				</div>   
			</div>
			<div class="carousel-item">
				<img src="${contextPath}/resources/images/이미지05.jpg" width="1100" height="500">
				<div class="carousel-caption">
					<h3>자연</h3>
					<p>자연을 정복하세요!</p>
				</div>   
			</div>
		</div>
		<a class="carousel-control-prev" href="#demo" data-slide="prev">
			<span class="carousel-control-prev-icon"></span>
		</a>
		<a class="carousel-control-next" href="#demo" data-slide="next">
			<span class="carousel-control-next-icon"></span>
		</a>
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
</div>

<%@ include file="layout/footer.jsp" %>
