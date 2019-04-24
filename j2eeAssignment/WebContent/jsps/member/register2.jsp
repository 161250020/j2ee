<%@ page import="j2ee.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>会员注册</title>
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
//读取新建的用户的信息
Member_info m=(Member_info)session.getAttribute("register_new_member");

%>

<div style="border: 1px solid green; width: 84%; height: 100px; margin: 0 auto; text-align: center; line-height: 100px">
    <b>注册</b>
</div>

<hr>

<div style="width: 50%; margin: 0 auto;">
<form action="/j2eeAssignment/register2_mail_ensure" method="get">
    <div style="height: 300px">
        <!--分配一个ID，隐藏在cookie当中即可，之后存入数据库-->

        <div class="one">用户名：</div>
        <div class="two"><%=m.getUsername() %></div>

        <div class="one">姓名：</div>
        <div class="two"><%=m.getName() %></div>

        <div class="one">电话：</div>
        <div class="two"><%=m.getPhone_number() %></div>

        <div class="one">网银ID：</div>
        <div class="two"><%=m.getBank_id() %></div>

        <div class="one">邮箱：</div>
        <div class="two"><%=m.getMail() %></div>

        <div class="one"><input type="text" placeholder="邮箱验证码..." style="width: 90%" name="in_identify_code"></div>
        <div class="two"></div>
    </div>

    <!--此处显示注册错误的原因-->
    <div style="height: 30px;background-color: rgba(255,165,0,0.53)"></div>

    <div align="center">
        <br>
        <button class="button success" type="submit">确认注册</button>
    </div>
</form>
</div>

</body>
</html>