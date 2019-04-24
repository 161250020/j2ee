<%@ page import="java.util.ArrayList" %>
<%@ page import="j2ee.model.Restaurant_register_application_info" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>餐厅注册审批结果</title>
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
    </style>
</head>
<body>

<%
    //获得注册审批的列表
    ArrayList<Restaurant_register_application_info> arr= (ArrayList<Restaurant_register_application_info>) session.getAttribute("examine_result");

%>

<form method="get" action="/j2eeAssignment/restaurant/examine_result">
<div class="one">
    <input name="chars7" placeholder="注册时候的餐厅的7位编码">
</div>
<div class="two" align="center">
    <button type="submit">确认</button>
</div>
</form>
<br>
<br>

<hr>

<b>结果：</b>
<table class="table" id="project">
    <th>注册的7位登录码</th><th>注册餐厅名称</th><th>注册时间</th><th>审批结果</th><th>审批经理ID</th>
    <%
        for(int i=0;i<arr.size();i++){
        	//审批结果
            String result="";
            if(arr.get(i).getResult()==0){
                result="未审批";
            }
            else if(arr.get(i).getResult()==1){
                result="审批通过失败";
            }
            else{
                result="审批通过成功";
            }
            
            out.println("<tr>\n" +
                    "        <td>"+arr.get(i).getLogin_id()+"</td><td>"+arr.get(i).getName()+"</td>" +
                    "<td>"+arr.get(i).getDate().toString()+"</td><td>"+result+"</td><td>"+arr.get(i).getManager_id()+"</td>\n" +
                    "    </tr>");
        }
    %>

</table>
</body>
</html>