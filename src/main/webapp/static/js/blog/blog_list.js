// blog数据
var datas = new Array();

// 获取博客列表数据
function getBlogData() {
	$.ajax({
		type : "POST",
		url : "/blog/getBlogData.go",
		data : '',
		dataType : "json",
		success : function(response) {
		console.log(response);
			var data1 = JSON.parse(response.obj);
			for(var i=0;i<data1.length;i++){
				data1[i].createTime = timeToString(data1[i].createTime);
			}
			datas = data1;
			// 展示已知数据
			table.render({
				elem : '#blog_list',
				cols : [ [ // 标题栏
//				  { fixed : 'left' 		, type  : 'checkbox'	}
				, { field : 'id'		, title : '序号'	, sort : true, hide: true}
				, { field : 'blogTitle'	, title : '标题'	, event: ''}
				, { field : 'type'		, title : '类型'	, width: 160, sort : true }
				, { field : 'blogAuthor', title : '作者'	, width: 120, sort : true }
				, { field : 'createTime', title : '时间'	, width: 170, sort : true }
				, { fixed : 'right'		, title : '操作'	, toolbar: '#bar_operation_detail', width:150}
					] ]
				,data 	: datas
//				,toolbar: '#barOperation_1' 
				,skin	: 'line' //表格风格 
				,even	: true
				,page	: true //是否显示分页
				,limits	: [5, 7, 10]
				,limit	: 10 //每页默认显示的数量
			});
		}
	});
}

//头工具栏事件
//table.on('toolbar(blog_filter)', function(obj){
//  var checkStatus = table.checkStatus(obj.config.id);
//  switch(obj.event){
//    case 'getCheckData':
//      var data = checkStatus.data;
//      layer.alert(JSON.stringify(data));
//    break;
//    case 'getCheckLength':
//      var data = checkStatus.data;
//      layer.msg('选中了：'+ data.length + ' 个');
//    break;
//    case 'isAll':
//      layer.msg(checkStatus.isAll ? '全选': '未全选');
//    break;
//  };
//});

//监听每一行的查看事件
table.on('tool(blog_filter)', function(obj){
	var data = obj.data;
	if(obj.event === 'detail')
		window.open(window.location.origin + data.blogUrl,'_blank');
});

// 获取博客下拉选数据类型
// function getBlogTypeCodeName() {
// $.ajax({
// type : "POST",
// url : "/system/datadictionary/getBlogDataLevel3.go",
// data : '',
// dataType : "json",
// success : function(res) {
// if (res.status == 1) {
// var dd = res.obj;
// var btype = document.getElementById("blog_type");
// btype.innerHTML = "";
// for (var i = 0; i < dd.length; i++) {
// var op = document.createElement("option");
// op.innerHTML = dd[i].dataName;
// op.value = dd[i].dataCode;
// btype.appendChild(op);
// }
// var all = document.createElement("option");
// all.value = 'all';
// all.innerHTML = 'all';
// btype.appendChild(all);
// }
// }
// });
// }