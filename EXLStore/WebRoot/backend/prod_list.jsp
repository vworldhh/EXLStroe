<%@ page import="cn.tedu.bean.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<title>商品管理</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="${ app }/backend/lib/bootstrap/css/bootstrap.css">
     
    <link rel="stylesheet" href="${ app }/backend/lib/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" href="${ app }/backend/css/index.css"/>

<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<style type="text/css">
	body{
		font-family: "微软雅黑";
		background-color: #EDEDED;
	}
	h2{
		text-align: center;
	}
	table{ 
		margin: 0 auto; 
		/* width: 96%; */
		text-align: center;
		border-collapse:collapse;
	}
	td, th{ padding: 7px;}
	th{
		background-color: #DCDCDC;
	}
	th.ths{
		width: 190px;
	} 
	input.pnum{
		width:80px;
		height:25px;
		font-size: 18px;
		text-align:center;
	}
	
</style>

<!--引入jquery的js库-->
<script src="${ app }/js/jquery-1.4.2.js"></script>
<script type="text/javascript">
	/*修改商品数量*/
	$(function(){
		$(".pnum").blur(function(){
			/*
			1.当输入框失去焦点时没有做任何修改，不应该向服务器发送请求
			2.当输入的数值不是合法数值时，要提示"您输入的数值不合法"
			3.当提示完"您输入的数值不合法"后，将输入框的数值还原上一次的值
			*/
			var _this = this;
			//获取商品新的库存（old）
			var oldPnum = $(this).attr("oldPnum");
			//获取商品新的库存（new）
			var newPnum = $(this).val();
			//获取商品id
			var pid = $(this).attr("id");
			if(oldPnum == newPnum){
				return;
			}
			//校验数据是否合法
			var reg = /^0$|^[1-9][0-9]*$/;
			if(!reg.test(newPnum)){
				alert("您输入的数值不合法！");
				$(this).val(oldPnum);
				return;
			}
			//使用ajax请求异步访问服务器，修改商品库存数量
			$.get("${ app }/servlet/BackProdPnumUpdateServlet", { "pnum" : newPnum, "pid" : pid}, function(result){
				if("true"==result){
					alert("修改成功");
					//修改成功后要及时更新旧的库存
					$(_this).attr("oldPnum", newPnum);
				}else{
					alert("修改失败");
				}
			});
		});
		
		//给所有商品的超链接添加点击事件，点击删除执行删除当前商品的操作。
		$(".del").click(function(){
			//添加确认对话框，确认是否真的删除商品
			if(!window.confirm("您确定要删除该商品吗？")){
				return;
			}
		
			var pid = $(this).parents("tr").find(".pnum").attr("id");
			var _this = $(this);
			//2.利用ajax异步访问服务器来删除指定id的商品
			$.post("${app}/servlet/BackProdDelServlet", {"pid":pid}, function(result){
				//true表示删除成功，false表示删除失败
				if("true" == result){
					alert("删除成功！");
					//同时将商品信息在页面上删除
					$(_this).parents("tr").remove();
				}else{
					alert("删除失败！");
				}
			});
		});
		
	})
</script>
</head>
<body>
	<h2>商品管理</h2>
	<table border="1">
		<tr>
			<th  class="ths">商品图片</th> 
			<th class="ths">商品名称</th>
			<th class="ths">商品种类</th>
			<th class="ths">商品单价</th>
			<th class="ths">库存数量</th>
			<th>描述信息</th>
			<th width="50px">操 作</th>
		</tr>

		<!-- 模版数据 -->
		<c:forEach items="${ list }" var="prod">
		 <tr >
		    <input type="hidden" name="pid" value="${ prod.id }"/>  
		 </tr>
		<tr>
			<td>
				<img width="120px" height="120px" alt="" src="${ app }/servlet/ProdImgServlet?imgurl=${ prod.imgurl }">
			</td>
		 
			<td>${ prod.name }</td>
			<td>${ prod.category }</td>
			<td>${ prod.price }</td>
			<td>
			 <input type="text" id="${ prod.id }" oldPnum="${ prod.pnum }" class="pnum" value="${ prod.pnum }"/>
			
			</td>
			   <td>${ prod.description }</td>
			<td> 
			 
			<c:if test="${ prod.stat=='0'}">
			   <a href="${app}/servlet/BackProdDelServlet?pid=${ prod.id }&stat=1"> 上架 </a>  
			</c:if>
			<c:if test="${ prod.stat=='1'}">
				<a href="${app}/servlet/BackProdDelServlet?pid=${ prod.id }&stat=0"> 下架 </a>  
			</c:if>
              
              
			    
			
			</td>
			
		</tr>
		</c:forEach>
		
	</table>
<center> 
<div class="pagination">
    <ul>
        <li><a href="#"><<</a></li>
        <c:forEach  begin="1"  end ="${allPage }" var="p" step="1">
          <li><a href="${app }/servlet/BackProdListServlet?showpage=${p}">${p }</a></li>
        </c:forEach>
        <li><a href="#">>></a></li>
    </ul>
</div>
</center>
</body>
</html>



