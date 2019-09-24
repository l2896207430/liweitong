<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生列表</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/myjs.js"></script>
<script type="text/javascript">
	$(function(){
		toPage('first');
	});
	function toPage(sp){
		var page = $("#sp").html();
		var pageCounts = $("#pageCounts").html();
		if(page.trim().length!=0){
			page = parseInt(page);
		}else{
			page = 1;
		}
		switch(sp){
		case "first":
			page = 1;
			break;
		case "pre":
			page = page - 1;
			break;
		case "next":
			page = page + 1;
			break;
		case "last":
			page = pageCounts;
			break;
		default:
			page = sp;
			break;
		}
		$.ajax({
			url:"StudentServlet",
			type:"post",
			dataType:"json",
			data:"opType=queryByPage&currentPage="+page,
			success:function(result){
				$("#totals").html(result.totals);
				$("#pageCounts").html(result.pageCounts);
				$("#sp").html(result.sp);
				//清空表格内容
				$("#myTable").empty();
				//添加表格表头行
				$("#myTable").append("<tr>"
						+"<th><input type=\"checkbox\" id=\"all\" onchange=\"checkAll();\"/></th>"
						+"<th>编号</th>"
						+"<th>姓名</th>"
						+"<th>账号</th>"
						+"<th>密码</th>"
						+"<th>性别</th>"
						+"<th>年龄</th>"
						+"<th>生日</th>"
						+"<th>创建时间</th>"
						+"<th>操作&nbsp;<a href=\"javascript:void(0);\" onclick=\"deleteMore("+result.sp+");\">批量删除</a></th>"
						+"</tr>");
				//添加表格主体数据
				var array = result.list;
				for(var i=0;i<array.length;i++){
					var student = array[i];
					$("#myTable").append("<tr onmouseover=\"mouseOver(this);\" onmouseout=\"mouseOut(this);\">"
					+"<td><input type=\"checkbox\" name=\"student\" value=\""+student.id+"\"/></td>"
					+"<td>"+student.id+"</td>"
					+"<td>"+student.name+"</td>"
					+"<td>"+student.username+"</td>"
					+"<td>"+student.password+"</td>"
					+"<td>"+(student.sex==1?"男":"女")+"</td>"
					+"<td>"+student.age+"</td>"
					+"<td>"+student.brithday+"</td>"
					+"<td>"+student.creaTime+"</td>"
					+"<td>"
					+"<a href=\"StudentServlet?opType=queryById&id="+student.id+"&currentPage="+result.sp+"\">修改</a>"
					+"|"
					+"<a href=\"javascript:void(0);\" onclick=\"deleteById("+result.sp+","+student.id+");\">删除</a>"
					+"</td>"
					+"</tr>");
				}
			}
		});
	}

</script>
</head>
<body>
		<center>
		欢迎【${s.name }】&nbsp;&nbsp;<a href="StudentServlet?opType=logout">注销用户</a>
		<table id="myTable" border="1">
			
		</table>
		总记录数：<span id="totals"></span>&nbsp;&nbsp;
		总页数：<span id="pageCounts"></span>&nbsp;&nbsp;
		当前页：<span id="sp"></span>&nbsp;&nbsp;
		<a href="javascript:void(0);" onclick="toPage('first');">首页</a>&nbsp;&nbsp;
		<a href="javascript:void(0);" onclick="toPage('pre');">上页</a>&nbsp;&nbsp;
		<a href="javascript:void(0);" onclick="toPage('next');">下页</a>&nbsp;&nbsp;
		<a href="javascript:void(0);" onclick="toPage('last');">末页</a>&nbsp;&nbsp;
	</center>
</body>
</html>