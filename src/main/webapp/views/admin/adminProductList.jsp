<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jp.co.aforce.beans.ProductWithStockSummary"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
</head>
<body>
	<h2>商品一覧</h2>

	<table border="1">
		<tr>
			<th>商品名</th>
			<th>サイズ別在庫</th>
			<th>価格</th>
			<th>発売日</th>
			<th>編集</th>
			<th>削除</th>
		</tr>
		<%
		List<ProductWithStockSummary> products = (List<ProductWithStockSummary>) request.getAttribute("products");
		if (products != null) {
			for (ProductWithStockSummary p : products) {
		%>
		<tr>
			<td><%=p.getName()%></td>
			<td><%=p.getStockSummary()%></td>
			<td>¥<%=p.getPrice()%></td>
			<td><%=p.getReleaseDate()%></td>
			<td><a
				href="<%=request.getContextPath()%>/admin/AdminProductEditServlet?id=<%=p.getId()%>">編集</a>
			</td>

			<td>
				<form action="<%=request.getContextPath()%>/admin/AdminProductDelete" method="post"
					onsubmit="return confirm('本当に削除しますか？');">
					<input type="hidden" name="product_id" value="<%=p.getId()%>">
					<input type="submit" value="削除">
				</form>
			</td>
		</tr>
		<%
		}
		}
		%>
	</table>

	<p>
		<a href="${pageContext.request.contextPath}/views/admin/adminHome.jsp">管理者ホームへ戻る</a>
	</p>

</body>
</html>
