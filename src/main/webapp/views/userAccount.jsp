<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント画面</title>
<link rel="stylesheet" href="../css/userAccount.css">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Serif+JP&display=swap"
	rel="stylesheet">
</head>
<body>
	<div class="account-container">
		<h2 class="account-title">アカウント情報</h2>

		<div class="account-actions">
			<a href="userEdit.jsp" class="action-link">情報を修正する</a> <a
				href="userDelete.jsp" class="action-link">アカウントを削除する</a>

			<form action="Logout" method="get">
				<button type="submit" class="logout-button">ログアウト</button>
			</form>

			<form action="LoginAction" method="post">
				<input type="hidden" name="action" value="backTop">
				<button type="submit" class="back-button">トップページに戻る</button>
			</form>
		</div>
	</div>

</body>
</html>