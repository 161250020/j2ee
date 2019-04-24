<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>支付结果</title>

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
<body style="background-color: rgba(204,204,204,0.17)">

<%
	//获取订单支付结果
	String order_pay_result=(String)session.getAttribute("order_pay_result");
	String order_pay_result_info="";
	if(order_pay_result.equals("0")){
		order_pay_result_info="支付超时！";
	}
	else if(order_pay_result.equals("1")){
		order_pay_result_info="余额不足！";
	}
	else{
		order_pay_result_info="支付成功！";
	}
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
    <div  style="height:130px; width: 80%;border: 1px solid green;">
        <br>
        <br>
        <!--支付结果-->
        <div align="center" style="width: 40%">
            <b>支付结果：<%=order_pay_result_info %></b>
        </div>
    </div>

</div>

</body>
</html>