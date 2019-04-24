<%@ page import="j2ee.model.Restaurant_register_application_info" %>
<%@ page import="j2ee.factory.ServiceFactory" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="j2ee.model.Restaurant_modify_application_info" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>待审批列表---餐厅信息修改</title>

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
    //直接调用方法，获得需要审批的注册商家的信息
    ArrayList<Restaurant_modify_application_info> rests= (ArrayList<Restaurant_modify_application_info>) ServiceFactory.getRestaurant_modify_application_infoManageService().getAllModifyApplications();

%>

<!--待开发-->
<form action="/j2eeAssignment/manager/logout" method="get">
<div style="height: 100px;background-color: darkcyan">
    <br>
    <div align="center"><b style="font-size: 25px;color: white">审批商家信息修改的申请</b></div>
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
    <!--商家分类-->
    <form action="/j2eeAssignment/manager/to2functions" method="get">
        <div style="height:800px; width: 20%;border: 1px solid green;float:left;box-sizing: border-box">
            <b>商品分类：</b>
            <hr>
            <div align="center">
                <button class="bt_rest_type" type="submit" name="func" value="register">餐厅注册申请</button>
                <br>
                <br>
                <button class="bt_rest_type" type="submit" name="func" value="modify">餐厅信息修改申请</button>
                <br>
                <br>
                <button class="bt_rest_type" type="submit" name="func" value="publish">餐厅商品添加申请</button>
                <br>
                <br>
            </div>
        </div>
    </form>

    <!--商家-->
    <form action="/j2eeAssignment/manager/to_modify" method="get">
    <div style="min-height:800px; width: 80%;border: 1px solid green;float:left;box-sizing: border-box">
        <table class="table" id="project">
            <th>审批</th><th>商家新名称</th><th>商家新类型</th><th>商家新地址</th>
            <%
                for(int i=0;i<rests.size();i++){
                    Restaurant_modify_application_info r=rests.get(i);
                    //注册商家类型
                    String type_name=ServiceFactory.getRestaurant_typesManageService().getTypeNameById(r.getNew_type_id());
                    out.println("<tr>\n" +
                            "                <td><button type=\"submit\" value=\""+r.getId()+"\" name=\"modify_id\">审批</button></td>\n" +
                            "                <td>"+r.getNew_name()+"</td>\n" +
                            "                <td>"+type_name+"</td>\n" +
                            "                <td>"+r.getNew_address()+"</td>\n" +
                            "            </tr>");
                }
            %>
        </table>
    </div>
    </form>
</div>

</body>
</html>