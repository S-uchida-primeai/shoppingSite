<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者メニュー</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/adminHome.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Serif+JP&display=swap" rel="stylesheet">
</head>
<body>

	<div class="account-container">
		<h2 class="account-title">管理者メニュー</h2>

		<div class="account-actions">
			<a href="adminAddProduct.jsp" class="action-link">商品を登録する</a>
			<a href="${pageContext.request.contextPath}/admin/AdminProductListServlet" class="action-link">商品一覧を見る</a>
			<a href="${pageContext.request.contextPath}/admin/AdminUserList" class="action-link">ユーザー一覧を見る</a>

			<form action="${pageContext.request.contextPath}/views/Logout" method="get">
				<button type="submit" class="logout-button">ログアウト</button>
			</form>
		</div>
	</div>

</body>
</html>
