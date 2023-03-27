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
  <<div class="body-content">
    <form method="post" class="form-x" action="${pageContext.request.contextPath}/shops/update">
      <input id="msg" type="text" value="${msg}" hidden />
      <div class="form-group">
        <div class="label">
          <label>店铺ID：</label>
        </div>
        <div class="field">
          <input type="text" readonly="readonly" class="input w50" value="${shop.shopId}" id="shopId" name="shopId" data-validate="required:请输入店铺id" />
          <div class="tips" id="tips1"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>店铺名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${shop.title}" id="title" vname="title" data-validate="required:请输入店铺名称" />
          <div class="tips" id="tips2"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>好评率：</label>
        </div>
        <div class="field">
          <input type="text" readonly="readonly" class="input w50" value="${shop.goodRatePercentage}" name="goodRatePercentage" v-model="goodRatePercentage" data-validate="number:好评率格式不正确"/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>服务得分：</label>
        </div>
        <div class="field">
          <input type="text" readonly="readonly" class="input w50" value="${shop.serviceScore}" name="serviceScore" v-model="serviceScore" data-validate="number:服务得分格式不正确"/>
          <div class="tips"></div>
        </div>
      </div>

      <div class="form-group">
        <div class="label">
          <label>物流得分：</label>
        </div>
        <div class="field">
          <input type="text" readonly="readonly" class="input w50" value="${shop.deliveryScore}" name="deliveryScore" v-model="deliveryScore" data-validate="number:物流得分格式不正确"/>
          <div class="tips"></div>
        </div>
      </div>

      <div class="form-group">
        <div class="label">
          <label>商品数量：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${shop.allItemCount}" name="allItemCount" data-validate="digits:销量格式不正确" />
          <div class="tips"></div>
        </div>
      </div>

      <div class="form-group">
        <div class="label">
          <label>关注人数：</label>
        </div>
        <div class="field">
          <input type="text" readonly="readonly" class="input w50" value="${shop.fansNum}" name="fansNum" data-validate="digits:关注人数格式不正确" />
          <div class="tips"></div>
        </div>
      </div>

      <div class="form-group">
        <div class="label">
          <label>店铺图片链接：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="picUrl" value="${shop.picUrl}" data-validate="url:链接格式不正确"/>
        </div>
      </div>

      <div class="form-group">
        <div class="label">
          <label>店铺状态：</label>
        </div>
        <div class="field">
          <select name="state" class="input w50" id="state">
            <c:if test="${shop.state==0}">
              <option value="1">营业</option>
              <option value="0" selected>已注销</option>
            </c:if>
            <c:if test="${shop.state==1}">
              <option value="1">营业</option>
              <option value="0" selected>已注销</option>
            </c:if>
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
<script>

    $(function () {
        //验证店铺id唯一
        $("#shopId").blur(function () {
            var id=$("#shopId").val();
            if(id!=null && id!=""){
                $.post("${pageContext.request.contextPath}/shops/selectId",{"id":id},function (data) {
                    if(data=="no"){
                        $("#tips1").css("color","green").html("该id可用");
                        $("#shopId").css("border-color","green");
                    } else{
                        $("#tips1").css("color","red").html("id已存在,请重新输入");
                        $("#shopId").focus();
                        $("#shopId").css("border-color","red");
                    }
                })
            }
        });
        //验证店铺title唯一
        $("#title").blur(function () {
            var title=$("#title").val();
            if(title!=null && title!=""){
                $.post("${pageContext.request.contextPath}/shops/selectTitle",{"title":title},function (data) {
                    if(data=="no"){
                        $("#tips2").css("color","green").html("店铺名称可用");
                        $("#title").css("border-color","green");
                    } else{
                        $("#tips2").css("color","red").html("店铺名称已存在,请重新输入");
                        $("#title").focus();
                        $("#title").css("border-color","red");
                    }
                })
            }
        });
        if($("#msg").val()=="修改成功"){
            alert("修改成功");
        }
        if($("#msg").val()=="修改失败"){
            alert("增加失败");
        }
    })
</script>
</body>
</html>