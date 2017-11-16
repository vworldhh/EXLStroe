<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>确认支付页面</title>
	<link href="${ app }/css/confirm.css" rel="stylesheet" type="text/css">
	<link href="${ app }/css/pay.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="/_head.jsp" %>
<!-- 确认支付form -->
<div id="warp">
	<div>
		<h3>
			订单号：${p2_Order}
			<br>
			<br>
			<br>
			<br>
			
			
			付款金额 ：${p3_Amt}
		</h3> 
		<dd class="ok_dd">
		       
				<a href="${app }/servlet/okServlet?oid=${p2_Order}"> <input  type="button" class="ok_pay" value="确认支付" > </a>
		</dd>
	</div>
</div>
<%@include file="/_foot.jsp" %>
</body>
</html>