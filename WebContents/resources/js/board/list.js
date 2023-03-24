console.log('board/list.js');

$(function(){
	
	let page = new URLSearchParams(location.search).get('page');
	let category = new URLSearchParams(location.search).get('category');
	let type = new URLSearchParams(location.search).get('type');
	let keyword = new URLSearchParams(location.search).get('keyword');
	
	// 페이지 이동 
	$('.pagination a').click(function(e){
		e.preventDefault();
		
		let form = $('<form/>');
		
		pageNum = $(this).attr('href');

		if (category!=null && category!="") {
			let categoryInput = $('<input/>',{
				type : 'hidden',
				name : 'category',
				value : category
			});
			form.append(categoryInput);
		} 

		if (type!=null && type!="") {
			let typeInput = $('<input/>',{
				type : 'hidden',
				name : 'type',
				value : type
			});
			form.append(typeInput);
		}
		if (keyword!=null && keyword!="") {
			let keywordInput = $('<input/>',{
				type : 'hidden',
				name : 'keyword',
				value : keyword
			});
			form.append(keywordInput);
		}
		
		form.attr('method','get')
			.attr('action',`${contextPath}/board/list/${pageNum}`)
			.appendTo('body')
			.submit();
	});
	
	// 검색 
	$('.searchForm button').click(function(e){
		e.preventDefault();
		
		let form = $('.searchForm');
		if (category!=null && category!="") {
			let categoryInput = $('<input/>',{
				type : 'hidden',
				name : 'category',
				value : category
			});
			form.append(categoryInput);
		}
				
		form.attr('action',`${contextPath}/board/list`)
			.submit();
	})

	// 글상세로 가기
	$('.title').click(function(e){
		e.preventDefault();
		
		let bno = $(this).attr('href');
		let form = $('<form/>');
		
		if(page != null){
			let pageNumInput = $('<input/>',{
				type : 'hidden',
				name : 'page',
				value : page
			});
			form.append(pageNumInput);
		}
		
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
		
		form.attr('method','get')
			.attr('action',`${contextPath}/board/detail/${bno}`)
			.appendTo('body')
			.submit();
	});
	
	// 글쓰기
	$('.write').click(function(e){
		e.preventDefault();
		
		let form = $('<form/>');
		
		if(page != null){
			let pageNumInput = $('<input/>',{
				type : 'hidden',
				name : 'page',
				value : page
			});
			form.append(pageNumInput);
		}
		
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
		
		form.attr('method','get')
			.attr('action',`${contextPath}/board/write`)
			.appendTo('body')
			.submit();
	});
})