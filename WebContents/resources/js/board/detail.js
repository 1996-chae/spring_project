console.log('board/detail.js');

// 게시판 작업
$(function(){
	
	let page = new URLSearchParams(location.search).get('page');
	
	// 수정하기
	$('.toModForm').on('click',function(e){
		e.preventDefault();
		let form = $('#viewForm');
		form
			.attr("method","get")
			.attr('action',`${contextPath}/board/modifyForm`)
			.submit();
	});
	
	// 삭제하기
	$('.remove').on('click',function(e){
		e.preventDefault();
		let form = $('#viewForm');
		
		form.attr("method","post")
			.attr('action',`${contextPath}/board/delete`)
			.submit();
	});
	
	// 목록으로 돌아가기
	$('.toList').on('click',function(e){
		e.preventDefault();
		
		let form = $('<form/>');
		
		if(page != null){
			form.attr('action',`${contextPath}/board/list/${page}`);
		} else {
			form.attr('action',`${contextPath}/board/list`);
		}
		
		setCriteria(form);
		
		form.attr('method','get')
			.appendTo('body')
			.submit();
		
	});
	
	// 목록으로 돌아가기
	$('.toList').on('click',function(e){
		e.preventDefault();
		
		let form = $('<form/>');
		
		if(page != null){
			form.attr('action',`${contextPath}/board/list/${page}`);
		} else {
			form.attr('action',`${contextPath}/board/list`);
		}
		
		setCriteria(form);
		
		form.attr('method','get')
			.appendTo('body')
			.submit();
		
	});
	
	// 목록으로 돌아가기
	$('.toList').on('click',function(e){
		e.preventDefault();
		
		let form = $('<form/>');
		
		if(page != null){
			form.attr('action',`${contextPath}/board/list/${page}`);
		} else {
			form.attr('action',`${contextPath}/board/list`);
		}
		
		setCriteria(form);
		
		form.attr('method','get')
			.appendTo('body')
			.submit();
	});
	
	// 목록으로 돌아가기
	$('.toList').on('click',function(e){
		e.preventDefault();
		
		let form = $('<form/>');
		
		if(page != null){
			form.attr('action',`${contextPath}/board/list/${page}`);
		} else {
			form.attr('action',`${contextPath}/board/list`);
		}
		
		setCriteria(form);
		
		form.attr('method','get')
			.appendTo('body')
			.submit();
	});
	
	
})

// criteria param 세팅
function setCriteria(form) {

	let category = new URLSearchParams(location.search).get('category');
	let type = new URLSearchParams(location.search).get('type');
	let keyword = new URLSearchParams(location.search).get('keyword');
	
	if (category!=null && category!="") {
			let categoryInput = $('<input/>',{
				type : 'hidden',
				name : 'category',
				value : category
			});
			form.append(categoryInput);
		} 
		
		if(type != null){
			let typeInput = $('<input/>',{
				type : 'hidden',
				name : 'type',
				value : type
			});
			form.append(typeInput);
		}
		
		if(keyword != null){
			let keywordInput = $('<input/>',{
				type : 'hidden',
				name : 'keyword',
				value : keyword
			});
			form.append(keywordInput);
		}
}
	

// 댓글 작업
$(function(){
	let bno = $('input[name="bno"]').val();
	
	// 댓글 목록 
	replyService.list(bno);

	// 댓글 새로고침
	$('.reply_rewind').on('click',function(){
		replyService.list(bno);
	});

	// 댓글 쓰기
	$('.reply_write').on('click',function(){
	
		let reply = $('.reply_content').val();
		let replyVO = {
			bno : bno,
			reply : reply, 
		}
		replyService.write(replyVO);
	});
	
	// 수정 버튼 이벤트
	$('.replyList').on('click','.reply_modBtn',function(){
		
		let rno = $(this).closest('div').data('rno');
		replyService.modifyForm(rno);
		
	});
	
	// 댓글 수정 완료
	$('.replyList').on('click','.reply_modify_write',function(){
		let rno = $(this).closest('div').data('rno');
		let id = $(this).closest('div').data('id');
		let reply = $('.reply_modify_content').val();
		
		let replyVO = {
			bno : bno,
			rno : rno,
			id : id, 
			reply : reply,
		}
		replyService.modify(replyVO);
	});
	
	// 삭제 버튼 이벤트
	$('.replyList').on('click','.reply_delBtn',function(){
		let rno = $(this).closest('div').data('rno');
		let id = $(this).closest('div').data('id');
		let replyVO = {
			bno : bno,
			rno : rno,
			id : id,
		}
		replyService.remove(replyVO);
	});
})