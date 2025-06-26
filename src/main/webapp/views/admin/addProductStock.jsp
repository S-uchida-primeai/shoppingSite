<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="jp.co.aforce.beans.Product"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>

<%
request.setCharacterEncoding("UTF-8");

String name = request.getParameter("name");
int price = Integer.parseInt(request.getParameter("price"));
String image = request.getParameter("image");
String releaseDateStr = request.getParameter("release_date");
java.sql.Date releaseDate = java.sql.Date.valueOf(releaseDateStr);

Product product = new Product();
product.setName(name);
product.setPrice(price);
product.setImage(image);
product.setRelease_date(releaseDate);

session.setAttribute("newProduct", product);

double[] sizes = { 25.0, 25.5, 26.0, 26.5, 27.0, 27.5, 28.0 };
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>在庫数入力</title>
</head>
<body>
	<h2>サイズごとの在庫数を入力</h2>

	<form action="<%=request.getContextPath()%>/ConfirmProduct"
		method="post">
		<table border="1">
			<tr>
				<th>サイズ</th>
				<th>在庫数</th>
			</tr>
			<%
			for (double size : sizes) {
			%>
			<tr>
				<td><%=size%> cm</td>
				<td><input type="number" name="stock_<%=size%>" min="0"
					required></td>
			</tr>
			<%
			}
			%>
		</table>
		<br> <input type="submit" value="確認画面へ">
	</form>
</body>
</html>
