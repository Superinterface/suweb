// 显示loading遮罩层
function loading() {
    var mask_bg = document.createElement("div");
    mask_bg.id = "mask_bg";
    mask_bg.style.position = "absolute";
    mask_bg.style.top = "0px";
    mask_bg.style.left = "0px";
    mask_bg.style.width = "100%";
    mask_bg.style.height = "100%";
    mask_bg.style.backgroundColor = "#777";
    mask_bg.style.opacity = 0.6;
    mask_bg.style.zIndex = 10001;
    document.body.appendChild(mask_bg);
 
    var mask_msg = document.createElement("div");
    mask_msg.style.position = "absolute";
    mask_msg.style.top = "35%";
    mask_msg.style.left = "42%";
    mask_msg.style.backgroundColor = "white";
    mask_msg.style.border = "#336699 1px solid";
    mask_msg.style.textAlign = "center";
    mask_msg.style.fontSize = "1.1em";
    mask_msg.style.fontWeight = "bold";
    mask_msg.style.padding = "0.5em 3em 0.5em 3em";
    mask_msg.style.zIndex = 10002;
    mask_msg.innerText = "正在执行,请稍后...";
    mask_bg.appendChild(mask_msg);
}
// 关闭遮罩层
function loaded() {
    var mask_bg = document.getElementById("mask_bg");
    if (mask_bg != null)
        mask_bg.parentNode.removeChild(mask_bg);
}
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
			}else{
				rightTopUserName.innerHTML = "您还未登录,请先登录";
			}
		}
	});
}
// 通用提示消息modal
function showmessage(message) {
	var defaultMessageModel = document.getElementById("defaultMessageModel");
	var defaultMessage = document.getElementById("defaultMessage");
	defaultMessage.innerHTML = message;
	$('#defaultMessageModel').modal(); // 以默认值初始化
	$('#defaultMessageModel').modal({  // initialized with no keyboard
		keyboard : false
	}); 
	$('#defaultMessageModel').modal('show');
}
// 注销
function logout(){
	
	var nextFlag = window.confirm("确定注销吗?");
	if (nextFlag == false){
		return ;
	}
	var rightTopUserName = document.getElementById("rightTopUserName");
	if(rightTopUserName.innerHTML.indexOf("未登录") > 1) {
		showmessage("您还未登录,请先登录.");
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
						/*
						 * var a = new Array("富强", "民主", "文明", "和谐", "自由", "平等",
						 * "公正", "法治", "爱国", "敬业", "诚信", "友善");
						 */
						var a = new Array("♥");
						var $i = $("<span/>").text(a[a_idx]);
						a_idx = (a_idx + 1) % a.length;
						var x = e.pageX, y = e.pageY;
						$i.css({
							"z-index" : 100000000,
							"top" : y - 20,
							"left" : x,
							"position" : "absolute",
							"font-weight" : "bold",
							"font-size" : "2em",
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

/* bootstrap多个div显示*/

$('#myTabs a').click(function(e) {
	e.preventDefault()
	$(this).tab('show')
});
/* $('#myTabs a[href="#profile"]').tab('show'); // Select tab by name
$('#myTabs a:first').tab('show'); // Select first tab
$('#myTabs a:last').tab('show'); // Select last tab
$('#myTabs li:eq(2) a').tab('show'); // Select third tab (0-indexed) */

