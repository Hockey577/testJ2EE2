
var urlstring = "http://106.14.210.170:8080/zjrt/";

function load(){
    $.ajax({
        type: "post",
        contentType: "application/json;charset=UTF-8",
        url: urlstring+"device/queryAll",
        dataType: "json",
        async : true,//是否异步请求
        success: function(data){
            console.log(JSON.stringify(data));
            var machine_list = "";
            for(var i = 0; i < data.length; i++){
                var datalist = data[i];
                machine_list += "<li><div class=\"list_content\"><p>"+datalist.subsystemid+"</p></div><div class=\"list_content\"><p>"+datalist.devicename+"</p></div><div class=\"list_content\"><a href=\"#\">设备位置</a></div><div class=\"list_content\"><a href=\"#\">设备文档</a></div></li>"
            }
            $("#ul_list").html(machine_list);
        },
    })
}

var text = {
    "success": true,
    "state": "200",
    "error": null,
    "data": [
        {
            "deviceid": 3,
            "devicename": "文档1",
            "docpath": "1",
            "originaldocname": null,
            "devicemap": "1",
            "originaldevicename": null,
            "subsystemid": 5
        },
        {
            "deviceid": 4,
            "devicename": "文档2",
            "docpath": "2",
            "originaldocname": null,
            "devicemap": "2",
            "originaldevicename": null,
            "subsystemid": 13
        }  ]
}



















