<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<script src="${contextPath}/resources/js/board/list.js"></script>

<div class="container">
	<div class="jumbotron">
		<h1>게시판</h1>
	</div>
	<div class="row">
		<div class="col-sm-7">
			<form class="form-inline justify-content-center">
				<div class="from-group">
					<ul class="category list-group list-group-horizontal justify-content-center">
						<li class="list-group-item ${cri.category=='' or cri.category==null ? 'active' : ''}">
							<a class="writeType" href="${contextPath}/board/list" rel="">전체</a>
						</li>
						<c:forEach items="${typeAll}" var="t">
						<li class="list-group-item ${t==cri.category ? 'active' : ''}">
							<a class="writeType" href="${contextPath}/board/list?category=${t}">${t}</a>
						</li>
						</c:forEach>
					</ul>
				</div>
			</form>
		</div>
		<div class="col-sm-5">
			<form class="form-inline justify-content-end searchForm">
				<select class="form-control mr-sm-2" name="type">
					<option value="T" >제목</option>
					<option value="C" ${cri.type=='C' ? 'selected':''}>내용</option>
					<option value="TC" ${cri.type=='TC' ? 'selected':''}>제목+내용</option>
					<option value="W" ${cri.type=='W' ? 'selected':''}>작성자</option>
				</select>
				<input type="text" class="form-control mr-sm-2" name="keyword" value="${cri.keyword}" placeholder="검색어" pattern="[1-9]{6}" >
				<button class="btn btn-primary">검색</button>
			</form>
		</div>
	</div>
	<ul class="nav justify-content-end">
		<a href="${contextPath}/board/write">글쓰기</a>
	</ul>
	<table class="table">
		<tr>
			<th>분류</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>날짜</th>
			<th>조회</th>
			<th>추천</th>
		</tr>
		<c:forEach items="${list}" var="b">
		<tr>
			<td>
				${b.writeType }
			</td>
			<td>
				<a href="${b.bno}" class="title">
					${b.title }  
					<c:if test="${b.replyCnt > 0}">
						[${b.replyCnt}]
					</c:if>
					<c:if test="${b.attachFileCnt > 0}">
					<img alt="파일존재" src="${contextPath}/resources/images/imagefileicon.png" height="15" width="15">
					</c:if>
				 </a>
			</td>
			<td>${b.nickname }</td>
			<td>${b.writeDate.month}월 ${b.writeDate.date}일
			${b.writeDate.hours}:${b.writeDate.minutes}</td>
			<td>${b.hitsCnt}</td>
			<td>${b.likeCnt}</td>
		</tr>
		</c:forEach>
	</table>
		
	
	<ul class="pagination d-flex justify-content-center">
		<c:if test="${p.prev}">
		<li class="page-item">
			<a class="page-link" href="${p.startPage-1}">이전페이지</a>
		</li>
		</c:if>
		<c:forEach begin="${p.startPage}" end="${p.endPage }" var="pageNum">
		<li class="page-item ${cri.page == pageNum ? 'active':''}">
			<a class="page-link" href="${pageNum }">${pageNum }</a>
		</li>
		</c:forEach>
		<c:if test="${p.next}">
		<li class="page-item">
			<a class="page-link" href="${p.endPage+1}">다음페이지</a>
		</li>
		</c:if>
	</ul>
	
</div>
<%@ include file="../layout/footer.jsp" %>


