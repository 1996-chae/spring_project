<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<script src="${contextPath}/resources/js/board/modify.js"></script>

<div class="container">
	
	<form class="modBoardForm" action="${contextPath}/board/modify?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
		
		<div class="form-group">
			<input type="hidden" name="bno" value="${board.bno}">
			<h3>제목</h3>
			<input type="text" name="title" class="form-control" placeholder="제목" value="${board.title}">
		</div>
		<div class="form-group">
			<input type="hidden" name="id" class="form-control" placeholder="작성자" value="${board.id}" readonly="readonly">
			<input type="hidden" name="nickname" value="${board.nickname}">
		</div>
		<div class="form-group">
			<h3>게시판 분류</h3> 
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
			<h3>내용</h3>
			<textarea rows="10" name="content" class="form-control">${board.content}</textarea>
		</div>
		<div class="fileForm">
			<div>
				<button type="button" class="btn btn-primary add">폼추가</button>
				<button type="button" class="btn btn-danger del">폼삭제</button>
			</div>
			<input type="file" name="attachFile" class="form-control attachFile">
		</div>
		<!-- 첨부파일이 있을 때  -->
		<c:if test="${not empty attachList }">
			<!-- 파일 다운로드 -->
			<c:forEach items="${attachList }" var="attach">
			<div class="media border p-3 my-2">
				<div class="media-body d-flex justify-content-between">
					<div>
						<img src="${contextPath}/resources/images/fileicon.png" style="width:20px">
						${attach.fileName}
					</div>
					<div>
						<label for="delCheck">삭제 : </label>
						<input type="checkbox" class="form-control delCheck" value="${attach.ano}" id="delCheck">					
					</div>
				</div>
			</div>
			</c:forEach>
			<!-- 이미지 미리보기 -->
			<h3>이미지 미리보기</h3>
			<c:forEach items="${attachList }" var="attach">
				<c:if test="${attach.fileType eq 'IMAGE'}">
					<div class="media border p-3 my-2">
						<div class="media-body d-flex justify-content-between">
							<img src="${contextPath}/imgDisplay?filePath=${attach.filePath}&fileName=${attach.fileName}" style="width:200px;">
							<p>${attach.fileName}</p>
						</div>
					</div>			
				</c:if>
			</c:forEach>
			
		</c:if>	
		
		<!-- 첨부파일이 없을 때  -->
		<c:if test="${empty attachList }">
			<div class="media border p-3 my-2">
				<div class="media-body">
					<b>등록된 첨부파일이 없습니다.</b>
				</div>
			</div>
		</c:if>
		<button class="btn btn-primary modBoardProc">수정하기</button>		
	</form>
</div>
<%@ include file="../layout/footer.jsp" %>
