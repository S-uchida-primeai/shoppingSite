<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規会員登録</title>
</head>
<body>
	<h1>新規会員登録</h1>
	<form action="newuseraction" method="post">
		<p><label>会員番号：<input type="text" name="id" required></label></p>
		<p><label>パスワード：<input type="password" name="password" required></label></p>
		<p><label>名前_姓：<input type="text" name="lastName" required></label></p>
		<p><label>名前_名：<input type="text" name="firstName" required></label></p>
		<p><label>住所：<input type="text" name="address" required></label></p>
		<p><label> メースアドレス：<input type="email" name="email" required></label></p>
		<button type="submit">登録</button>
		<button type="reset">リセット</button>
	</form>
	
	<a href="login-in.jsp">ログイン画面に戻る</a>
</body>
</html>