// 提交基本信息修改
function commitUpdateUserData() {debugger;

	var netNameVal	= $("#net_name").val();
	var genderVal	= $("#gender").val();
	var phoneVal	= $("#phone").val();
	var emailVal	= $("#email").val();
	var addressVal	= $("#address").val();

	$.ajax({
		type : "POST",
		url : "/user/updateUserData.go",
		data : "netName=" + netNameVal + "&gender=" + genderVal + "&phone="
				+ phoneVal + "&email=" + emailVal + "&address=" + addressVal,
		dataType : "json",
		success : function(data) {debugger;
			layer.msg(data.message);
			if (data.status == 1)
				$("input").val("");
		}
	});

}
// 提交密码修改
function commitpasswordUpdate() {debugger;
	$.ajax({
		type : "POST",
		url : "/user/updateUserPassowrd.go",
		data : 'oldPassword=' + $("#oldPassword").val() + '&loginPassword='
				+ $('#loginPassword').val() + '&loginPasswordAffirm='
				+ $('#loginPasswordAffirm').val(),
		dataType : "json",
		success : function(data) {debugger;
			layer.msg(data.message);
			if (data.status == 302)
				goIndex();
		},
		error : function (xhr,status,error){
			debugger;
			console.log(xhr);
			console.log(status);
			console.log(error);
		}
	});
}

// 加载用户基本信息
function loadUserData() {
	var _form = form;
	$.ajax({
		type : "POST",
		url : "/user/loadUser.go?time=" + new Date(),
		data : '',
		dataType : "json",
		success : function(data) {
			layer.msg(data.message);
			if (data.status == 1) {
				var obj = data.obj;
				$("#id").val(obj.id);
				$("#login_name").val(obj.loginName);
				$("#net_name").val(obj.netName);
				$("#gender").val(obj.gender);
				$("#phone").val(obj.phone);
				$("#email").val(obj.email);
				$("#address").val(obj.address);
				$("#create_time").val(timeToString(obj.createtime));
			}
			_form.render('select');
		},
		error : function (xhr,status,error){
			debugger;
			console.log(xhr);
			console.log(status);
			console.log(error);
		}
	});
}