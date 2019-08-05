

// 默认第一页
var pageno = 1;
// 默认一页显示10行数据 10,25,50,100,
var pagesize = 10;
// 页数
var totalcount = 1;

// 上一页,下一页,页码数,触发方法
function changePageData(param) {
	if ("string" == typeof param && "up" == param)
		pageno = pageno <= 1 ? 1 : pageno - 1;
	if ("string" == typeof param && "down" == param)
		pageno = pageno >= totalcount ? totalcount : pageno + 1;
	if ("number" == typeof param)
		pageno = param;
}

// 记一笔账单方法
function commit() {
	var budget_type = document.getElementById("budget_type");
	var reason_type = document.getElementById("reason_type");
	var moneyStr = document.getElementById("money").value;
	var money = (moneyStr == "" || moneyStr == undefined || parseFloat(moneyStr) == NaN) ? 0 : parseFloat(moneyStr);
	var event_time = document.getElementById("event_time");
	var comments = document.getElementById("comments");
	
	// 判空
	if(money == 0 || event_time.value == ""){
		layer.msg("参数缺失,请输入必须的参数(金额,时间).");
		return ;
	}
	$.ajax({
		type : "POST",
		url : "/util/bill/commit.go",
		data : 'budgetType=' + budget_type.value + '&reasonType='
				+ reason_type.value + '&money=' + money + '&eventTime='
				+ event_time.value + '&comments=' + comments.value,
		dataType : "json",
		success : function(data) {
			layer.msg(data.message);
			if (data.status == 1) {
				budget_type.value = "budget_type_2";
				reason_type.value = "reason_type_other";
				document.getElementById("money").value = "";
				event_time.value = "";
				comments.value = "";
			}
		}
		,error : function (response) {
			console.log(response);
		}
		
	});

}

// 查询当前用户历史账单
function findAccountHistoryList() {
	//var indexs = layer.load(0);
	var str = '';
	$.ajax({
		type : "POST",
		url : "/util/bill/findBillList.go",
		data : '',
		dataType : "json",
		success : function(data) {
		console.log(data.obj);
		if (data.status == 1) {
			table.render({
				elem : '#bill_list',
				cols : [ [ // 标题栏
//				  { fixed : 'left' 		, type  : 'checkbox'	}
				, { field : 'id'		, title : '序号'		, sort : true}
				, { field : 'budgetType', title : '收支类型'	, sort : true}
				, { field : 'reasonType', title : '原因类型'	, sort : true}
				, { field : 'money'		, title : '金额'		, sort : true}
				, { field : 'eventTime'	, title : '事件时间'	, sort : true,templet : function(e){ return timeToString(e.eventTime)} }
				, { field : 'comments'	, title : '备注'		}
					] ] // timeToString(list[i].eventTime) .substring(0, 11)
				,data 	: data.obj
//				,toolbar: '#barOperation_1' 
				,skin	: 'line' //表格风格 
				,even	: true
				,page	: true //是否显示分页
				,limits	: [5, 7, 10]
				,limit	: 10 //每页默认显示的数量
			});
		} else if (data.status == -1) {
			layer.msg(data.message);
		}
		//layer.close(indexs);
		}
	,error : function (response){
	//	layer.close(indexs);
	}
	});
}
// 修改一笔数据
function updateBillForId(id) {
	
	// 改变html dom对象增加提交按钮,待用户修改完成,点击提交按钮,发送请求提交修改
	return ;
	var id  ;
	$.ajax({
		type : "POST",
		url : "/util/bill/updateBillForId.go",
		data : '',
		dataType : "json",
		success : function(data) {
			
		}
	});
	
}
// 删除一笔数据
function deleteBillForId(id) {
	if(window.confirm("确定删除该条数据吗?")){
		$.ajax({
			type : "POST",
			url : "/util/bill/deleteBillForId.go",
			data : 'id=' + id ,
			dataType : "json",
			success : function(data) {
				if(data.status == 1) {
					layer.msg(data.message);
					findAccountHistoryList();
				}
			}
		});
	}
}
