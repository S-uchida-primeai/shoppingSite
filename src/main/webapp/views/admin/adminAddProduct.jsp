<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録フォーム</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/adminAddProduct.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Serif+JP&display=swap" rel="stylesheet">
</head>
<body>

	<div class="form-container">
		<h2 class="form-title">商品登録フォーム</h2>

		<form action="${pageContext.request.contextPath}/ConfirmProduct" method="post" class="product-form">
			<label for="name">商品名：</label>
			<input type="text" name="name" id="name" required>

			<label for="price">価格：</label>
			<input type="number" name="price" id="price" required>

			<label for="image">画像ファイル名：</label>
			<input type="text" name="image" id="image" required>

			<label for="release_date">発売日（YYYY-MM-DD）：</label>
			<input type="date" name="release_date" id="release_date" required>

			<h3 class="stock-title">サイズごとの在庫数</h3>
			<table class="stock-table">
				<tr>
					<th>サイズ</th>
					<th>在庫数</th>
				</tr>
				<%
					double[] sizes = {25.0, 25.5, 26.0, 26.5, 27.0, 27.5, 28.0};
					for (double size : sizes) {
				%>
				<tr>
					<td><%= size %> cm</td>
					<td><input type="number" name="stock_<%= size %>" min="0" value="0" required></td>
				</tr>
				<% } %>
			</table>

			<input type="submit" value="次へ" class="submit-button">
		</form>

		<a href="${pageContext.request.contextPath}/views/admin/adminHome.jsp" class="back-link">管理者メニューに戻る</a>
	</div>

</body>
</html>
