
function commit() {debugger;
	var budget_type = document.getElementById("budget_type").value;
	var reason_type = document.getElementById("reason_type").value;
	var money = parseFloat(document.getElementById("money").value);
	var event_time = document.getElementById("event_time").value;
	var comments = document.getElementById("comments").value;
	$.ajax({
		type : "POST",
		url : "/util/account/commit",
		data : 'budgetType='+budget_type+'&reasonType='+reason_type+'&money='+money+'&eventTime='+event_time+'&comments='+comments
		,
		
		dataType : "json",
		success : function(data) {debugger;
		console.log(data)
			showmessage(data.message);
			if (data.status == 1) {
			}
		}
	});

}