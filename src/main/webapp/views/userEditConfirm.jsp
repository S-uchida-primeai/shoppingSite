<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報確認</title>
<link rel="stylesheet" href="../css/userEditConfirm.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Serif+JP&display=swap" rel="stylesheet">
</head>
<body>

	<%
	request.setCharacterEncoding("UTF-8");
	String lastName = request.getParameter("lastName");
	String firstName = request.getParameter("firstName");
	String email = request.getParameter("email");
	
	// セッションに値を保存
	session.setAttribute("edit_last_name", lastName);
	session.setAttribute("edit_first_name", firstName);
	session.setAttribute("edit_email", email);
	%>
	%>
	
	<div class="confirm-container">
		<h2 class="confirm-title">会員情報の確認</h2>
		<p class="confirm-text">以下の内容で修正を行いますか？</p>

		<div class="confirm-info">
			<p>姓：<strong><%= lastName %></strong></p>
			<p>名：<strong><%= firstName %></strong></p>
			<p>メールアドレス：<strong><%= email %></strong></p>
		</div>

		<form action="UserEditServlet" method="post" class="form-inline">
			<input type="hidden" name="lastName" value="<%=lastName%>">
			<input type="hidden" name="firstName" value="<%=firstName%>">
			<input type="hidden" name="email" value="<%=email%>">
			<button type="submit" class="confirm-button">修正を確定</button>
		</form>

		<form action="userEdit.jsp" method="post" class="form-inline">
			<button type="submit" class="back-button">戻る</button>
		</form>
	</div>

</body>
</html>
