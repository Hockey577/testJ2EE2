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
/*************显示参加的文件的名称***************/
$(".pdfFile").change(function () {
    var file = $(".pdfFile").val();
    var fileName = getFileName(file);

    function getFileName(o){
        var pos=o.lastIndexOf("\\");
        return o.substring(pos+1);
    }
    $(".showFile").text(fileName);
});
/*************删除文件名***************/
$(".delete").click(function () {
    $(".fileName").text("");
});
/*************点击确定之后制作矩阵按钮出现红旗***************/
$("#B_flag_sure").click(function () {
    $("#B_flag").removeClass("hide");
});
$("#A_flag_sure").click(function () {
    $("#A_flag").removeClass("hide");
});
/************下拉框动画************/
$(document).ready(function(){
    //点击P标签切换下拉框
    $('.select_left').on('click', function(event) {
        $('.select_left').toggleClass('open');
        event.stopPropagation();
    });
    //点击li标签,赋值、切换下拉框、给选中的li标签添加选中样式同级元素移除选中样式、阻止事件冒泡
    $('.select_left ul').on('click','li',function(event){
        var _this = $(this);
        $('.select_left p').text(_this.text());
        clusterId = _this.attr('data-value');
        getSubSystemList();
        $('.select_left').toggleClass('open');
        _this.addClass('selected').siblings().removeClass('selected');
        event.stopPropagation();
    })
    //点击除下拉框的其它地方，收起下拉框
    $(document).on('click',function(){
        $('.select_left').removeClass('open');
    })
});

$(document).ready(function(){
    //点击P标签切换下拉框
    $('.select_right').on('click', function(event) {
        $('.select_right').toggleClass('open');
        event.stopPropagation();
    });
    //点击li标签,赋值、切换下拉框、给选中的li标签添加选中样式同级元素移除选中样式、阻止事件冒泡
    $('.select_right ul').on('click','li',function(event){
        var _this = $(this);
        $('.select_right p').text(_this.text());
        subsystemId = _this.attr('data-value');
        $('.select_right').toggleClass('open');
        _this.addClass('selected').siblings().removeClass('selected');
        event.stopPropagation();
    })
    //点击除下拉框的其它地方，收起下拉框
    $(document).on('click',function(){
        $('.select_right').removeClass('open');
    })
});
/***********文字随着轮播变化**********/
$(document).ready(
    $(".carousel-indicators li").each(function () {

        if($(this).hasClass("active"))
        {
            var index1=$(".carousel-indicators li").index(this);
            $(".titles p").eq(index1).removeClass("none");
        }
        else{
            var index2=$(".carousel-indicators li").index(this);
            $(".titles p").eq(index2).addClass("none");
        }
    })
);


$(function(){
    $('#myCarousel').on('slide.bs.carousel', function () {});
    $(".carousel-indicators li").each(function () {
        if($(this).hasClass("active"))
        {
            var index1=$(".carousel-indicators li").index(this);
            $(".titles p").eq(index1).removeClass("none");
        }
        /*else{
            var index2=$(".carousel-indicators li").index(this);
            $(".titles p").eq(index2).addClass("none");
        }*/
    })
});

/*创建<li><p>以便添加*/
var innerP=$("<p></p>");
var PicName=$("<li></li>");
/*******添加图片并且PicName的内容是图片名称*******/
$("#fileupload").change(function () {
    var file = $("#fileupload").val();
    var fileName = getFileName(file);

    function getFileName(o){
        var pos=o.lastIndexOf("\\");
        return o.substring(pos+1);
    }
    innerP.text(fileName);
    //加上样式以解决样式失效问题
    innerP.addClass("p_append");
    PicName.append(innerP);
    //点击确定后添加到ul中
    $("#sure").click(function () {
        $(".pic_list_in ul").append(PicName);
    });
    //添加之后并且绑定点击事件
    PicName.on("click",function () {
        $(this).siblings().removeClass("choice_PicName");
        $(this).addClass("choice_PicName");
    });
    //添加图片并且把图片显示出来
    var $file = $(this);
    var objUrl = $file[0].files[0];
    //获得一个http格式的url路径:mozilla(firefox)||webkit or chrome
    var windowURL = window.URL || window.webkitURL;
    //createObjectURL创建一个指向该参数对象(图片)的URL
    var dataURL;
    dataURL = windowURL.createObjectURL(objUrl);
    $("#picShow").attr("src",dataURL);
});
/*被选中的图片删除，否则删除第一张图片*/
/*$(".pic_list_in ul li").click(function () {
      $(this).siblings().removeClass("choice_PicName");
      $(this).addClass("choice_PicName");
  });*/

