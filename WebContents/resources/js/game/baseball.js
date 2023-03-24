console.log('game/baseball.js')
console.log(game);
$(function(){
	
	$('.ssgg button').click(function(e){
		e.preventDefault();
		let form = $('.ssgg');
		
		let roundInput = $('<input/>',{
			type : 'hidden',
			name : 'round',
			value : game.round
		});
		let gameOverInput = $('<input/>',{
			type : 'hidden',
			name : 'gameOver',
			value : game.gameOver
		});
		
		let resultsInput = $('<input/>',{
			type : 'hidden',
			name : 'results',
			value : game.results
		});
		let answerInput = $('<input/>',{
			type : 'hidden',
			name : 'answer',
			value : game.answer
		});
		
		alert(`${game.round}라운드 종료`);
		
		form.attr('method','post')
			.attr('action',`${contextPath}/game/baseball`)
			.append($('[name="${_csrf.parameterName}"]'))
			.append(roundInput)
			.append(gameOverInput)
			.append(resultsInput)
			.append(answerInput)
			.submit();
	});
});