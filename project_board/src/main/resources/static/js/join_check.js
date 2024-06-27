//아이디 유효성검사
$(document).ready(function() {
	
	let patternEmail = /^[a-z0-9_-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
	let nickPattern = /([^가-힣a-zA-Z0-9])/i;

	let a = [0,0,0,0];

	$("#email").on("propertychange change keyup paste input", function(){
		
		if(!patternEmail.test($.trim($("#email").val()))){
			$('#emailchk').html("아이디는 이메일 형식으로 입력해 주세요.").css('color','red');
			a[0] = 0;
		} else {
			var email = $('#email').val();
			$.ajax({
				url: '/emailCheck',
				method: 'post',
				data : {
						"email" : email
				},
				dataType : 'text',
				
				success : function(data) {
					if (data == "redundancy") {
						$('#emailchk').html("중복된 아이디입니다").css('color', 'red');
						a[0] = 0;
					} else if (data == "noredundancy") {
						$('#emailchk').html("사용가능한 아이디입니다").css('color', 'green');
						a[0] = 1;
					;
					} else {
						$('#emailchk').html("아이디를 입력해주세요").css('color', 'red');
						a[0] = 0;
					}
				},
				error: function (data, status, err) {
					// 에러날때 확인하는 곳
			    },
			});
		}
		;
		isSubmit();
	});
	
	
	// 비밀번호
	$("#password").on("propertychange change keyup paste input", function(){
		let pw = $('#password').val();
		let num = pw.search(/[0-9]/g);
		let	engLow = pw.search(/[a-z]/ig);

		//빈값		
		if(!$('#password').val()){
			$('#pwchk').html("영문과 숫자를 포함한 6~16자").css('color','red');		
			a[1] = 0;
		// 자릿수 부족
		} else if($('#password').val().length < 6 || $('#password').val().length > 16){
			$('#pwchk').html("영문과 숫자를 포함한 6~16자").css('color','red');		
			a[1] = 0;
		} else {
			// 조건 부족
			if(num < 0 || engLow < 0 ){
				$('#pwchk').html("영문과 숫자를 포함한 6~16자").css('color','red');	
				a[1] = 0;
			} else {
				// 성공
				$('#pwchk').html("사용가능한 아이디입니다").css('color', 'green');
				a[1] = 1;
			}
		}
		isSubmit();
	});
	
		// 비밀번호 확인
	$("#password2").on("propertychange change keyup paste input", function(){
		let pw = $('#password').val();
		//빈값		
		if(!$('#password2').val()){
			$('#pw2chk').html("비밀번호가 일치하지 않습니다.").css('color','red');		
			a[2] = 0;
		} else {
			// 조건 부족
			if(pw != $('#password2').val()){
				$('#pw2chk').html("비밀번호가 일치하지 않습니다.").css('color','red');		
				a[2] = 0;
			} else {
				// 성공
				$('#pw2chk').html("비밀번호가 일치 합니다.").css('color', 'green');
				a[2] = 1;
			}
		}
		isSubmit();
	});
	
	// 닉네임 확인
	$("#nickname").on("propertychange change keyup paste input", function(){
		var nickname = $('#nickname').val();
		//빈값		
		if(!nickname){
			$('#nicknamechk').html("사이트에서 사용하실 닉네임을 입력해 주세요.(2~6자)").css('color','red');		
			a[3] = 0;
		}else if(nickPattern.test($.trim($("#nickname").val()))){
			$('#nicknamechk').html("사용할 수 없는 닉네임 입니다.(한글, 영문, 숫자 2~6자)").css('color','red');					
			a[3] = 0;
		} else {
			// 조건 부족
			if(nickname.length < 2 || nickname.length > 6){
				$('#nicknamechk').html("사이트에서 사용하실 닉네임을 입력해 주세요.(2~6자)").css('color','red');		
				a[3] = 0;
			} else {
				
				$.ajax({
					url: '/nicknameCheck',
					method: 'post',
					data : {
							"nickname" : nickname
					},
					dataType : 'text',
					
					success : function(data) {
						if (data == "redundancy") {
							$('#nicknamechk').html("중복된 닉네임입니다").css('color', 'red');
							a[0] = 0;
						} else if (data == "noredundancy") {
							// 성공
							$('#nicknamechk').html("사용가능한 닉네임 입니다.").css('color', 'green');
							a[3] = 1;
						}
					},
				});
			}
		}
		isSubmit();
	});	
	
	
	
	// 유효성 검사를 모두 통과하면 해당 함수 실행할 것
	function isSubmit(){
		let ok = true;
		
		a.forEach(function(n){
			if(n==0){
				ok = false;
				return;
			} 
		});
		
		if(ok){
			$('#submit-btn').attr("disabled", false);
		} else {
			$('#submit-btn').attr("disabled", true);			
		}
	}	
	
	
})