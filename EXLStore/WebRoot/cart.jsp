<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
	<head>
		<link href="${ app }/css/cart.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<script type="text/javascript" src="${ app }/js/jquery-1.4.2.js"></script>
		<script type="text/javascript">
			//文档就绪事件
			$(function(){
				//减号
				$(".delNum").click(function(){
					var $buyNumInp = $(this).siblings("input");//siblings获取它的兄弟元素
					var buyNum = $buyNumInp.val();
					var newBuyNum = buyNum;
					if(buyNum>1){
						newBuyNum = buyNum - 1;
						$buyNumInp.val(newBuyNum);
					}
					//ajax将session中的cartmap数量减一
					var pid = $buyNumInp.attr("id");
					$.post("${ app }/servlet/AjaxUpdateByNumServlet", {"pid":pid, "buyNum":newBuyNum});
					
					totalMoney(this, newBuyNum);
				});
				//加号
				$(".addNum").click(function(){
					var $buyNumInp = $(this).siblings("input");
					var buyNum = $buyNumInp.val();
					var newBuyNum = parseInt(buyNum) + 1;
					$buyNumInp.val(newBuyNum);
					var pid = $buyNumInp.attr("id");
					$.post("${ app }/servlet/AjaxUpdateByNumServlet", {"pid":pid, "buyNum":newBuyNum});
					
					totalMoney(this, newBuyNum);
				});
				
				//重新计算总金额
				function totalMoney(thisobj, newBuyNum){
					var price = $(thisobj).parents("ul").find(".li_price").text();
					var prod_sum = price * newBuyNum;
					$(thisobj).parents("ul").find(".sum_price").text(prod_sum)
					var totalMoney = 0;
					$(".sum_price").each(function(){
						totalMoney += parseFloat($(this).text());
					});
					
					$("#span_2").text(totalMoney)
				}
				
				
				//删除按钮点击事件(删除当前商品同时也在购物车中删除)
				$(".delProd").click(function(){
					//1.获取商品的id
					var pid = $(this).attr("id");
					//2.利用ajax请求删除购物车中指定的id的商品
					$.post("${ app }/servlet/CartUpdateServlet", {"pid":pid, "buyNum":-1});
					//3.删除当前页面的商品
					$(this).parents("ul").remove();
					
					var totalMoney = 0;
					$(".sum_price").each(function() {
						totalMoney += parseFloat($(this).text());
					});
					
					//设置所有商品的总价
					$("#span_2").text(totalMoney);
					
				});
				
			});
			
			
		</script>
	</head>
	<body>
		<%@include file="/_head.jsp" %>
		<c:if test="${empty cartmap }">
		  <center> <h2> <span style="color:red;"> 祸害你的购物车已经空了， 有时间多去逛逛 </span></h2> </center>
		</c:if>
		<c:if test="${not empty cartmap }"> 
		<div id="wrap">
			<!-- 标题信息 -->
			${ msg }
			<ul id="title">
				<li>
					<input name="allC" class="allC" type="checkbox" value="" onclick=""/>
					<span id="title_checkall_text">全选</span>
				</li>
				<li class="li_prod">商品</li>
				<li>单价（元）</li>
				<li>数量</li>
				<li>小计（元）</li>
				<li>操作</li>
			</ul>

			<!-- 购物信息 -->
			<c:set var="totalMoney" value="0"></c:set>
			<c:forEach items="${ cartmap }" var="entry">
				<ul class="prods">
					<li>
						<input type="checkbox" class="prodC" name="prodC"/> 
					</li>
					<li class="li_prod">
						<img src="${ app }/servlet/ProdImgServlet?imgurl=${ entry.key.imgurl }" width="90" height="90" class="prodimg" />
						<span class="prodname">${ entry.key.name }</span>
					</li>
					<li class="li_price">${ entry.key.price }</li>
					<li>
						<a href="javascript:void(0)" class="delNum" >-</a>
						<input class="buyNumInp" id="${ entry.key.id }" type="text" value="${ entry.value }" >
						<a href="javascript:void(0)" class="addNum" >+</a>
					</li>
					<li class="sum_price">${ entry.key.price * entry.value }</li>
					<li><a id="${ entry.key.id }" class="delProd" href="javascript:void(0)">删除</a></li>
				</ul>
				<c:set var="totalMoney" value="${ totalMoney + entry.key.price * entry.value }"></c:set>
			</c:forEach>
			<!-- 总计条 -->
			<div id="total">
				<div id="total_1">
					<input type="checkbox" class="allC" name="allC"/> 
					<span>全选</span>
					<a id="del_a" href="javascript:void(0)">删除选中的商品</a>
					<div id="div_sum">
						<span id="span_1">总价：</span>
						<span>￥</span>
						<span id="span_2" class="total_sum_price">${ totalMoney }</span>
					</div>
				</div>
				<div id="total_2">	
					<a id="goto_order" href="${ app }/order_add.jsp">去结算</a>
				</div>
			</div>
		</div>
		</c:if>
		<%@include file="/_foot.jsp" %>
	</body>
</html>