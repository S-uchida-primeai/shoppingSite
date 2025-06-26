<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link rel="stylesheet" href="../css/userLogin.css">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Serif+JP&display=swap"
	rel="stylesheet">
</head>
<body>

	<div class="logo-container">
		<img src="../img/nike-tiempo-2026-maestro-1.jpg" alt="TIEMPOロゴ"
			class="logo">
		<p class="subtitle">ログイン</p>
	</div>

	<c:if test="${not empty errorMsg}">
		<p class="error-message">${errorMsg}</p>
	</c:if>

	<!-- LoginActionに送られる -->
	<form action="LoginAction" method="post">
		<p>
			<label>会員番号<input type="text" name="id" value="${enteredId}" required></label>
		</p>
		<p>
			<label>パスワード<input type="password" name="password" required></label>
		</p>
		<button type="submit" name="action" value="login">ログイン</button>
	</form>

	<form action="LoginAction" method="post">
		<p class="register-text">初めての方は、こちらから会員登録できます。</p>
		<button type="submit" name="action2" value="register">会員登録はこちら</button>
	</form>

</body>
</html>