<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>修改内容</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="${pageContext.request.contextPath}/products/update">
      <div class="form-group">
        <div class="label">
          <label>商品名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${product.title}" name="title" data-validate="required:请输入商品名称" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>商品淘宝id：</label>
        </div>
        <div class="field">
          <input type="text" readonly="readonly" class="input w50" value="${product.commodityId}" name="commodityId" data-validate="required:请输入对应的商品淘宝id" />
          <div class="tips" id="tips1"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>商品id：</label>
        </div>
        <div class="field">
          <input type="text" readonly="readonly" class="input w50" value="${product.productId}" name="productId" data-validate="required:请输入商品id" />
          <div class="tips" id="tips2"></div>
        </div>
      </div>


      <if condition="$iscid eq 1">
        <div class="form-group">
          <div class="label">
            <label>商品类别：</label>
          </div>
          <div class="field">
            <select id="tabId" name="tabId">
              <c:forEach items="${tabs}" var="tab">
                <option value="${tab.tabId}" <c:if test="${tab.tabId==product.tabId}">selected="selected"</c:if>>
                        ${tab.name}
                </option>
              </c:forEach>
            </select>
            <div class="tips"></div>
          </div>
        </div>
      </if>

         <div class="form-group">
        <div class="label">
          <label>商品初始价格：</label>
        </div>
        <div class="field">
          <input type="text" readonly="readonly" class="input w50" value="${product.originPrice}" name="originPrice" v-model="price" data-validate="required:请输入商品初始价格" />
          <div class="tips" id="tips3"></div>
        </div>
      </div>
        <div class="form-group">
        <div class="label">
          <label>商品当前价格：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${product.price}" name="price" v-model="price" data-validate="required:请输入商品当前价格" />
          <div class="tips" id="tips4"></div>
        </div>
      </div>
        <div class="form-group">
        <div class="label">
          <label>销量：</label>
        </div>
        <div class="field">
          <input type="text" readonly="readonly" class="input w50" name="saleNum" value="${product.saleNum}"  data-validate="number:销量必须为整数" />
          <div class="tips" id="tips5"></div>
        </div>
      </div>
      <div class="clear"></div>
      <div class="form-group">
        <div class="label">
          <label>商品链接：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="url" value="${product.url}" />
          <div class="tips" id="tips6"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>商品图片链接：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="image" value="${product.image}"/>
          <div class="tips" id="tips7"></div>
        </div>
      </div>

      <div class="form-group">
        <div class="label">
          <label>是否包邮：</label>
        </div>
        <div class="field">
          <select id="isFreePostage" name="isFreePostage">
            <c:choose>
              <c:when test="${product.isFreePostage==1}">
                <option value="1" selected="selected">是</option>
                <option value="0">否</option>
              </c:when>
              <c:otherwise>
                <option value="1">是</option>
                <option value="0" selected="selected">否</option>
              </c:otherwise>
            </c:choose>
          </select>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>商品状态：</label>
        </div>
        <div class="field">
          <select id="state" name="state">
            <<c:choose>
            <c:when test="${product.state==1}">
              <option value="1" selected="selected">在售</option>
              <option value="0">已下架</option>
            </c:when>
            <c:otherwise>
              <option value="1">在售</option>
              <option value="0" selected="selected">已下架</option>
            </c:otherwise>
          </c:choose>
          </select>
          <div class="tips"></div>
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

    $(function(){
        //验证淘宝id唯一
        $("#commodityId").blur(function () {
            var comid=$("#commodityId").val();
            if(comid!=null && comid!=""){
                $.post("${pageContext.request.contextPath}/products/selectcid",{"comid":comid},function (data) {
                    if(data=="no"){
                        $("#tips1").css("color","green").html("该id可用");
                        $("#commodityId").css("border-color","green");
                    } else{
                        $("#tips1").css("color","red").html("id已存在,请重新输入");
                        $("#commodityId").focus();
                        $("#commodityId").css("border-color","red");
                    }
                })
            }
        });
        //验证商品id唯一
        $("#productId").blur(function () {
            var proid=$("#productId").val();
            if(proid!=null && proid!=""){
                $.post("${pageContext.request.contextPath}/products/selectProid",{"proid":proid},function (data) {
                    if(data=="no"){
                        $("#tips2").css("color","green").html("该id可用");
                        $("#productId").css("border-color","green");
                    } else{
                        $("#tips2").css("color","red").html("id已存在,请重新输入");
                        $("#productId").focus();
                        $("#productId").css("border-color","red");
                    }
                })
            }
        });
    })

</script>
</body>
</html>