$(".pic_list_in ul").on("click","li",function () {

    $(this).siblings().removeClass("choice_PicName");
    $(this).addClass("choice_PicName");
    $(this).children(".pic_id").attr("id","delete_id");

});

$("#deletePicName").click(function () {
   $(".pic_list_in ul li").each(function () {
       if($(this).hasClass("choice_PicName"))
       {
           $(this).remove();
       }
   })
});
/*******添加一条维护经验******/
function addMaintenance(title) {
    var maintenance=$("<li></li>");
    maintenance.addClass("mechine_lis_li");
    /*<div class="list_content">
        <a href="#">2500监看设备</a>
    </div>*/
    var list_containt1=$("<div></div>");
    list_containt1.addClass("list_content");
    var link_title=$("<a></a>");
    link_title.text(title);
    list_containt1.append(link_title);
    maintenance.append(list_containt1);


    /*<div class="list_content"><p class="time">2018-01-10</p></div>*/
    var list_containt2=$("<div></div>");
    list_containt2.addClass("list_content");
    var list_containt2_in1=$("<div></div>");
    list_containt2_in1.addClass("list_content");

    //获取系统当前日期
    function p(s) {
        return s < 10 ? '0' + s: s;
    }
    var myDate = new Date();
    //获取当前年
    var year=myDate.getFullYear();
    //获取当前月
    var month=myDate.getMonth()+1;
    //获取当前日
    var date=myDate.getDate();
    var now=year+'-'+p(month)+"-"+p(date)+" ";

    var innerP1=$("<p></p>");
    innerP1.text(now);
    innerP1.addClass("time");
    list_containt2_in1.append(innerP1);
    list_containt2.append(list_containt2_in1);


    var list_containt2_in2=$("<div></div>");
    list_containt2_in2.addClass("list_content");

    var link1_list_containt2_in2=$("<a>置顶 </a>");
    link1_list_containt2_in2.addClass("top");
    link1_list_containt2_in2.attr("href","#");
    list_containt2_in2.append(link1_list_containt2_in2);


    var link2_list_containt2_in2=$("<a>修改 </a>");
    link2_list_containt2_in2.addClass("modify");
    link2_list_containt2_in2.attr("href","#");
    link2_list_containt2_in2.attr("data-toggle","modal");
    link2_list_containt2_in2.attr("data-target","#myModal01");

    list_containt2_in2.append(link2_list_containt2_in2);

    var link3_list_containt2_in2=$("<a>删除</a>");
    link3_list_containt2_in2.addClass("delete");
    link3_list_containt2_in2.attr("href","#");
    list_containt2_in2.append(link3_list_containt2_in2);

    list_containt2.append(list_containt2_in2);
    maintenance.append(list_containt2);
    maintenance.on("click",function () {
        class_delete();

    });
    $("#mechine_list_ul").append(maintenance);
}
$("#addMaintenance").unbind("click").click(function () {
    $("#add_submit").unbind("click").click(function () {
        //获取输入的标题传递给添加维护经验函数
        var title = document.getElementById("title").value;
        addMaintenance(title);
    });


});

/*********删除一条维护经验*********/
$(".delete").each(function () {
    $(this).click(function () {
        var deleteMaintenance=$(this).parents("li");
        deleteMaintenance.remove();
    });
});
/*********修改一条维护经验*************/
/*
$(".modify").each(function () {

    $(this).click(function () {
        /********获取标题*******/
       // var modifyTitleLink=$(this).parent().parent().prev().children("a");
        /********获取时间*******/
        //var modifyTimeP=$(this).parent().prev().children("p");

       //$("#modifyButton").click(function () {
            /*修改标题*/
            //modifyTitleLink.text(document.getElementById("modifyTitle").value);
            /*修改时间*/
                //获取系统当前日期
                /*function p(s) {
                    return s < 10 ? '0' + s: s;
                }
                var myDate = new Date();
                //获取当前年
                var year=myDate.getFullYear();
                //获取当前月
                var month=myDate.getMonth()+1;
                //获取当前日
                var date=myDate.getDate();
                var now=year+'-'+p(month)+"-"+p(date)+" ";

                modifyTimeP.text(now);
        });
    });
});*/
/********点击轮播标题改变*******/
$(".carousel-indicators li").click(function () {
    var index=$(".carousel-indicators li").index(this);
    $(".titles p").addClass("none");
    $(".titles p").eq(index).removeClass("none");
});
/*******轮播导航被点击时标题改变*******/
$("#prev").click(function () {
    $(".carousel-indicators li").each(function () {
       if($(this).hasClass("active"))
       {
           var index=$(".carousel-indicators li").index(this);
           if((index-1)<0)
           {
                index=4;
           }
           else{
                index-=1;
           }
           $(".titles p").addClass("none");
           $(".titles p").eq(index).removeClass("none");
       }
    });
});
$("#next").click(function () {
    $(".carousel-indicators li").each(function () {
        if($(this).hasClass("active"))
        {
            var index=$(".carousel-indicators li").index(this);
            index=(index+1)%5;
            $(".titles p").addClass("none");
            $(".titles p").eq(index).removeClass("none");
        }
    });
});
/**每隔0.5秒检测一次当前轮播项目***/
function nowItem() {
    $(".carousel-indicators li").each(function () {
        if($(this).hasClass("active"))
        {
            var index=$(".carousel-indicators li").index(this);
            $(".titles p").addClass("none");
            $(".titles p").eq(index).removeClass("none");
        }
    });
}
setInterval(function (){ nowItem()},500);

