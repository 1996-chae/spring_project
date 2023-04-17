console.log('replyService.js');

let replyService = {
	
	list : function(bno){
	
		$.ajax({
			url : `${contextPath}/reply/list`,
			type : 'get',
			dataType: 'json',
			data : {bno : bno},
			success : function(replyList){
				replyListRender(replyList);
			}, 
			error: function(xhr, status, error){
				alert('댓글목록 조회 에러');
				console.log(xhr);
				console.log(status);
				console.log(error);
			}
		});	// ajax end	
		
	},
	
	//댓글 작성
	write : function(replyVO){
		
		var token = $('meta[name="_csrf_token"]').attr("content");
		var header = $('meta[name="_csrf_header"]').attr("content");
		
		$.ajax({
			type : 'post',
			url : `${contextPath}/reply/write`, 
			contentType : "application/json; charset=utf-8",  
			data : JSON.stringify(replyVO),
			dataType: 'json',
			beforeSend : function(xhr){
				xhr.setRequestHeader(header, token);
			},
			success : function(replyList){
				replyListRender(replyList);
				$('.reply_content').val('');
			}, 
			error: function(xhr, status, error){
				alert('댓글목록 등록 에러');
				console.log(xhr);
				console.log(status);
				console.log(error);
			}
		}); // ajax end
	},
	
	// 수정 하기
	modifyForm : function(rno){
		
		replyModifyRender(rno);
		
	},
	
	// 수정 완료
	modify : function(replyVO){
		
		var token = $('meta[name="_csrf_token"]').attr("content");
		var header = $('meta[name="_csrf_header"]').attr("content");
		
		$.ajax({
			type : 'post',
			url : `${contextPath}/reply/modify`, 
			contentType : "application/json; charset=utf-8",  
			data : JSON.stringify(replyVO),
			dataType: 'json',
			beforeSend : function(xhr){
				xhr.setRequestHeader(header, token);
			},
			success : function(replyList){
				replyListRender(replyList);
			}, 
			error: function(xhr, status, error){
				alert('댓글목록 수정 에러');
				console.log(xhr);
				console.log(status);
				console.log(error);
			}
		}); // ajax end
	}, 
	
	remove : function(replyVO){
	
		var token = $('meta[name="_csrf_token"]').attr("content");
		var header = $('meta[name="_csrf_header"]').attr("content");
		
		$.ajax({
			type : 'post',
			url : `${contextPath}/reply/remove`, 
			contentType : "application/json; charset=utf-8",  
			data : JSON.stringify(replyVO),
			dataType: 'json',
			beforeSend : function(xhr){
				xhr.setRequestHeader(header, token);
			},
			success : function(replyList){
				replyListRender(replyList);
			}, 
			error: function(xhr, status, error){
				alert('댓글목록 삭제 에러');
				console.log(xhr);
				console.log(status);
				console.log(error);
			}
		}); // ajax end
	}
};

// 댓글 화면 렌더링
function replyListRender(replyList){

	let id = $('input[name=user_id]').val();
	let grade = $('input[name=user_grade]').val();
	
	let output = '';
	for(let r of replyList){
	
		// 밀리초를 Date 객체로 변환
		const dateObj = new Date(r.replyDate);
		  
		// 연도, 월, 일, 시간, 분, 초 값을 추출
		const year = dateObj.getFullYear();
		const month = dateObj.getMonth() + 1;
		const day = dateObj.getDate();
		const hour = dateObj.getHours();
		const minute = dateObj.getMinutes();
		const second = dateObj.getSeconds();
		
		output += `
		<li class="list-group-item">
			<div class="row">
				<div class="col-8 mb-2">
					<textarea class="form-control" readonly>${r.reply}</textarea>
					<span class="badge badge-success">${r.nickname}</span>
					<span class="badge badge-info">
						${year}.${month}.${day}  ${hour}:${minute}:${second}
					</span>
				</div>`
			
		if(r.id==id){
			output+= `
			<div class="col-4 text-right" data-rno="${r.rno}" data-id="${r.id}">
				<button class="btn btn-sm btn-info reply_modBtn">수정</button>
				<button class="btn btn-sm btn-danger reply_delBtn">삭제</button>
			</div>`;
		} else if(grade=="ROLE_ADMIN"){
			output+= `
			<div class="col-4 text-right" data-rno="${r.rno}">
				<button class="btn btn-sm btn-danger reply_delBtn">삭제</button>
			</div>
			`;
		}
		output += `
			</div>
			<div class="reply_content_${r.rno} mb-3" data-rno="${r.rno}" data-id="${r.id}">
			</div>
		</li>
		`;
	}
	$('.replyList ul').html(output);
}

// 댓글 수정 렌더링
function replyModifyRender(rno){
	let output = `
		<textarea rows="3" class="form-control reply_modify_content"></textarea>
		<button class="btn btn-primary reply_modify_write" type="button">수정완료</button>  
		<button class="btn btn-danger reply_modify_cancel" type="button">취소</button>  
	`;
	$(`.reply_content_${rno}`).html(output);
}
