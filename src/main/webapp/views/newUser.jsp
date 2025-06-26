<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
  // セッションから値を取得
  String id = (String) session.getAttribute("reg_id");
  String password = (String) session.getAttribute("reg_password");
  String lastName = (String) session.getAttribute("reg_last_name");
  String firstName = (String) session.getAttribute("reg_first_name");
  String address = (String) session.getAttribute("reg_address");
  String email = (String) session.getAttribute("reg_email");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規会員登録</title>
<link rel="stylesheet" href="../css/userLogin.css">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Serif+JP&display=swap"
	rel="stylesheet">
</head>
<body>
	<div class="logo-container">
		<img src="../img/nike-tiempo-2026-maestro-1.jpg" alt="TIEMPOロゴ"
			class="logo">
		<p class="subtitle">新規会員登録</p>
	</div>
	<form action="newUserConfirm" method="post">
		<p>
			<label>ID：<input type="text" name="id" value="<%= id != null ? id : "" %>" required></label>
		</p>
		<p>
			<label>パスワード：<input type="password" name="password" value="<%= password != null ? password : "" %>" required></label>
		</p>
		<p>
			<label>名前_姓：<input type="text" name="lastName" value="<%= lastName != null ? lastName : "" %>" required></label>
		</p>
		<p>
			<label>名前_名：<input type="text" name="firstName" value="<%= firstName != null ? firstName : "" %>" required></label>
		</p>
		<p>
			<label>住所：<input type="text" name="address" value="<%= address != null ? address : "" %>" required></label>
		</p>
		<p>
			<label> メースアドレス：<input type="email" name="email" value="<%= email != null ? email : "" %>" required></label>
		</p>
		<button type="submit">確認</button>
		<button type="reset">リセット</button>
		<p>
			<a href="login-in.jsp" class="back-link">ログイン画面に戻る</a>
		</p>
	</form>


</body>
</html>