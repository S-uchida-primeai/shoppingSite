<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>登録エラー</title>
</head>
<body>
	<h2>商品登録中にエラーが発生しました。</h2>
	<p>もう一度やり直してください。</p>

	<br>
	<a href="<%=request.getContextPath()%>/views/admin/adminAddProduct.jsp">← 商品登録フォームに戻る</a>
</body>
</html>
