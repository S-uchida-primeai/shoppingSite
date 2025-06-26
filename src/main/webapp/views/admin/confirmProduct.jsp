<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="jp.co.aforce.beans.Product"%>
<%@ page import="java.util.Map"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>

<%
Product product = (Product) session.getAttribute("newProduct");
Map<Double, Integer> stockMap = (Map<Double, Integer>) session.getAttribute("stockMap");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録確認</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/adminProductConfirm.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Serif+JP&display=swap" rel="stylesheet">
</head>
<body>

	<div class="confirm-container">
		<h2 class="confirm-title">以下の内容で登録しますか？</h2>

		<div class="product-info">
			<p><strong>商品名：</strong><%=product.getName()%></p>
			<p><strong>価格：</strong><%=product.getPrice()%> 円</p>
			<p><strong>画像ファイル名：</strong><%=product.getImage()%></p>
			<p><strong>発売日：</strong><%=product.getRelease_date()%></p>
		</div>

		<h3 class="stock-title">サイズごとの在庫数</h3>
		<table class="stock-table">
			<tr>
				<th>サイズ</th>
				<th>在庫数</th>
			</tr>
			<%
			for (Map.Entry<Double, Integer> entry : stockMap.entrySet()) {
			%>
			<tr>
				<td><%=entry.getKey()%> cm</td>
				<td><%=entry.getValue()%> 足</td>
			</tr>
			<% } %>
		</table>

		<form action="<%=request.getContextPath()%>/RegisterProduct" method="post">
			<input type="submit" value="登録する" class="submit-button">
		</form>

		<a href="<%=request.getContextPath()%>/views/admin/adminAddProduct.jsp" class="back-link">← 修正する</a>
	</div>

</body>
</html>
