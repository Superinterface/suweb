function loadUserDate(){
	
	var loginName	= document.getElementById("loginName");
	var netName		= document.getElementById("netName");
	var createTime	= document.getElementById("createTime");
	
	$.ajax({
		type : "POST",
		url : "/user/loadUser.go",
		data : '',
		dataType : "json",
		success : function(data) {debugger;
			if(data.status == 1){
				createTime.value	= timeToString(new Date(data.obj.createtime));
				loginName.value		= data.obj.loginName;
				netName.value		= data.obj.netName;
			}
		}
	});
}