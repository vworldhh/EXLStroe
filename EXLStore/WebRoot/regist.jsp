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
    
    <script type="text/javascript">
    $(function(){
        $("input[name='username']").blur(function () {
            if (!formObj.checkNull("username","用户名不能为空")){
                return;
            }
            var username = $(this).val();
	        $.post("${app}/servlet/AjaxCheckUsernameServlet",{"username":username},function (result) {
	            $("#username_msg").html(result);
	        });
        });
        $("input[name='email']").blur(function () {
            if (!formObj.checkNull("email", "邮箱不能为空")) {
                return;
            }
            var email = $(this).val();
            $.post("${app}/CheckUserNameServlet", {"email": email}, function (result) {
               alert(result);
                $("#email_msg").html(result);
                  
            });
        });




        $("input[name=username]").blur(function(){

            var val = $(this).val();

            if(!formObj.checkNull("username","用户名不能为空")){

            }else{

             //  $("#username_msg").html("<img src='${app}/img/regist/right.ico'>");
            }
        });

        $("input[name=password]").blur(function(){
            var val = $(this).val();

            if(!formObj.checkNull("password","密码不能为空")){

            }else{
                if(!formObj.checkPassword("password","两次密码不相同")){

                }else{
                    $("#password_msg")
                        .html("<img src='${app}/img/regist/right.ico'>");


                    var pass2 = $("input[name=password2]").val();

                    if(pass2){
                        $("#password2_msg")
                            .html("<img src='${app}/img/regist/right.ico'>");
                    }


                }
            }
        });
        $("input[name=password2]").blur(function(){
            if(!formObj.checkNull("password","密码不能为空")){

            }else{
                if(!formObj.checkPassword2("password","两次密码不相同")){

                }else{
                    $("#password2_msg")
                        .html("<img src='${app}/img/regist/right.ico'>");
                    $("#password_msg")
                        .html("<img src='${app}/img/regist/right.ico'>");
                }
            }

        });
        $("input[name=email]").blur(function(){
            var val = $(this).val();

            if(!formObj.checkNull("email","邮箱不能为空")){

            }else{
                if(!formObj.checkEmail("email","邮箱格式不正确")){
                }else{
                    $("#email_msg")
                        .html("<img src='${app}/img/regist/right.ico'>");
                }
            }


        })
        $("input[name=nickname]").blur(function(){
            var val = $(this).val();
            if(!formObj.checkNull("nickname","手机号码不能为空")){

            }else{ 
                    $("#phone_msg")
                        .html("<img src='${app}/img/regist/right.ico'>");
               } 
        });



    });
    var formObj={
        checkForm:function(){
            var flag=true;
            //非空验证
            flag = this.checkNull("username","用户名不能为空!");
            flag = this.checkNull("password","密码不能为空")&&flag;
            flag = this.checkNull("password2","确认密码不能为空")&&flag;

            flag = this.checkNull("email","邮箱不能为空")&&flag;

            //两次输入的密码是否相同
            flag = this.checkPassword("password","两次密码不相同")&&flag;
            //邮箱格式
            flag = this.checkEmail("email","邮箱格式不正确")&&flag;
            //验证电话号码
            flag = this.checknickname("nickname", "手机号格式不正确")&&flag;
            //返回flag
            return flag;
        },
        checkNull:function(name,msg){
            var value=$("input[name="+name+"]").val();
            if($.trim(value)==""){
                this.setMsg(name,msg);
                return false;
            }
            return true;
        },
        checkPassword:function(name,msg){
            var pwd=$("input[name="+name+"]").val();
            var pwd2=$("input[name="+name+"2]").val();
            if($.trim(pwd)!=""&&$.trim(pwd2)!=""){
                if(pwd!=pwd2){
                    this.setMsg(name,msg);
                    return false;
                }
            }
            return true;
        },
        checkPassword2:function(name,msg){
            var pwd=$("input[name="+name+"]").val();
            var pwd2=$("input[name="+name+"2]").val();
            if($.trim(pwd)!=""&&$.trim(pwd2)!=""){
                if(pwd!=pwd2){
                    this.setMsg(name+"2",msg);
                    return false;
                }
            }
            return true;
        },

        checkEmail:function(name,msg){
            var value=$("input[name="+name+"]").val();
            var reg=/^\w+@\w+(\.\w+)+$/;
            if(!reg.test(value)){
                this.setMsg(name,msg);
                return false;
            }
            return true;
        },

//         checkphone:function(name, msg){
//             var value=$("input[name="+name+"]").val();
//             var reg = /^1[3|4|5|8][0-9]\d{4,8}$/;
//             if(!reg.test(value)){
//                 this.setMsg(name,msg);
//                 return false;
//             }
//             return true;
//         },

        setMsg:function(name,msg){
            $("#"+name+"_msg").text(msg);
        }
    };

    function checkbox_1(){
        //获取button
        var butn = $("input[type='checkbox']");
        if($("input[type='checkbox']").is(':checked')){
            //被选中
            alert(butn);


        }else{

        }
    }

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
    <br>
   

        <div class="" id="login-wrapper">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div id="logo-login">
                        <h2> 
                           SingUP
                        </h2>
                           <font color="red">
				              ${msg }
				          </font>
                    </div>
                </div>

            </div>

            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="account-box"> 
                        <form role="form" action="${app}/servlet/RegistServlet" method="post">
                            <div class="form-group">
                               
                                <label for="inputUsernameEmail">用户名</label>
                                <input type="text" id="inputUsernameEmail" class="form-control" name="username"/>
                                <span id ="username_msg" style="color:red"></span> 
                            </div>
                             <div class="form-group">
                                 
                                <label for="inputPassword">密&nbsp;&nbsp;码:</label>
                                <input type="password" id="inputPassword" class="form-control" name="password" />
                                <span id ="password_msg" style="color:red"></span>
                            </div>
                             <div class="form-group">
                                <!--a href="#" class="pull-right label-forgot">Forgot password?</a-->
                                <label for="inputPassword">确认密码:</label>
                                <input type="password" id="inputPassword" class="form-control" name="password2" />
                                 <span id="password2_msg" style="color:red"></span>
                            </div>
                              <div class="form-group"> 
                                <label for="inputUsernameEmail">昵&nbsp;&nbsp;称:</label>
                                <input type="text" id="inputUsernameEmail" class="form-control" name="nickname" />
                                <span id="phone_msg" style="color:red;"></span>
                            </div>
                             
                             <div class="form-group"> 
                                <label for="inputUsernameEmail">邮&nbsp;&nbsp;箱:</label>
                                <input type="text" id="inputUsernameEmail" class="form-control" name="email" />
                                <span id="email_msg" style="color:red"></span>
                            </div>
                           
                            
                           <a href="${app }/login.jsp">登录</a>
                            <button class="btn btn btn-primary pull-right" type="submit">
                                                                                          注册
                            </button>
                        </form>
                        
                    </div>
                </div>
            </div>
        </div>
        

 		<p>&nbsp;</p>
         

    </div>
    

 


</body>

</html>
