// 树的数据
var jsondata = "";
var tree;
var util;
var $;
var element;

// 选择的数据字典信息
var _code;
var _name;
var _id = -1;

layui.use([ 'tree','element', 'util' ], function() {
	tree = layui.tree;
	element = layui.element;
	util = layui.util;
	$ = layui.jquery;
	// 按钮事件
	util.event('lay-click', {
		commitAddDictionary : function() {
			if (_id == -1) {
				layer.msg('未选择任何数据', {
					time : 8000,
					btn : [ '好~', '我知道了!' ]
				});
				return;
			}
			var pid = _id;
			var dataCode = $("#new_code").val();
			var dataName = $("#new_name").val();
			var indexs = layer.load(0);
			$.ajax({
				type : 'post',
				url : '/system/datadictionary/addDictionary.go',
				data : 'pid=' + pid + "&dataCode=" + dataCode + "&dataName="
						+ dataName,
				dataType : 'json',
				success : function(response) {

					layer.msg(response.message, {
						time : 5000, // 20s后自动关闭
						btn : [ '确定', '关闭' ]
					});

					// 如果成功新增,则重载数据字典数据,清空填写框内的数据
					if (response.status == 1) {
						clearData();
						loadMenuTree();
					}
					layer.close(indexs);
				},
				error : function(response) {
					console.log(response);
					layer.close(indexs);
				}
			});

		},
		commitUpdateDictionary : function() {
			var dataCode = $("#update_code").val();
			var dataName = $("#update_name").val();
			var indexs = layer.load(0);
			$.ajax({
				type : 'post',
				url : '/system/datadictionary/updateDictionary.go',
				data : 'id=' + _id + "&dataCode=" + dataCode + "&dataName="
						+ dataName,
				dataType : 'json',
				success : function(response) {

					layer.msg(response.message, {
						time : 5000, // 20s后自动关闭
						btn : [ '确定', '关闭' ]
					});

					// 如果成功修改,则重载数据字典数据,清空填写框内的数据
					if (response.status == 1) {
						clearData();
						loadMenuTree();
					}
					layer.close(indexs);
				},
				error : function(response) {
					console.log(response);
					layer.close(indexs);
				}
			});
		},
		commitDeleteDictionary : function() {
			var indexs = layer.load(0);
			layer.confirm('确定要删除吗？', {
				btn : [ '确定', '取消' ], // 按钮
				shade : false
			// 显示遮罩
			}, function(index) {
				// 提交表单的代码，需要你自己写，然后用 layer.close 关闭就可以了，取消可以省略
				$.ajax({
					type : 'post',
					url : '/system/datadictionary/deleteDictionary.go',
					data : 'id=' + _id,
					dataType : 'json',
					success : function(response) {
						layer.msg(response.message, {
							time : 5000, // 20s后自动关闭
							btn : [ '确定', '关闭' ]
						});
						// 如果成功删除数据,则重载数据字典数据,清空填写框内的数据
						if (response.status == 1) {
							clearData();
							loadMenuTree();
						}
						layer.close(indexs);
					},
					error : function(response) {
						console.log(response);
						layer.close(indexs);
					}
				});
				layer.close(index);
			},function(index){
				layer.close(indexs);
			});
			
		}
	});
});

/* 加载数据字典数据 */
function loadMenuTree() {
	$.ajax({
		type : 'post',
		url : '/system/datadictionary/getMenuTree?A' + new Date(),
		data : '',
		dataType : 'json',
		success : function(response) {
			jsondata = JSON.parse(response.obj);
			jsondata.id = "DataDictionary";
			showtree();
		},
		error : function(response) {
			console.log(response);
		}
	});

}

// 加载树
function showtree() {

	inst1 = tree.render(jsondata);

	var add_code = $("#add_code");
	var add_name = $("#add_name");
	var update_code = $("#update_code");
	var update_name = $("#update_name");
	var delete_code = $("#delete_code");
	var delete_name = $("#delete_name");

	// 给每个键值对div添加单击事件监听
	$('.layui-tree-entry').click(function(e) {
		var id = $(e.currentTarget).parent().attr('data-id');
		$.ajax({
			type : 'post',
			url : '/system/datadictionary/findDataById.go',
			data : 'id=' + id,
			dataType : 'json',
			success : function(response) {

				_code = response.obj.dataCode;
				_name = response.obj.dataName;
				_id = response.obj.id;
				add_code.val(_code);
				add_name.val(_name);
				update_code.val(_code);
				update_name.val(_name);
				delete_code.val(_code);
				delete_name.val(_name);

			},
			error : function(response) {
				console.log(response);
			}
		});
	});
}
// 清空数据字典相关input数据
function clearData() {
	$("#add_code").val("");
	$("#add_name").val("");
	$("#new_code").val("");
	$("#new_name").val("");
	$("#update_code").val("");
	$("#update_name").val("");
	$("#delete_code").val("");
	$("#delete_name").val("");
	_id = -1;
}
