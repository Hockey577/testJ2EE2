var urlstring = "http://106.14.210.170:8080/zjrt/";
/*找到所有的集权系统并且添加到网页中*/
function  addAll_clusterSystem() {
    $.ajax({
        type: "get",
        url: urlstring+"cluster/listName",
        contentType: "application/json",
        dataType: "json",
        data:JSON.stringify(),
        async : false,
        success: function (dataString) {
            var dataList=dataString.data;
            //console.log(dataList);
            for(var i=0,item;item=dataList[i];i++)
            {
                if(item)
                {
                    var navLi=$("<li></li>");
                    var navLi_a=$("<a></a>");
                    var navLi_a_p=$("<p></p>");
                    navLi_a_p.text(item.systemname);
                    navLi_a.append(navLi_a_p);
                    navLi.append(navLi_a);
                    $("#subSystem_nav").append(navLi);
                }
            }
        },
        /*error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }*/
    });
}
addAll_clusterSystem();


/*林锐斌写的搜索框*/
var urlstring = "http://106.14.210.170:8080/zjrt/";
var cache = [];//缓存池
var page=1;
var dataNum;
getPNum();
getListName();
getData();
$("#prePage").on('click',function(){
    if(page>1){
        page--;
        $("#page_now").text(page);
        getData();
    }
});
$("#nextPage").on('click',function(){
    page++;
    $("#page_now").text(page);
    getData();
});

function getPNum(){
    $.ajax({
        type: "post",
        url: urlstring+"device/countId",
        contentType: "application/json",
        dataType: "json",
        success: function (dataString) {
            dataNum = dataString.object.number;
            //$("#page_all").text(dataNum/10+1);
            //console.log(dataString.object.number);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function getData(){
    var datajson = {
        "startRow":(page-1)*10,
        "pageSize":10
    };
    $.ajax({
        type: "post",
        url: urlstring+"device/queryAll",
        contentType: "application/json",
        dataType: "json",
        data:JSON.stringify(datajson),
        success: function (dataString) {
            var dataList = dataString.data;

            renderData(dataList);
            cache=dataList;
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

//渲染数据
function renderData(dataList){
    var telp = '';
    for(var i= 0,item;item=dataList[i++];){
        telp += `<li>
			<div class="list_content">
					<p>2018-01-11</p>
					</div>
					<div class="list_content">
					<p>${item.devicename}</p>
			</div>
			<div class="list_content">
					<a href="${item.devicemap}">设备位置</a>
					</div>
					<div class="list_content">
					<a href="${item.docpath}">设备文档</a>
					</div>
					<div class="list_content">
					<a href="#" class="modify" data-toggle="modal" data-target="#myModal01">修改</a>
					<a href="#" class="delete">删除</a>
					</div>
					</li>`;
    }
    $("#mechine_list_ul").html(telp);
}
//集群名字列表
function getListName(){
    $.ajax({
        type: "get",
        url: urlstring+"cluster/listName",
        contentType: "application/json",
        dataType: "json",
        success: function (dataString) {
            var dataList = dataString.data;

            var telp = '';
            for(var i= 0,item;item=dataList[i++];){
                telp += `<li data-value="${item.systemname}" >${item.systemname}</li>`;
            }
            $("#systemList").append(telp);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
//添加数据
function addData(){
    var formData = new FormData();
    var files = [];
    var file1 = document.getElementById("xFile").files[0];
    var file2 = document.getElementById("xFile2").files[0];
    //files.push(file1);
    //files.push(file2);
    //console.log(files);

    formData.append('subsystemid', "1");
    formData.append('devicename', $("#devicename").val());
    formData.append('file', files);
    $.ajax({
        type: "post",
        url: urlstring+"device/addDevice",
        cache: false,
        processData: false,
        contentType: false,
        dataType: "json",
        data: formData,
        success: function (dataString) {
            //searchMatain();
            page=1;
            getData();
            //console.log(dataString);
            //renderData(dataList);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
/*解决画板和pdf放大缩小问题*/
$(document).keydown(function (event) {

    if(event.keyCode === 17)
    {
        $(".wrap").css("display","none");
    }
});
$(document).keyup(function (event) {
    if(event.keyCode === 17)
    {
        $(".wrap").css("display","inherit");
    }
});


/*和子系统、查看pdf有关的按钮点击出现pdf.js*/


    $( "#clusterSystem_iframe" ).contents().find( ".turnTo_pdf" ).click(function () {
        alert("a");
        var temp='';
    temp+='<iframe id="pdfJS_iframe" class="pdf_show " src="generic/web/viewer.html" scrolling="no">\n' +
        '\t\t</iframe>';
    $("body").append(temp);
    $(".wrap").removeClass("dispaly_none");
    /*点击查看子系统的按钮上面挂pdf的地址*/
    /*这个按钮点击之后就就把地址挂在<p id="subSystem_path"的value上面*///以便之后的view.html获取到这个地址
    $("#subSystem_path").text($(this).attr("value"));
    });



$("#backTo_sysytem").click(function () {
    $("#pdfJS_iframe").remove();
    $(".wrap").addClass("dispaly_none")
    $("canvas").remove();
});
$("#subSystem_nav li").each(function () {
    $(this).click(function () {
        if(!$(this).hasClass("input_search"))
        {
            $("#backTo_sysytem").trigger("click");
        }
    });

});
/*点击集群系统切换不同的页面*/
$("#subSystem_nav li").click(function () {
    if(!$(this).hasClass("input_search"))
    {
        var clusterName=$(this).children("a").children("p").text();
        var path=getAllCluster(clusterName);
        $("#clusterSystem_iframe").attr("src","http://106.14.210.170:8080/zjrt/upload/"+path);
    }
});

/*获取所有集群系统信息**再找到名称对应的path*/
function getAllCluster(clusterName) {
    var path;
    $.ajax({
        type: "get",
        url: urlstring+"cluster/list",
        contentType: "application/json",
        dataType: "json",
        data:JSON.stringify(),
        async : false,
        success: function (dataString) {
            var dataList=dataString.data;
            for(var i=0,item;item=dataList[i];i++)
            {
                if(item)
                {
                    if(item.systemname==clusterName)
                    {
                        path=item.systemhtmlpath;
                        break;
                    }
                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
    return path;
}