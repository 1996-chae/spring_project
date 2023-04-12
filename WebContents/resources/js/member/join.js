console.log('member/join.js');

$(function(){
	
	let checkList = {
		id : false,
		nickname : false
	} 
	
	$('input[name=id]').keyup(function(e){
		e.preventDefault();
		$('.user_id_message').html('');
		checkList.id = false;
	});
	$('input[name=nickname]').keyup(function(e){
		e.preventDefault();
		$('.user_nick_message').html('');
		checkList.nickname = false;
	});
	
	$('.user_id_check').click(function(e){
		e.preventDefault();
		
		var id = $('input[name=id]').val();
		$.ajax({
			url : `${contextPath}/member/idCheck`, 
			type : 'get',
			data : {id : id},
			success : function(result){
				let output = '';
				if(result == 0){
					checkList.id = true;
					output = '사용 가능한 아이디 아이디 입니다.'
				} else {
					checkList.id = false;
					output = '사용 불가능한 아이디 닉네임 입니다.'
				}
				$('.user_id_message').html(output);
			}, 
			error: function(xhr, status, error){
				alert('아이디 체크 오류');
				console.log(xhr);
				console.log(status);
				console.log(error);
			}
		}); // ajax 종료
	});
	
	$('.user_nick_check').click(function(e){
		e.preventDefault();
		
		var nickname = $('input[name=nickname]').val();
		
		$.ajax({
			type : 'get',
			url : `${contextPath}/member/nickCheck`,
			data : {nickname : nickname},
			success : function(result){
				let output = '';
				if(result == 0){
					checkList.nickname = true;
					output = '사용 가능한 아이디 닉네임 입니다.'
				} else {
					checkList.nickname = false;
					output = '사용 불가능한 아이디 닉네임 입니다.'	
				}
				$('.user_nick_message').html(output);
			}, 
			error: function(xhr, status, error){
				alert('닉네임 체크 오류');
				console.log(xhr);
				console.log(status);
				console.log(error);
			}
		}); // ajax 종료
	});
	
	$('.user_join').on('click',function(e){
		e.preventDefault();
		
		if(checkList.nickname == true && checkList.id == true && $('input[name=pwd_check]').eq($('input[name=pwd]'))){
			let form = $('.join_form');
			form.submit();
		}
		
	})
	
});
