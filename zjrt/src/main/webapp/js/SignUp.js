
var urlstring = "http://106.14.210.170:8080/zjrt/";

/*确认用户名*/
$("#username").blur(function(){
    var username = $("#username").val();
    var checkuser = {"username": username };
    $.ajax({
        type: "post",
        contentType: "application/json;charset=UTF-8",//解决错误415输入类型错误
        url: urlstring+"user/register",
        dataType: "json",
        success: function(data){
            //console.log(checkuser);
            if(data.state == 200){
                alert("该用户名可以使用");
            }
            if(username == ""){
                alert("用户名不能为空");
            }
        }
    })
});
/*确认手机号*/
$("#phonenumber").blur(function(){
    var phonenumber = $("#phonenumber").val();
    var checkphone = {"phonenum": phonenumber};
    $.ajax({
        type: "post",
        contentType: "application/json;charset=UTF-8",
        url: urlstring+"user/register",
        dataType: "json",
        success: function(data){
            //console.log(checkphone);
            //console.log(JSON.stringify(data));
            if (phonenumber == "") {
                alert("您未输入手机号！");
            }//这里未添加判断手机号是否合法
        }
    })
});

/*先设置禁用注册按钮*/
$("#vertify_code").blur(function(){
    var vertify_code = $("#vertify_code").val();
    if(vertify_code == ""){
        $("#signup_on").attr("disabled", "true");
    }
    if(vertify_code !== ""){
        $("#signup_on").removeAttr("disabled");
    }
});
/*点击注册*/
$("#signup_on").click(function(){
    var username = $("#username").val();
    var password = $("#password").val();
    var phonenumber = $("#phonenumber").val();
    var type = 0;
    /*确认身份*/
    var idents = document.getElementsByName("ident");
    for(var i = 0; i < idents.length; i++) {
        if(idents[i].checked) {
            if(idents[i].value === "user") {
                type = 0;
                break;
            }
            else if(idents[i].value === "manager") {
                type = 1;
                break;
            }
            else{
                alert("您还未选择身份");
                break;
            }
        }
    }
    var checkdata = {
        "username": username,
        "password": password,
        "phonenum": phonenumber,
        "type": type
    };
    $.ajax({
        type: "post",
        contentType: "application/json;charset=UTF-8",
        url: urlstring+"user/register",
        dataType: "json",
        data: JSON.stringify(checkdata),
        success: function(data){
            //console.log(checkdata);
            //console.log(JSON.stringify(data));
            if(type == 0 && data.state == 200) {
                alert("您注册身份的是普通用户");
                window.location.href = "../zjrt/user_shouye.html";
            }
            else if(type == 1 && data.state == 200){
                alert("您注册的身份是管理员");
                window.location.href = "../zjrt/manager_shouye.html";
            }
            else if(data.state === 101){
                alert("用户名已被使用");
            }
            else {
                alert("您还未选择身份");
            }
        },
        error: function(data){
            alert(data.error);
        }
    })
});
var test = {
    "success": true,
    "state": "200",
    "error": "null"
};



















