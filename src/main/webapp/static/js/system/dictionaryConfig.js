// 树的数据
var jsondata = "";
var tree;

// 选择的数据字典信息
var _code;
var _name;
var _id = -1;

layui.use([ 'tree'], function() {
	tree = layui.tree;
	loadMenuTree();
});

// 提交新增/修改/删除数据字典方法
function commitAddOrUpdateOrDeleteDictionary (type) {debugger;
	
	if(type == undefined) return;
	if (_id == -1) { layer.msg('未选择任何数据,请选中一条数据重试!'); return; }
	
	// 遮罩
	var indexs = layer.load(0);
	
	var pid = _id;
	
	var dataCode = '';
	var dataName = '';
	var twoUrl = '';
	var dataes = '';
	
	if(type == 'insert'){
		dataCode = $("#new_code").val();
		dataName = $("#new_name").val();
		twoUrl = 'addDictionary.go';
		dataes = 'pid=' + pid + "&dataCode=" + dataCode + "&dataName=" + dataName;
	}else if(type == 'update'){
		dataCode = $("#update_code").val();
		dataName = $("#update_name").val();
		twoUrl = 'updateDictionary.go';
		dataes = 'id=' + _id + "&dataCode=" + dataCode + "&dataName=" + dataName;
	}else if(type == 'delete'){
		twoUrl = 'deleteDictionary.go';
		dataes = 'id=' + _id;
		if(!confirm('确定要删除吗？')) {
			layer.close(indexs);
			return ;
		}
		
	}
	
	$.ajax({
		type : 'post',
		url : '/system/datadictionary/' + twoUrl,
		data : dataes,
		dataType : 'json',
		success : function(response) {
			layer.msg(response.message);
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

}

/* 加载数据字典数据 */
function loadMenuTree() {
	$.ajax({
		type : 'post',
		url : '/system/datadictionary/getDictionaryTree.go?A' + new Date(),
		data : '',
		dataType : 'json',
		success : function(response) {
//			console.log(response);
			jsondata = JSON.parse(response.obj);
			jsondata.id = "DataDictionary";
			showtree();
		},
		error : function(response) {
			console.log(response);
			if (response.status == 302 && response.responseText == 'noPower'){
				layer.msg("没有权限");
				goIndex();
			}
		}
	});
}

// 加载树
function showtree() {
	
	// layui方法, 加载树的数据, 进行渲染.
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
