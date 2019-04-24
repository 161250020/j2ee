<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.staticfile.org/foundation/5.5.3/css/foundation.min.css">
    <link rel="stylesheet" href="http://static.runoob.com/assets/foundation-icons/foundation-icons.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/foundation/5.5.3/js/foundation.min.js"></script>
    <script src="https://cdn.staticfile.org/foundation/5.5.3/js/vendor/modernizr.js"></script>

    <style rel="stylesheet">
        .show-identity{
            width: 33%;
            float: left;
            height: 260px;
            box-sizing: border-box;
            text-align: center;
        }
    </style>
</head>
<body>

<div style="border: 1px solid green; width: 84%; height: 100px; margin: 0 auto; text-align: center; line-height: 100px">
<b>Yummy!</b>
</div>

<hr>

<div style="width: 84%; margin: 0 auto;">
    <h5 style="font-family: 楷体"><b>登录/注册：</b></h5>
</div>

</div>

<div style="width: 84%; margin: 0 auto;">
    <form method="get" action="/j2eeAssignment/index">
        <div class="show-identity">
            <button type="submit" name="submit" value="member" class="button success" style="height: 100%; width: 100%">
                <i class="fi-torsos-female-male" style="font-size: 100px"></i>
                <br>
                会员
            </button>
        </div>

        <div class="show-identity">
            <button type="submit" name="submit" value="restaurant" class="button success" style="height: 100%; width: 100%">
                <i class="fi-torso-business" style="font-size: 100px"></i>
                <br>
                餐厅
            </button>
        </div>

        <div class="show-identity">
            <button type="submit" name="submit" value="manager" class="button success" style="height: 100%; width: 100%">
                <i class="fi-torsos-all" style="font-size: 100px"></i>
                <br>
                经理
            </button>
        </div>
    </form>
</div>

</body>
</html>