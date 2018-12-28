$(function(){
	var windows_height=$(window).height();
	$("body").css("height",windows_height+"px");
	$(".nav li a").click(function(){
		$(this).css({background:"rgb(57,93,123)",color:"rgb(255,255,255)"});
	});
});
