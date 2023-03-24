<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<script src="${contextPath}/resources/js/board/detail.js"></script>

<div class="container">
	<div class="jumbotron">
		<h1>글상세</h1>
	</div>
	<form id="viewForm">
	<table class="table">
		<tr>
			<th>제목</th>
			<td colspan="5">
				${board.title}
			</td>
		</tr>
		
		<tr>
			<th>작성자</th>
			<td>
				${board.nickname}
			</td>
			<th>최초게시일</th>
			<td>${board.writeDate}</td>
		</tr>
		<tr>
			<th>글번호</th>
			<td>
				${board.bno}
				<input type="hidden" name="bno" value="${bno}">
			</td>
			<th>조회수</th>
			<td>예정</td>
			<th>댓글수</th>
			<td>예정</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td colspan="5">
				<div class="my-2">
					<a href="${contextPath}/fileDownload?no=${board.bno}&fileName=${attach.fileName}&path=board">파일 불러고기 예정</a>
				</div>
			</td>
		</tr>	
		<tr>
			<th>내용</th>
			<td colspan="5">
				<textarea rows="10" name="content" class="form-control" readonly="readonly" >${board.content}</textarea>
			</td>
		</tr>
		<tr>
			<th>이미지</th>
			<td colspan="5">
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
			</td>
		</tr>
		<tr>
			<td colspan="6" class="text-center">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				
				<sec:authorize access="isAuthenticated()">
				<sec:authentication property="principal.username" var="user_id"/>
				<c:if test="${board.id eq user_id}">
					<a class="btn btn-info" href="${contextPath}/board/modify/${board.bno}">수정하기</a>
					<button type="button" class="btn btn-secondary remove">삭제</button>
				</c:if>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUB_ADMIN')">
					<button type="button" class="btn btn-secondary remove">삭제</button>
				</sec:authorize>
				<button type="button" class="btn btn-success toList">목록</button>
			</td>
		</tr>
	</table>
	</form>
	
	<form class="replyForm">
		<table class="table">
			<tr>
				<td>
					<textarea rows="6" class="form-control reply_content"></textarea>
				</td>
			</tr>
			<tr class="text-right">
				<td colspan="2"><button class="btn btn-primary reply_write">댓글등록</button></td>
			</tr>
		</table>
	</form>
	<div class="replyList card">
		<c:forEach items="${replyList}" var="reply">
			<ul class="card-header">
				${reply.nickname}
			</ul>
			<ul class="card-body">
				<li>
					${reply.reply}
				</li>
				<li>
					${reply.replyDate}
				</li>
			</ul>
			<ul class="card-footer">
				<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUB_ADMIN',${reply.id})">
					<a class="btn btn-info">수정하기</a>
					<a class="btn btn-secondary">삭제</a>
				</sec:authorize>
			</ul>
		</c:forEach>
	</div>
</div>


<%@ include file="../layout/footer.jsp" %>