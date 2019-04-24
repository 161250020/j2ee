<%@ page import="j2ee.model.Member_order_info" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="j2ee.model.Restaurant_info" %>
<%@ page import="j2ee.factory.ServiceFactory" %>
<%@ page import="j2ee.model.Delivery_address_info" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>点餐</title>

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
<!--待开发-->
<div style="height: 100px;background-color: darkcyan">
    <br>
    <div align="center"><b style="font-size: 25px;color: white">点餐</b></div>
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
<%
    //获取日期
    String date1= (String) session.getAttribute("order_start_time");
    String date2= (String) session.getAttribute("order_end_time");
    //获取金额
    double mon1= (double) session.getAttribute("order_start_mon");
    double mon2= (double) session.getAttribute("order_end_mon");
    //获取需要显示的订单的信息
    ArrayList<Member_order_info> mios= (ArrayList<Member_order_info>) session.getAttribute("order_orders_list");
%>
<!--商家信息-->
<div>
    <!--商家分类-->
    <div style="height:800px; width: 20%;border: 1px solid green;float:left;box-sizing: border-box">
        <b>商品分类：</b>
        <hr>
        <form action="/j2eeAssignment/to3functions" method="get">
            <div align="center">
                <button class="bt_rest_type" name="func" value="order">点餐</button>
                <br>
                <br>
                <button class="bt_rest_type" name="func" value="unsubscribe">退订</button>
                <br>
                <br>
                <button class="bt_rest_type" name="func" value="consume">消费</button>
                <br>
                <br>
            </div>
        </form>
    </div>

    <!--商家-->
    <div style="min-height:800px; width: 80%;border: 1px solid green;float:left;box-sizing: border-box">
        <form action="/j2eeAssignment/member/statistic_order" method="get">
            <div style="width: 20%;float: left;box-sizing: border-box">
                <b>时间范围：</b>
            </div>
            <div style="width: 34%;float: left;box-sizing: border-box">
                <input type="date" style="width: 90%" value=<%=date1%> name="start_date"/>
            </div>
            <div style="width: 33%;float: left;box-sizing: border-box">
                <input type="date" style="width: 90%" value=<%=date2%> name="end_date"/>
            </div>

            <div style="width: 20%;float: left;box-sizing: border-box">
                <b>金额范围：</b>
            </div>
            <div style="width: 34%;float: left;box-sizing: border-box">
                <input style="width: 90%" value=<%=mon1%> name="start_money">
            </div>
            <div style="width: 33%;float: left;box-sizing: border-box">
                <input style="width: 90%" value=<%=mon2%> name="end_money">
            </div>

            <div style="width: 20%;float: left;box-sizing: border-box">
                <b>按餐厅显示：</b>
            </div>
            <div style="width: 80%;float: left;box-sizing: border-box">
                <input type="radio" name="order_by_restaurant" value="1">是
                <input type="radio" name="order_by_restaurant" value="0" checked>否
            </div>

            <br><br><br><br>
            <input type="submit" value="确认显示" style="border: 0px">
        </form>

        <hr>
        <table class="table" id="project">
            <th>订单号</th><th>下单时间</th><th>支付时间</th><th>送达时间</th>
            <th>餐厅名</th><th>总价</th><th>送餐地址</th><th>优惠金额</th>
            <th>订单状态</th><th>订单结果</th>
            <%
                int num1=0;//总餐厅数量
                ArrayList<String> rest_ids=new ArrayList();
                double num2=0;//总消费金额
                for(int i=0;i<mios.size();i++){
                    if(rest_ids.contains(mios.get(i).getRestaurant_id())){//若已含该餐厅
                        
                    }
                    else{//若未含该餐厅
                        rest_ids.add(mios.get(i).getRestaurant_id());//添加餐厅ID
                        num1=num1+1;//餐厅数量加一
                    }
                    num2=num2+mios.get(i).getSum_price();
                    
                    //支付时间
                    String pay_time="";
                    if(!(mios.get(i).getPay_time() ==null)){//不为空
                        pay_time=mios.get(i).getPay_time().toString();
                    }
                    //送达时间
                    String get_time="";
                    if(!(mios.get(i).getDelivery_time_received() ==null)){
                        get_time=mios.get(i).getDelivery_time_received().toString();
                    }
                    //餐厅名
                    String rest_name="";
                    Restaurant_info r= ServiceFactory.getRestaurant_infoManageService().getInfoBy7Chars(mios.get(i).getRestaurant_id());
                    rest_name=r.getName();
                    //送餐地址
                    String address=ServiceFactory.getDelivery_address_infoManageService().getDelivery_address_infoById(mios.get(i).getDelivery_address_id()).getAddress();
                    //订单状态
                    String state="";
                    if(mios.get(i).getState()==0){
                        state="订单下达成功";
                    }
                    else if(mios.get(i).getState()==1){
                        state="已生产";
                    }
                    else if(mios.get(i).getState()==2){
                        state="已配送";
                    }
                    else{
                        state="已送达";
                    }
                    //订单结果
                    String result="";
                    if(mios.get(i).getResult()==0){
                        result="退订";
                    }
                    else if(mios.get(i).getResult()==1){
                        result="未在规定时间支付";
                    }
                    else if(mios.get(i).getResult()==2){
                        result="订单成功";
                    }
                    else{
                        result="余额不足";
                    }
                    out.println("<tr>\n" +
                            "                <td>"+mios.get(i).getId()+"</td><td>"+mios.get(i).getOrder_time().toString()+"</td><td>"+pay_time+"</td><td>"+get_time+"</td>\n" +
                            "                <td>"+rest_name+"</td><td>"+mios.get(i).getSum_price()+"</td><td>"+address+"</td><td>"+mios.get(i).getPs_money_yummy()+"</td>\n" +
                            "                <td>"+state+"</td><td>"+result+"</td>\n" +
                            "            </tr>");
                }
            %>

        </table>

        <hr>
        <b>总计：</b><br>
        <b>总餐厅数量：<%=num1%></b><br>
        <b>总消费金额：<%=num2%></b><br>
        <b>总订单数量：<%=mios.size()%></b><br>
    </div>
</div>

</body>
</html>