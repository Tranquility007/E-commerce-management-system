<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <%--<script src="${pageContext.request.contextPath}/js/jquery.js"></script>--%>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/pintuer.js"></script>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/products/findByPagers" id="listform">
    <div class="panel admin-panel">
        <div class="padding border-bottom">
            <ul class="search" style="padding-left:10px;">
                <if condition="$iscid eq 1">
                    <li>
                        <select id="tabId" name="tabId" class="input" style="width:120px; line-height:17px;" onchange="findByTabId()">
                            <option value="">请选择分类</option>
                            <c:forEach items="${tabs}" var="tab">
                                <option value="${tab.tabId}" <c:if test="${tab.tabId==id}">selected="selected"</c:if>>
                                        ${tab.name}</option>
                            </c:forEach>
                    </li>
                </if>
                <li>
                    <input type="text" placeholder="搜索关键字"  id="title" name="title" class="input"
                           value="" style="width:250px; line-height:17px;display:inline-block"/>
                    <a href="javascript:void(0)" class="button border-main icon-search" id="selectTitle"> 搜索</a>
                </li>
            </ul>
        </div>
        <input id="msg" type="text" value="${msg}" hidden />
        <table class="table table-hover text-center">
            <tr>
                <th width="150" style="text-align:left; padding-left:20px;">ID</th>
                <th>图片</th>
                <th>名称</th>
                <th>价格</th>
                <th width="15%">商品类别</th>
                <th>销量</th>
                <th>包邮</th>
                <th width="300">操作</th>
            </tr>
            <volist name="list" id="vo">
                <c:forEach items="${p.list}" var="product">
                    <tr>      <%--商品productId--%>
                            <td style="text-align:left; padding-left:20px;">
                                <%--<input type="checkbox" name="id[]" value="" />--%>
                                ${product.productId}
                        </td>
                            <%--商品image--%>
                        <td width="10%"><img src="${product.image}" alt="" width="70" height="50"/>
                        </td>
                            <%--商品名称title--%>
                        <td>${product.title}</td>
                            <%--{#     商品价格price       #}--%>
                        <td>${product.price}</td>
                            <%--{#    商品类别teb_id      #}--%>
                        <td>${product.tabName}</td>
                            <%--{#      商品销量saleNum      #}--%>
                        <td>${product.saleNum}</td>
                            <%--{#      是否包邮isFreePostage      #}--%>
                        <td>
                            <c:choose>
                                <c:when test="${product.isFreePostage==1}">
                                    是
                                </c:when>
                                <c:otherwise>
                                    否
                                </c:otherwise>
                            </c:choose>
                        </td>
                            <%--{#     操作       #}--%>
                        <td>
                            <div class="button-group"><a class="button border-main" href="${pageContext.request.contextPath}/products/toupdate/${product.productId}"><span
                                    class="icon-edit"></span> 修改</a>
                                <a class="button border-red"  href="javascript:void(0)" onclick="del(${product.productId})"><span
                                    class="icon-trash-o"></span> 删除</a></div>
                        </td>
                    </tr>
                </c:forEach>
            </volist>
        </table>
    </div>
</form>
<p><%@ include file="/basepage/pager.jsp" %></p>
<script type="text/javascript">
    $(function () {

        if ($("#msg").val() == "查询成功") {
            alert("查询成功");
        }
        if ($("#msg").val() == "不存在该条记录") {
            alert("不存在该条记录");
        }
        if($("#msg").val() == "未查询到相应商品"){
            alert("未查询到相应商品");
        }
        //模糊查询
        $("#selectTitle").click(function () {
            //获取输入框的值
            var title = $("#title").val();
            if(title==null && title==""){
                $("#title").focus();
                alert("请输入要查询的商品名称")
            }else{
                //将该值传回到findName的controller
                window.location.href="${pageContext.request.contextPath}/products/findByName?title="+title+"";
            }
        })

    });

    function del(id){
        if(confirm("您确定要删除吗?")){
            //确定删除，进入deleteByPrimaryKey方法
            window.location.href="${pageContext.request.contextPath}/products/delete/"+id+""
        }
    }
    /**
     * 根据类别查找商品
     */
    function findByTabId() {
        var myselect = document.getElementById('tabId');
        var index = myselect.selectedIndex;
        var id = myselect.options[index].value;
        // alert(id);
        location.href="${pageContext.request.contextPath}/products/findByTabId/"+id+""
    }

</script>
</body>
</html>