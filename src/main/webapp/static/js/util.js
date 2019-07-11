var login_status = false;
// 时间戳转换时间字符串
function timeToString(time = +new Date()){
	    var date = new Date(time + 8 * 3600 * 1000); // 增加8小时
	    return date.toJSON().substr(0, 19).replace('T', ' ');
}

// 加载用户信息
function loadUser(){
	$.ajax({
		type : "POST",
		url : "/user/loadUser.go",
		data : '',
		dataType : "json",
		success : function(data) {
			var rightTopUserName = document.getElementById("rightTopUserName");
			if(data.status == 1){
				rightTopUserName.innerHTML = "欢迎 : " + data.obj.loginName;
				rightTopUserName.href = "/view/user/personal_information.html";
				login_status = true;
			}else{
				rightTopUserName.innerHTML = "您还未登录,请先登录";
			}
		}
	});
}

// 注销
function logout(){
	
	var nextFlag = window.confirm("确定注销吗?");
	if (nextFlag == false){
		return ;
	}
	var rightTopUserName = document.getElementById("rightTopUserName");
	if(!login_status) {
		layer.msg("您还未登录,请先登录.");
		return ;
	}
	$.ajax({
		type : "POST",
		url : "/user/logout.go",
		data : '',
		dataType : "json",
		success : function(data) {
			showmessage(data.message);
			goIndex();
		}
	});
	
}

// 两秒后跳转主页方法
function goIndex(){
	setTimeout(function(){
		window.location = "/";
	},2000);
}

// 鼠标点击特效,借鉴自 https://blog.csdn.net/Blog_7Core_CN/article/details/83472358
/* 鼠标点击特效 - 7Core.CN */
	var a_idx = 0;
	jQuery(document).ready(
		function($) {
			$("html").click(
					function(e) {
						
						  var a = new Array("富强", "民主", "文明", "和谐", "自由", "平等",
						  "公正", "法治", "爱国", "敬业", "诚信", "友善");
						 
						// var a = new Array("♥");
						var $i = $("<span/>").text(a[a_idx]);
						a_idx = (a_idx + 1) % a.length;
						var x = e.pageX, y = e.pageY;
						$i.css({
							"z-index" : 100000,
							"top" : y - 20,
							"left" : x,
							"position" : "absolute",
							"font-weight" : "bold",
							"font-size" : "1em",
							"color" : "#ff6651"
						});
						$("body").append($i);
						$i.animate({
							"top" : y - 180,
							"opacity" : 0
						}, 1500, function() {
							$i.remove();
						});
					});
		});
/* 解决ajax请求重定向问题 */
$.ajaxSetup({
    complete : function(xhr, status) {
        // 拦截器实现超时跳转到登录页面
        // 通过xhr取得响应头
        var REDIRECT = xhr.getResponseHeader("REDIRECT");
        // 如果响应头中包含 REDIRECT 则说明是拦截器返回的
        if (REDIRECT == "REDIRECT" || xhr.status == 302) {
            var win = window;
            while (win != win.top) {
                win = win.top;
            }
            // 重新跳转到 login.html
            win.location.href = xhr.getResponseHeader("CONTENTPATH");
        }
    }
});

/* bootstrap多个div显示 */

$('#myTabs a').click(function(e) {
	e.preventDefault()
	$(this).tab('show')
});