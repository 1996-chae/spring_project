<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<script src="${contextPath}/resources/js/board/list.js"></script>

<div class="container">
	<div class="jumbotron">
		<h1>게시판</h1>
	</div>
	
	<form class="form-inline searchForm">
		<div class="from-group">
			<ul class="category d-flex justify-content-center">
				<li class="page-item ${cri.category=='' or cri.category==null ? 'active' : ''}">
					<a class="page-link writeType" href="${contextPath }/board/list">전체</a>
				</li>
				<c:forEach items="${typeAll}" var="t">
				<li class="page-item ${t==cri.category ? 'active' : ''}">
					<a class="page-link writeType" href="${contextPath }/board/list?category=${t}">${t}</a>
				 </li>
				 </c:forEach>
			</ul>
		</div>
		<div class="form-group">
		<select class="form-control" name="type">
			<option value="T" >제목</option>
			<option value="C" ${cri.type=='C' ? 'selected':''}>내용</option>
			<option value="TC" ${cri.type=='TC' ? 'selected':''}>제목+내용</option>
			<option value="W" ${cri.type=='W' ? 'selected':''}>작성자</option>
		</select>
		</div>
		<div class="form-group">
			<input type="search" class="form-control" name="keyword" value="${cri.keyword}">
		</div>
		<button class="btn btn-primary">검색</button>
	</form>
	<a href="${contextPath}/board/write">글쓰기</a>
	<table class="table">
		<tr>
			<th>분류</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>날짜</th>
			<th>조회</th>
			<th>댓글</th>
		</tr>
		<c:forEach items="${list}" var="b">
		<tr>
			<td>
				${b.writeType }
			</td>
			<td>
				<a href="${b.bno}" class="title">
				${b.title } 
				 </a>
			</td>
			<td>${b.nickname }</td>
			<td>${b.writeDate }</td>
			<td>추가 예정</td>
			<td>추가 예정</td>
		</tr>
		</c:forEach>
	</table>
	
	<ul class="pagination">
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


