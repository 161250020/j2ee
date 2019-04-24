<%@ page import="j2ee.model.Restaurant_info" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改餐厅基本信息</title>

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
<body>

<%
    //获得餐厅的基本信息
    Restaurant_info r= (Restaurant_info) session.getAttribute("rest_info");
%>

<!--待开发-->
<div style="height: 100px;background-color: darkcyan">
    <br>
    <div align="center"><b style="font-size: 25px;color: white">修改餐厅基本信息</b></div>
</div>

<!--选择按钮-->
<form action="/j2eeAssignment/restaurant/to4functions" method="get">
    <div align="center">
        <button class="bt" type="submit" name="func" value="orders">订单</button>
        <button class="bt" type="submit" name="func" value="publish_info">发布信息</button>
        <button class="bt" type="submit" name="func" value="look_over_sum_info">查看统计信息</button>
        <button class="bt" type="submit" name="func" value="personal_info">基本信息</button>
    </div>
</form>

<!--商家信息-->
<div>
    <!--商家分类-->
    <form action="/j2eeAssignment/restaurant/to2functions" method="get">
        <div style="height:800px; width: 20%;border: 1px solid green;float:left;box-sizing: border-box">
            <b>商品分类：</b>
            <hr>
            <div align="center">
                <button class="bt_rest_type" type="submit" name="func" value="personal_info">基本信息</button>
                <br>
                <br>
                <button class="bt_rest_type" type="submit" name="func" value="modify_pass">修改密码</button>
                <br>
                <br>
                <button class="bt_rest_type" type="submit" name="func" value="logout">退出登录</button>
                <br>
                <br>
            </div>
        </div>
    </form>

    <!--商家-->
	<form action="/j2eeAssignment/restaurant/ensure_modify_rest_info" method="get">
    <div style="min-height:800px; width: 80%;border: 1px solid green;float:left;box-sizing: border-box">
        <div class="one">
            <b>编码（7位）：</b>
        </div>
        <div class="two">
            <b><%=r.getLogin_id()%></b>
        </div>

        <div class="one">
            <b>餐厅名称：</b>
        </div>
        <div class="two">
            <input style="width: 50%;" name="new_name">
        </div>

        <div class="one">
            <b>邮箱：</b>
        </div>
        <div class="two">
            <b><%=r.getMail()%></b>
        </div>

        <div class="one">
            <b>注册日期：</b>
        </div>
        <div class="two">
            <b><%=r.getDate().toString()%></b>
        </div>

        <div class="one">
            <b>地址：</b>
        </div>
        <div class="two">
            <input style="width: 50%;" name="new_add">
        </div>
        <div class="one">
            <b>地址---城市：</b>
        </div>
        <div class="two">
            <input style="width: 50%;" name="new_city">
        </div>
        <div class="one">
            <b>地址---区县：</b>
        </div>
        <div class="two">
            <input style="width: 50%;" name="new_district">
        </div>
        <div class="one">
            <b>地址---镇/街道：</b>
        </div>
        <div class="two">
            <input style="width: 50%;" name="new_town">
        </div>
        <div class="one">
            <b>地址---路：</b>
        </div>
        <div class="two">
            <input style="width: 50%;" name="new_street">
        </div>

        <div class="one">
            <b>餐厅类型：</b>
        </div>
        <div class="two">
            <select name="new_type">
                <option value ="1">美食</option>
                <option value ="2">快餐便当</option>
                <option value="3">特色菜系</option>
                <option value="4">异国料理</option>
                <option value="5">小吃夜宵</option>
                <option value="6">甜点饮品</option>
                <option value="7">果蔬生鲜</option>
                <option value="8">商店超市</option>
                <option value="9">鲜花绿植</option>
                <option value="10">医药健康</option>
            </select>
        </div>

        <div class="one">
            <b>网银ID：</b>
        </div>
        <div class="two">
            <b><%=r.getBank_id()%></b>
        </div>

        <hr>
        <b>PS：若输入的内容为空格，则默认为原本的餐厅信息，餐厅的类型一定要选择！</b><br>
        <button type="submit">确认修改</button>
    </div>
</div>

</body>
</html>