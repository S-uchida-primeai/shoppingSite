<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.co.aforce.beans.Login"%>
<%
Login user = (Login) session.getAttribute("user");
if (user == null) {
	// セッション切れなどならログイン画面に戻す
	response.sendRedirect(request.getContextPath() + "/views/login-in.jsp");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>会員メニュー</h1>
	<form action="Logout" method="get">
		<p>
			ようこそ、<%=user.getLastName()%>
			<%=user.getFirstName()%>
			さん
		</p>
		<p><a href="userEdit.jsp">編集</a></p>
		<p><a href="userDelete.jsp">削除</a></p>
		<button type="submit" name="action">ログアウト</button>		
	</form>
	
	
</body>
</html>