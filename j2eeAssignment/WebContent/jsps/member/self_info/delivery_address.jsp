<%@ page import="j2ee.model.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="j2ee.factory.ServiceFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>送货地址</title>

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
    <div align="center"><b style="font-size: 25px;color: white">送货地址</b></div>
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
    <div style="height:800px; width: 20%;border: 1px solid green;float:left;box-sizing: border-box">
        <b>商品分类：</b>
        <hr>
        <form action="/j2eeAssignment/self_info/to5functions" method="get">
        <div align="center">
            <button class="bt_rest_type" type="submit" name="func" value="basic_info">基本信息</button>
            <br>
            <br>
            <button class="bt_rest_type" type="submit" name="func" value="delivery_add">送货地址</button>
            <br>
            <br>
            <button class="bt_rest_type" type="submit" name="func" value="change_pass">修改密码</button>
            <br>
            <br>
            <button class="bt_rest_type" type="submit" name="func" value="member_level">会员级别</button>
            <br>
            <br>
            <button class="bt_rest_type" type="submit" name="func" value="basic_func">基础功能</button>
            <br>
            <br>
        </div>
        </form>
    </div>

    <!--商家-->
    <div style="height:800px; width: 80%;border: 1px solid green;float:left;box-sizing: border-box">
        <b>送货地址：</b><br><br>
        <form action="/j2eeAssignment/self_info/change_address" method="get">
        <table class="table" id="project">
            <th>编号ID</th><th>城市</th><th>区县</th><th>镇/街道</th><th>路</th><th>修改地址</th>
            <%
                //获得该用户正在使用的所有地址
                //根据member的信息获得该会员有哪些支付的地址，地址需要是可以正在使用的那些
				Member_info m=(Member_info) session.getAttribute("user_info");
				ArrayList<Delivery_address_info> addresses1=(ArrayList<Delivery_address_info>) ServiceFactory.getDelivery_address_infoManageService().getAllAddressByID(m.getId());
				ArrayList<Delivery_address_info> addresses=new ArrayList();//正在使用的地址
				for(int i=0;i<addresses1.size();i++) {
					if(addresses1.get(i).getIn_use()==1) {
						addresses.add(addresses1.get(i));
					}
				}
				session.setAttribute("member_addresses", addresses);
                //显示地址信息
                for(int i=0;i<addresses.size();i++){
                    out.println("<tr>\n" +
                            "                <td>"+addresses.get(i).getId()+"</td><td>"+addresses.get(i).getCity()+"</td>" +
                            "<td>"+addresses.get(i).getDistrict()+"</td><td>"+addresses.get(i).getTown()+"</td>" +
                            "<td>"+addresses.get(i).getStreet()+"</td>" +
                            "<td><button type=\"submit\" name=\"modify_address_id\" value=\""+addresses.get(i).getId()+"\">修改地址</button></td>\n" +
                            "            </tr>");
                }
            %>
        </table>
        </form>
        <form action="/j2eeAssignment/self_info/new_address" method="get">
        <button type="submit">添加新送货地址</button>
        </form>
    </div>
</div>

</body>
</html>