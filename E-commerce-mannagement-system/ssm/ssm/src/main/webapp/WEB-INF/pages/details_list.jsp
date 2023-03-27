<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/pintuer.js"></script>
</head>
<body>
    <div class="panel admin-panel">
        <form method="post" id="frm" action="${pageContext.request.contextPath}/details/findByPagers">
            <div class="padding border-bottom">
                <ul class="search" style="padding-left:10px;">
                    <li>
                        <input type="text" placeholder="请输入店铺名称"  id="name" class="input"
                               value="" style="width:250px; line-height:17px;display:inline-block"/>
                        <a href="javascript:void(0)" class="button border-main icon-search" id="selectName"> 搜索</a>
                    </li>
                </ul>
            </div>
            <input id="msg" type="text" value="${msg}" hidden />
            <table class="table table-hover text-center">
            <tr>
                <th width="150">店铺名称</th>
                <th>商品名称</th>
                <th>商品价格</th>
                <th>商品销量</th>
                <th>优惠券面额</th>
                <th>下单链接</th>
            </tr>
            <c:forEach items="${p.list}" var="sap">
            <tr>
                <td>${sap.shop}</td>
                <td>${sap.product}</td>
                <td>${sap.price}</td>
                <td>${sap.saleNum}</td>
                <td>${sap.couponValue}</td>
                <td> <a href="${sap.url}" target="_blank" class="button border-main icon-cloud-upload"> 点击领取优惠券</a></td>
            </tr>
            </c:forEach>
        </table>
</form></div>
<p><%@ include file="/basepage/pager.jsp" %></p>

<script type="text/javascript">
    $(function () {
        if($("#msg").val()=="查询成功"){
            alert("查询成功");
        }
        if($("#msg").val()=="未找到该店铺"){
            alert("未找到该店铺");
        }

        //模糊查询
        $("#selectName").click(function () {
            //获取输入框的值
            var title = $("#name").val();
            if(title==null && title==""){
                $("#name").focus();
                alert("请输入要查询的商品类别关键词")
            }else{
                //将该值传回到findName的controller
                window.location.href="${pageContext.request.contextPath}/details/findByPagers?name="+title+"";
            }
        })
    })
</script>
</body>
</html>
