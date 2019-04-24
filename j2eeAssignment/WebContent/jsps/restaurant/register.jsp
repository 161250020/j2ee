<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
//获得新注册的餐厅的7位编码
String chars7=(String)session.getAttribute("new_7chars");
//获得注册的错误原因
String register_err_info=(String)session.getAttribute("register_err_info");
%>

<div style="border: 1px solid green; width: 84%; height: 100px; margin: 0 auto; text-align: center; line-height: 100px">
    <b>注册</b>
</div>

<hr>

<form method="get" action="/j2eeAssignment/rest/register">
<div style="width: 50%; margin: 0 auto;">
    <div style="height: 660px">
        <!--分配一个ID，隐藏在cookie当中即可，之后存入数据库-->

        <div class="one">编码（用于登录，有7位）：</div>
        <div class="two"><%=chars7 %></div>

        <div class="one">密码：</div>
        <div class="two"><input id="new_password" type="password" name="new_pass"></div>

        <div class="one">重新输入密码：</div>
        <div class="two"><input id="new_re_password" type="password" name="re_new_pass"></div>

        <div class="one">餐厅名称：</div>
        <div class="two"><input type="text" name="new_name"></div>

        <div class="one">餐厅类型：</div>
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

        <div class="one">餐厅具体地址描述：</div>
        <div class="two"><input type="text" name="new_add"></div>
        <div class="one">餐厅地址---市：</div>
        <div class="two"><input type="text" name="new_city"></div>
        <div class="one">餐厅地址---区县：</div>
        <div class="two"><input type="text" name="new_district"></div>
        <div class="one">餐厅地址---镇/街道：</div>
        <div class="two"><input type="text" name="new_town"></div>
        <div class="one">餐厅地址---路：</div>
        <div class="two"><input type="text" name="new_street"></div>

        <div class="one">网银ID：</div>
        <div class="two"><input type="text" name="new_bank_id"></div>

        <div class="one">网银密码：</div>
        <div class="two"><input id="new_bank_pass" type="password" name="new_bank_pass"></div>

        <div class="one">邮箱：</div>
        <div class="two"><input type="email" name="new_email"></div>

        <div class="one"></div>
        <div class="two" align="center"><button onclick="encrypt()" type="submit" style="height: 45px;line-height: 18px;background-color: rgba(0,0,0,0.18);color: black">发送邮箱验证码</button></div>
    </div>

    <!--此处显示注册错误的原因-->
    <div style="height: 30px;background-color: rgba(255,165,0,0.53)"><%=register_err_info %></div>

</div>
</form>
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