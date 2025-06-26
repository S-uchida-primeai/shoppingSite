<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jp.co.aforce.beans.Product"%>

<%
List<Product> cart = (List<Product>) request.getAttribute("cart");
if (cart == null) {
	out.println("▶ cartがnullです");
	return;
}
%>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>購入完了</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/orderSuccess.css">
</head>
<body>
	<div class="success-container">
		<h1>ご購入ありがとうございました！</h1>
		<p>以下の内容で注文を受け付けました。</p>

		<ul class="product-list">
			<%
			for (Product p : cart) {
			%>
			<li>
				<span class="product-name"><%= p.getName() %></span>
				<span class="product-info">サイズ：<%= p.getSize() %> ／ 数量：<%= p.getQuantity() %></span>
			</li>
			<%
			}
			%>
		</ul>

		<a href="<%=request.getContextPath()%>/UserMenuServlet">トップページに戻る</a>

	</div>
</body>
</html>