/**********28号之后写的代码********/
/*设备文档的添加*/

function addDeviceDocument(){
    /*2018-01-11*/
//获取系统当前日期
    function p(s) {
        return s < 10 ? '0' + s: s;
    }
    var myDate = new Date();
//获取当前年
    var year=myDate.getFullYear();
//获取当前月
    var month=myDate.getMonth()+1;
//获取当前日
    var date=myDate.getDate();
    var now=year+'-'+p(month)+"-"+p(date)+" ";

    var document_time=$("<p></p>");
    document_time.text(now);
    var div_document_time=$("<div></div>");
    div_document_time.addClass("list_content");
    div_document_time.append(document_time);


    /*2500监看设备*/
    var document_title=$("<p></p>");
    document_title.text(document.getElementById("document_addTitle").value);
    var div_document_title=$("<div></div>");
    div_document_title.addClass("list_content");
    div_document_title.append(document_title);

    /*设备位置*/
    var document_location=$("<a>设备位置</a>");
    var div_document_location=$("<div></div>");
    div_document_location.addClass("list_content");
    div_document_location.append(document_location);
    /*设备文档*/
    var document_document=$("<a>设备文档</a>");
    var div_document_document=$("<div></div>");
    div_document_document.addClass("list_content");
    div_document_document.append(document_document);

    /*修改删除*/
    /*<a href="#" class="modify" data-toggle="modal" data-target="#myModal01">修改</a>
    <a href="#" class="delete">删除</a>*/
    var document_change=$("<a>修改</a>");
    document_change.addClass("modify");
    document_change.addClass("document_modify");

    document_change.attr("data-toggle","modal");
    document_change.attr("data-target","#myModal01");


    var document_delete=$("<a>删除</a>");
    document_delete.addClass("delete");
    var div_document_changeDelete=$("<div></div>");
    div_document_changeDelete.addClass("list_content");
    div_document_changeDelete.append(document_change);
    div_document_changeDelete.append(document_delete);

    var document_li=$("<li></li>");
    document_li.append(div_document_time);
    document_li.append(div_document_title);
    document_li.append(div_document_location);
    document_li.append(div_document_document);
    document_li.append(div_document_changeDelete);

    document_li.on("click",function () {
        class_delete();
    });
    $(".mechine_list>ul").append(document_li);
}
$("#document_add").unbind("click").click(function () {

    $("#document_add_sure").unbind("click").click(function () {
       addDeviceDocument();
    });
});
/**********删除集群系统*********/
$(".pic_list_in ul").on("click","li",function () {
    $(this).siblings().removeClass("choice_system");
    $(this).addClass("choice_system");
});
/*$(".pic_list_in ul li").click(function () {
    $(this).siblings().removeClass("choice_system");
    $(this).addClass("choice_system");
});*/
$("#delete_system").click(function () {
    $(".pic_list_in ul li").each(function () {
        if($(this).hasClass("choice_system"))
        {
            $(this).remove();
        }
    })
});
/*********添加集群系统**********/
$("#add_system").unbind("click").click(function () {
    $("#add_system_sure").unbind("click").click(function () {
        var add_systemLi=$("<li></li>");
        add_systemLi.text(document.getElementById("pdfFile").innerText);
        add_systemLi.addClass("pic_list_in_Li");
        $(".pic_list_in ul").append(add_systemLi);
    });
});

