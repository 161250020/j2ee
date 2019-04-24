<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>会员登录</title>
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
//获得登录错误的信息
String err_info= (String) session.getAttribute("err_info");

%>

<div style="border: 1px solid green; width: 84%; height: 100px; margin: 0 auto; text-align: center; line-height: 100px">
    <b>登录</b>
</div>

<hr>

<div style="width: 30%; margin: 0 auto;">
    <form method="get" action="/j2eeAssignment/member/login">
        <input type="text" placeholder="邮箱/用户名" name="in_username">
        <input type="password" placeholder="密码" name="in_pass" id="in_pass">

        <div style="background-color: orange;width: 100%;height: 25px"><%=err_info %></div>
        
        <div class="one"></div>
        <div class="two" align="center"><input type="submit" name="function" value="注册" style="border: 0px"></div>
        
        <div align="center">
            <button onclick="encrypt()" type="submit" name="function" value="登录" class="button success" style="margin: 0 auto">登录</button>
        </div>
    </form>
</div>
<script>
	function encrypt(){
		var value=document.getElementById("in_pass").value;
	    var encryption="";
		for(var i=0;i<value.length;i++){
			encryption+=value.charCodeAt(i);
		}
		document.getElementById("in_pass").value=encryption;
	}
</script>
</body>
</html>