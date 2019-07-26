// blog数据
var datas = new Array();
var table;

debugger;
layui.use([ 'layer', 'element', 'util', 'table' ], function() {debugger;
	form = layui.form;
	// Tab的切换功能，切换事件监听等，需要依赖element模块
	$ = layui.jquery;
	element = layui.element;
	util = layui.util;
	table = layui.table;
	//监听工具条
	table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	  var data = obj.data; //获得当前行数据
	  var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	  var tr = obj.tr; //获得当前行 tr 的DOM对象
	 
	  if(layEvent === 'detail'){ //查看
	    //do somehing
	  } else if(layEvent === 'del'){ //删除
	    layer.confirm('真的删除行么', function(index){
	      obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
	      layer.close(index);
	      //向服务端发送删除指令
	    });
	  } else if(layEvent === 'edit'){ //编辑
	    //do something
	    
	    //同步更新缓存对应的值
	    obj.update({
	      username: '123'
	      ,title: 'xxx'
	    });
	  }
	});
	element.render('nav');
	getBlogData();
});

// 获取博客列表数据
function getBlogData() {
	$.ajax({
		type : "POST",
		url : "/blog/getBlogData.go",
		data : '',
		dataType : "json",
		success : function(response) {
			var data1 = JSON.parse(response.obj);
			for(var i=0;i<data1.length;i++){
				data1[i].createTime = timeToString(data1[i].createTime);
			}
			datas = data1;
			console.log(datas);
			// 展示已知数据
			table.render({
				elem : '#blog_list',
				cols : [ [ // 标题栏
				  { field : 'id'		, title : '序号'	, sort : true, hide: true}
				, { field : 'blogTitle'	, title : '标题'	, event: ''}
				, { field : 'blogUrl'	, title : '路径'	, event: ''}
				, { field : 'type'		, title : '类型'	, width: 160, sort : true }
				, { field : 'blogAuthor', title : '作者'	, width: 120, sort : true}
				, { field : 'createTime', title : '时间'	, width: 170, sort : true } 
				// minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
				// ,{field:'experience', title: '热度', sort: true}
				,{field : 'update'}
				] ],
				data : datas
				,toolbar: 'default' 
				,skin: 'line' //表格风格 
				, even : true
				,page: true //是否显示分页
				,limits: [5, 7, 10]
				,limit: 7 //每页默认显示的数量
			});
		}
	});
}

// // 默认第一页
// var pageno = 1;
// // 默认一页显示10行数据 10,25,50,100,
// var pagesize = 10;
// // 页数
// var totalcount = 1;
//
// // 上一页,下一页,页码数,触发方法
// function changePageData(param) {
// if ("string" == typeof param && "up" == param)
// pageno = pageno <= 1 ? 1 : pageno - 1;
// if ("string" == typeof param && "down" == param)
// pageno = pageno >= totalcount ? totalcount : pageno + 1;
// if ("number" == typeof param)
// pageno = param;
// var type = document.getElementById("blog_type");
// getBlogs(type.value);
// }

// 获取数据,页面载入后执行
// function getBlogs(e) {
// var indexs = layer.load(0);
// var dat = {
// pageNo : pageno,
// pageSize : pagesize,
// type : e != undefined ? "string" == typeof e ? e : '' : ''
// };
// var params = [];
// for (var key in dat) {
// params.push(key + '=' + dat[key]);
// }
// var postData = params.join('&');
// $.ajax({
// type : "POST",
// url : "/blog/getBlog.go",
// data : postData,
// dataType : "json",
// success : function(data) {
// if (data.status == 1) {
//
// var obj = data.obj;
// var blogs = obj.blogs;
// var page = obj.page;
// pageno = page.pageNo;
// pagesize = page.pageSize;
// totalcount = page.totalCount;
//
// var bodyz = document.getElementById("blog_tbody");
// bodyz.innerHTML = "";
// var footz = document.getElementById("blog_tfoot");
// footz.innerHTML = "";
//
// var foot_tr = document.createElement("tr");
// var foot_td = document.createElement("td");
// foot_td.colSpan = 5;
// foot_td.rowSpan = 1;
// foot_td.align = "center";
//
// var btnup = document.createElement("button");
// btnup.type = "button";
// btnup.className = "btn btn-default";
// btnup.onclick = function() {
// changePageData('up');
// };
// btnup.innerHTML = "上一页";
// foot_td.appendChild(btnup);
//
// for (var i = 1; i <= totalcount; i++) {
// var Pa;
// if (pageno == parseInt(i)) {
// Pa = document.createElement("span");
// } else {
// Pa = document.createElement("a");
// Pa.setAttribute("onclick", "changePageData("
// + parseInt(i) + ")");
// Pa.href = "#";
// }
// Pa.style = "margin-left:5px;margin-right:5px;";
// Pa.innerHTML = i;
// foot_td.appendChild(Pa);
// }
//
// var btndown = document.createElement("button");
// btndown.type = "button";
// btndown.className = "btn btn-default";
// btndown.onclick = function() {
// changePageData('down');
// };
// btndown.innerHTML = "下一页";
// foot_td.appendChild(btndown);
//
// foot_tr.appendChild(foot_td);
// footz.appendChild(foot_tr);
//
// for ( let i in blogs) {
// var tr = document.createElement("tr");
// // tr.className = "success";
// var td_no = document.createElement("td"); // 序号
// // (当前页码数-1*页码数据量行数)+1 +i
// td_no.innerHTML = (pageno - 1) * pagesize + 1
// + parseInt(i);
// var td_title = document.createElement("td"); // 标题
// var a = document.createElement("a"); // 标题的超链接
// a.innerHTML = blogs[i].blogTitle;
// var td_type = document.createElement("td"); // 类型
// td_type.innerHTML = blogs[i].type;
// var td_author = document.createElement("td"); // 作者
// td_author.innerHTML = blogs[i].blogAuthor;
// var td_time = document.createElement("td"); // 时间
// td_time.innerHTML = timeToString(blogs[i].createTime);
// var td_1 = document.createElement("td"); // 热度
// td_1.innerHTML = "冰凉";
// a.target = "_blank";
// a.href = blogs[i].blogUrl;
// td_title.appendChild(a);
// tr.appendChild(td_no);
// tr.appendChild(td_title);
// tr.appendChild(td_type);
// tr.appendChild(td_author);
// tr.appendChild(td_time);
// tr.appendChild(td_1);
// bodyz.appendChild(tr);
// }
// }
// layer.close(indexs);
// }
// });
// }

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
