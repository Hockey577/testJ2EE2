
var urlstring = "http://106.14.210.170:8080/zjrt/";

/*先设置禁用找回按钮*/
$("#vertify_code").blur(function(){
    var vertify_code = $("#vertify_code").val();
    if(vertify_code == ""){
        $("#find_paswd").attr("disabled", "true");
    }
    if(vertify_code !== ""){
        $("#find_paswd").removeAttr("disabled");
    }
});

/*点击找回*/
$("#find_paswd").click(function(){
    var username = $("#username").val();
    var newpassword = $("#newpassword").val();
    var phonenumber = $("#phonenumber").val();
    var vertify_code = $("#vertify_code").val();
    var vertify_number = vertify_code.match();
    //console.log(vertify_number);
    var checkdata = {
        "username": username,
        "password": newpassword,
        "phonenum": phonenumber
    };
    $.ajax({
        type: "post",
        contentType: "application/json;charset=UTF-8",
        url: urlstring+"user/modifyPwd",
        dataType: "json",
        data: JSON.stringify(checkdata),
        success: function(data){
            //console.log(checkdata);
            //console.log(JSON.stringify(data));
            /*if( ){
                if (data.state == 200) {
                    alert("密码修改成功!")
                    window.location.href = "denglu.html";
                }
                if (data.state == 101)
                    alert("用户名不正确！")
            }*/
        }
    })
});

var test = {
    "success": true,
    "state": "200",
    "error": "null"
};




















