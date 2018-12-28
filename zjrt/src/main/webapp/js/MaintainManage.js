var urlstring = "http://106.14.210.170:8080/zjrt/";
var cache = [];//缓存池
var pageSize = 15;
var page=1;
var pageNum;//页数
getData();

$("#prePage").on('click',function(){
    if(page>1){
        page--;
        $("#page_now").text(page);
        getData();
    }
});
$("#nextPage").on('click',function(){
    if(page<pageNum){
        page++;
        $("#page_now").text(page);
        getData();
    }
});
$(document).keyup(function (e) {
    if (e.keyCode == 13&&$("#searchCon").val()!=="") {
        searchMatain();
    }
});
function getPNum(){
    $.ajax({
        type: "post",
        url: urlstring+"maintain/countId",
        contentType: "application/json",
        dataType: "json",
        success: function (dataString) {
            dataNum = dataString.object.number;
            var i = dataNum/pageSize;
            var j = dataNum%pageSize;
            //console.log(i+","+j);
            if(dataNum<pageSize){
                pageNum = 1;
            }
            else {
                if(j==0){
                    pageNum = Math.floor(i);
                }
                else{
                    pageNum = Math.floor(i)+1;
                }
            }
            $("#page_all").text(pageNum);
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
        "startRow":(page-1)*pageSize,
        "pageSize":pageSize
    };
    $.ajax({
        type: "post",
        url: urlstring+"maintain/list",
        contentType: "application/json",
        dataType: "json",
        data:JSON.stringify(datajson),
        success: function (dataString) {
            var dataList = dataString.data;
            if(page==1){
                for(var i= 0,item;item=dataList[i++];){
                    if(item){
                        if(item.type==1){
                            //console.log(i);
                            var t=dataList[i-1];
                            dataList.splice(i-1,1);
                            dataList.unshift(t);
                        }
                    }
                }
                //console.log(dataList);
            }
            if(page != 1){
                $("#all_page").text(page);
            }
            renderData(dataList);
            getPNum();
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
        if(item){
            var isTop;
            if(item.type==1){
                isTop = "取消置顶";
            }
            else{
                isTop = "置顶";
            }
            telp += '<li> <div class="list_content"> <a href="javascript:;">'+item.devicename+'</a> </div> <div class="list_content"> <div class="list_content">'+
                '<p class="time">'+item.addtime+'</p> </div> <div class="list_content"> <a href="#" class="top" onclick="toggleTop(this)">'+isTop+'</a>'+
                '<a href="#" class="modify" data-toggle="modal" data-target="#myModal01" onclick="reData(this)">修改</a><a href="#" class="delete" onclick="deleteData(this)">删除</a>'+
                '</div> </div> </li>';
        }
    }
    $("#mechine_list_ul").html(telp);
}
//搜索维护经验
function searchMatain(){
    var datajson={
        "devicename":$("#searchCon").val()
    };
    $.ajax({
        type: "post",
        url: urlstring+"maintain/queryByName",
        contentType: "application/json",
        dataType: "json",
        data:JSON.stringify(datajson),
        success: function (dataString) {
            var dataList = dataString.data;
            for(var i= 0,item;item=dataList[i++];){
                if(item){
                    if(item.type==1){
                        //console.log(i);
                        var t=dataList[i-1];
                        dataList.splice(i-1,1);
                        dataList.unshift(t);
                    }
                }
            }
            //console.log(dataList);
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
//添加数据
function addData(){
    var formData = new FormData();
    var files = [];
    var file1 = document.getElementById("xFile").files[0];
    var file2 = document.getElementById("xFile2").files[0];
    files.push(file1);
    files.push(file2);
    //console.log(files);

    formData.append('title', $("#title").val());
    formData.append('devicename', $("#devicename").val());
    formData.append('addtime', getNowFormatDate());
    formData.append('username', $("#username").val());
    formData.append('description', $("#description").val());
    formData.append('solution', $("#solution").val());
    formData.append('type', '0');
    formData.append('file', file1);
    formData.append('file', file2);
    console.log(JSON.stringify(formData.files));
    $.ajax({
        type: "post",
        url: urlstring+"maintain/addMaintain",
        cache: false,
        processData: false,
        contentType: false,
        dataType: "json",
        data: formData,
        success: function (dataString) {
            //searchMatain();
            console.log(JSON.stringify(formData.files));
            page=1;
            getData();
            console.log(dataString);
            //renderData(dataList);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
//显示图片
function showImg(obj){
    //检验是否为图像文件
    var file = obj.files[0];
    if(!/image\/\w+/.test(file.type)){
        alert("看清楚，这个需要图片！");
        return false;
    }
    var reader = new FileReader();
    //将文件以Data URL形式读入页面
    reader.readAsDataURL(file);
    reader.onload=function(e){
        //显示文件
        $(obj).siblings("label").children("img").attr("src",this.result);
    }
}
//修改数据
function reData(obj){
    //console.log(cache[$(obj).parent().parent().parent().index()]);
    var index=$(obj).parent().parent().parent().index();
    //初始化修改表单
    $("#modifyTitle").val(cache[index].title);
    $("#modifyUsername").val(cache[index].username);
    $("#modifyDevicename").val(cache[index].devicename);
    $("#modifyDescription").val(cache[index].description);
    $("#modifySolution").val(cache[index].solution);

    $("#modifyButton").click(function(){
        editData(index);
    });
}
function editData(index){
    console.log(index);
    $("#modifyButton").off("click");
    var formData = new FormData();
    var file = [];
    file.push(document.getElementById("modifyxFile").files[0]);
    file.push(document.getElementById("modifyxFile2").files[0]);
    //console.log(file);
    formData.append('maintainid', cache[index].maintainid);
    formData.append('title', $("#modifyTitle").val());
    formData.append('devicename', $("#modifyDevicename").val());
    formData.append('addtime', getNowFormatDate());
    formData.append('username', $("#modifyUsername").val());
    formData.append('description', $("#modifyDescription").val());
    formData.append('solution', $("#modifySolution").val());
    formData.append('type', '1');
    formData.append('file', file);
    $.ajax({
        type: "post",
        url: urlstring+"maintain/addMaintain",
        cache: false,
        processData: false,
        contentType: false,
        dataType: "json",
        data: formData,
        success: function (dataString) {
            page=1;
            getData();
            //console.log(dataString);

            //searchMatain();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
//删除数据
function deleteData(obj){
    //console.log(cache[$(obj).parent().parent().parent().index()]);
    var datajson={
        "maintainid":cache[$(obj).parent().parent().parent().index()].maintainid
    };
    $.ajax({
        type: "post",
        url: urlstring+"maintain/deleteById",
        contentType: "application/json",
        dataType: "json",
        data:JSON.stringify(datajson),
        success: function (dataString) {
            page=1;
            getData();
            //cache.splice($(obj).parent().parent().parent().index(),1);
            //renderData(cache);
            //searchMatain();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
//置顶
function toggleTop(obj){
    console.log(cache[$(obj).parent().parent().parent().index()].type);
    if(cache[$(obj).parent().parent().parent().index()].type==0){
        _setTop(obj);
    }
    else{
        _undoTop(obj);
    }
}
function _setTop(obj){
    //cache[$(obj).parent().parent().parent().index()].type=1;
    $(obj).text("取消置顶");
    var a=$(obj);
    //$(obj).on('click',undoTop(obj));
    var datajson={
        "maintainid":cache[$(obj).parent().parent().parent().index()].maintainid
    };
    $.ajax({
        type: "post",
        url: urlstring+"maintain/setTop",
        contentType: "application/json",
        dataType: "json",
        data:JSON.stringify(datajson),
        success: function (dataString) {console.log(dataString)
            //page=1;
            getData();
            //cache.splice($(obj).parent().parent().parent().index(),1);
            //renderData(cache);
            //searchMatain();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
//取消置顶
function _undoTop(obj){
    //cache[$(obj).parent().parent().parent().index()].type=0;
    $(obj).text("置顶");
    console.log(cache[$(obj).parent().parent().parent().index()]);
    var datajson={
        "maintainid":cache[$(obj).parent().parent().parent().index()].maintainid
    };
    $.ajax({
        type: "post",
        url: urlstring+"maintain/undoTop",
        contentType: "application/json",
        dataType: "json",
        data:JSON.stringify(datajson),
        success: function (dataString) {console.log(dataString)
            //page=1;
            getData();
            //cache.splice($(obj).parent().parent().parent().index(),1);
            //renderData(cache);
            //searchMatain();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

//获取当前时间，格式YYYY-MM-DD
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}
