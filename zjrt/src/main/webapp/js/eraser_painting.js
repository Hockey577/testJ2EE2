/******************浏览器窗口与字根大小***********/
var WinWidth=window.innerWidth;
var WinHeight=window.innerHeight;
var rootFontSize=WinWidth*100.0/1920;//100px为根元素，屏幕宽1920px
$("html").css("font-size",rootFontSize+"px");
$( "#clusterSystem_iframe" ).contents().find( ".turnTo_pdf" ).click(function () {

    wholrCavas();
});
var canvasWidth=$("#clusterSystem_iframe").width();
var canvasHeight=$("#clusterSystem_iframe").height();
/*以下修改*/
//设置橡皮擦和画线的初始逻辑值
var startPaint=true;
var startWipe=false;
$("#startPaint").click(function () {
    startPaint=true;
    startWipe=false;
});
$("#starWipe").click(function () {
   startWipe=true;
   startPaint=false;
});
/*以上修改*/
function wholrCavas() {


    /******************展开渐变条选颜色***********/
    var choiceColor=false;
    $("#colorNow").click(function () {
        if(choiceColor===false)
        {
            /*以下是修改*/
            $("#controller").removeClass("disappear");
            /*以上是修改*/
            $(".colorUl").removeClass("disappear");
            choiceColor=true;
        }
        else{
            /*以下是修改*/
            $("#controller").addClass("disappear");
            /*以上是修改*/
            $(".colorUl").addClass("disappear");
            choiceColor=false
        }
    });

    /******************手动添加canvas以控制高度宽度***********/
        //var canvasWidth=WinWidth;
        // var canvasHeight=WinHeight;
    var myCanvas = document.createElement("canvas");
    myCanvas.id = "canvas";
    $(".wrap").prepend(myCanvas);
    document.getElementById("canvas").width =$("#clusterSystem_iframe").width();
    //alert($("#clusterSystem_iframe").width()+" "+$("#clusterSystem_iframe").height());
    $("body").height=canvasHeight;
    document.getElementById("canvas").height =$("#clusterSystem_iframe").height();


    /*******************************源码源码*********************************/
//定义宽和高
//var canvasWidth = Math.min(800,$(window).width()-20); //适配移动端
//var canvasHeight = canvasWidth;

//定义鼠标是否按下
    var isMouseDown = false;
//定义上一次鼠标的位置
    var lastLoc = {x:0,y:0}; //初始化为0
//定义时间戳
    var lastTimestamp = 0;
//定义上一次线条的宽度
    var lastLineWidth = -1;
//当前笔的颜色
    var strokeColor = "#ff0000";

//拿到canvas
    var canvas = document.getElementById("canvas");
//拿到相应的上下文绘图环境
    var context = canvas.getContext("2d");

//设定画布的宽和高
    canvas.width = canvasWidth;
    canvas.height = canvasHeight;

//适配移动端
    $("#controller").css("width",canvasWidth+"px");

//绘制米字格
//drawGrid();
//清除按钮操作
    /*$("#clear_btn").click(
        function(e){
            context.clearRect(0,0,canvasWidth,canvasHeight);
            //重新绘制米字格
            drawGrid();
        }
    );*/
    $(".color_btn").click(
        function(e) {
            $(".color_btn").removeClass("color_btn_selected");
            $(this).addClass("color_btn_selected");
            strokeColor = $(this).css("background-color");
            $("#colorNow").css("background-color",strokeColor);
        }
    );

//逻辑整合

    /**
     * 开始
     */
    function beginStroke(point){
        isMouseDown = true;
        lastLoc = windowToCanvas(point.x, point.y);
        lastTimestamp = new Date().getTime();
    }
    /**
     * 结束
     */
    function endStroke(){
        isMouseDown = false;
    }
    /**
     * 绘图
     */
    function moveStroke(point){
        //核心代码
        var curLoc = windowToCanvas(point.x,point.y);
        var curTimestamp = new Date().getTime();
        /****Draw Start****/
        context.beginPath();
        /*以下修改*/
        if(startPaint)
        {
            context.moveTo(lastLoc.x,lastLoc.y);
            context.lineTo(curLoc.x ,curLoc.y);
            startWipe=false;
        }
        if(startWipe)
        {
            context.clearRect(lastLoc.x,lastLoc.y,20,20);
            startPaint=false;
        }
        /*以上修改*/


        //计算速度
        var s = calcDistance(curLoc, lastLoc);
        var t = curTimestamp - lastTimestamp;
        var lineWidth = calcLineWidth(t,s);
        context.strokeStyle = strokeColor;
        context.lineWidth = lineWidth;
        context.lineCap = "round"; //矩形的帽子 可以绘制出平滑的线条
        context.lineJoin = "round";
        context.stroke();
        /****Draw End****/
        lastLoc = curLoc;
        lastTimestamp = curTimestamp;
        lastLineWidth = lineWidth;
    }
//鼠标事件
    canvas.onmousedown=function(e){
        e.preventDefault();
        beginStroke({x:e.clientX,y:e.clientY});
    };

    canvas.onmouseup=function(e){
        e.preventDefault();
        endStroke();
    };

    canvas.onmouseout=function(e){
        e.preventDefault();
        endStroke();
    };

    canvas.onmousemove=function(e){
        e.preventDefault();
        if(isMouseDown){
            //可以绘图了
            moveStroke({x:e.clientX,y:e.clientY});
        }
    };
//触控事件
    canvas.addEventListener('touchstart',function(e){
        e.preventDefault();
        touch = e.touches[0];
        beginStroke({x:touch.pageX,y:touch.pageY});
    });
    canvas.addEventListener('touchmove',function(e){
        e.preventDefault();
        if(isMouseDown){
            //可以绘图了
            touch = e.touches[0];
            moveStroke({x:touch.pageX,y:touch.pageY});
        }
    });
    canvas.addEventListener('touchend',function(e){
        e.preventDefault();
        endStroke();
    });


    /**
     * 绘制米字格
     */
    function drawGrid(){

        context.save();

        //绘制红色的正方形边框
        context.strokeStyle = "rgb(230,11,9)";

        context.beginPath();
        context.moveTo(3,3);
        context.lineTo(canvas.width - 3 ,3);
        context.lineTo(canvas.width - 3 ,canvas.height - 3);
        context.lineTo(3 ,canvas.height - 3);
        context.closePath();

        context.lineWidth = 6;
        context.stroke();

        //绘制米字格
        context.beginPath();
        context.moveTo(0,0);
        context.lineTo(canvasWidth,canvasHeight);

        context.moveTo(canvasWidth,0);
        context.lineTo(0,canvasHeight);

        context.moveTo(canvasWidth/2,0);
        context.lineTo(canvasWidth/2,canvasHeight);

        context.moveTo(0,canvasHeight/2);
        context.lineTo(canvasWidth,canvasHeight/2);

        context.lineWidth = 1;
        context.stroke();

        context.restore();
    }
    /**
     * 窗口到画布的位置
     */
    function windowToCanvas(x,y){
        var bbox = canvas.getBoundingClientRect();
        return {x:Math.round(x-bbox.left),y:Math.round(y-bbox.top)};
    }

    /**
     * 计算距离
     */
    function calcDistance(loc1,loc2){
        return Math.sqrt((loc1.x-loc2.x)*(loc1.x-loc2.x)+(loc1.y-loc2.y)*(loc1.y-loc2.y));
    }

    /**
     * 计算笔的宽度
     */
    var maxLineWidth = 5;
    var minLineWidth = 1;
    var maxStrokeV = 10;
    var minStrokeV = 0.1;
    function calcLineWidth(t,s){
        var v = s/t;
        var resultLineWidth;
        if(v<=minStrokeV){
            resultLineWidth = maxLineWidth;
        }else if(v>=maxStrokeV){
            resultLineWidth = minLineWidth;
        }else{
            //使用差值的方式
            resultLineWidth = maxLineWidth-(v-minStrokeV)/(maxStrokeV-minStrokeV)*(maxLineWidth-minLineWidth);
        }
        if(lastLineWidth == -1){
            return resultLineWidth;
        }
        return lastLineWidth*(2/3)+resultLineWidth*(1/3);
    }
    /*键盘ctrl按下清除画布*/
//清除按钮操作
    /*$("#clear_btn").click(function(e){
            context.clearRect(0,0,canvasWidth,canvasHeight);
            //重新绘制米字格
            drawGrid();
        });*/
    $(document).keydown(function (event) {

        if(event.keyCode === 17)
        {
            context.clearRect(0,0,canvasWidth,canvasHeight);
        }
    });

}