/*******************28号之后写的代码*******************/
/*添加里面的下拉菜单动作1*/
$(document).ready(function(){
    //点击P标签切换下拉框
    $('.select_colony1 > p').on('click', function(event) {
        $('.select_colony1').toggleClass('open');
        event.stopPropagation();
    });
    //点击li标签,赋值、切换下拉框、给选中的li标签添加选中样式同级元素移除选中样式、阻止事件冒泡
    $('.select_colony1 ul li').on('click',function(event){
        var _this = $(this);
        $('.select_colony1 p').text(_this.attr('data-value'));
        $('.select_colony1').toggleClass('open');
        _this.addClass('selected').siblings().removeClass('selected');
        event.stopPropagation();
    })
    //点击除下拉框的其它地方，收起下拉框
    $(document).on('click',function(){
        $('.select_colony1').removeClass('open');
    })
});

/*添加里面的下拉菜单动作1*/
$(document).ready(function(){
    //点击P标签切换下拉框
    $('.select_colony2 > p').on('click', function(event) {
        $('.select_colony2').toggleClass('open');
        event.stopPropagation();
    });
    //点击li标签,赋值、切换下拉框、给选中的li标签添加选中样式同级元素移除选中样式、阻止事件冒泡
    $('.select_colony2 ul li').on('click',function(event){
        var _this = $(this);
        $('.select_colony2 p').text(_this.attr('data-value'));
        $('.select_colony2').toggleClass('open');
        _this.addClass('selected').siblings().removeClass('selected');
        event.stopPropagation();
    })
    //点击除下拉框的其它地方，收起下拉框
    $(document).on('click',function(){
        $('.select_colony2').removeClass('open');
    })
});
function id_delete_system() {
    $(".pic_list_in ul li").click(function () {
        $(this).siblings().removeClass("choice_system");
        $(this).addClass("choice_system");
    });
    $("#delete_system").click(function () {
        $(".pic_list_in ul li").each(function () {
            if($(this).hasClass("choice_system"))
            {
                $(this).remove();
            }
        })
    });
}
function class_delete() {
    $(".delete").each(function () {
        $(this).click(function () {
            var deleteMaintenance=$(this).parents("li");
            deleteMaintenance.remove();
        });
    });
}

//每个修改按钮被点击，时记下所处li是第几个
var modify_li_index;
$("body").on("click",".modify",function () {
        //几下被点击的是第几个li
        var thisLi_modify=$(this).parent().parent().parent();
        modify_li_index=$("#mechine_list_ul li").index(thisLi_modify);
});

//modal里面的修改按钮被点击时修改对应信息条的事件和标题
$("#modifyButton").click(function () {
    var modifyLi=$("#mechine_list_ul li").eq(modify_li_index);
    /*获取要修改的标题*/
   var modifyTitle=modifyLi.children("div").eq(0).children("a");
        //修改标题
    modifyTitle.text(document.getElementById("modifyTitle").value);
    /*获取要修改的时间*/
    var modifyTime=modifyLi.children("div").eq(1).children("div").eq(0).children("p");
    //获取系统当前日期
    function p(s) {
        return s < 10 ? '0' + s: s;
    }
    var myDate = new Date();
    //获取当前年
    var year=myDate.getFullYear();
    //获取当前月
    var month=myDate.getMonth()+1;
    //获取当前日
    var date=myDate.getDate();
    var now=year+'-'+p(month)+"-"+p(date)+" ";
    modifyTime.text(now);


});
var document_modify_index;
$(".mechine_list ul").on("click",".document_modify",function () {
    var this_document_modify=$(this).parent().parent();
    document_modify_index=$(".mechine_list ul li").index(this_document_modify);

});
$("#document_modifyTitle_sure").unbind("click").click(function () {
    /*获取要修改的文档管理的li*/
    var document_modify_li=$(".mechine_list ul li").eq(document_modify_index);
    /*获取要修改的标题*/
    var document_modify_title =document_modify_li.children("div").eq(1).children("p");
        //修改标题
        document_modify_title.text(document.getElementById("document_modifyTitle").value);
    /*获取要修改的日期*/
    var document_modify_time =document_modify_li.children("div").eq(0).children("p");
        //获取系统当前日期
        function p(s) {
            return s < 10 ? '0' + s: s;
        }
        var myDate = new Date();
        //获取当前年
        var year=myDate.getFullYear();
        //获取当前月
        var month=myDate.getMonth()+1;
        //获取当前日
        var date=myDate.getDate();
        var now=year+'-'+p(month)+"-"+p(date)+" ";
    document_modify_time.text(now);


});
/*$(".document_modify").each(function () {
    $(this).click(function () {
        var title_p=$(this).parent().parent().children().eq(1).children();
        $("#document_modifyTitle_sure").click(function () {
            title_p.text(document.getElementById("document_modifyTitle").value);
        });
    });
});


function document_modyfyTitle() {
    $(".document_modify").each(function () {
        $(this).click(function () {
            var title_p=$(this).parent().parent().children().eq(1).children();
            $("#document_modifyTitle_sure").click(function () {
                title_p.text(document.getElementById("document_modifyTitle").value);
            });
        });
    });
}*/
/************下拉框动画************/
$(document).ready(function(){
    //点击P标签切换下拉框
    $('.user_selectLeft').on('click', function(event) {
        $('.user_selectLeft').toggleClass('open');
        event.stopPropagation();
    });
    //点击li标签,赋值、切换下拉框、给选中的li标签添加选中样式同级元素移除选中样式、阻止事件冒泡
    $('.user_selectLeft ul li').on('click',function(event){
        var _this = $(this);
        $('.user_selectLeft p').text(_this.attr('data-value'));
        $('.user_selectLeft').toggleClass('open');
        _this.addClass('selected').siblings().removeClass('selected');
        event.stopPropagation();
    })
    //点击除下拉框的其它地方，收起下拉框
    $(document).on('click',function(){
        $('.user_selectLeft').removeClass('open');
    })
});

