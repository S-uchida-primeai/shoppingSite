<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>会員情報編集画面</h1>

	<form action="<%= request.getContextPath() %>/views/UserEditServlet" method="post">
		<p><label>名前_姓：<input type="text" name="lastName"></label></p>
		<p><label>名前_名：<input type="text" name="firstName"></label></p>
		<p><label> メースアドレス：<input type="email" name="email"></label></p>
		<button type="submit">登録</button>
	</form>
	<p><a href="user-menu.jsp">会員メニュー画面へ戻る</a></p>
</body>
</html>