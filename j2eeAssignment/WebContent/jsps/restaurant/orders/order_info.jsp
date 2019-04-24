<%@ page import="j2ee.model.Member_order_info" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="j2ee.model.Member_order_content_info" %>
<%@ page import="j2ee.factory.ServiceFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>

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
    //获得需要展示的订单的基本信息
    Member_order_info m= (Member_order_info) session.getAttribute("order_content_info");
    String order_list_id=m.getOrder_list_id();
    //获得订单所含商品的信息---三种
    ArrayList<Member_order_content_info> content= (ArrayList<Member_order_content_info>) ServiceFactory.getMember_order_content_infoManageService().orderContentByOrder_list_id(order_list_id);

%>

<!--待开发-->
<div style="height: 100px;background-color: darkcyan">
    <br>
    <div align="center"><b style="font-size: 25px;color: white">订单详情</b></div>
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
<div align="center">
    <!--商家-->
    <div style="min-height:800px; width: 80%;border: 1px solid green;" align="left">
        <b>订单ID：<%=m.getId() %></b>
        <b>单品：</b>
        <table class="table" id="project">
            <th>商品名</th><th>购买数量</th><th>总价</th>
            <%
                for(int i=0;i<content.size();i++){
                    if(content.get(i).getType()==0){
                        out.println("<tr>\n" +
                                "                <td>"+content.get(i).getName()+"</td>" +
                                "<td>"+content.get(i).getNum()+"</td><td>"+content.get(i).getPrice()+"</td>\n" +
                                "            </tr>");
                    }
                }
            %>

        </table>
        <b>套餐：</b>
        <table class="table">
            <th>商品名</th><th>购买数量</th><th>总价</th>
            <%
                for(int i=0;i<content.size();i++){
                    if(content.get(i).getType()==1){
                        out.println("<tr>\n" +
                                "                <td>"+content.get(i).getName()+"</td>" +
                                "<td>"+content.get(i).getNum()+"</td><td>"+content.get(i).getPrice()+"</td>\n" +
                                "            </tr>");
                    }
                }
            %>
        </table>
        <b>优惠：</b>
        <table class="table">
            <th>商品名</th><th>购买数量</th><th>总价</th>
            <%
                for(int i=0;i<content.size();i++){
                    if(content.get(i).getType()==2){
                        out.println("<tr>\n" +
                                "                <td>"+content.get(i).getName()+"</td>" +
                                "<td>"+content.get(i).getNum()+"</td><td>"+content.get(i).getPrice()+"</td>\n" +
                                "            </tr>");
                    }
                }
            %>
        </table>
    </div>
</div>

</body>
</html>
