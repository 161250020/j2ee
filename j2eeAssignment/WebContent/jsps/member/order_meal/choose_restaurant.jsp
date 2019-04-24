<%@ page import="j2ee.model.Restaurant_info" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>选择餐厅</title>

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
    </style>
</head>
<body style="background-color: rgba(204,204,204,0.17)">

<%
    //获得所有餐厅的信息
    ArrayList<Restaurant_info> rests_info= (ArrayList<Restaurant_info>) session.getAttribute("all_rest_info");
    int type_id=(int)session.getAttribute("rest_type_id");
    ArrayList<Restaurant_info> rests_display_info=new ArrayList<Restaurant_info>();
    if(type_id==0){//分类显示
    	rests_display_info=rests_info;
    }
    else{
    	for(int i=0;i<rests_info.size();i++){
    		if(rests_info.get(i).getType_id()==type_id){
    			rests_display_info.add(rests_info.get(i));
    		}
    	}
    }
    
%>

<!--待开发-->
<div style="height: 100px;background-color: darkcyan">
    <br>
    <div align="center"><b style="font-size: 25px;color: white">选择餐厅</b></div>
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
    <div style="height:1200px; width: 20%;float:left;box-sizing: border-box;border: 1px solid green">
        <b>商家分类：</b>
        <hr>
        <div align="center">
        <form action="/j2eeAssignment/changeRestTypes" method="get">
                <button class="bt_rest_type" type="submit" name="type_id" value="0">全部商家</button>
                <br>
                <br>
                <button class="bt_rest_type" type="submit" name="type_id" value="1">美食</button>
                <br>
                <br>
                <button class="bt_rest_type" type="submit" name="type_id" value="2">快餐便当</button>
                <br>
                <br>
                <button class="bt_rest_type" type="submit" name="type_id" value="3">特色菜系</button>
                <br>
                <br>
                <button class="bt_rest_type" type="submit" name="type_id" value="4">异国料理</button>
                <br>
                <br>
                <button class="bt_rest_type" type="submit" name="type_id" value="5">小吃夜宵</button>
                <br>
                <br>
                <button class="bt_rest_type" type="submit" name="type_id" value="6">甜点饮品</button>
                <br>
                <br>
                <button class="bt_rest_type" type="submit" name="type_id" value="7">果蔬生鲜</button>
                <br>
                <br>
                <button class="bt_rest_type" type="submit" name="type_id" value="8">商店超市</button>
                <br>
                <br>
                <button class="bt_rest_type" type="submit" name="type_id" value="9">鲜花绿植</button>
                <br>
                <br>
                <button class="bt_rest_type" type="submit" name="type_id" value="10">医药健康</button>
                <br>
                <br>
            </form>
        </div>
    </div>

    <!--商家-->
    <div style="min-height:1200px; width: 80%;border: 1px solid green;float:left;box-sizing: border-box">
    <form action="/j2eeAssignment/order_meal/choose_restaurant" method="get">
        <table class="table" id="project">
            <th>7位ID</th><th>名称</th><th>地址</th><th>注册日期</th>
            <% 
                for(int i=0;i<rests_display_info.size();i++){
                    out.println("<tr>\n" +
                            "                <td><input type=\"submit\" name=\"rest_id\" value=\""+rests_display_info.get(i).getLogin_id()+"\" style=\"border: 0px;width: 100%\"></td><td>"+rests_display_info.get(i).getName()+"</td>" +
                            "<td>"+rests_display_info.get(i).getAddress()+"</td><td>"+rests_display_info.get(i).getDate().toString()+"</td>\n" +
                            "            </tr>");
                }
            %>
        </table>
    </form>
    </div>
</div>

</body>
</html>