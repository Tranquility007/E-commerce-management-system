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
        <form method="post" id="frm" action="${pageContext.request.contextPath}/tabs/list">
            <table class="table table-hover text-center">
            <tr>
                <th width="150">类别ID</th>
                <th>名称</th>
                <th>商品数量</th>
            </tr>
            <c:forEach items="${p.list}" var="tab">
            <tr>
                <td>${tab.tabId}</td>
                <td>${tab.name}</td>
                <td>${tab.num}</td>
            </tr>
            </c:forEach>
        </table>
</form></div>
<p><%@ include file="/basepage/pager.jsp" %></p>
</body>
</html>
