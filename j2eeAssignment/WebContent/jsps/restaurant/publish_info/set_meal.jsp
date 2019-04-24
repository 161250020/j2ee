<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>发布信息---套餐</title>

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
<!--待开发-->
<div style="height: 100px;background-color: darkcyan">
    <br>
    <div align="center"><b style="font-size: 25px;color: white">发布套餐商品信息</b></div>
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
	<form action="/j2eeAssignment/restaurant/to3functions4" method="get">
    <div style="height:800px; width: 20%;border: 1px solid green;float:left;box-sizing: border-box">
        <b>商品分类：</b>
        <hr>
        <div align="center">
            <button class="bt_rest_type" type="submit" name="func" value="single_produce">单品</button>
            <br>
            <br>
            <button class="bt_rest_type" type="submit" name="func" value="set_meal">套餐</button>
            <br>
            <br>
            <button class="bt_rest_type" type="submit" name="func" value="preferencial">优惠</button>
            <br>
            <br>
        </div>
    </div>
    </form>

    <!--商家-->
    <form action="/j2eeAssignment/restaurant/new_set_meal" method="get">
    <div style="min-height:800px; width: 80%;border: 1px solid green;float:left;box-sizing: border-box">
        <div class="one">
            <b>套餐商品名称：</b>
        </div>
        <div class="two">
            <input style="width: 50%" name="new_name">
        </div>

        <div class="one">
            <b>套餐商品价格：</b>
        </div>
        <div class="two">
            <input style="width: 50%" name="new_price">
        </div>

        <div class="one">
            <b>套餐商品数量：</b>
        </div>
        <div class="two">
            <input style="width: 50%" name="new_num">
        </div>

        <div class="one">
            <b>套餐商品发布开始时间：</b>
        </div>
        <div class="two">
            <input type="date" style="width: 90%" name="new_start_time"/>
        </div>

        <div class="one">
            <b>套餐商品发布结束时间：</b>
        </div>
        <div class="two">
            <input type="date" style="width: 90%" name="new_end_time"/>
        </div>
        <b>PS：发布后，经理会审批这些数据，审批通过后会自动显示在商品列表当中！</b>
        <br>
        
		<hr>

        <button type="submit">确认发布</button>
    </div>
    </form>
</div>

</body>
</html>