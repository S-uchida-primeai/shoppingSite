<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除完了</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/userDeleteSuccess.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Serif+JP&display=swap" rel="stylesheet">
</head>
<body>

  <div class="success-container">
    <h1>削除が完了しました</h1>
    <p>ご利用ありがとうございました。</p>
    <a href="<%= request.getContextPath() %>/views/login-in.jsp">ログイン画面へ戻る</a>
  </div>

</body>
</html>
