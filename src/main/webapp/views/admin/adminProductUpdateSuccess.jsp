<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新完了</title>
</head>
<body>

	<h2>商品情報を更新しました</h2>

	<form
		action="${pageContext.request.contextPath}/admin/AdminProductListServlet"
		method="get">
		<input type="submit" value="商品一覧に戻る">
	</form>

</body>
</html>
