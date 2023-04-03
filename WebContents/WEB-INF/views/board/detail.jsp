<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<script src="${contextPath}/resources/js/board/detail.js"></script>
<script src="${contextPath}/resources/js/board/replyService.js"></script>

<div class=container>
	<div class="jumbotron">
		<h1>글상세</h1>
	</div>
	<form id="viewForm">
	<table class="table">
		<tr>
			<th>글번호</th>
			<td>
				${board.bno}
				<input type="hidden" name="bno" value="${bno}">
			</td>
			<th>제목</th>
			<td>
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
			<th>조회수</th>
			<td>${board.hitsCnt}</td>
			<th>추천수</th>
			<td>${board.likeCnt}</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td colspan="5">
				<c:choose>
					<c:when test="${board.attachFileCnt > 0}">
					<c:forEach items="${attachList}" var="attach">
						<a href="${contextPath}/fileDownload?no=${board.bno}&fileName=${attach.fileName}&path=board">${attach.fileName}</a><br>
					</c:forEach>
					</c:when>
					<c:otherwise>
						첨부된 파일이 없습니다.
					</c:otherwise>
				</c:choose>
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
				
				<button type="button" class="btn btn-success to_prev_board">이전 글</button>
				<sec:authorize access="isAuthenticated()">
					<sec:authentication property="principal.username" var="user_id"/>
					<sec:authentication property="principal.authorities[0]" var="user_grade"/>
					<input type="hidden" name="user_id" value="${user_id}">
					<input type="hidden" name="user_grade" value="${user_grade}">
					<c:if test="${board.id eq user_id}">
						<a class="btn btn-info" href="${contextPath}/board/modify/${board.bno}">수정하기</a>
						<button type="button" class="btn btn-secondary remove">삭제</button>
					</c:if>
					<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUB_ADMIN')">
						<button type="button" class="btn btn-secondary remove">삭제</button>
					</sec:authorize>
				</sec:authorize>
				<button type="button" class="btn btn-success toList">목록</button>   
				<button type="button" class="btn btn-success to_next_board">다음 글</button>
			</td>
		</tr>
	</table>
	</form>
	
	<div class="replyForm">
		<div id="comment-count"> 댓글 <spen id="count">${board.replyCnt}</spen></div>
		<table class="table">
			<tr class="text-right">
				<td>
					<textarea rows="" cols="100" class="form-control reply_content" placeholder="댓글을 입력해 주세요."></textarea>
				</td>
				<td colspan="2"><button class="btn btn-primary reply_write">댓글등록</button></td>
			</tr>
		</table>
	</div>
	
	<div class="replyList card">
		<ul class="list-group"/>
	</div>
</div>


<%@ include file="../layout/footer.jsp" %>