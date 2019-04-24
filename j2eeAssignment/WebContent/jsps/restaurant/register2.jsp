<%@ page import="j2ee.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>餐厅注册</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.staticfile.org/foundation/5.5.3/css/foundation.min.css">
    <link rel="stylesheet" href="http://static.runoob.com/assets/foundation-icons/foundation-icons.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/foundation/5.5.3/js/foundation.min.js"></script>
    <script src="https://cdn.staticfile.org/foundation/5.5.3/js/vendor/modernizr.js"></script>

    <style rel="stylesheet">
        .one, .two{
            width: 50%;
            height: 45px;
            float: left;
            box-sizing: border-box;
        }
    </style>
</head>
<body>

<%
//获得注册的餐厅的基本信息
Restaurant_info r=(Restaurant_info)session.getAttribute("register_new_rest");
%>

<div style="border: 1px solid green; width: 84%; height: 100px; margin: 0 auto; text-align: center; line-height: 100px">
    <b>注册</b>
</div>

<hr>

<form method="get" action="/j2eeAssignment/rest/register2">
<div style="width: 50%; margin: 0 auto;">
    <div style="height: 500px">
        <!--分配一个ID，隐藏在cookie当中即可，之后存入数据库-->

        <div class="one">编码（用于登录，有7位）：</div>
        <div class="two"><%=r.getLogin_id() %></div>

        <div class="one">餐厅名称：</div>
        <div class="two"><%=r.getName() %></div>

        <div class="one">餐厅类型ID：</div>
        <div class="two"><%=r.getType_id() %></div>

        <div class="one">餐厅具体地址描述：</div>
        <div class="two"><%=r.getAddress() %></div>
        <div class="one">餐厅地址---市：</div>
        <div class="two"><%=r.getCity() %></div>
        <div class="one">餐厅地址---区县：</div>
        <div class="two"><%=r.getDistrict() %></div>
        <div class="one">餐厅地址---镇/街道：</div>
        <div class="two"><%=r.getTown() %></div>
        <div class="one">餐厅地址---路：</div>
        <div class="two"><%=r.getStreet() %></div>

        <div class="one">网银ID：</div>
        <div class="two"><%=r.getBank_id() %></div>

        <div class="one">邮箱：</div>
        <div class="two"><%=r.getMail() %></div>

        <div class="one"><input type="text" placeholder="邮箱验证码..." style="width: 90%" name="in_identifycode"></div>
        <div class="two" align="center"></div>
    </div>

    <!--此处显示注册错误的原因-->
    <div style="height: 30px;background-color: rgba(255,165,0,0.53)"></div>

    <div align="center">
        <br>
        <button class="button success" type="submit">确认注册</button>
    </div>
</div>
</form>
</body>
</html>