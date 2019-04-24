<%@ page import="java.util.ArrayList" %>
<%@ page import="j2ee.model.Member_order_info" %>
<%@ page import="j2ee.factory.ServiceFactory" %>
<%@ page import="j2ee.model.Restaurant_info" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单列表</title>

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
    //获得餐厅的基本信息
    Restaurant_info r= (Restaurant_info) session.getAttribute("rest_info");

	//修改数据库中的订单，自动确认订单送达
	ServiceFactory.getMember_order_infoManageService().ensure_order_arrived_os();
	
    //获得该餐厅所有订单的基本信息
    ArrayList<Member_order_info> orders= (ArrayList<Member_order_info>) ServiceFactory.getMember_order_infoManageService().getAllOrdersByRestId(r.getLogin_id());

%>

<!--待开发-->
<div style="height: 100px;background-color: darkcyan">
    <br>
    <div align="center"><b style="font-size: 25px;color: white">订单列表</b></div>
</div>

<!--选择按钮-->
<form action="/j2eeAssignment/restaurant/to4functions" method="get">
    <div align="center">
        <button class="bt" type="submit" name="func" value="orders">订单</button>
        <button class="bt" type="submit" name="func" value="publish_info">发布信息</button>
        <button class="bt" type="submit" name="func" value="look_over_sum_info">查看统计信息</button>
        <button class="bt" type="submit" name="func" value="personal_info">基本信息</button>
    </div>
</form>

<!--商家信息-->
<div>
<form action="/j2eeAssignment/restaurant/modify_order_state" method="get">
    <table class="table" id="project">
        <th>订单号</th><th>下单时间</th><th>支付时间</th>
        <th>会员ID</th><th>总价</th><th>送餐地址</th><th>优惠金额</th>
        <th>订单状态/结果</th>
        <%
            for(int i=0;i<orders.size();i++){
            	//显示订单基本内容的input的value
                String order_id="id_"+orders.get(i).getId();
                if(orders.get(i).getResult()==2){//成功支付的订单
                	//支付时间
                    String pay_time="";
                    if(orders.get(i).getPay_time()!=null){
                        pay_time=orders.get(i).getPay_time().toString();
                    }
                    //送餐地址
                    String address="";
                    if(orders.get(i).getDelivery_address_id()!=null){
                        address=ServiceFactory.getDelivery_address_infoManageService().getDelivery_address_infoById(orders.get(i).getDelivery_address_id()).getAddress();
                    }
                    //根据订单状态现实不同的信息
                    if(orders.get(i).getState()==0){//显示已生产的按钮
                        String value="已生产_"+orders.get(i).getId();
                        out.println("<tr>\n" +
                                "            <td><input type=\"submit\" name=\"order_id\" value=\""+order_id+"\" style=\"border: 0px;width: 100%\"></td><td>"+orders.get(i).getOrder_time().toString()+"</td>" +
                                "<td>"+pay_time+"</td>\n" +
                                "            <td>"+orders.get(i).getMember_id()+"</td><td>"+orders.get(i).getSum_price()+"</td>" +
                                "<td>"+address+"</td><td>"+orders.get(i).getPs_money_yummy()+"</td>\n" +
                                "            <td><button type=\"submit\" name=\"order_id\" value=\""+value+"\">已生产</button></td>\n" +
                                "        </tr>");
                    }
                    else if(orders.get(i).getState()==1){//显示已配送的按钮
                        String value="已配送_"+orders.get(i).getId();
                        out.println("<tr>\n" +
                                "            <td><input type=\"submit\" name=\"order_id\" value=\""+order_id+"\" style=\"border: 0px;width: 100%\"></td><td>"+orders.get(i).getOrder_time().toString()+"</td>" +
                                "<td>"+pay_time+"</td>\n" +
                                "            <td>"+orders.get(i).getMember_id()+"</td><td>"+orders.get(i).getSum_price()+"</td>" +
                                "<td>"+address+"</td><td>"+orders.get(i).getPs_money_yummy()+"</td>\n" +
                                "            <td><button type=\"submit\" name=\"order_id\" value=\""+value+"\">已配送</button></td>\n" +
                                "        </tr>");
                    }
                    else if(orders.get(i).getState()==2){//显示已配送的提示
                        out.println("<tr>\n" +
                                "            <td><input type=\"submit\" name=\"order_id\" value=\""+order_id+"\" style=\"border: 0px;width: 100%\"></td><td>"+orders.get(i).getOrder_time().toString()+"</td>" +
                                "<td>"+pay_time+"</td>\n" +
                                "            <td>"+orders.get(i).getMember_id()+"</td><td>"+orders.get(i).getSum_price()+"</td>" +
                                "<td>"+address+"</td><td>"+orders.get(i).getPs_money_yummy()+"</td>\n" +
                                "            <td>已配送</td>\n" +
                                "        </tr>");
                    }
                    else{//显示已送达的提示
                        out.println("<tr>\n" +
                                "            <td><input type=\"submit\" name=\"order_id\" value=\""+order_id+"\" style=\"border: 0px;width: 100%\"></td><td>"+orders.get(i).getOrder_time().toString()+"</td>" +
                                "<td>"+pay_time+"</td>\n" +
                                "            <td>"+orders.get(i).getMember_id()+"</td><td>"+orders.get(i).getSum_price()+"</td>" +
                                "<td>"+address+"</td><td>"+orders.get(i).getPs_money_yummy()+"</td>\n" +
                                "            <td>已送达</td>\n" +
                                "        </tr>");
                    }
                }
                else if(orders.get(i).getResult()==0){//退订的订单
                	//支付时间
                    String pay_time="";
                    if(orders.get(i).getPay_time()!=null){
                        pay_time=orders.get(i).getPay_time().toString();
                    }
                    //送餐地址
                    String address="";
                    if(orders.get(i).getDelivery_address_id()!=null){
                        address=ServiceFactory.getDelivery_address_infoManageService().getDelivery_address_infoById(orders.get(i).getDelivery_address_id()).getAddress();
                    }
                    out.println("<tr>\n" +
                            "            <td><input type=\"submit\" name=\"order_id\" value=\""+order_id+"\" style=\"border: 0px;width: 100%\"></td><td>"+orders.get(i).getOrder_time().toString()+"</td>" +
                            "<td>"+pay_time+"</td>\n" +
                            "            <td>"+orders.get(i).getMember_id()+"</td><td>"+orders.get(i).getSum_price()+"</td>" +
                            "<td>"+address+"</td><td>"+orders.get(i).getPs_money_yummy()+"</td>\n" +
                            "            <td>已退订</td>\n" +
                            "        </tr>");
                }
                else{
                	//其余result的订单为未支付的订单，则不显示
                }

            }
        %>

    </table>
</form>
</div>

</body>
</html>