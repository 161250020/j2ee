<%@ page import="j2ee.model.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>选择商品</title>

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

Restaurant_info rest_info=(Restaurant_info)session.getAttribute("choose_rest_info");
ArrayList<Restaurant_singleproduct_info> restaurant_singleproduct_infos=(ArrayList<Restaurant_singleproduct_info>)session.getAttribute("choose_rest_singleproduct");
ArrayList<Restaurant_setmeal_info> restaurant_setmeal_infos=(ArrayList<Restaurant_setmeal_info>)session.getAttribute("choose_rest_setmeal");
ArrayList<Restaurant_preferencial_info> restaurant_preferencial_infos=(ArrayList<Restaurant_preferencial_info>)session.getAttribute("choose_rest_preferencial");

String choose_commodity_err_info=(String)session.getAttribute("choose_commodity_err_info");
%>

<!--待开发-->
<div style="height: 100px;background-color: darkcyan">
    <br>
    <div align="center"><b style="font-size: 25px;color: white">商家名称</b></div>
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

<form action="/j2eeAssignment/order_meal/choose_commodity" method="get">
<!--商家信息-->
<div>
    <!--商家-->
    <div style="height:800px; width: 80%;border: 1px solid green;float:left;box-sizing: border-box">
        <b>单品：</b>
        <table class="table" id="project">
            <th>商品名</th><th>库存数量</th><th>单价</th><th>购买数量</th>
            <%
            //在input的name后面添上singleproduct，以免单品的商品的ID和套餐、优惠的商品的ID一样，而混淆；其余两个类似
            for(int i=0;i<restaurant_singleproduct_infos.size();i++){
            	out.println("<tr>\n" +
                        "                <td>"+restaurant_singleproduct_infos.get(i).getCommodity_name()+"</td><td>"+restaurant_singleproduct_infos.get(i).getCom_num()+"</td><td>"+restaurant_singleproduct_infos.get(i).getCom_price()+"</td><td><input style=\"width: 50px;height: 30px\"  name=\""+restaurant_singleproduct_infos.get(i).getId()+"singleproduct"+"\"></td>\n" +
                        "            </tr>");
            }
            %>
        </table>
        <b>套餐：</b>
        <table class="table">
            <th>商品名</th><th>库存数量</th><th>单价</th><th>购买数量</th>
            <%
            for(int i=0;i<restaurant_setmeal_infos.size();i++){
            	out.println("<tr>\n" +
                        "                <td>"+restaurant_setmeal_infos.get(i).getSet_meal_name()+"</td><td>"+restaurant_setmeal_infos.get(i).getSetmeal_num()+"</td><td>"+restaurant_setmeal_infos.get(i).getSetmeal_price()+"</td><td><input style=\"width: 50px;height: 30px\"  name=\""+restaurant_setmeal_infos.get(i).getId()+"setmeal"+"\"></td>\n" +
                        "            </tr>");
            }
            %>
        </table>
        <b>优惠：</b>
        <table class="table">
            <th>商品名</th><th>库存数量</th><th>原价</th><th>现价</th><th>购买数量</th>
            <%
            for(int i=0;i<restaurant_preferencial_infos.size();i++){
            	out.println("<tr>\n" +
                        "                <td>"+restaurant_preferencial_infos.get(i).getCom_name()+"</td><td>"+restaurant_preferencial_infos.get(i).getNum()+"</td><td>"+restaurant_preferencial_infos.get(i).getRaw_price()+"</td><td>"+restaurant_preferencial_infos.get(i).getNow_price()+"</td><td><input style=\"width: 50px;height: 30px\" name=\""+restaurant_preferencial_infos.get(i).getId()+"preferencial"+"\"></td>\n" +
                        "            </tr>");
            }
            %>
        </table>
    </div>
</div>

<!--总价计算-->
<div align="right">
    <button type="submit">去结算</button>
</div>

<div>
<b><%=choose_commodity_err_info %></b>
</div>

</form>

</body>
</html>
