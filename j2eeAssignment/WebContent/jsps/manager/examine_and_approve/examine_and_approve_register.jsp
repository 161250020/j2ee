<%@ page import="j2ee.model.Restaurant_register_application_info" %>
<%@ page import="j2ee.model.Restaurant_types" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="j2ee.factory.ServiceFactory" %>
<%@ page import="j2ee.model.Restaurant_info" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>审批---注册</title>

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
    //获得注册的餐厅的信息
    Restaurant_register_application_info r= (Restaurant_register_application_info) session.getAttribute("register_info");
    //获得餐厅类型
    String types= ServiceFactory.getRestaurant_typesManageService().getTypeNameById(r.getType_id());
    //检测结果
    String result="检测结果：";
    ArrayList<Restaurant_info> rests= (ArrayList<Restaurant_info>) ServiceFactory.getRestaurant_infoManageService().getAllRestsInfo();
    //检测数据库中的7位编码是否重复
    //检测数据库中的email是否重复
    for(int i=0;i<rests.size();i++){
        if(rests.get(i).getMail().equals(r.getMail())){
            result=result+"该邮箱已注册；";
        }
        if(rests.get(i).getLogin_id().equals(r.getLogin_id())){
            result=result+"该7位登录号已注册；";
        }
    }
%>

<!--待开发-->
<form action="/j2eeAssignment/manager/logout" method="get">
<div style="height: 100px;background-color: darkcyan">
    <br>
    <div align="center"><b style="font-size: 25px;color: white">审批商家注册的申请</b></div>
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
<div style="width: 50%; margin: 0 auto;">
    <div style="height: 450px">
        <!--分配一个ID，隐藏在cookie当中即可，之后存入数据库-->

        <div class="one">编码（用于登录，有7位）：</div>
        <div class="two"><%=r.getLogin_id()%></div>

        <div class="one">餐厅名称：</div>
        <div class="two"><%=r.getName()%></div>

        <div class="one">餐厅类型：</div>
        <div class="two"><%=types%></div>

        <div class="one">餐厅具体地址描述：</div>
        <div class="two"><%=r.getAddress()%></div>
        <div class="one">餐厅地址---市：</div>
        <div class="two"><%=r.getCity()%></div>
        <div class="one">餐厅地址---区县：</div>
        <div class="two"><%=r.getDistrict()%></div>
        <div class="one">餐厅地址---镇/街道：</div>
        <div class="two"><%=r.getTown()%></div>
        <div class="one">餐厅地址---路：</div>
        <div class="two"><%=r.getStreet()%></div>

        <div class="one">网银ID：</div>
        <div class="two"><%=r.getBank_id()%></div>

        <div class="one">邮箱：</div>
        <div class="two"><%=r.getMail()%></div>

    </div>

    <div style="background-color: orange;height: 30px"><b><%=result%></b></div>
<form action="/j2eeAssignment/manager/register_result" method="get">
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