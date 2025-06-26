<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.co.aforce.beans.ProductWithStockSummary"%>
<%@ page import="java.util.List"%>
<%@ page import="jp.co.aforce.beans.ProductSize"%>

<%
ProductWithStockSummary product = (ProductWithStockSummary) request.getAttribute("product");
List<ProductSize> sizes = (List<ProductSize>) request.getAttribute("sizes");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品編集</title>
</head>
<body>

	<h2>商品編集画面</h2>

	<form
		action="<%=request.getContextPath()%>/admin/AdminProductEditConfirmServlet"
		method="post">
		<input type="hidden" name="id" value="<%=product.getId()%>">

		商品名：<input type="text" name="name" value="<%=product.getName()%>"><br>
		価格：<input type="number" name="price" value="<%=product.getPrice()%>"><br>
		画像ファイル名：<input type="text" name="image"
			value="<%=product.getImage()%>"><br> 発売日：<input
			type="date" name="release_date" value="<%=product.getReleaseDate()%>"><br>

		<h3>サイズ別在庫：</h3>
		<%
		for (ProductSize ps : sizes) {
		%>
		サイズ
		<%=ps.getSize()%>： <input type="hidden" name="size"
			value="<%=ps.getSize()%>"> <input type="number" name="stock"
			value="<%=ps.getStock()%>" min="0"><br>
		<%
		}
		%>

		<input type="submit" value="確認">
	</form>

	<form
		action="${pageContext.request.contextPath}/admin/AdminProductListServlet"
		method="get">
		<input type="submit" value="戻る">
	</form>
</body>
</html>
