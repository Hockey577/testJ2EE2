
var urlstring = "http://106.14.210.170:8080/zjrt/";

/*点击登录*/
    function login(){
        var username = $("#username").val();
        var password = $("#password").val();
        var checkdata = { "username": username, "password": password };
        $.ajax({
            type: "post",
            url: urlstring+"user/login",
            contentType: "application/json;charset=UTF-8",
            dataType: "json",
            data:JSON.stringify(checkdata),
            success: function (data) {
                console.log(JSON.stringify(data));
                if(data.state == 102){
                    alert("用户名或密码错误！");
                }
                var idents = document.getElementsByName("ident");
                for(var i = 0; i < idents.length; i++) {
                    if(idents[i].checked) {
                        if(idents[i].value === "user" && data.object.type === 0 ) {
                            window.location.href = "../zjrt/user_shouye.html";
                        }
                        else if(idents[i].value === "manager" && data.object.type === 1 ) {
                            window.location.href = "../zjrt/manager_shouye.html";
                        }
                        else{
                            alert("您没有登录权限");
                        }
                    }
                }
            },
        });
    }

/*数据*/
    var test ={
        "success": true,
        "state": "200",
        "object": {
            "userid": 18,
            "username": "111",
            "password": "123456",
            "type": 1,
            "phonenum": "15869191710",
            "code": 0
        },
        "error": null
    }













