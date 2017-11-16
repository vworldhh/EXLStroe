<%@ page import="cn.tedu.bean.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<link rel="stylesheet" href="${ app }/css/head.css"/>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />

<script type="text/javascript" src="${ app }/js/jquery-1.4.2.js"></script>
<script type="text/javascript">
	$(function(){
		$("#search_btn").click(function(){
			var search = $(this).prev("input").val();
			window.location.href = "${ app }/servlet/ProdListBySearchServlet?search="+search;
		});
		  
	});
	
	
	
</script>



<div id="common_head">
	<div id="line1">
		<div id="content"> 
		 
			<c:if test="${empty sessionScope.user }">
				<a href="${ app }/login.jsp">登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="${ app }/regist.jsp">注册</a>
			</c:if>
			<c:if test="${not empty sessionScope.user }">
				欢迎 ${sessionScope.user.username } 回来，&nbsp;<a href="${ app }/servlet/LogoutServlet">退出</a>
			</c:if>&nbsp;&nbsp; 
			<c:if test="${sessionScope.user.role=='admin'}"> 
		  	 |&nbsp;&nbsp;<a href="${ app }/backend/manage.jsp">后台</a>
			</c:if>
		</div>
	</div>
	<div id="line2">
		<img id="logo" src="${ app }/img/head/logo.jpg"/>
		<input type="text" name="search"/>
		<input id="search_btn" type="button" value="搜 索"/>
		<span id="goto">
		 
			<a id="goto_order"  href="${ app }/servlet/OrderListServlet">我的订单</a>
		 
			<a id="goto_cart" href="${ app }/cart.jsp">我的购物车</a>
		</span>
		<img id="erwm" src="${ app }/img/head/qr.jpg"/>
	</div>
	<div id="line3">
		<div id="content">
			<ul>
				<li><a href="${ app }/index.jsp">首页</a></li>
				<li><a href="${ app }/servlet/ProdListByConditionServlet">全部商品</a></li>
				<li><a href="#">精装护肤</a></li>
				 
				<li><a href="#">包包天堂</a></li>
				 
				<li><a href="#">美食街</a></li>
				 
				<li><a href="#">华丽服装</a></li>
				 
			</ul>
		</div>
	</div>
</div>