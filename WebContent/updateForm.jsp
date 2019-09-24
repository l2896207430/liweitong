<%@page import="entity.Student"%>
<%@page import="daoimpl.StudentDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="StudentServlet?opType=update" method="post">
		<input type="hidden" name="id" value="${student.id }" /> 
		<input type="hidden" name="currentPage" value="${currentPage }" /> 
		姓名:<input type="text" name="name" value="${student.name }" /><br /> 
		年龄:<input type="text" name="age" value="${student.age }" /><br />
		性别:<input type="radio" name="sex"  value="1"<c:if test="${student.sex==1 }">checked="checked"</c:if> /> 男
		    <input type="radio" name="sex"  value="0"<c:if test="${student.sex==0 }">checked="checked"</c:if> /> 女<br />
		帐号:<input type="text" name="username" value="${student.username }" /><br />
		密码:<input type="text" name="password" value="${student.password }" /><br />
		出生日期:<input type="text" name="brithday" value="${student.brithday }" /><br />
		<input type="submit" value="修改" /> <input type="reset" value="重置" />

	</form>
</body>
</html>