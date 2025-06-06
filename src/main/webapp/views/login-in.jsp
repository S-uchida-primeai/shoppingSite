<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>ログイン画面</h1>
	<!-- LoginActionに送られる -->
	<form action="LoginAction" method="post">
		<p>
			<label>会員番号<input type="text" name="id" required></label>
		</p>
		<p>
			<label>パスワード<input type="password" name="password" required></label>
		</p>
		<button type="submit" name="action" value="login">ログイン</button>
	</form>
	<form action="LoginAction" method="post">
		<button type="submit" name="action2" value="register">新規会員登録</button>
	</form>

</body>
</html>