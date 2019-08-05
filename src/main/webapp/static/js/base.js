// layui变量
var layer, laydate, form, tree, table, element, util, $;
// 登录状态
var login_status = false;
;!function() {
	layer = layui.layer;
	laydate = layui.laydate;
	form = layui.form;
	tree = layui.tree;
	table = layui.table;
	element = layui.element;
	util = layui.util;
	$ = layui.jquery;
}();
// 获取菜单树数据,并加载到指定id的元素上
function getMenuTreeLoadDom() {
	var str = '';
	$.ajax({
		type : 'POST',
		url : '/system/basic/getMenuTreeForPower.go',
		data : '',
		dataType : 'json',
		success : function(response) {
			if (response.status == 1){
				var obj = response.obj;
				$("#menu_left_nav_ul").html(obj.MenuLeft);
				$("#menu_top_right_ul").html(obj.MenuTopOfRight);
				element.render('nav');
				menuShowOrHide();
			}
		},
		error : function(response) {
			console.log(response);
		}
	});
	form.render();
}

// 显示关于信息
function showAbout(){
	layer.open({
		  type: 0,
		  skin: '', //样式类名
		  closeBtn: 1 //不显示关闭按钮
		  ,area: ['40em', '30em']
		  ,shadeClose: true //开启遮罩关闭
		  ,anim: 4 //动画类型
		  ,content: 
			  '<blockquote class="layui-elem-quote layui-quote-nm">'+
			  '	<h3>个人信息:	</h3><hr></br>'+
			  '	<p>网名		:	su</p><hr>'+
			  '	<p>github	:	SuperInteface</p><hr>'+
			  '	<p>爱好		:	平时喜欢研究网上有意思的技术以及解决方案,沉淀自己的技术,累了就会放空自己,音乐,电影,美食,发呆,上网冲浪.</p><hr>'+
			  '	<p>建设初衷	:	职业生涯的 "记事本",技能成长的 "试验田".</p><hr>'+
			  '	<h3>在这里你可以:	</h3><hr></br>'+
			  '	<p>①注册一个账号记录你的消费支出与收入.</p><hr>' + 
			  '	<p>②查看博客列表和上传博客,以及后续其他功能.</p><hr>'+
			  '</blockquote>'
		});
}

// 加载用户信息
function loadUser() {
	$.ajax({
		type : "POST",
		url : "/user/loadUser.go",
		data : '',
		dataType : "json",
		success : function(data) {
			var rightTopUserName = document.getElementById("");
			if (data.status == 1) {
				login_status = true;
			}
		}
	});
}

// 注销
function logout() {
	var nextFlag = window.confirm("确定注销吗?");
	if (nextFlag == false)
		return;
	$.ajax({
		type : "POST",
		url : "/user/logout.go",
		data : '',
		dataType : "json",
		success : function(response) {
			layer.msg(response.message);
			if (response.status == 1) {
				goIndex();
			}
		},
		error : function(response) {
			console.log(response);
		}
	});
}

// 两秒后跳转主页方法
function goIndex() {
	setTimeout(function() {
		window.location = "/";
	}, 2000);
}

var menuFlag = true;
// 监听指定开关隐藏显示菜单
form.on('switch(menu_form)', function(data){
	menuShowOrHide();
});

function menuShowOrHide(){
	if (menuFlag){
		// 左侧菜单左移
		$("#menu_left").animate({left:'-200px'});
		// 主体内容左移
		$(".layui-body").animate({left:'0px'});
		// 页脚内容左移
		$(".layui-footer").animate({left:'0px'});
	} else{
		// 左侧菜单回位
		$("#menu_left").animate({left:'0px'});
		// 主体内容回位
		$(".layui-body").animate({left:'200px'});
		// 页脚内容回位
		$(".layui-footer").animate({left:'200px'});
	}
	menuFlag = !menuFlag;
}

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