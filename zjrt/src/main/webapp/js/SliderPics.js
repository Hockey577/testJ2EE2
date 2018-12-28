
var urlstring = "http://106.14.210.170:8080/zjrt/";

$.ajax({
    type: "get",
    contentType: "application/json;charset=UTF-8",
    url: urlstring+"images/list",
    dataType: "json",
    success: function(data){
        console.log(JSON.stringify(data));
        $("#first").attr("src",urlstring+"images/list/"+"468fb525-8ca9-4717-bd11-17bb9f70ef5d.jpg");
    },
    error: function(data){
        alert(data.error);
    }
})


/*var xhr = new XMLHttpRequest();
xhr.open("GET", urlstring+"images/list", true);

xhr.responseType = "blob";
xhr.onload = function()
{
    if (this.readyState != 4)
        return;

    var reader = new FileReader();
    reader.onload = function()
    {
        $("#first").attr("src",this.result);

    };
    reader.readAsDataURL(this.response);
}
xhr.send();*/














