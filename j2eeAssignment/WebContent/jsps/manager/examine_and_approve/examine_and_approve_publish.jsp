<%@ page import="j2ee.model.Restaurant_publish_application_info" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>审批---发布</title>

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
        .one, .two{
            width: 50%;
            height: 45px;
            float: left;
            box-sizing: border-box;
        }
        .bt{
            background-color: lightgreen;
            height: 50px; width: 33%;
            border: 0px;
            font-size: 15px;
            border-radius: 5px;
        }

        .bt_rest_type{
            width: 90%;
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
<body>

<%
    Restaurant_publish_application_info r= (Restaurant_publish_application_info) session.getAttribute("publish_info");
    //获得新商品的类型
    String type="";
    if(r.getCom_type()==1){
        type="单品";
    }
    else if(r.getCom_type()==2){
        type="套餐";
    }
    else{
        type="优惠";
    }
    //提示：审批的时候，会提示时间是否正确：1：不低于当前时间段；2：时间的先后不能超过；另外：优惠的优惠前不低于优惠后的价格；
    String tip="";
    if(r.getStart_date().getTime()>=r.getEnd_date().getTime()){
        tip=tip+"发布时间段不对，开始时间迟于结束时间；";
    }
    long time=new java.util.Date().getTime();
    if(time>=r.getEnd_date().getTime()){
        tip=tip+"商品发布的结束时间迟于当前时间，发布无效；";
    }
    if(r.getCom_type()==3){
        if(r.getRaw_price()<=r.getPrice()){
            tip=tip+"优惠前金额低于优惠后金额，优惠无效；";
        }
    }
%>

<!--待开发-->
<form action="/j2eeAssignment/manager/logout" method="get">
<div style="height: 100px;background-color: darkcyan">
    <br>
    <div align="center"><b style="font-size: 25px;color: white">审批商家商品添加的申请</b></div>
    <div align="right"><input type="submit" style="border: 0px" value="退出登录"></div>
</div>
</form>

<!--选择按钮-->
<form action="/j2eeAssignment/manager/to3functions" method="get">
    <div align="center">
        <button class="bt" type="submit" name="func" value="sum">查看统计信息</button>
        <button class="bt" type="submit" name="func" value="settle">结算</button>
        <button class="bt" type="submit" name="func" value="exam">审批</button>
    </div>
</form>

<!--商家信息-->
<div style="width: 30%; margin: 0 auto;">
    <b>添加新商品：</b><br>
    <b>餐厅ID（7位ID）：<%=r.getRestaurant_id()%></b><br>
    <b>新商品的类型：<%=type%></b><br>
    <b>新商品名称：<%=r.getName()%></b><br>
    <%
        if(r.getCom_type()==3){
            //若为优惠的商品，显示一下原件
            out.println("<b>新商品原价格："+r.getRaw_price()+"</b><br>");
        }
    %>
    <b>新商品价格：<%=r.getPrice()%></b><br>
    <b>新商品数量：<%=r.getNum()%></b><br>
    <b>新商品发布的开始时间：<%=r.getStart_date().toString()%></b><br>
    <b>新商品发布的结束时间：<%=r.getEnd_date().toString()%></b><br>

    <div style="background-color: orange;height: 30px"><b>审批提示：<%=tip%></b></div>

<form action="/j2eeAssignment/manager/publish_result" method="get">
    <div align="center">
        <br>
        <button class="button success" type="submit" value="pass" name="func">审批通过</button>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button class="button success" type="submit" value="no_pass" name="func">审批不通过</button>
    </div>
</form>
</div>

</body>
</html>
