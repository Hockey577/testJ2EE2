var urlstring = "http://106.14.210.170:8080/zjrt/";
/*添加集群系统*/
$("body").on("click","#add_system_sure",function () {
    var systemname = $("#pdfFile_name").text();
    //创建formdata对象，formData用来存储表单的数据，表单数据时以键值对形式存储的。
    var formData = new FormData();
    formData.append("systemname",systemname);
    formData.append('file', $('#pdfFile')[0].files[0]);

    $.ajax({
        type: "post",
        url: urlstring+"cluster/addCluster",
        cache: false,
        processData: false,
        contentType: false,
        dataType: "json",
        data:formData,
        success: function(data) {

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }

    });

});

/*添加图片到首页以便于user_jiqunguanli使用*/
/*$("body").on("click","#add_system_sure",function () {
    var description = $("#pdfFile_name").text();
    //创建formdata对象，formData用来存储表单的数据，表单数据时以键值对形式存储的。
    var formData = new FormData();
    formData.append("description",description);
    formData.append('file', $('#pdfFile')[0].files[0]);

    $.ajax({
        type: "post",
        url: urlstring+"images/addImage",
        cache: false,
        processData: false,
        contentType: false,
        dataType: "json",
        data:formData,
        success: function(dataString) {
            var dataList=dataString.data;
            console.log(dataList);

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }

    });

});*/

/*获取所有集群系统信息**再找到名称对应的id*/
function getAllCluster(clusterName) {
   var id;
    $.ajax({
        type: "get",
        url: urlstring+"cluster/listName",
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
                        id=item.systemid;
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
    return id;
}
/*通过id找到集群系统并且删除*/
$("body").on("click","#delete_system",function () {
    /*获取要删除的集权系统的名称*/
    var clusterName;
    $(".pic_list_in ul li").each(function () {
            if($(this).hasClass("choice_system"))
           {
                clusterName=$(this).text();
                $(this).remove();
           }
    });
    var id=getAllCluster(clusterName);
    var datajson = {
        "systemid":id
    };
    $.ajax({
        type: "post",
        url: urlstring + "cluster/deleteById",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(datajson),
        success: function (dataString) {
            //console.log(dataString);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
});
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
                    var add_systemLi=$("<li></li>");
                    add_systemLi.text(item.systemname);
                    add_systemLi.addClass("pic_list_in_Li");
                    if(i==0)
                    {
                        add_systemLi.addClass("choice_system");
                    }
                    $(".pic_list_in ul").append(add_systemLi);
                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
addAll_clusterSystem();

/*B—添加子系统图*/
$("body").on("click","#B_flag_sure",function () {
    var subsystemname = $("#pdfFileB_name").text();
    var systemid;
    var systemName;
    //获取当前集群名称
    $(".pic_list_in ul li").each(function () {
        if($(this).hasClass("choice_system"))
        {
            systemName=$(this).text();
            systemid=getAllCluster(systemName);
        }
    });

    //创建formdata对象，formData用来存储表单的数据，表单数据时以键值对形式存储的。
    var formData = new FormData();
    formData.append("subsystemname",subsystemname);
    formData.append("systemid",systemid);
    formData.append('file', $('#pdfFileB')[0].files[0]);
   // console.log(subsystemname+systemid);
    $.ajax({
        type: "post",
        url: urlstring+"sub/addSub",
        cache: false,
        processData: false,
        contentType: false,
        dataType: "json",
        data:formData,
        success: function(data) {
            console.log(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }

    });
});

/*A—添加子系统图*/
$("body").on("click","#A_flag_sure",function () {
    var subsystemname = $("#pdfFileA_name").text();
    var systemid;
    var systemName;
    //获取当前集群名称
    $(".pic_list_in ul li").each(function () {
        if($(this).hasClass("choice_system"))
        {
            systemName=$(this).text();
            systemid=getAllCluster(systemName);
        }
    });

    //创建formdata对象，formData用来存储表单的数据，表单数据时以键值对形式存储的。
    var formData = new FormData();
    formData.append("subsystemname",subsystemname);
    formData.append("systemid",systemid);
    formData.append('file', $('#pdfFileA')[0].files[0]);
    console.log(subsystemname+systemid);
    $.ajax({
        type: "post",
        url: urlstring+"sub/addSub",
        cache: false,
        processData: false,
        contentType: false,
        dataType: "json",
        data:formData,
        success: function(data) {
            console.log(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }

    });
});

