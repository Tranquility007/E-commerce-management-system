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
    <div class="panel admin-panel">
        <form method="post" action="${pageContext.request.contextPath}/shops/findByPagers">
        <div class="padding border-bottom">
            <ul class="search">
                <li>
                    <div class="field">
                        <input type="text" id="title" name="title" class="input" placeholder="请输入搜索关键字" style="width:60%; float:left;padding-right: 15px" data-place="right" />
                        <button type="button" class="button border-green" id="selectTitle"><span class="icon-search"></span>查询</button>
                        <input id="msg" type="text" value="${msg}" hidden />
                    </div>
                </li>
            </ul>
        </div>
        <table class="table table-hover text-center">
            <tr>
                <th width="150"><input type="checkbox" name="firstCb" id="firstCb"/>
                    ID
                </th>
                <th>图片</th>
                <th>名称</th>
                <th>好评率</th>
                <th>服务得分</th>
                <th>物流得分</th>
                <th>商品数量</th>
                <th>关注人数</th>
                <th width="120">状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${p.list}" var="shop">

            <tr>
                <%--id--%>
                <td><input type="checkbox" name="gid" value="${shop.shopId}" />
                        ${shop.shopId}
                </td>
                    <%--图片picUrl--%>
                <td width="10%"><img src="${shop.picUrl}" alt="" width="70" height="50"/></td>
                    <%--名称title--%>
                <td>${shop.title}</td>
                    <%--好评率goodRatePercentage--%>
                <td>${shop.goodRatePercentage}</td>
                    <%--服务得分serviceScore--%>
                <td>${shop.serviceScore}</td>
                    <%--物流得分deliveryScore--%>
                <td>${shop.deliveryScore}</td>
                        <%--商品数量allItemCount--%>
                    <td>${shop.allItemCount}</td>
                    <%--关注人数fansNum--%>
                    <td>${shop.fansNum}</td>
                    <%--状态state--%>
                <c:if test="${shop.state==1}">
                    <td>营业</td>
                </c:if>
                <c:if test="${shop.state==0}">
                    <td>已注销</td>
                </c:if>
                    <td><div class="button-group">
                        <a class="button border-main" href="${pageContext.request.contextPath}/shops/toupdate/${shop.shopId}"><span
                                class="icon-edit"></span> 修改</a>
                        <a class="button border-red" href="javascript:void(0)" onclick="del(${shop.shopId})"><span class="icon-trash-o"></span> 删除</a>
                    </div></td>
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
            if($("#msg").val()=="不存在该条记录"){
            alert("不存在该条记录");
        }
        if($("#msg").val()=="删除成功"){
            alert("删除成功");
        }
        if($("#msg").val()=="删除失败"){
            alert("删除失败");
        }
        if($("#msg").val()=="增加成功"){
            alert("增加成功");
        }
        if($("#msg").val()=="修改失败"){
            alert("修改失败");
        }
        //模糊查询
        $("#selectTitle").click(function () {
            //获取输入框的值
            var title = $("#title").val();
            if(title==null && title==""){
                $("#title").focus();
                alert("请输入要查询的店铺关键词")
            }else{
                //将该值传回到findName的controller
                window.location.href="${pageContext.request.contextPath}/shops/findByName?title="+title+"";
            }
    })
    });

    function del(id){
        if(confirm("您确定要删除吗?")){
            //确定删除，进入deleteByPrimaryKey方法
            window.location.href="${pageContext.request.contextPath}/shops/delete/"+id+""
        }
    }


</script>

</body>
</html>
