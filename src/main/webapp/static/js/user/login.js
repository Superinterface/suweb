// 登录
function login() {
	var username = document.getElementById("inputUsername").value;
	var password = document.getElementById("inputPassword").value;
	var authCode = document.getElementById("authCodeInput").value;
	$.ajax({
		type : "POST",
		url : "/user/login.go",
		data : 'loginName=' + username + '&loginPassword=' + password + '&authCode=' + authCode,
		dataType : "json",
		success : function(data) {
			showmessage(data.message);
			if (data.status == 1) { // 登陆成功
				window.location="/"
			} else { // 登陆失败

			}
		}
	});
}

//获取登录验证码
function getAuthCode() {
	$("#authCode").attr("src","/system/basic/getAuthCode.go?random="+Math.random());
}
