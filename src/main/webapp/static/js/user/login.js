layui.use([ 'form', 'element', 'util', 'layer' ], function() {
	var form = layui.form;
	// Tab的切换功能，切换事件监听等，需要依赖element模块
	var $ = layui.jquery;
	var element = layui.element;
	var util = layui.util;
});
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
			layer.msg(data.message);
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
