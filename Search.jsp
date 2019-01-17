<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>校系心得搜尋系統</title>
</head>
<body background = "img/background1.jpg"> 

<u><font color="red"><font size="7"><font face="標楷體"><p align="left">校系心得搜尋</p></font></font></font></u>
<form action='${requestUri}' method='get'>
<input type='text' name='keyword' placeholder = '請輸入想搜尋的學校系名' />
<input type='submit' value='搜尋' />
</form>
</body>
</html>