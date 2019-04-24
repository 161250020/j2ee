<%@ page import="j2ee.model.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="j2ee.factory.ServiceFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!--会有待支付状态的订单，可以跳转到支付页面-->
    <meta charset="UTF-8">
    <title>订单</title>

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
    //获得该用户所有订单信息---后面的时候，根据订单的信息决定显示的内容是否有button
    ArrayList<Member_order_info> all_order_info=(ArrayList<Member_order_info>)session.getAttribute("all_order_info");

%>

<!--待开发-->
<div style="height: 100px;background-color: darkcyan">
    <br>
    <div align="center"><b style="font-size: 25px;color: white">订单列表</b></div>
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
<form method="get" action="/j2eeAssignment/member/to_pay_orders">
&nbsp;&nbsp;&nbsp;&nbsp;<button type="submit" class="button secondary">查看待支付订单</button>
</form>
<div align="center">
    <div  style="min-height:800px; width: 80%;border: 1px solid green;">
    <form method="get" action="/j2eeAssignment/member/ensure_arrived_or_refund">
        <!--订单的信息-->
        <table class="table" id="project">
            <th>序列</th><th>下单时间</th><th>支付时间</th><th>送达时间</th>
            <th>餐厅名</th><th>总价</th><th>送餐地址</th><th>优惠金额</th>
            <th>订单状态</th><th>确认送达</th><th>可退金额</th><th>退单</th>
              <%
                for(int i=0;i<all_order_info.size();i++){
                    //根据订单的状态决定需要展示的订单列表的状态，以及是否需要显示按钮
                    Member_order_info m=all_order_info.get(i);
                    //已确认送达的订单不现实确认送达按钮；若显示则按钮的name=订单ID+ensure_arrived；
                    boolean b1=true;
                    //无法退订的订单不显示退订按钮；若显示则按钮的name=订单ID+refund_order；
                    boolean b2=true;
                    if(m.getState()==3){//订单已送达---不显示确认送达，不显示退订按钮
                        b1=false;
                        b2=false;
                    }
                    if((m.getResult()==0)||(m.getResult()==1)||(m.getResult()==4)){//已退订、支付不成功（2种）---不显示确认送达，不显示退订按钮
                        b1=false;
                        b2=false;
                    }
                    if(m.getState()==2){//已配送---显示确认送达，不显示退订按钮
                    	b1=true;
                        b2=false;
                    }

                    //获得餐厅名称
                    String rest_name= ServiceFactory.getRestaurant_infoManageService().getInfoBy7Chars(m.getRestaurant_id()).getName();
                    //获得送餐地址
                    ArrayList<Delivery_address_info> delivery_adds= (ArrayList<Delivery_address_info>) ServiceFactory.getDelivery_address_infoManageService().getAllAddressByID(m.getMember_id());
                    String add="";
                    for(int j=0;j<delivery_adds.size();j++){
                        if(delivery_adds.get(j).getId().equals(m.getDelivery_address_id()))
                            add=delivery_adds.get(j).getAddress();
                    }
                    //获得订单状态
                    //获得可退金额
                    String state="";
                    double ret_money=0;
                    if(m.getState()==0){
                        state="订单下达成功";
                        ret_money=m.getSum_price();
                    }
                    else if(m.getState()==1){
                        state="已生产";
                        ret_money=m.getSum_price()/2;
                    }
                    else if(m.getState()==2){
                        state="已配送";
                        ret_money=0;
                    }
                    else{
                        state="已送达";
                        ret_money=0;
                    }

                    //获得支付时间
                    String pay_time="";
                    if(m.getPay_time()!=null){
                        pay_time=m.getPay_time().toString();
                    }
                    //获得送达时间
                    String received_time="";
                    if(m.getDelivery_time_received()!=null){
                        received_time=m.getDelivery_time_received().toString();
                    }
                    if(b1&&b2){//两个按钮都显示
                    	out.println("<tr>\n" +
                                "                <td><input type=\"submit\" name=\"order_info\" value=\""+(i+1)+"\" style=\"border: 0px;width: 100%\"></td><td>"+m.getOrder_time().toString()+"</td><td>"+pay_time+"</td><td>"+received_time+"</td><td>"+rest_name+"</td><td>"+m.getSum_price()+"</td><td>"+add+"</td>\n" +
                                "                <td>"+m.getPs_money_yummy()+"</td><td>"+state+"</td>\n" +
                                "                <td><button type=\"submit\" name=\"ensure_arrived\" value=\""+(i+1)+"\">确认送达</button></td><td>"+ret_money+"</td><td><button type=\"submit\" name=\"refund_order\" value=\""+(i+1)+"\">退单</button></td>\n" +
                                "            </tr>");
                    }
                    else if((b1==false)&&(b2==false)){//两个按钮都不显示
                        out.println("<tr>\n" +
                                "                <td><input type=\"submit\" name=\"order_info\" value=\""+(i+1)+"\" style=\"border: 0px;width: 100%\"></td><td>"+m.getOrder_time().toString()+"</td><td>"+pay_time+"</td><td>"+received_time+"</td><td>"+rest_name+"</td>\n" +
                                "                <td>"+m.getSum_price()+"</td><td>"+add+"</td><td>"+m.getPs_money_yummy()+"</td><td>"+state+"</td>\n" +
                                "                <td>无法确认送达</td><td>"+ret_money+"</td><td>无法退单</td>\n" +
                                "            </tr>");
                    }
                    else{//显示确认送达，不显示退订按钮
                    	out.println("<tr>\n" +
                                "                <td><input type=\"submit\" name=\"order_info\" value=\""+(i+1)+"\" style=\"border: 0px;width: 100%\"></td><td>"+m.getOrder_time().toString()+"</td><td>"+pay_time+"</td><td>"+received_time+"</td><td>"+rest_name+"</td><td>"+m.getSum_price()+"</td><td>"+add+"</td>\n" +
                                "                <td>"+m.getPs_money_yummy()+"</td><td>"+state+"</td>\n" +
                                "                <td><button type=\"submit\" name=\"ensure_arrived\" value=\""+(i+1)+"\">确认送达</button></td><td>"+ret_money+"</td><td>无法退单</td>\n" +
                                "            </tr>");
                    }
                }
            %>

        </table>
    </form>
    </div>
</div>

</body>
</html>