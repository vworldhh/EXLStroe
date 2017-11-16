<%@ page import="cn.tedu.bean.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<link href="${ app }/css/prodInfo.css" rel="stylesheet" type="text/css">
	<script src="${ app }/js/jquery-1.4.2.js"></script>
	<script type="text/javascript">
		$(function(){
			//为（-）减号添加点击事件，实现购买数量减一
			$("#delNum").click(function(){
				var $buyNumInp = $("#buyNumInp");
				var buyNum = $buyNumInp.val();
				if(buyNum>1){
					$buyNumInp.val(parseInt(buyNum)-1);
				}
			});
			//为（+）加号添加点击事件，实现购买数量加一
			$("#addNum").click(function(){
				var $buyNumInp = $("#buyNumInp");
				var buyNum = $buyNumInp.val();
				$buyNumInp.val(parseInt(buyNum)+1);
			});
			
			/* 验证购买数量是否合法
			 * 正则表达式为：^[1-9][0-9]*$ 
			 */
			$("#buyNumInp").blur(function(){
				var reg = /^[1-9][0-9]*$/;
				//获取购买数量
				var buyNum = $(this).val();
				if(!reg.test(buyNum)){
					alert("您输入的购买数量不合法！");
					$(this).val(1);
					return;
				}
			});
		});
	</script>
</head>
<body>
<%@include file="/_head.jsp" %>
	<div id="warp">
		<div id="left">
			<div id="left_top">
				<!-- 商品大图 -->
				<img src="${ app }/servlet/ProdImgServlet?imgurl=${ prod.imgurl }"/>
			</div>
			<div id="left_bottom">
				<img id="lf_img" src="${ app }/img/prodInfo/lf.jpg"/>
				<img id="mid_img" src="${ app }/servlet/ProdImgServlet?imgurl=${ prod.imgurl }" width="60px" height="60px"/>
				<img id="rt_img" src="${ app }/img/prodInfo/rt.jpg"/>
			</div>
		</div>
	<form action="${ app }/servlet/CartUpdateServlet"  method="post">
		<div id="right">
			<div id="right_top">
				<span id="prod_name">${ prod.name } <br/></span>
				<br>
				<span id="prod_desc">${ prod.description }<br/></span>
			</div>
			<div id="right_middle">
				<span id="right_middle_span">
					EasyMall 价：
				<span class="price_red">${ prod.price }
				<br/>
			    运&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费：满 100 免运费<br />
			    服&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务：由EasyMall负责发货，并提供售后服务<br />
			    购买数量：
	            <a href="javascript:void(0)" id="delNum" onclick="">-</a>
	            <input type="text" id="buyNumInp" name="buyNum" value="1">
		        <a href="javascript:void(0)" id="addNum" onclick="">+</a>
			</div>
			<div id="right_bottom">
				<input type="hidden" name="pid" value="${ prod.id }"/>
				<input class="add_cart_but" type="submit" value=""/>	
			</div>
		</div>
	</form>
	</div>
<%@include file="/_foot.jsp" %>
</body>
</html>