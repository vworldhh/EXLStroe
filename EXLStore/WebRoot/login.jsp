<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>

<head>
    <meta charset="utf-8">
    <title>ELXStore</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    
    <script type="text/javascript" src="${app}/assets/js/jquery.min.js">
    
    </script>
 
    <link rel="stylesheet" href="${app}/assets/css/loader-style.css">
    <link rel="stylesheet" href="${app}/assets/css/bootstrap.css">
    <link rel="stylesheet" href="${app}/assets/css/signin.css"> 
    <link rel="shortcut icon" href="${app}/assets/ico/minus.png">
    <script type="text/javascript" src="${app}/js/jquery-1.4.2.js"></script>
	<script type="text/javascript">
	$(function(){
		var uname = "${cookie.remname.value }";
		$("#username").val(decodeURI(uname));
	});
	</script>
	 
 
</head>

<body> 
    <!-- Preloader -->
    <div id="preloader">
        <div id="status">&nbsp;</div>
    </div>
    
    <div class="container"> 
    <br>
    <br>
    <br>
    <br>
    

        <div class="" id="login-wrapper">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div id="logo-login">
                        <h2> 
                           SingIN
                              
                        </h2>
                        <div colspan="2" style="color:red;text-align:center">
						   ${ msg }
					   </div>
                    </div>
                </div>

            </div>

            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="account-box"> 
                        <form role="form" action="${app}/servlet/LoginServlet" method="post">
                            <div class="form-group"> 
                                <label for="inputUsernameEmail">用户名:</label> 
                                <input type="text" class="form-control" id="username" name="username" value=" "/>
                                
                            </div>
                            <div class="form-group">
                                
                                <label for="inputPassword">密&nbsp;&nbsp; 码:</label>
                                <input type="password"  class="form-control" name="password" value=""/>
                                
                            </div>
                            <div class="checkbox pull-left">
                                <label>
                                    <input type="checkbox" name="remname" value="true">记住用户名</label>
                            </div>
                           
                            <button class="btn btn btn-primary pull-right" type="submit">
                                                                                          登 录
                            </button>
                        </form>
                         
                       
                       
                        <div class="row-block">
	                        <div class="row">
		                    </div>
		                      <a href="${app }/regist.jsp">注册</a>
                        </div>
                    </div>
                </div>
            </div>
           
        </div>
         

 	 

    </div>
   


</body>

</html>
