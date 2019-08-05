
// 注册js校验
function checkout(dom){
	var dominput = document.getElementById(dom).value.trim();
	var domText  = document.getElementById(dom+"Text");
	var picFlag = false;
	var text = "";
	domText.innerHTML = "";
	if(dom == "loginName"){
		if(dominput.length > 10 || dominput.length < 4 || dominput.length == 0) {
			domText.innerHTML = "用户名长度不正确,请重新输入~";
		}else{
			picFlag = true;
		}
	}else if(dom == "loginPassword"){
		var dominput1 = document.getElementById("loginPasswordAffirm").value.trim();
		if(dominput != dominput1) {
			domText.innerHTML = "两次输入的密码不一致请校验后重新输入~";
		}else if(dominput.length < 8 || dominput.length >16){
			domText.innerHTML = "密码长度不正确,请重新输入~";
		}else{
			picFlag = true;
			document.getElementById("loginPasswordAffirmText").innerHTML = "";
			document.getElementById("loginPasswordAffirmText").className = "glyphicon glyphicon-ok";
		}
	}else if(dom == "loginPasswordAffirm"){
		var dominput1 = document.getElementById("loginPassword").value.trim();
		if(dominput != dominput1) {
			domText.innerHTML = "两次输入的密码不一致请校验后重新输入~";
		}else if(dominput.length < 8 || dominput.length >16){
			domText.innerHTML = "密码长度不正确,请重新输入~";
		}else{
			picFlag = true;
			document.getElementById("loginPasswordText").innerHTML = "";
			document.getElementById("loginPasswordText").className = "glyphicon glyphicon-ok";
		}
	}else if(dom == "netName"){
		if(dominput.length>10 || dominput.length==0) {
			domText.innerHTML = "网名长度不正确,请重新输入~";
		}else{
			picFlag = true;
		}
	}else if(dom == "phone"){
		if(!(/^1[34578]\d{9}$/.test(dominput))){
			domText.innerHTML = "手机号码输入不正确,请重新输入~";
		}else{
			picFlag = true;
		}
	}else if(dom == "email"){
		if(dominput.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) == -1) {
			domText.innerHTML = "邮箱输入不正确,请重新输入~";
		}else{
			picFlag = true;
		}
	}
	
	domText.className = picFlag ? "layui-icon layui-icon-ok" : "layui-icon layui-icon-close";
	
}

// 注册
function register(){
	var login_name = $("#loginName").val().trim();
	var login_password = $("#loginPassword").val().trim();
	var login_password_affirm = $("#loginPasswordAffirm").val().trim();
	var net_name = $("#netName").val().trim();
	var gender_ = $("#gender").val();
	var phone_ = $("#phone").val().trim();
	var email_ = $("#email").val().trim();
	
	if(login_name.length > 10 || login_name.length == 0) {
		layer.msg("登录名不符合规范");
		return ;
	}
	if(login_password.length < 8 || login_password.length >16) {
		layer.msg("密码不符合规范");
		return ;
	}
	if(login_password != login_password_affirm){
		layer.msg("两次输入密码不一致");
		return ;
	}
	if(net_name.length>10 || net_name.length==0) {
		layer.msg("网名不符合规范");
		return;
	}
	if(email_.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) == -1) {
		layer.msg("邮箱不符合规范");
		return ;
	}
	
	var dat = {
			loginName : login_name,
			loginPassword : login_password,
			loginPasswordAffirm : login_password_affirm,
			/* username : '真实姓名', */
			netName : net_name,
			gender : gender_,
			phone : phone_,
			email : email_
	};
	var params = [];
	for ( var key in dat) {
		params.push(key + '=' + dat[key]);
	}
	var postData = params.join('&');
	$.ajax({
		type : "POST",
		url : "/user/register.go",
		data : postData,
		dataType : "json",
		success : function(data) {
			layer.msg(data.message);
			if(data.status == 1){
				goIndex();
			}
		}
	});
}

