<%@ page import="java.util.ArrayList" %>
<%@ page import="j2ee.factory.ServiceFactory" %>
<%@ page import="j2ee.model.Member_info" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>查看统计信息---yummy财务</title>

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
    //获得时间段
    String date1= (String) session.getAttribute("yummy_start_date");
    String date2= (String) session.getAttribute("yummy_end_date");
    //获得需要展示的数据
    ArrayList<Double> nums= (ArrayList<Double>) session.getAttribute("yummy_sum_nums");
   
%>

<!--待开发-->
<form action="/j2eeAssignment/manager/logout" method="get">
<div style="height: 100px;background-color: darkcyan">
    <br>
    <div align="center"><b style="font-size: 25px;color: white">查看统计信息---餐厅</b></div>
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
<div>
    <!--商家分类-->
    <form action="/j2eeAssignment/manager/to3functions1" method="get">
    <div style="height:800px; width: 20%;border: 1px solid green;float:left;box-sizing: border-box">
        <b>商品分类：</b>
        <hr>
        <div align="center">
            <button class="bt_rest_type" type="submit" name="func" value="restaurant">餐厅</button>
            <br>
            <br>
            <button class="bt_rest_type" type="submit" name="func" value="member">会员</button>
            <br>
            <br>
            <button class="bt_rest_type" type="submit" name="func" value="yummy">yummy财务</button>
            <br>
            <br>
        </div>
    </div>
    </form>

    <!--商家-->
    <form action="/j2eeAssignment/manager/calculate_yummy_num" method="get">
    <div style="min-height:800px; width: 80%;border: 1px solid green;float:left;box-sizing: border-box">
        <div class="one">
            <div class="one">
                <b>选择日期：</b>
            </div>
            <div class="two">
                <input type="date" style="width: 90%" value=<%=date1 %> name="start_date"/>
            </div>
        </div>
        <div class="two">
            <div class="one">
                <input type="date" style="width: 90%" value=<%=date2 %> name="end_date"/>
            </div>
            <div class="two">
                <button type="submit">确认</button>
            </div>
        </div>

        <div class="one">
            <b>赚取金额（成功的订单）：</b>
        </div>
        <div class="two">
            <%=nums.get(0) %>
        </div>
        <div class="one">
            <b>发出且已使用的优惠：</b>
        </div>
        <div class="two">
            <%=nums.get(1) %>
        </div>
    </div>
    </form>
</div>

</body>
</html>