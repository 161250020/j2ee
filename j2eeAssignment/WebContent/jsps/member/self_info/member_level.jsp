<%@ page import="j2ee.model.Member_info" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>会员等级</title>

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
<body>

<%
	//获得需要显示的内容
	//会员等级
	int member_level=(int)session.getAttribute("member_level");
	//会员等级的描述
	String member_level_comment=(String)session.getAttribute("member_level_comment");
	if(member_level==0){
		/*
		0级会员的时候，不会有优惠，所以没有存在数据库当中，
		如果存在数据库当中，在系统自动发送当天优惠的时候，就会发送0元的优惠给用户，
		这样不太好（如果会员当天升级，就无法领取对应的当天优惠了），这样对前面的订单支付的地方的代码要求比较高，
		所以我干脆在数据库中不存储0级的信息，在这里显示一下，虽然这样的设计不太好，但是勉强可以使用；
		*/
		member_level_comment="无优惠";
	}
%>

<!--待开发-->
<div style="height: 100px;background-color: darkcyan">
    <br>
    <div align="center"><b style="font-size: 25px;color: white">会员等级</b></div>
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
<div>
    <!--商家分类-->
    <div style="height:800px; width: 20%;border: 1px solid green;float:left;box-sizing: border-box">
        <b>商品分类：</b>
        <hr>
        <form action="/j2eeAssignment/self_info/to5functions" method="get">
        <div align="center">
            <button class="bt_rest_type" type="submit" name="func" value="basic_info">基本信息</button>
            <br>
            <br>
            <button class="bt_rest_type" type="submit" name="func" value="delivery_add">送货地址</button>
            <br>
            <br>
            <button class="bt_rest_type" type="submit" name="func" value="change_pass">修改密码</button>
            <br>
            <br>
            <button class="bt_rest_type" type="submit" name="func" value="member_level">会员级别</button>
            <br>
            <br>
            <button class="bt_rest_type" type="submit" name="func" value="basic_func">基础功能</button>
            <br>
            <br>
        </div>
        </form>
    </div>

    <!--商家-->
    <div style="height:800px; width: 80%;border: 1px solid green;float:left;box-sizing: border-box">
        <div style="width: 80%;border: 1px solid green;height: 50%;">
            <b>会员等级：<%=member_level %></b><br><br>
            <b>会员等级优惠说明：<%=member_level_comment %></b><br><br>
        </div>
    </div>
</div>

</body>
</html>