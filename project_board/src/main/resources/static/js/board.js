function board_submit(){
	var title = $('#title').val();
	var content = document.getElementById("content").innerHTML;
	
	$.ajax({
		url: '/board/insertBoard',
		method: 'post',
		data : {
				"title" : title,
				"content": content
		},
		dataType : 'text',
		success : function(board_id) {
	    	alert("게시물 등록 성공");
	    	console.log(board_id);
	    	location.href = "/board/detail/"+board_id;
		},
		error: function (data, status, err) {
	    	alert("등록에 실패 하였습니다.");
	    },
	});
	
}