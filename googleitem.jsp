<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>校系心得搜尋系統</title>
</head>
<body>
<%
String[][] orderList = (String[][])  request.getAttribute("query");
for(int i =0 ; i < orderList.length;i++){%>
	<a href='<%= orderList[i][1] %>'><%= orderList[i][0] %></a>
	<br>
	<font color="green">
	<h style="font-size:10px ;" color="green" >
	<%= orderList[i][1] %>
	</h>
	</font>
	<br>
	<br>
<%
}
%>
</body>
</html>