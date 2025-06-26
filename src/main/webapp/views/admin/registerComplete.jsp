<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>登録完了</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/registerComplete.css">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Serif+JP&display=swap" rel="stylesheet">
</head>
<body>

	<div class="success-container">
		<h2 class="success-title">商品登録が完了しました</h2>

		<p class="success-message">商品の登録が正常に完了しました。</p>

		<a href="${pageContext.request.contextPath}/views/admin/adminHome.jsp" class="back-link">
			管理者メニューに戻る
		</a>
	</div>

</body>
</html>
