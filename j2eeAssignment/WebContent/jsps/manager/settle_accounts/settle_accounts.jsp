<%@ page import="java.util.ArrayList" %>
<%@ page import="j2ee.model.Member_order_content_info" %>
<%@ page import="j2ee.model.Member_order_info" %>
<%@ page import="j2ee.factory.ServiceFactory" %>
<%@ page import="j2ee.model.Yummy_settle_accounts_info" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.UUID" %>
<%@ page import="j2ee.model.Manager_info" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>结算</title>

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
        .one, .two{
            width: 50%;
            height: 45px;
            float: left;
            box-sizing: border-box;
        }
        .bt{
            background-color: lightgreen;
            height: 50px; width: 33%;
            border: 0px;
            font-size: 15px;
            border-radius: 5px;
        }

        .bt_rest_type{
            width: 90%;
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
    //获得可以结算的订单的信息---订单成功且已送达
    ArrayList<Member_order_info> pre_order_infos= (ArrayList<Member_order_info>) ServiceFactory.getMember_order_infoManageService().getSuccessOrders();
    ArrayList<Member_order_info> order_infos=new ArrayList();
    for(int i=0;i<pre_order_infos.size();i++){
        if(pre_order_infos.get(i).getAccount()==0){//此订单未被结算
            order_infos.add(pre_order_infos.get(i));
        }
    }
    //获得上次结算的日期，如果没有任何的结算记录，就是空的
    String last_date=ServiceFactory.getYummy_settle_accounts_infoManageService().getLastAccountDateTime();
    //存储本次结算的时间到session
    Timestamp t=new Timestamp(new java.util.Date().getTime());
    //需要展示的数据
    double num1=0;
    double num2=0;
    double num3=0;
    double num4=0;
    for(int i=0;i<order_infos.size();i++){
        num1=num1+order_infos.get(i).getSum_price();
        num2=num2+order_infos.get(i).getPs_money_yummy();
        num3=num3+order_infos.get(i).getSum_price()/2;
    }
    num4=num3;
    //添加需要添加的结算记录的信息的session
    Yummy_settle_accounts_info y=new Yummy_settle_accounts_info();
    y.setId(UUID.randomUUID().toString());
    y.setAccount_time(t);
    String account_id=UUID.randomUUID().toString();
    y.setAccount_id(account_id);
    Manager_info m= (Manager_info) session.getAttribute("manager_info");
    y.setManager_id(m.getId());
    y.setRest_money(num3);
    y.setTotal_in_money(num1);
    y.setTotal_out_money(num2);
    session.setAttribute("account_info",y);
    session.setAttribute("account_orders",order_infos);
%>

<!--待开发-->
<form action="/j2eeAssignment/manager/logout" method="get">
<div style="height: 100px;background-color: darkcyan">
    <br>
    <div align="center"><b style="font-size: 25px;color: white">查看统计信息---餐厅</b></div>
    <div align="right"><input type="submit" style="border: 0px" value="退出登录"></div>
</div>
</form>

<!--选择按钮-->
<form action="/j2eeAssignment/manager/to3functions" method="get">
    <div align="center">
        <button class="bt" type="submit" name="func" value="sum">查看统计信息</button>
        <button class="bt" type="submit" name="func" value="settle">结算</button>
        <button class="bt" type="submit" name="func" value="exam">审批</button>
    </div>
</form>

<!--商家信息-->
<div>
    <!--商家-->
    <div align="center" style="min-height:800px; width: 80%;border: 1px solid green;float:left;box-sizing: border-box">
        <div class="one">
            <b>上次结算日期：</b>
        </div>
        <div class="two">
            <%=last_date%>
        </div>

        <div class="one">
            <b>本次结算日期：</b>
        </div>
        <div class="two">
            <%=t.toString()%>
        </div>

        <div class="one">
            <b>所有订单涉及总金额：</b>
        </div>
        <div class="two">
            <%=num1%>
        </div>

        <div class="one">
            <b>发出优惠总金额（已使用）：</b>
        </div>
        <div class="two">
            <%=num2%>
        </div>

        <div class="one">
            <b>餐厅收益：</b>
        </div>
        <div class="two">
            <%=num3%>
        </div>

        <div class="one">
            <b>yummy收益：</b>
        </div>
        <div class="two">
            <%=num4%>
        </div>

        <form method="get" action="/j2eeAssignment/manager/settle_account">
            <button type="submit">确认结算</button>
        </form>
        <br>
        <div align="left" style="border: 1px solid green">
            <b>PS：结算后，钱款会支付给各餐厅；</b>
            <br>
            <b>结算规则：</b>
            <br>
            待分配金额=共计获得金额-发出优惠总金额；
            <br>
            餐厅获得金额=(餐厅订单赚取的金额/共计获得金额)*(待分配金额*50%);
            <br>
            yummy结余金额=待分配金额*50%；
            <br>
            仅仅结算：已送达且成功支付且以往未结算的订单，退单的订单在退单的时候就已经结算完毕！
        </div>
    </div>
</div>

</body>
</html>