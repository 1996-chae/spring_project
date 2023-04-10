console.log('game/baseball.js')
$(function(){

	var token = $('meta[name="_csrf_token"]').attr("content");
	var header = $('meta[name="_csrf_header"]').attr("content");
	let game = [];

	$('.baseball_base').on('click','.baseball_turn_start',function(e){
		e.preventDefault();
		alert('시작!!!!');
		
		$.ajax({
			type : 'post',
			url : `${contextPath}/game/baseball/start`, 
			contentType : "application/json; charset=utf-8",  
			dataType: 'json',
			beforeSend : function(xhr){
				xhr.setRequestHeader(header, token);
			},
			success : function(vo){
				console.log(vo);
				game = {
					round : vo.round,
					gameOver : vo.gameOver,
					results : vo.results,
					guess : vo.guess,
					answer : vo.answer,
				};
				playBaseball(vo);
				
				if(vo.gameOver){
					overBaseball(vo);
				}
				
			}, 
			error: function(xhr, status, error){
				alert('야구 게임 오류');
				console.log(xhr);
				console.log(status);
				console.log(error);
			}
		}); // ajax end
	});
	
	$('.baseball_base').on('click','.baseball_turn_end',function(e){
		e.preventDefault();
		
		game.guess = $('input[name = guess]').val();
		
		$.ajax({
			type : 'post',
			url : `${contextPath}/game/baseball/turn`, 
			contentType : "application/json; charset=utf-8",  
			data : JSON.stringify(game),
			dataType: 'json',
			beforeSend : function(xhr){
				xhr.setRequestHeader(header, token);
			},
			success : function(vo){
				console.log(vo);
				
				game = {
					round : vo.round,
					gameOver : vo.gameOver,
					results : vo.results,
					guess : vo.guess,
					answer : vo.answer,
				};
				playBaseball(vo);
				
				if(vo.gameOver){
					overBaseball(vo);
				}
				
			}, 
			error: function(xhr, status, error){
				alert('야구 게임 오류');
				console.log(xhr);
				console.log(status);
				console.log(error);
			}
		}); // ajax end
	});
});

// 야구게임 렌더링
function playBaseball (vo) {
	
	let output = `
		<h1>1~9 숫자 야구 게임 - 라운드 ${vo.round}</h1>
		<p>1~9 중 중복되지 않는 3개의 숫자를 입력하세요. 예) 123</p>
		<form class="ssgg">
			<input type="text" name="guess" pattern="[1-9]{3}" maxlength="3" required autofocus>
			<button class="baseball_turn_end">제출</button>
		</form>
		<p>결과:</p>
		<ul class="list-group">
		`
	for(let index = 0; index < vo.results.length; index++) {
		output += `<li class="list-group-item border">${vo.results[index]}</li>`
		}
	output += `</ul>`
		
	$(`.baseball_base`).html(output);
	
}
function overBaseball (vo) {
	
	let output = `
	    <h1>1~9 숫자 야구 게임 - 게임 종료</h1>
	    <p>정답: ${vo.answer}</p>
	    <p>당신의 시도: ${vo.round}</p>
	    <ul class="list-group">
	    `
	for(let index = 0; index < vo.results.length; index++) {
		output += `<li class="list-group-item border">${vo.results[index]}</li>`
		}
	output += `
	    </ul>
		<form method="post">
			<h1>1~9 숫자 야구 게임</h1>
		    <p>게임을 재시작하려면 아래의 버튼을 클릭하세요.</p>
			<button class="baseball_turn_start">다시 하기</button>
		</form>
	`
		
	$(`.baseball_base`).html(output);
	
}