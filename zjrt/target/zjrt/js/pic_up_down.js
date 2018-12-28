
var urlstring = "http://106.14.210.170:8080/zjrt/";
var cache = 0;//缓存池
var picnum = 0;
getData();
function getData() {
    $.ajax({
        type: "get",
        url: urlstring + "images/list",
        contentType: "application/json",
        dataType: "json",
        success: function (PicList) {
            //console.log(PicList);
            var picList = PicList.data;
            var picnames = "";
            var cache01 = [];
            for(var i = 0; i < picList.length; i++){
                picnames += "<li><p class=\"pic_num_name\">图片编号:</p><span class=\"pic_id\">"+picList[i].imageid+"</span><p class=\"p_append\">"+"图片描述:   "+picList[i].description+"</p></li>"
                //console.log(picList[i].imageid+picList[i].imagepath);
                cache01 = picList;
            }
            //console.log(cache01);
            $("#pic_list_ul").append(picnames);
            //console.log($("#pic_list_ul"));
            $(".pic_list_in ul").on("click","li",function () {
                $(this).siblings().removeClass("choice_PicName");
                $(this).addClass("choice_PicName");
                $(this).children(".pic_id").parent().siblings().children("#delete_id").attr("id"," ");
                $(this).children(".pic_id").attr("id","delete_id");
                var picid = $("#delete_id").text();/*text（）遇到的问题，解决方法：https://yq.aliyun.com/ziliao/167497*/
                //console.log(picid);
                picnum = picid;
                var image_path = "http://106.14.210.170:8080/zjrt/upload/";
                /*获得图片地址（非常重要）*/
                for(var i = 0; i < cache01.length; i++){
                    if(picnum == cache01[i].imageid){
                        image_path += cache01[i].imagepath;
                    }
                }
                    $("#picShow").attr("src",image_path);
                //console.log(image_path);
                return picnum;//这里是为了抛出picnum01供下边删除图片时使用
            });
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
var pictext = {
    "success": true,
    "state": "200",
    "error": null,
    "data": [
        {
            "imageid": 1,
            "imagename": "图1",
            "imagepath": "123",
            "description": "123"
        },
        {
            "imageid": 2,
            "imagename": "图2",
            "imagepath": "123",
            "description": "123"
        }
    ]
};
$('#sure').click(function() {
    var fd = new FormData();
    var file = $("#fileupload").val();
    fd.append('file', $('#fileupload')[0].files[0]);
    fd.append('description', $('#xText').val());
    $.ajax({
        url: 'http://106.14.210.170:8080/zjrt/images/addImage',
        type: 'post',
        contentType: "application/json",
        dataType: "json",
        data:fd,
        cache: false,//cache设置为false，上传文件不需要缓存。
        processData: false,
        contentType: false,
        success: function (picdata) {
            var PicList = picdata.data;
            //console.log(PicList);
            //console.log("上传成功！");
            window.location.reload();
        },
        error: function (msg) {
            //console.log(msg);
        }
    });
});

/*删除图片*/

$('#deletePicName').click(function delete_pic(){
    var checkpic = { "imageid": picnum };
    console.log(JSON.stringify(checkpic));
    if(window.confirm("您确定要删除编号为"+picnum+"的图片？")){
        $.ajax({
            url: 'http://106.14.210.170:8080/zjrt/images/deleteById',
            type: 'post',
            contentType: "application/json;UTF-8",
            dataType: "json",
            data: JSON.stringify(checkpic),
            success: function (data) {
               // console.log(JSON.stringify(data));
                alert("删除成功！");
            },
            /*error: function (msg) {
             console.log(msg);
             console.log("图片不存在！");
             }*/
        });
        return true;
    }
    else{
        window.location.reload();
        return false;
    }

});



/*由于没有图片名称显示，所以只能根据图片路径显示图片辅助管理员选择图片并删除，
 * 这里用来显示即将被删除的图片*/

//var PicName=$("<li></li>");
//PicName.click(function(){
//    $.ajax({
//        url: 'http://106.14.210.170:8080/zjrt/images/list',
//        type: 'get',
//        contentType: "application/json",
//        dataType: "json",
//        success: function (data) {
//            console.log(JSON.stringify(data));
//            var image_path = "http://106.14.210.170:8080/zjrt/upload/"+data.imagepath;
//            console.log(JSON.stringify(data));
//            $("#picShow").append($(this).imagepath);
//        },
//        error: function (msg) {
//            console.log(msg);
//        }
//    });
//});






















