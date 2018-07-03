var isAlreadyUsing = true;
var isEnoughLength = false;
var isPasswordConfirmed = false;

	$("#nickname").focusout(function(){
		var nicknameVal = $('#nickname').val();
		if(nicknameVal == "") {
			alert("닉네임을 입력해주세요");
		} else {
			$.ajax({
				url: "/mvc/idcheck",
				data: {nickname: nicknameVal},
				success: function(result){
					if(result == 1) {
						isAlreadyUsing = true;
						$('#nickname-check').html("이미 사용중입니다.");
					} else {
						isAlreadyUsing = false;
						$('#nickname-check').html("사용 가능합니다.");						
					}
				}
			})
		}
	});
	
	$("#password").keyup(function(){
		if($("#password").val().length < 4) {
			$("#password-length").html("4자리 이상을 입력하세요.");
			isEnoughLength = false;
		} else {
			$("#password-length").html("");
			isEnoughLength = true;
		}
	})
	
	$("#password-confirm").keyup(function(){
		if($("#password").val() == $("#password-confirm").val()) {
			$('#password-check').html("일치합니다.");
			isPasswordConfirmed = true;
		} else {
			$('#password-check').html("일치하지 않습니다.");
			isPasswordConfirmed = false;
		}
	})
	
	$("form").submit(function(){
		if(isAlreadyUsing) {
			alert("아이디가 중복됩니다.");
			return false;
		} else if(!isEnoughLength){
			alert("비밀번호는 4자리 이상 입력해주세요.");
			return false;
		} else if(!isPasswordConfirmed) {
			alert("비밀번호를 확인해주세요.");
			return false;
		}
	})