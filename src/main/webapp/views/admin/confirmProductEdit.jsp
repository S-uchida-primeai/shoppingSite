<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jp.co.aforce.beans.ProductWithStockSummary" %>
<%@ page import="jp.co.aforce.beans.ProductSize" %>
<%@ page import="java.util.List" %>

<%
    ProductWithStockSummary product = (ProductWithStockSummary) request.getAttribute("product");
    List<ProductSize> sizes = (List<ProductSize>) request.getAttribute("sizes");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品編集確認</title>
</head>
<body>

<h2>商品編集内容の確認</h2>

<p>商品名：<%= product.getName() %></p>
<p>価格：¥<%= product.getPrice() %></p>
<p>画像ファイル名：<%= product.getImage() %></p>
<p>発売日：<%= product.getReleaseDate() %></p>

<h3>サイズ別在庫：</h3>
<ul>
<%
    for (ProductSize ps : sizes) {
%>
    <li>サイズ <%= ps.getSize() %>：在庫 <%= ps.getStock() %></li>
<%
    }
%>
</ul>

<!-- 確定ボタン（次に登録処理に進む） -->
<form action="${pageContext.request.contextPath}/admin/AdminProductUpdateServlet" method="post">
    <input type="hidden" name="id" value="<%= product.getId() %>">
    <input type="hidden" name="name" value="<%= product.getName() %>">
    <input type="hidden" name="price" value="<%= product.getPrice() %>">
    <input type="hidden" name="image" value="<%= product.getImage() %>">
    <input type="hidden" name="release_date" value="<%= product.getReleaseDate() %>">

    <%
        for (ProductSize ps : sizes) {
    %>
        <input type="hidden" name="size" value="<%= ps.getSize() %>">
        <input type="hidden" name="stock" value="<%= ps.getStock() %>">
    <%
        }
    %>

    <input type="submit" value="確定">
</form>

<!-- 戻るボタン（編集画面に戻る） -->
<form action="${pageContext.request.contextPath}/admin/AdminProductEditServlet" method="get">
    <input type="hidden" name="id" value="<%= product.getId() %>">
    <input type="submit" value="戻る">
</form>

</body>
</html>
