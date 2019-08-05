// 获取省province数据,境内所有城市
function getProvince() {
	var province = document.getElementById("province");
	province.innerHTML = "";
	$.ajax({
		type : "POST",
		url : "/address/getProvince.go",
		data : '',
		dataType : "json",
		success : function(data) {
			if (data.status == 1) {
				var dis = data.obj;
				var op1 = document.createElement("option");
				op1.innerHTML = "请选择";
				province.appendChild(op1);
				for (var i = 0; i < dis.length; i++) {
					// option
					var op = document.createElement("option");
					op.value = dis[i].id;
					op.innerHTML = dis[i].district;
					province.appendChild(op);
				}
			}
		}
	});

}
// 获取市city数据,根据省份代码
function getCity() {
	var province = document.getElementById("province");
	var city = document.getElementById("city");
	city.innerHTML = "";
	$.ajax({
		type : "POST",
		url : "/address/getCity.go",
		data : 'provinceCode=' + parseInt(province.value),
		dataType : "json",
		success : function(data) {
			if (data.status == 1) {
				var dis = data.obj;
				for (var i = 0; i < dis.length; i++) {
					// option
					var op = document.createElement("option");
					op.value = dis[i].id;
					op.innerHTML = dis[i].district;
					city.appendChild(op);
				}
				var other = document.createElement("option");
				other.value = "other";
				other.innerHTML = "其他";
				city.appendChild(other);
			}
		}
	});
}
// 获取区area数据,根据城市代码
function getArea() {
	var city = document.getElementById("city");
	var area = document.getElementById("area");
	area.innerHTML = "";
	$.ajax({
		type : "POST",
		url : "/address/getArea.go",
		data : 'cityCode=' + parseInt(city.value),
		dataType : "json",
		success : function(data) {
			if (data.status == 1) {
				var dis = data.obj;
				for (var i = 0; i < dis.length; i++) {
					// option
					var op = document.createElement("option");
					op.value = dis[i].id;
					op.innerHTML = dis[i].district;
					area.appendChild(op);
				}
				var other = document.createElement("option");
				other.value = "other";
				other.innerHTML = "其他";
				area.appendChild(other);
			}
		}
	});
}

// 获取博客1级类型code name
function getBlogTypeOne() {
	var oo = document.getElementById("blog_type_one");
	oo.innerHTML = "";
	$.ajax({
		type : "POST",
		url : "/system/datadictionary/getDataByCode.go",
		data : 'code=blog_type',
		success : function(data) {
			if (data.status == 1) {
				var dd = data.obj;
				var op1 = document.createElement("option");
				op1.innerHTML = "请选择";
				oo.appendChild(op1);
				for (var i = 0; i < dd.length; i++) {
					var op = document.createElement("option");
					op.value = dd[i].dataCode;
					op.innerHTML = dd[i].dataName;
					oo.appendChild(op);
				}
			}
			form.render('select');	
		},
		 error:function(XMLHttpRequest, textStatus, errorThrown){
             console.log(XMLHttpRequest);
             console.log(textStatus);
             console.log(errorThrown);
         }
	
	});
}
//获取博客2级类型code name
form.on('select(blog_type_one)', function(data){
	var oo = document.getElementById("blog_type_two");
	var code = document.getElementById("blog_type_one");
	oo.innerHTML = "";
	$.ajax({
		type : "POST",
		url : "/system/datadictionary/getDataByCode.go",
		data : 'code=' + code.value,
		dataType : "json",
		success : function(data) {
			if (data.status == 1) {
				var dd = data.obj;
				for (var i = 0; i < dd.length; i++) {
					var op = document.createElement("option");
					op.value = dd[i].dataCode;
					op.innerHTML = dd[i].dataName;
					oo.appendChild(op);
				}
				var other = document.createElement("option");
				other.value = "other";
				other.innerHTML = "其他";
				oo.appendChild(other);
			}
			form.render('select');
		}
	});
});
// 上传博客
function blogUpload() {
	var formData = new FormData();
	formData.append("file", $("#file")[0].files[0]);
	//formData.append("token", $("#token").val());
	formData.append("blog_type_one", $("#blog_type_one").val());
	formData.append("blog_type_two", $("#blog_type_two").val());
	formData.append("blog_title", $("#blog_title").val());
	$.ajax({
		url : "/blog/blogUpload.go",
		type : "POST",
		data : formData,
		// 不要设置Content-Type请求头，因为文件数据是以 multipart/form-data 来编码
		contentType : false,
		// 不要对data参数进行序列化处理，默认为true
		processData : false,
		success : function(response) {
			// 根据返回结果指定界面操作
			layer.msg(response.message);
			$("#blog_type_one").val("");
			$("#blog_type_two").val("");
			$("#blog_title").val("");
			document.getElementById("file").value = "";
		},
		error : function() {
			layer.msg("上传失败！");
		}
	});
}