$(document).ready(function(){
    //点击P标签切换下拉框
    $('.user_selectRight').on('click', function(event) {
        $('.user_selectRight').toggleClass('open');
        event.stopPropagation();
    });
    //点击li标签,赋值、切换下拉框、给选中的li标签添加选中样式同级元素移除选中样式、阻止事件冒泡
    $('.user_selectRight ul li').on('click',function(event){
        var _this = $(this);
        $('.user_selectRight p').text(_this.attr('data-value'));
        $('.user_selectRight').toggleClass('open');
        _this.addClass('selected').siblings().removeClass('selected');
        event.stopPropagation();
    })
    //点击除下拉框的其它地方，收起下拉框
    $(document).on('click',function(){
        $('.user_selectRight').removeClass('open');
    })
});
$("#add_user_maintain").unbind("click").click(function () {
    $("#sure_user_add_maintain").unbind("click").click(function () {
        //li
        var user_maintain_li=$("<li></li>");


        //2500监看设备
        var maintainTitle=$("<a></a>");
        maintainTitle.text(document.getElementById("maintain_equip_name").value);
        maintainTitle.attr("href","#");
        var div_maintainTitle=$("<div></div>");
        div_maintainTitle.addClass("list_content_one");
        div_maintainTitle.attr("data-toggle","modal");
        div_maintainTitle.attr("data-target","#myModal_01");
        div_maintainTitle.append(maintainTitle);

        user_maintain_li.append(div_maintainTitle);

        //王XX
        var maintainPerson=$("<p></p>");
        maintainPerson.text(document.getElementById("user_maintain_person").value);
        var div_maintainPerson=$("<div></div>");
        div_maintainPerson.addClass("list_content_one");
        div_maintainPerson.append(maintainPerson);

        user_maintain_li.append(div_maintainPerson);
        //监看信号不完整
        var maintainDescribe=$("<p></p>");
        maintainDescribe.text(document.getElementById("maintain_sure_title").value);
        var div_maintainDescribe=$("<div></div>");
        div_maintainDescribe.addClass("list_content_one");
        div_maintainDescribe.append(maintainDescribe);

        user_maintain_li.append(div_maintainDescribe);
        //2018-01-11
        var maintainTime=$("<p></p>");
        function p(s) {
            return s < 10 ? '0' + s: s;
        }
        var myDate = new Date();
        //获取当前年
        var year=myDate.getFullYear();
        //获取当前月
        var month=myDate.getMonth()+1;
        //获取当前日
        var date=myDate.getDate();
        var now=year+'-'+p(month)+"-"+p(date)+" ";
        maintainTime.text(now);
        var div_maintainTime=$("<div></div>");
        div_maintainTime.addClass("list_content_one");
        div_maintainTime.append(maintainTime);

        user_maintain_li.append(div_maintainTime);
        //ul
        $("#user_maintain").append(user_maintain_li);
    });


});



