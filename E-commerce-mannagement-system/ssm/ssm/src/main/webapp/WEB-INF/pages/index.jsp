<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>后台管理中心</title>  
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="${pageContext.request.contextPath}/images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />后台管理中心</h1>
  </div>
  <div class="head-l"><a class="button button-little bg-green" href="" target="_blank"><span class="icon-home"></span> 前台首页</a> &nbsp;&nbsp;<a href="${pageContext.request.contextPath}/index.jsp" class="button button-little bg-blue"><span class="icon-wrench"></span> 清除缓存</a> &nbsp;&nbsp;<a class="button button-little bg-red" href="${pageContext.request.contextPath}/index.jsp"><span class="icon-power-off"></span> 退出登录</a> </div>
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>

    <h2><span class="icon-jsfiddle"></span>商品类别管理</h2>
    <ul style="display:block">
        <li><a href="${pageContext.request.contextPath}/tabs/findByPagers" target="right"><span class="icon-caret-right"></span>商品类别列表</a></li>
        <li><a href="${pageContext.request.contextPath}/tabs/tosave" target="right"><span class="icon-caret-right"></span>增加商品类别</a></li>
        </ul>

  <h2><span class="icon-columns"></span>商品管理</h2>
  <ul style="display:block">
    <li><a href="${pageContext.request.contextPath}/products/findByPagers" target="right"><span class="icon-caret-right"></span>商品列表</a></li>
    <li><a href="${pageContext.request.contextPath}/products/tosave" target="right"><span class="icon-caret-right"></span>增加商品</a></li>

  </ul>
    <h2><span class="icon-language"></span>店铺管理</h2>
    <ul style="display:block">
        <li><a href="${pageContext.request.contextPath}/shops/findByPagers" target="right"><span class="icon-caret-right"></span>店铺列表</a></li>
        <li><a href="${pageContext.request.contextPath}/shops/tosave" target="right"><span class="icon-caret-right"></span>增加店铺</a></li>
    </ul>

    <h2><span class="icon-delicious"></span>统计管理</h2>
    <ul style="display:block">
        <li><a href="${pageContext.request.contextPath}/tabs/list" target="right"><span class="icon-caret-right"></span>商品类别统计</a></li>
        <li><a href="${pageContext.request.contextPath}/details/toselect" target="right"><span class="icon-caret-right"></span>店铺商品查询</a></li>
    </ul>

</div>
<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>
<ul class="bread">
    <li><a href="${pageContext.request.contextPath}/index.jsp" target="right" class="icon-home"> 首页</a></li>
    <li>欢迎您，尊敬的<span style="color: #03b6fd">${user}</span>管理员！</li>
    </li>
</ul>
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="${pageContext.request.contextPath}/welcome.jsp" name="right" width="100%" height="100%"></iframe>
</div>
</body>
</html>