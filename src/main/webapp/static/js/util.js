
// 时间戳转换时间字符串
function timeToString(time = +new Date()){
	    var date = new Date(time + 8 * 3600 * 1000); // 增加8小时
	    return date.toJSON().substr(0, 19).replace('T', ' ');
}

// 鼠标点击特效,借鉴自 https://blog.csdn.net/Blog_7Core_CN/article/details/83472358
/* 鼠标点击特效 - 7Core.CN */
	var a_idx = 0;
jQuery(document).ready( 
		function($) {
			$("html").click(
					function(e) {
						
						  var a = new Array("富强", "民主", "文明", "和谐", "自由", "平等",
						  "公正", "法治", "爱国", "敬业", "诚信", "友善");
						 
						// var a = new Array("♥");
						var $i = $("<span/>").text(a[a_idx]);
						a_idx = (a_idx + 1) % a.length;
						var x = e.pageX, y = e.pageY;
						$i.css({
							"z-index" : 100000,
							"top" : y - 20,
							"left" : x,
							"position" : "absolute",
							"font-weight" : "bold",
							"font-size" : "1em",
							"color" : "#ff6651"
						});
						$("body").append($i);
						$i.animate({
							"top" : y - 180,
							"opacity" : 0
						}, 1500, function() {
							$i.remove();
						});
					});
		});