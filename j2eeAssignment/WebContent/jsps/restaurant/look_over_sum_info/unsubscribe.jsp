<%@ page import="j2ee.model.Member_order_info" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="j2ee.model.Delivery_address_info" %>
<%@ page import="j2ee.factory.ServiceFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>查看统计信息---退订</title>

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
    //获得订单的信息
    ArrayList<Member_order_info> orders= (ArrayList<Member_order_info>) session.getAttribute("unsubscribe_rest_orders_list");
    String start_time= (String) session.getAttribute("unsubscribe_rest_start_time");
    String end_time= (String) session.getAttribute("unsubscribe_rest_end_time");
    double start_mon= (double) session.getAttribute("unsubscribe_rest_start_mon");
    double end_mon= (double) session.getAttribute("unsubscribe_rest_end_mon");

%>

<!--待开发-->
<div style="height: 100px;background-color: darkcyan">
    <br>
    <div align="center"><b style="font-size: 25px;color: white">查看退订统计信息</b></div>
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
    <!--商家分类-->
    <form action="/j2eeAssignment/restaurant/to3functions3" method="get">
        <div style="height:800px; width: 20%;border: 1px solid green;float:left;box-sizing: border-box">
            <b>商品分类：</b>
            <hr>
            <div align="center">
                <button class="bt_rest_type" type="submit" name="func" value="order">点餐</button>
                <br>
                <br>
                <button class="bt_rest_type" type="submit" name="func" value="unsubscribe">退订</button>
                <br>
                <br>
                <button class="bt_rest_type" type="submit" name="func" value="finance">财务</button>
                <br>
                <br>
            </div>
        </div>
    </form>

    <!--商家-->
    <form action="/j2eeAssignment/restaurant/unsubscribe_get_orders" method="get">
    <div style="min-height:800px; width: 80%;border: 1px solid green;float:left;box-sizing: border-box">
        <div style="width: 20%;float: left;box-sizing: border-box">
            <b>时间范围：</b>
        </div>
        <div style="width: 34%;float: left;box-sizing: border-box">
            <input type="date" style="width: 90%" value=<%=start_time%> name="start_date"/>
        </div>
        <div style="width: 33%;float: left;box-sizing: border-box">
            <input type="date" style="width: 90%" value=<%=end_time%> name="end_date"/>
        </div>

        <div style="width: 20%;float: left;box-sizing: border-box">
            <b>金额范围：</b>
        </div>
        <div style="width: 34%;float: left;box-sizing: border-box">
            <input style="width: 90%" value=<%=start_mon%> name="start_money">
        </div>
        <div style="width: 33%;float: left;box-sizing: border-box">
            <input style="width: 90%" value=<%=end_mon%> name="end_money">
        </div>

        <div style="width: 20%;float: left;box-sizing: border-box">
            <b>按会员显示：</b>
        </div>
        <div style="width: 80%;float: left;box-sizing: border-box">
            <input type="radio" name="order_by_member" value="1">是
            <input type="radio" name="order_by_member" value="0" checked>否
        </div>

        <br><br><br><br>
        <input type="submit" value="确认显示" style="border: 0px">

        <hr>
        <table class="table" id="project">
            <th>订单号</th><th>下单时间</th>
            <th>支付时间</th><th>送达时间</th>
            <th>会员ID</th><th>总价</th>
            <th>送餐地址</th><th>优惠金额</th>
            <th>订单状态</th>
            <%
                double num1=0;//总消费金额
                ArrayList<String> member_id=new ArrayList();
                for(int i=0;i<orders.size();i++){
                    if(!member_id.contains(orders.get(i).getMember_id())){
                        member_id.add(orders.get(i).getMember_id());
                    }
                    num1=num1+orders.get(i).getSum_price();

                    //支付时间
                    String pay_time="";
                    if(orders.get(i).getPay_time()!=null)
                        pay_time=orders.get(i).getPay_time().toString();
                    //送达时间
                    String get_time="";
                    if(orders.get(i).getDelivery_time_received()!=null)
                        get_time=orders.get(i).getDelivery_time_received().toString();
                    //获得送餐地址
                    String add="";
                    if(orders.get(i).getDelivery_address_id()!=null){
                        Delivery_address_info d= ServiceFactory.getDelivery_address_infoManageService().getDelivery_address_infoById(orders.get(i).getDelivery_address_id());
                        add=d.getAddress();
                    }
                    //获得订单状态
                    String state="";
                    if(orders.get(i).getState()==0){
                        state="订单下达成功";
                    }
                    else if(orders.get(i).getState()==1){
                        state="已生产";
                    }
                    else if(orders.get(i).getState()==2){
                        state="已配送";
                    }
                    else{
                        state="已送达";
                    }

                    out.println("<tr>\n" +
                            "                    <td>"+orders.get(i).getId()+"</td><td>"+orders.get(i).getOrder_time().toString()
                            +"</td><td>"+pay_time+"</td><td>"+get_time+"</td>\n" +
                            "                    <td>"+orders.get(i).getMember_id()+"</td><td>"+orders.get(i).getSum_price()
                            +"</td><td>"+add+"</td><td>"+orders.get(i).getPs_money_yummy()+"</td>\n" +
                            "                    <td>"+state+"</td>\n" +
                            "                </tr>");
                }
            %>
        </table>

        <hr>
        <b>总计：</b><br>
        <b>总会员数量：<%=member_id.size()%></b><br>
        <b>总消费金额：<%=num1%></b><br>
        <b>总订单数量：<%=orders.size()%></b><br>
    </div>
    </form>
</div>

</body>
</html>