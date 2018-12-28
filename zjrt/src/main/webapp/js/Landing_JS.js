/***********************设置根字体大小**************/
var WinWidth=window.innerWidth;
var rootFontSize=WinWidth*100.0/1920;//100px为根元素，屏幕宽1920px
$("html").css("font-size",rootFontSize+"px");

/***********************字体随窗口动态变化**************/
$(window).resize(function () {
    WinWidth=window.innerWidth;
    rootFontSize=WinWidth*100.0/1920;//100px为根元素，屏幕宽1920px
    $("html").css("font-size",rootFontSize+"px");
});
$(".landing_checkbox input").click(function () {
    var inputId=$(this).attr("id");
    $(".landing_checkbox input").each(function () {
       $(this).css("border","#0380ba");
    });
    $("label").each(function () {
       $(this).css("color","#797979");
    });

    $("label").each(function () {
        var labelFor=$(this).attr("for");
        if(inputId==labelFor)
        {
            $(this).css("color","#0380ba");
        }
    });

});

