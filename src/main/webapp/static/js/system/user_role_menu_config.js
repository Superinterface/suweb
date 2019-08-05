
// ajax请求后台获取现有的用户角色菜单权限
function getUserRoleMenuPowerForAjax(){
	$.ajax({
		type : 'post',
		url : '/system/basic/getUserRoleMenuPower.go',
		data : '',
		dataType : 'json',
		success : function(response) {
			console.log(response);
			layer.msg(response.message);
		},
		error : function(response) {
			console.log(response);
		}
	});
}