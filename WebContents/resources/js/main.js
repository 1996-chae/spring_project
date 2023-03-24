console.log('main.js');
$(function(){
	
	for (const param of new URLSearchParams(window.location.search)) {
	  console.log("param 데이터 : " + param);
	};
	
	// 로그인
	$('.user_login').on('click',function(e){
		e.preventDefault();
		alert('로그인 작동');
		let returnUrl = window.location.href;
		let form = $('.user_form');
		let returnUrlInput = $('<input/>',{
				type : 'hidden',
				name : 'returnUrl',
				value : returnUrl
			});
		
		form.attr('method','post')
			.attr('action',`${contextPath}/member/login`)
			.append(returnUrlInput)
			.submit();
		
	});
	
	// 로그아웃
	$('.user_logout').on('click',function(e){
		e.preventDefault();
		
		let returnUrl = window.location.href;
		let form = $('.user_form');
		let returnUrlInput = $('<input/>',{
				type : 'hidden',
				name : 'returnUrl',
				value : returnUrl
			});
		
		form.attr('method','post')
			.attr('action',`${contextPath}/member/logout`)
			.append(returnUrlInput)
			.submit();
	});


	
});

