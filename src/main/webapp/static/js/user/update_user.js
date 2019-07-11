var form;
var $;
var element;
var util;

layui.use([ 'layer', 'form', 'element', 'util' ], function() {
	form = layui.form;
	// Tab的切换功能，切换事件监听等，需要依赖element模块
	$ = layui.jquery;
	element = layui.element;
	util = layui.util;
	// 自定义属性事件
	util.event('lay-click', {
		// 提交基本信息修改
		commitUpdateUserData : function() {

			$.ajax({
				type : "POST",
				url : "/user/updateUserData.go",
				data : "netName=" + $("#net_name").val() + "&gender="
						+ $("#gender").val() + "&phone=" + $("#phone").val()
						+ "&email=" + $("#email").val() + "&address="
						+ $("#address").val(),
				dataType : "json",
				success : function(data) {
					layer.msg(data.message);
					if (data.status == 1)
						$("input").val("");
				}
			});

		},
		// 提交密码修改
		commitpasswordUpdate : function() {

			$.ajax({
				type : "POST",
				url : "/user/updateUserPassowrd.go",
				data : 'oldPassword=' + $("#oldPassword").val()
						+ '&loginPassword=' + $('#loginPassword').val()
						+ '&loginPasswordAffirm='
						+ $('#loginPasswordAffirm').val(),
				dataType : "json",
				success : function(data) {
					layer.msg(data.message);
					if (data.status == 302)
						goIndex();
				}
			});

		}
	});
	loadUser();
	loadUserData();
});

// 加载用户基本信息
function loadUserData() {
	var _form = form;
	$.ajax({
		type : "POST",
		url : "/user/loadUser.go?time="+new Date(),
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
		}
	});
}