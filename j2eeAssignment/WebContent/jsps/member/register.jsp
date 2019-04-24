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
    String register_err_info= (String) session.getAttribute("register_err_info");
%>

<div style="border: 1px solid green; width: 84%; height: 100px; margin: 0 auto; text-align: center; line-height: 100px">
    <b>注册</b>
</div>

<hr>

<div style="width: 50%; margin: 0 auto;">
    <div style="height: 440px">
        <form method="get" action="/j2eeAssignment/register_mailOrNot">

            <div class="one">用户名：</div>
            <div class="two"><input type="text" name="new_username" placeholder="长度大于6......"></div>

            <div class="one">密码：</div>
            <div class="two"><input id="new_password" type="password" name="new_password" placeholder="长度大于6......"></div>

            <div class="one">重新输入密码：</div>
            <div class="two"><input id="new_re_password" type="password" name="new_re_password" placeholder="长度大于6......"></div>

            <div class="one">姓名：</div>
            <div class="two"><input type="text" name="new_name"></div>

            <div class="one">电话：</div>
            <div class="two"><input type="text" name="new_phone"></div>

            <div class="one">网银ID：</div>
            <div class="two"><input type="text" name="new_bank_id"></div>

            <div class="one">网银密码：</div>
            <div class="two"><input id="new_bank_pass" type="password" name="new_bank_pass"></div>

            <div class="one">邮箱：</div>
            <div class="two"><input type="email" name="new_mail"></div>

            <div class="one"></div>
            <div class="two" align="center"><button onclick="encrypt()" type="submit" style="height: 45px;line-height: 18px;background-color: rgba(0,0,0,0.18);color: black">发送邮箱验证码</button></div>
        </form>
    </div>

    <!--此处显示注册错误的原因-->
    <div style="height: 30px;background-color: rgba(255,165,0,0.53)"><%=register_err_info%></div>
    
</div>
<script>
	//按过发送验证码的按钮之后，为两次输入的密码，以及网银的密码加密
	function encrypt(){
		var new_password=document.getElementById("new_password").value;
		var new_re_password=document.getElementById("new_re_password").value;
		var new_bank_pass=document.getElementById("new_bank_pass").value;
		var value1="",value2="",value3="";//三个密码转化过后的数据
		for(var i=0;i<new_password.length;i++){
			value1+=new_password.charCodeAt(i);
		}
		for(var i=0;i<new_re_password.length;i++){
			value2+=new_re_password.charCodeAt(i);
		}
		for(var i=0;i<new_bank_pass.length;i++){
			value3+=new_bank_pass.charCodeAt(i);
		}
		document.getElementById("new_password").value=value1;
		document.getElementById("new_re_password").value=value2;
		document.getElementById("new_bank_pass").value=value3;
	}
</script>
</body>
</html>