<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jp.co.aforce.beans.Product"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<%
List<Product> cart = (List<Product>) request.getAttribute("cart");
if (cart != null) {
    session.setAttribute("cart", cart);  // ← ここでセッションに保存！
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文内容確認</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cartConfirm.css">
  <link href="https://fonts.googleapis.com/css2?family=Noto+Serif+JP&display=swap" rel="stylesheet">
</head>
<body>
	<div class="confirm-container">
	<h1>注文内容の確認</h1>

	<c:forEach var="product" items="${cart}">
	<div class="product-block">
		<p>商品名：${product.name}</p>
		<p>価格：¥<fmt:formatNumber value="${product.price}" type="number" groupingUsed="true" /></p>
		<p>サイズ：${product.size}</p>
		<p>数量：${product.quantity}</p>
		<hr>
	</div>
	</c:forEach>

	<%
	int total = 0;
	if (cart != null) {
		for (Product p : cart) {
			total += p.getPrice() * p.getQuantity();
		}
		request.setAttribute("total", total);
	}
	%>

	<p>合計金額：¥<%= String.format("%,d", total) %></p>

	<form action="<%=request.getContextPath()%>/OrderSuccess" method="post">
  <c:forEach var="product" items="${cart}" varStatus="status">
  <input type="hidden" name="product_id_${status.index}" value="${product.id}">
  <input type="hidden" name="size_${status.index}" value="${product.size}">
  <input type="hidden" name="quantity_${status.index}" value="${product.quantity}">
</c:forEach>
<input type="hidden" name="count" value="${fn:length(cart)}" />
  <button type="submit">購入する</button>
</form>

	<p>
		<a href="${pageContext.request.contextPath}/views/CartServlet">カートに戻る</a>

	</p>

</body>
</html>
