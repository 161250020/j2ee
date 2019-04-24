<%@ page import="java.util.ArrayList" %>
<%@ page import="j2ee.model.Restaurant_info" %>
<%@ page import="j2ee.factory.ServiceFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>查看统计信息---餐厅</title>

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
    //获得需要展示的信息
    ArrayList<Restaurant_info> rest_infos= (ArrayList<Restaurant_info>) ServiceFactory.getRestaurant_infoManageService().getAllRestsInfo();
    ArrayList<Integer> nums=new ArrayList();
    for(int i=0;i<10;i++){
        nums.add(0);//初始化餐厅种类的数目为0
    }
    for(int i=0;i<rest_infos.size();i++){
        int type_id=rest_infos.get(i).getType_id();//第i个餐厅的种类的ID
        int pre_num=nums.get(type_id-1);//原本这种餐厅的数量
        nums.set(type_id-1,pre_num+1);//重新设置这种餐厅的数量
    }
    
	//需要显示的商家注册的时间段
    String start_date=(String)session.getAttribute("start_date");
    String end_date=(String)session.getAttribute("end_date");
    //获得注册商家数目
    int register_rest_num=(int)session.getAttribute("register_rest_num");
    
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
    <!--商家分类-->
    <form action="/j2eeAssignment/manager/to3functions1" method="get">
    <div style="height:800px; width: 20%;border: 1px solid green;float:left;box-sizing: border-box">
        <b>商品分类：</b>
        <hr>
        <div align="center">
            <button class="bt_rest_type" type="submit" name="func" value="restaurant">餐厅</button>
            <br>
            <br>
            <button class="bt_rest_type" type="submit" name="func" value="member">会员</button>
            <br>
            <br>
            <button class="bt_rest_type" type="submit" name="func" value="yummy">yummy财务</button>
            <br>
            <br>
        </div>
    </div>
    </form>

    <!--商家-->
    <div style="min-height:800px; width: 80%;border: 1px solid green;float:left;box-sizing: border-box">
        <div class="one">
            <b>总餐厅数：</b>
        </div>
        <div class="two">
            <b><%=rest_infos.size()%></b>
        </div>

        <b>餐厅类型分布数：</b>
        <div align="center" style="width: 60%">
            <div class="one">美食</div>
            <div class="two"><%=nums.get(0)%></div>

            <div class="one">快餐便当</div>
            <div class="two"><%=nums.get(1)%></div>

            <div class="one">特色菜系</div>
            <div class="two"><%=nums.get(2)%></div>

            <div class="one">异国料理</div>
            <div class="two"><%=nums.get(3)%></div>

            <div class="one">小吃夜宵</div>
            <div class="two"><%=nums.get(4)%></div>

            <div class="one">甜点饮品</div>
            <div class="two"><%=nums.get(5)%></div>

            <div class="one">果蔬生鲜</div>
            <div class="two"><%=nums.get(6)%></div>

            <div class="one">商店超市</div>
            <div class="two"><%=nums.get(7)%></div>

            <div class="one">鲜花绿植</div>
            <div class="two"><%=nums.get(8)%></div>

            <div class="one">医药健康</div>
            <div class="two"><%=nums.get(9)%></div>

        </div>
 		<hr>
 		
		<form method="get" action="/j2eeAssignment/manager/calculate_rest_num">
 		<div class="one">
            <div class="one">
                <b>选择日期：</b>
            </div>
            <div class="two">
                <input type="date" style="width: 90%" value=<%=start_date %> name="start_date"/>
            </div>
        </div>
        <div class="two">
            <div class="one">
                <input type="date" style="width: 90%" value=<%=end_date %> name="end_date"/>
            </div>
            <div class="two">
                <button type="submit">确认</button>
            </div>
        </div>
        </form>
        
        <hr>
        <div class="one">
            <b>注册商家数目：</b>
        </div>
        <div class="two">
            <b><%=register_rest_num %></b>
        </div>
    </div>
</div>

</body>
</html>