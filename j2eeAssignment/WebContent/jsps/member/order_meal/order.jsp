<%@ page import="j2ee.model.Delivery_address_info" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="j2ee.model.Restaurant_singleproduct_info" %>
<%@ page import="j2ee.model.Restaurant_setmeal_info" %>
<%@ page import="j2ee.model.Restaurant_preferencial_info" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>生成的订单</title>

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
<body style="background-color: rgba(204,204,204,0.17)">

<%

    //获得所有的地址
    ArrayList<Delivery_address_info> member_addresses= (ArrayList<Delivery_address_info>) session.getAttribute("member_addresses");
    //获得所有商品信息
    ArrayList<Restaurant_singleproduct_info> restaurant_singleproduct_infos=(ArrayList<Restaurant_singleproduct_info>)session.getAttribute("choose_rest_singleproduct");
    ArrayList<Restaurant_setmeal_info> restaurant_setmeal_infos=(ArrayList<Restaurant_setmeal_info>)session.getAttribute("choose_rest_setmeal");
    ArrayList<Restaurant_preferencial_info> restaurant_preferencial_infos=(ArrayList<Restaurant_preferencial_info>)session.getAttribute("choose_rest_preferencial");
    //获得所有商品数量信息（页面上面仅显示数量>0的商品）
    ArrayList<Integer> num1= (ArrayList<Integer>) session.getAttribute("num1");
    ArrayList<Integer> num2= (ArrayList<Integer>) session.getAttribute("num2");
    ArrayList<Integer> num3= (ArrayList<Integer>) session.getAttribute("num3");
    //获得可以优惠的金额
    double ps_money= (double) session.getAttribute("ps_money");
    //计算总价（总商品价格-优惠金额，需要>=0）
    double sum=0;
    for(int i=0;i<num1.size();i++){
        if(num1.get(i)>0){
            sum=sum+num1.get(i)*restaurant_singleproduct_infos.get(i).getCom_price();
        }
    }
    for(int i=0;i<num2.size();i++){
        if(num2.get(i)>0){
            sum=sum+num2.get(i)*restaurant_setmeal_infos.get(i).getSetmeal_price();
        }
    }
    for(int i=0;i<num3.size();i++){
        if(num3.get(i)>0){
            sum=sum+num3.get(i)*restaurant_preferencial_infos.get(i).getNow_price();
        }
    }
    sum=sum-ps_money;
    if(sum<0){
        sum=0;
    }
    session.setAttribute("order_sum_money", sum);

    //获得系统判定的送餐时间
    double min_mins=0;
    
%>

<!--待开发-->
<div style="height: 100px;background-color: darkcyan">
    <br>
    <div align="center"><b style="font-size: 25px;color: white">订单</b></div>
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
<div align="center">
<form method="get" action="/j2eeAssignment/order_meal/order">
    <div  style="height:800px; width: 80%;border: 1px solid green;">
        <!--选择地址-->
        <div align="center" style="width: 40%">
            <b>选择地址：</b>
            <select name="select_address">
            	<%
                    for(int i=0;i<member_addresses.size();i++){
                        out.println("<option value =\""+member_addresses.get(i).getId()+"\">"+member_addresses.get(i).getAddress()+"</option>");
                    }
                %>
            </select>
        </div>

        <table class="table" id="project">
            <th>商品名</th><th>购买数量</th>
            <%
                for(int i=0;i<num1.size();i++){
                    if(num1.get(i)>0){
                        out.println("<tr>\n" +
                                "                <td>"+restaurant_singleproduct_infos.get(i).getCommodity_name()+"</td><td>"+num1.get(i)+"</td>\n" +
                                "            </tr>");
                    }
                }
                for(int i=0;i<num2.size();i++){
                    if(num2.get(i)>0){
                        out.println("<tr>\n" +
                                "                <td>"+restaurant_setmeal_infos.get(i).getSet_meal_name()+"</td><td>"+num2.get(i)+"</td>\n" +
                                "            </tr>");
                    }
                }
                for(int i=0;i<num3.size();i++){
                    if(num3.get(i)>0){
                        out.println("<tr>\n" +
                                "                <td>"+restaurant_preferencial_infos.get(i).getCom_name()+"</td><td>"+num3.get(i)+"</td>\n" +
                                "            </tr>");
                    }
                }
            %>
        </table>

        <br>
        <b>优惠价格：<%=ps_money %></b>
        <br>
        <b>总价：<%=sum %></b>
        <br>

        <hr>
        <button type="submit">去支付</button>
    </div>
</form>
</div>

</body>
</html>