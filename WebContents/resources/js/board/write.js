console.log('board/write.js');

$(function(){
	
	// 글 카테고리 설정(운영자)
	$("#selboxDirect").hide();
	$("#writeType").change(function() {
        //직접입력을 누를 때 나타남
		if($("#writeType").val() == "direct") {
			$("#selboxDirect").show();
		}  else {
			$("#selboxDirect").hide();
		}
	});
	
	// 파일 업로드 서비스
	let formCount = 1; 
	$('.fileForm .add').click(function(){
		if(formCount>=5){
			alert('최대 5개까지 추가할 수 있습니다.')
			return;
		}
		let fileFormTag = $('.fileForm .attachFile').eq(0).clone().val('');
		$('.fileForm').append(fileFormTag);
		formCount++;
	});
	
	$('.fileForm .del').click(function(){
		if(formCount <=1){
			alert('마지막폼은 삭제할 수 없습니다.');
			return;
		}
		$('.fileForm .attachFile').eq(formCount-1).remove();
		formCount--;
	});
	
	// 파일 쓰기
	$('.write_complet').click(function(e){
		e.preventDefault();
		alert('작동');
		let form = $('.write_form');
		
		form.submit();
	});
});