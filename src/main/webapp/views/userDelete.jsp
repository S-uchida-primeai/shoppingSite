<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員削除確認</title>
</head>
<body>
	<h1>削除画面</h1>
	<form action="UserDeleteServlet" method="post">
		<p>会員情報を削除しますか？</p>
		<button type="submit" name="confirm" value="yes">はい</button>
		<button type="submit" name="confirm" value="no">いいえ</button>
	</form>
	


</body>
</html>