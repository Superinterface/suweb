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
	var money = parseFloat(document.getElementById("money").value);
	var event_time = document.getElementById("event_time");
	var comments = document.getElementById("comments");
	$.ajax({
		type : "POST",
		url : "/util/bill/commit",
		data : 'budgetType=' + budget_type.value + '&reasonType='
				+ reason_type.value + '&money=' + money + '&eventTime='
				+ event_time.value + '&comments=' + comments.value,
		dataType : "json",
		success : function(data) {
			showmessage(data.message);
			if (data.status == 1) {
				budget_type.value = "budget_type_2";
				reason_type.value = "reason_type_other";
				document.getElementById("money").value = "";
				event_time.value = "";
				comments.value = "";
			}
		}
	});

}

// 查询当前用户历史账单
function findAccountHistoryList() {
	loading();
	$.ajax({
		type : "POST",
		url : "/util/bill/findBillList",
		data : '',
		dataType : "json",
		success : function(data) {
			var tbody = document.getElementById("bill_tbody");
			tbody.innerHTML = "";
			if (data.status == 1) {
				var list = data.obj;
				if (list != undefined && list.length > 0) {
					var tfoot = document.getElementById("bill_tfoot");
					var str = "";
					for (var i = 0; i < list.length; i++) {
						str += "<tr>";
						str += "<td>" + (i + 1) + "</td>"; // 序号
						str += "<td>" + list[i].budgetType + "</td>"; // 收支类型
						str += "<td>" + list[i].reasonType + "</td>"; // 原因类型
						str += "<td>" + list[i].money + "</td>"; // 金额
						str += "<td>"
								+ timeToString(list[i].eventTime)
										.substring(0, 11) + "</td>";// 事件时间
						str += "<td>" + list[i].comments + "</td>"; // 备注
						str += "<td>"; // 操作
						str += "<button class='btn btn-default' onclick='updateBillForId("
								+ list[i].id + ")'>修改</button>";
						str += "<button class='btn btn-default' onclick='deleteBillForId("
								+ list[i].id + ")'>删除</button>";
						str += "</td>";
					}
					tbody.innerHTML = str;
				}
			} else if (data.status == -1) {
				showmessage(data.message);
			}
			loaded();
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
		url : "/util/bill/updateBillForId",
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
			url : "/util/bill/deleteBillForId",
			data : 'id=' + id ,
			dataType : "json",
			success : function(data) {debugger;
				
				if(data.status == 1) {
					showmessage(data.message);
					findAccountHistoryList();
				}
			}
		});
	}
}
