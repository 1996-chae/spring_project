<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<script src="${contextPath}/resources/js/board/write.js"></script>

<div class="container">
	
	<form class="write_form" action="${contextPath}/board/write?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
		
		<div class="form-group">
			게시판 분류 : 
			<select id="writeType" name="writeType" class="form-control" >
				<option value="none" disabled>=== 선택 ===</option>
			<c:forEach items="${typeAll}" var="t">
				<option value="${t}" ${"공지"==t ? 'hidden' : ''}>${t}</option>
			</c:forEach>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUB_ADMIN')">
					<option value="공지">공지(운영자 전용)</option>
					<option value="direct">직접입력(운영자 전용)</option>
				</sec:authorize>
			</select>
			<input type="text" id="selboxDirect" name="selboxDirect">
		</div>
		
		<div class="form-group">
			제목 : <input type="text" class="form-control" name="title">
		</div>
		<div class="form-group fileForm">
			<div>
				<button type="button" class="btn btn-primary add">폼추가</button>
				<button type="button" class="btn btn-danger del">폼삭제</button>
			</div>
			<input type="file" name="attachFile" class="form-control attachFile">
		</div>
		<div class="form-group">
			내용 : <textarea rows="10" class="form-control" name="content"></textarea>
		</div>
		
		<button type="button" class="btn btn-primary write_complet">글쓰기</button>
	</form>
</div>




<%@ include file="../layout/footer.jsp" %>