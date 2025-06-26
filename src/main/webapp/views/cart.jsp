<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="jp.co.aforce.beans.Product" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<%
  List<Product> cart = (List<Product>) request.getAttribute("cart");
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>カート内容</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/cart.css">
</head>
<body>
  <h1>カートの中身</h1>

  <%
    if (cart == null || cart.isEmpty()) {
  %>
    <p>カートは空です。</p>
  <%
    } else {
  %>
    <ul>
      <% for (Product p : cart) { %>
        <li>
          商品名：<%= p.getName() %><br>
          サイズ：<%= p.getSize() %><br>
          価格：¥<%= String.format("%,d", p.getPrice()) %><br>
          数量：<%= p.getQuantity() %><br>
          
          <!-- 削除ボタン（フォーム） -->
          <form action="<%= request.getContextPath() %>/CartDeleteServlet" method="post" class="confirm-form">
            <input type="hidden" name="productId" value="<%= p.getId() %>">
            <input type="hidden" name="size" value="<%= p.getSize() %>">
            <button type="submit">削除</button>
          </form>
          <hr>
        </li>
      <% } %>
    </ul>

    <!-- 合計金額 -->
    <%
      int total = 0;
      for (Product p : cart) {
        total += p.getPrice() * p.getQuantity();
      }
    %>
    <p>合計金額：¥<%= String.format("%,d", total) %></p>

    <!-- 確認ボタン -->
    <form action="<%= request.getContextPath() %>/CartConfirmServlet" method="post">
  <% for (int i = 0; i < cart.size(); i++) {
       Product p = cart.get(i);
  %>
    <input type="hidden" name="productId_<%= i %>" value="<%= p.getId() %>">
    <input type="hidden" name="size_<%= i %>" value="<%= p.getSize() %>">
    <input type="hidden" name="quantity_<%= i %>" value="<%= p.getQuantity() %>">
  <% } %>
  <button type="submit">購入確認へ進む</button>
</form>


  <%
    }
  %>

  <p><a href="<%= request.getContextPath() %>/UserMenuServlet">トップへ戻る</a></p>
</body>
</html>
