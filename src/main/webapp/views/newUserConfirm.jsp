<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規会員登録 確認</title>
<link rel="stylesheet" href="../css/userLogin.css">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Serif+JP&display=swap"
	rel="stylesheet">
</head>
<body>
	<div class="logo-container">
		<img src="../img/nike-tiempo-2026-maestro-1.jpg" alt="TIEMPOロゴ"
			class="logo">
		<p class="subtitle">新規会員登録 確認</p>
	</div>


	<form action="newuseraction" method="post">
		<p>ID: ${id}</p>
		<p>パスワード: ********</p>
		<!-- パスワードは非表示にすることも -->
		<p>名前_姓: ${lastName}</p>
		<p>名前_名: ${firstName}</p>
		<p>住所: ${address}</p>
		<p>メールアドレス: ${email}</p>

		<!-- 登録用に隠しフィールドに値をセット -->
		<input type="hidden" name="id" value="${id}"> <input
			type="hidden" name="password" value="${password}"> <input
			type="hidden" name="lastName" value="${lastName}"> <input
			type="hidden" name="firstName" value="${firstName}"> <input
			type="hidden" name="address" value="${address}"> <input
			type="hidden" name="email" value="${email}">

		<div class="button-group">
			<button type="submit">登録確定</button>
		</div>
	</form>

	<form action="newUser.jsp" method="get">
		<div class="button-group">
			<button type="submit">戻る</button>
		</div>
	</form>
</body>
</html>