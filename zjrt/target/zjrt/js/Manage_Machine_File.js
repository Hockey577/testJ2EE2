var urlstring = "http://106.14.210.170:8080/zjrt/";
var cache = [];//缓存池
var pageSize = 12;
var page=1;
var pageNum;//页数
var clusterId = '';//当前集群
var subsystemId = '';//当前子系统id
getSystemList();
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
        url: urlstring+"device/countId",
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
        url: urlstring+"device/queryAll",
        contentType: "application/json",
        dataType: "json",
        data:JSON.stringify(datajson),
        success: function (dataString) {
            var dataList = dataString.data;
            //console.log(dataList);
            renderData(dataList);
            cache=dataList;
            getPNum();
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
    var string= "http://106.14.210.170:8080/zjrt/upload/";
    var telp = '';
    for(var i= 0,item;item=dataList[i++];){
        telp += '<li><div class="list_content"><p>'+item.devicename+'</p></div><div class="list_content">'+
					'<a href="'+string+item.devicemap+'">设备位置</a></div><div class="list_content"><a href="'+ string+item.docpath+'">设备文档</a></div><div class="list_content">'+
					'<a href="#" class="modify" data-toggle="modal" data-target="#myModal01" onclick="reData(this);">修改</a><a href="#" class="delete" onclick="deleteData(this)">删除</a></div></li>';
    }
    $("#mechine_list_ul").html(telp);
    //<div class="list_content"><p>2018-01-11</p></div>
}
//集群名字列表
function getSystemList(){
    $.ajax({
        type: "get",
        url: urlstring+"cluster/listName",
        contentType: "application/json",
        dataType: "json",
        success: function (dataString) {
            var dataList = dataString.data;
            //console.log(dataList);
            if(dataList[0].systemname){
                $('.select_left').children('p').text(dataList[0].systemname);
                clusterId = dataList[0].systemid + '';
                getSubSystemList();
            }
            var telp = '';
            for(var i= 0,item;item=dataList[i++];){
                telp += '<li data-value="'+item.systemid+'">'+item.systemname+'</li>';
            }
            $(".select_left ul").html(telp);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
//子系统列表
function getSubSystemList(){
    var datajson = {
        "systemid":clusterId
    };
    $.ajax({
        type: "post",
        url: urlstring+"sub/getByCluster",
        contentType: "application/json",
        dataType: "json",
        data:JSON.stringify(datajson),
        success: function (dataString) {
            var dataList = dataString.data;
            //console.log(dataList);
            if(dataList[0]){
                $('.select_right').children('p').text(dataList[0].subsystemname);
                subsystemId = dataList[0].subsystemid + '';
            }
            else{
                $('.select_right').children('p').empty();
            }
            var telp = '';
            for(var i= 0,item;item=dataList[i++];){
                telp += '<li data-value="'+item.subsystemid+'">'+item.subsystemname+'</li>';
            }
            $(".select_right ul").html(telp);
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
    var file1 = document.getElementById("xFile02").files[0];
    var file2 = document.getElementById("xFile03").files[0];
    files.push(file1);
    files.push(file2);
    //console.log(files);

    formData.append('subsystemid', subsystemId);
    formData.append('devicename', $("#devicename").val());
    formData.append('file', file1);
    formData.append('file', file2);
    $.ajax({
        type: "post",
        url: urlstring+"device/addDevice",
        cache: false,
        processData: false,
        contentType: false,
        dataType: "json",
        data: formData,
        success: function (dataString) {
            console.log(dataString);
            page=1;
            getData();
            //renderData(dataList);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
//修改数据
function reData(obj){
    //console.log(cache[$(obj).parent().parent().index()]);
    var index=$(obj).parent().parent().index();
    //初始化修改表单
    $("#modifyDevicename01").val(cache[index].devicename);

    $("#modifyButton01").click(function(){
        editData(index);
    });
}
function editData(index){
    console.log(index);
    $("#modifyButton01").off("click");
    var formData = new FormData();
    var files = [];
    var file1 = document.getElementById("xFile").files[0];
    var file2 = document.getElementById("xFile01").files[0];
    files.push(file1);
    files.push(file2);
    //console.log(files);

    formData.append('deviceid', cache[index].deviceid);
    formData.append('subsystemid', subsystemId);
    formData.append('devicename', $("#modifyDevicename01").val());
    formData.append('file', file1);
    formData.append('file', file2);
    $.ajax({
        type: "post",
        url: urlstring+"device/addDevice",
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
    var datajson={
        "deviceid":cache[$(obj).parent().parent().index()].deviceid
    };
    $.ajax({
        type: "post",
        url: urlstring+"device/deleteById",
        contentType: "application/json",
        dataType: "json",
        data:JSON.stringify(datajson),
        success: function (dataString) {
            //console.log(dataString);
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
//搜索设备文档
function searchMatain(){
    var datajson={
        "devicename":$("#searchCon").val(),
        "systemname":$('.select_left p').text(),
        "subsystemname":$('.select_right p').text()
    };
    //console.log(datajson);
    $.ajax({
        type: "post",
        url: urlstring+"device/queryByTerm",
        contentType: "application/json",
        dataType: "json",
        data:JSON.stringify(datajson),
        success: function (dataString) {
            var dataList = dataString.data;
            //console.log(dataString);
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