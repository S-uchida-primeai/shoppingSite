<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規会員登録</title>
</head>
<body>
	<form action="<%= request.getContextPath() %>/views/newuseraction" method="post">
		<p><label>会員番号 :<input type="text" name="id"></label></p>
		<p><label>パスワード :<input type="text" name="password"></label></p>
		<p><label>名前_姓 :<input type="text" name="lastName"></label></p>
		<p><label>名前_名 :<input type="text" name="firstName"></label></p>
		<p><label>住所 :<input type="text" name="address"></label></p>
		<p><label> :<input type="email" name="email"></label></p>
		<button type="submit">登録</button>
	</form>

</body>
</html>