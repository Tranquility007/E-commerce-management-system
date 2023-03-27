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
    <div class="body-content">
        <form method="post" class="form-x" action="${pageContext.request.contextPath}/tabs/save">
            <input id="msg" type="text" value="${msg}" hidden />
            <div class="form-group">
                <div class="label">
                    <label>ID：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="" id="tabId" name="tabId" data-validate="number:请输入商品类别ID" />
                    <div class="tips" id="tips1"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>名称：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="" id="name" name="name" data-validate="required:请输入商品类别名称" />
                    <div class="tips" id="tips2"></div>
                </div>
            </div>
                <div class="form-group">
                    <div class="label">
                        <label>状态：</label>
                    </div>
                    <div class="field">
                        <select name="state" class="input w50" id="state">
                            <option value="0" disabled="disabled">请选择状态</option>
                            <option value="1">可用</option>
                            <option value="0">不可用</option>
                        </select>
                    </div>
                </div>
            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        //验证类别Id唯一
        $("#tabId").blur(function () {
            var tid=$("#tabId").val();
            if(tid!=null && tid!=""){
                $.post("${pageContext.request.contextPath}/tabs/selectTid",{"tid":tid},function (data) {
                    if(data=="no"){
                        $("#tips1").css("color","green").html("该id可用");
                        $("#tabId").css("border-color","green");
                    } else{
                        $("#tips1").css("color","red").html("id已存在,请重新输入");
                        $("#tabId").focus();
                        $("#tabId").css("border-color","red");
                    }
                })
            }
        });
        //验证类别名称唯一
        $("#name").blur(function () {
            var name=$("#name").val();
            if(name!=null && name!=""){
                $.post("${pageContext.request.contextPath}/tabs/selectName",{"name":name},function (data) {
                    if(data=="no"){
                        $("#tips2").css("color","green").html("类别可用");
                        $("#name").css("border-color","green");
                    } else{
                        $("#tips2").css("color","red").html("类别已存在,请重新输入");
                        $("#name").focus();
                        $("#name").css("border-color","red");
                    }
                })
            }
        });
        if($("#msg").val()=="增加失败"){
            alert("增加失败");
        }
    })
</script>
</body>
</html>