<%@ page import="j2ee.model.*" %>
<%@ page import="j2ee.factory.ServiceFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    
	<meta http-equiv="refresh" content="121; url=http://localhost:8080/j2eeAssignment/jsps/member/order_meal/pay_result.jsp">
    <title>支付订单</title>

    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.bootcss.com/foundation/5.5.3/css/foundation.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/foundation/5.5.3/js/foundation.min.js"></script>
    <script src="https://cdn.bootcss.com/foundation/5.5.3/js/vendor/modernizr.js"></script>

    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css">

    <style rel="stylesheet">
        .bt{
            background-color: lightgreen;
            height: 50px; width: 24.5%;
            border: 0px;
            font-size: 15px;
            border-radius: 5px;
        }

        .bt_rest_type{
            width: 60%;
            height: 40px;
            border: 0px; border-radius: 5px;
            background-color: rgba(0, 0, 255, 0.47);
            color: white;
        }
        /*图片和简介的div的属性*/

        .show {
            border-radius: 5px;
            width: auto;
            height: auto;
            margin: 10% 10%;
            text-align: center;
        }

        div.img {
            margin: 5px;
            border: 1px solid #ccc;
            float: left;
            width: 200px;
        }

        div.img:hover {
            border: 1px solid #777;
        }

        div.img img {
            width: 100%;
            height: 160px;
        }

        div.desc {
            padding: 15px;
            text-align: left;
            font-size: 16px;
            font-family: 楷体;
            height: 80px;
        }
    </style>
</head>
<body style="background-color: rgba(204,204,204,0.17)" onLoad="window.setTimeout('getSecs()',1)">

<%
    //账户的信息
    Member_info m= (Member_info) session.getAttribute("user_info");

	//系统判断的送餐时间
	int min_mins=(int)session.getAttribute("min_mins");
	
	//订单的基本信息
	Member_order_info moi=(Member_order_info)session.getAttribute("order_info");
	
	//初始化订单支付的结果为：0---支付超时，若点击了支付，则跳到计算的页面，然后修改支付结果
	session.setAttribute("order_pay_result", "0");
%>

<!--待开发-->
<div style="height: 100px;background-color: darkcyan">
    <br>
    <div align="center"><b style="font-size: 25px;color: white">支付订单</b></div>
</div>

<!--选择按钮-->
<div align="center">
    <form action="/j2eeAssignment/to4functions" method="get">
        <button class="bt" type="submit" name="func" value="order_meal">点餐</button>
        <button class="bt" type="submit" name="func" value="orders">订单</button>
        <button class="bt" type="submit" name="func" value="sum_info">查看统计信息</button>
        <button class="bt" type="submit" name="func" value="personal_info">个人信息</button>
    </form>
</div>

<!--商家信息-->
<div align="center">
<form action="/j2eeAssignment/order_meal/pay_result" method="get">
    <div  style="height:400px; width: 80%;border: 1px solid green;">
        <br>
        <br>
        <!--支付账户-->
        <div align="center" style="width: 40%">
            <b>支付的账户：<%=m.getBank_id() %></b>
        </div>

        <br>
        <br>
        <!--剩余时间-->
        <div align="center" style="width: 40%">
            <b>支付剩余时间（共120s）：</b>
            <b id="rest_time"></b>
        </div>
        <div align="center" style="width: 50%">
            <b>送餐时间（须为整数，若低于系统判断的时间，无法保证是否送到！）：
            	<input name="get_time" placeholder="系统判定至少：<%=min_mins %>分钟..."/>
            </b>
        </div>

        <hr>
        <button type="submit">确认付款</button>
    </div>
</form>
</div>

<script language="JavaScript">
//获取当前时间
startday=new Date();
clockStart=startday.getTime();
function initStopwatch(){
	var myTime=new Date();
	var timeNow=myTime.getTime();
	var timeDiff=timeNow-clockStart;//获取间隔时间
	this.diffSecs=timeDiff/1000; //时间以毫秒为单位
	return(this.diffSecs);
}
function getSecs(){
	var mySecs=initStopwatch();
	var mySecs1=""+mySecs;
	var time = parseInt(mySecs1);
	time=120-time;
	
	var t = time+"秒";
    document.getElementById("rest_time").innerHTML=t;
	window.setTimeout('getSecs()',1000);
}
</script>

</body>
</html>