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
        <form method="post" id="frm" action="${pageContext.request.contextPath}/tabs/findByPagers">
            <div class="padding border-bottom">
                <ul class="search" style="padding-left:10px;">
                    <li>
                        <input type="text" placeholder="请输入搜索关键字"  id="name" class="input"
                               value="" style="width:250px; line-height:17px;display:inline-block"/>
                        <a href="javascript:void(0)" class="button border-main icon-search" id="selectName"> 搜索</a>
                    </li>
                </ul>
            </div>
            <input id="msg" type="text" value="${msg}" hidden />
            <table class="table table-hover text-center">
            <tr>
                <th width="150">ID</th>
                <th>名称</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${p.list}" var="tab">
            <tr>
                <td>${tab.tabId}</td>
                <td>${tab.name}</td>
                <c:if test="${tab.state==0}">
                        <td>不可用</td>
                </c:if>
                <c:if test="${tab.state==1}">
                    <td>可用</td>
                </c:if>
                <td><div class="button-group">
                    <a class="button border-main" href="${pageContext.request.contextPath}/tabs/toupdate/${tab.tabId}"><span
                        class="icon-edit"></span> 修改</a>
                    <a class="button border-red" href="javascript:void(0)" onclick="del(${tab.tabId})"><span class="icon-trash-o"></span> 删除</a>
                </div></td>
            </tr>
            </c:forEach>
        </table>
</form></div>
<p><%@ include file="/basepage/pager.jsp" %></p>

<script type="text/javascript">
    $(function () {
        if($("#msg").val()=="删除成功"){
            alert("删除成功");
        }
        if($("#msg").val()=="删除失败"){
            alert("删除失败");
        }
        if($("#msg").val()=="增加成功"){
            alert("增加成功");
        }
        if($("#msg").val()=="修改成功"){
            alert("修改成功");
        }
        if($("#msg").val()=="修改失败"){
            alert("修改失败");
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
                window.location.href="${pageContext.request.contextPath}/tabs/findByName?name="+title+"";
            }
        })
    })

    function del(id){
        if(confirm("您确定要删除吗?")){
            //确定删除，进入deleteByPrimaryKey方法
        window.location.href="${pageContext.request.contextPath}/tabs/delete/"+id+""
        }
    }
</script>
</body>
</html>
