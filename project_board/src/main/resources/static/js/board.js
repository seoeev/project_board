

function board_submit(){
	var title = $('#title').val();
	var content = document.getElementById("board_content").innerHTML;
	var friend_only = 'N';
	
	if($('#is_friend_only').is(':checked')){
		friend_only = 'Y';
	}
	
	if( title == "" || title == null || title == undefined || ( title != null && typeof value == "object" && !Object.keys(title).length ) ){
		alert("제목을 입력해 주세요.");
		$("#title").focus();
		return;
	}
	
	if( content == "" || content == null || content == undefined || ( content != null && typeof content == "object" && !Object.keys(content).length ) ){
		alert("내용을 입력해 주세요.");
		$("#content").focus();
		return;
	}
	
	var board_id = $('#board_id').val();
	var action_url = '/board/insertBoard';
	// board_id가 없다면 신규 등록, 있다면 수정
	
	if(board_id != ""){
		action_url = '/board/updateBoard';
	}	
	
	$.ajax({
		url: action_url,
		method: 'post',
		data : {
				"title" : title,
				"content": content,
				"is_friend_only": friend_only,
				"board_id": board_id
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








function deleteBoard(board_id){
	
	if(confirm("삭제 하시겠습니까?")){
		location.href = "/board/delete/"+board_id;
	}else {
		
	}
	
}