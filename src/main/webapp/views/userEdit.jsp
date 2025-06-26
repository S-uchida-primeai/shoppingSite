<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
    String lastName = (String) session.getAttribute("edit_last_name");
    String firstName = (String) session.getAttribute("edit_first_name");
    String email = (String) session.getAttribute("edit_email");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報修正</title>
<link rel="stylesheet" href="../css/userEdit.css">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Serif+JP&display=swap"
	rel="stylesheet">
</head>
<body>
	<div class="edit-container">
		<h2 class="edit-title">会員情報の修正</h2>

		<form action="userEditConfirm.jsp" method="post" class="edit-form">
			<p>
				<label>姓：<input type="text" name="lastName" value="<%= lastName != null ? lastName : "" %>" required></label>
			</p>
			<p>
				<label>名：<input type="text" name="firstName" value="<%= firstName != null ? firstName : "" %>" required></label>
			</p>
			<p>
				<label>メールアドレス：<input type="email" name="email" value="<%= email != null ? email : "" %>" required></label>
			</p>
			<button type="submit" class="submit-button">確認</button>
		</form>

		<form action="LoginAction" method="post" class="back-form">
			<input type="hidden" name="action" value="backTop">
			<button type="submit" class="back-button">トップページに戻る</button>
		</form>
	</div>

</body>
</html>