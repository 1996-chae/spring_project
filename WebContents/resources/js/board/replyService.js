console.log('replyService.js');

let replyService = {
	
	list : function(bno){
		console.log('댓글목록 func list : '+bno)
		$.ajax({
			url : `${contextPath}/reply/list`,
			type : 'get',
			dataType: 'json',
			data : {bno : bno},
			success : function(replyList){
				replyListRender(replyList);
			}, 
			error: function(xhr, status, error){
				alert('댓글목록 조회 실패');
				console.log(xhr);
				console.log(status);
				console.log(error);
			}
		});	// ajax end	
	},
	
	write : function(replyVO){
		$.ajax({
			type : 'post',
			url : `${contextPath}/reply/write`, 
			data : replyVO, 
			success : function(result){
				alert(result);
				location.reload(true);
			}, 
			error : function(){
				alert('댓글 등록 에러');
				location.reload(true);
			}
		}); // ajax end 
	},
	
	//수정
	modify : function(vo){
		$.ajax({
			type : 'post', 
			url : `${contextPath}/reply/modify`, 
			data : vo, 
			success : function(result){
				$('.reply_content').val('');
				location.reload(true);
			}, 
			error : function(){
				alert('댓글 수정 에러');
				location.reload(true);
			}
		}); // ajax end
	}, 
	
	remove : function(rno,bno){
		$.ajax({
			type : 'post', 
			url : `${contextPath}/reply/delete`, 
			data : {
				rno : rno,
				bno : bno
			}, 
			success : function(result){
				alert(result);
				location.reload(true);
			}, 
			error : function(){
				alert('댓글 삭제 에러');
				location.reload(true);
			}
		}); // ajax end
	}
};

// 댓글 화면 렌더링
function replyListRender(replyList){
	var id = $('input[name=now_login_id]').val()
	let output = '';
	for(let r of replyList){
		output += 
			`<li class="list-group-item d-flex justify-content-between">
				<div>
					<p>${r.reply}</p>
					<span class="badge badge-success">${r.nickname}</span>
					<span class="badge badge-info">${r.replyDate}</span>
				</div>`
	}
	output += `</li>`;
	$('.replyList ul').html(output);
}

// 댓글 수정 렌더링
