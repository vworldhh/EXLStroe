<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Bootstrap Admin</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="${ app }/backend/lib/bootstrap/css/bootstrap.css">
     
    <link rel="stylesheet" href="${ app }/backend/lib/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" href="${ app }/backend/css/index.css"/>

    <script src="${ app }/backend/lib/jquery-1.7.2.min.js" type="text/javascript"></script>

    <!-- Demo page code -->

    <style type="text/css">
        #line-chart {
            height:300px;
            width:400px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>

     
  </head>
  
    <div class="content">
        
        <div class="header">
            
            <h4 class="page-title">用户列表</h4>
        </div>
        
                <ul class="breadcrumb">
            <li>主页<span class="divider">/</span></li>
            <li class="active">用户列表</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    
 <c:if test="${empty userList }">
       <center><h4 style="color:red;">您的后台当前没用用户</h4></center>
 </c:if>      
  <c:if test="${not empty userList }">            
	<div class="btn-toolbar">
	    <button class="btn btn-primary"><i class="icon-plus"></i> 新增用户</button>
	    
	  <div class="btn-group">
	  </div>
	</div>
 
 
	<div class="well">
	    <table class="table">
	      <thead>
	        <tr> 
	          <th>用户名</th>
	          <th>身份</th> 
	          <th>昵称</th>
	          <th>邮箱</th> 
	          <th>状态</th>
	          <th style="width: 20px;"></th>
	        </tr>
	      </thead>
	      <tbody> 
		     <c:forEach items="${ userList }" var="user">
		        
		        <tr>
		         <input type="hidden" name="pid" value="${user.id}"/>  
		          <td>${user.username} </td>
		          <td>${user.role}</td>
		          <td>${user.nickname}</td>
		          <td>${user.email}</td>
		          <td>
		              <a href="${app }/backend/user.jsp"><i class="icon-pencil"></i></a>
		              <a href="${app }/servlet/DeleteUserServlet?pid=${user.id}" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
		          </td>
		        </tr>
		       </c:forEach> 
	      </tbody>
	    </table>
	</div> 
</c:if>  
<div class="pagination">
    <ul>
        <li><a href="#">上一页</a></li>
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">下一页</a></li>
    </ul>
</div>
    
  </body>
</html>


