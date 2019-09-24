<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript">
     function checkUserName(){
    	 if($("input[name='username']").val()!=null){
    		 $.ajax({
        		 url:"StudentServlet?opType=checkUsername",
        		 type:"post", 
        		 data:"username="+$("input[name='username']").val(),
        		 dataType:"json",
        		 success:function(result){
        			 if (result.code==1) {
    					$("#usernameMsg").css("color","green");
    				}else{
    					$("#usernameMsg").css("color","red");
    				}
        			$("#usernameMsg").html(result.msg);
        		 }
        	 }); 
    	 }
     }

</script>
</head>
<body>
    <form action="StudentServlet?opType=add" method="post">
                姓名:<input type="text" name="name" /><br/>
                帐号:<input type="text" name="username" onblur="checkUserName();"/><span id="usernameMsg"></span><br/>
                密码:<input type="text" name="password" /><br/>
                年龄:<input type="text" name="age" /><br/>
      <input type="radio" name="sex" value="1" />男&nbsp;<input type="radio" name="sex" value="0" />女<br/>
                出生日期:<input type="text" name="brithday" /><br/>
      <input type="submit" value="注册" />&nbsp;&nbsp;
      <input type="reset" value= "重 置" />&nbsp;&nbsp;
               已有账号?<a href="login.jsp">点击进行登录</a>
    </form>
</body>
</html>