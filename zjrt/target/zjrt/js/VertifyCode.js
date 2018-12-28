
var urlstring = "http://106.14.210.170:8080/zjrt/";


var InterValObj;
var count = 60;
var curCount;
function sendMessage() {
    var phonenum = $("#phonenumber").val();
    var checkphone = {"phonenum": phonenum };
    //console.log(checkphone);
    $.ajax({
        type: "post",
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        url: urlstring+"message/send",
        data: JSON.stringify(checkphone),
        success: function (data) {
            //console.log(checkphone);
            //console.log(JSON.stringify(data));
            if(data.state== "OK"){
                curCount = count;
                $("#verification_code").attr("disabled", "true");
                $("#verification_code").val(curCount + "s");
                InterValObj = window.setInterval(SetRemainTime, 1000);
            }
            else {
                alert(data.msg);
            }
        }
    });
}
function SetRemainTime() {
    if (curCount == 0) {
        window.clearInterval(InterValObj);
        $("#verification_code").removeAttr("disabled");
        $("#verification_code").val("获取");
    }
    else {
        curCount--;
        $("#verification_code").val(curCount + "s");
    }
}
var test = {
    "success":true,
    "state": "OK",
    "error": null,
    "data": "验证码：150810"
}