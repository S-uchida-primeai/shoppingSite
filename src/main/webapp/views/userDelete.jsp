<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員削除確認</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/userDeleteConfirm.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Serif+JP&display=swap" rel="stylesheet">
</head>
<body>

  <div class="confirm-container">
    <h1>ユーザー削除確認</h1>
    <p>このユーザーを削除してもよろしいですか？</p>
    <form action="UserDeleteServlet" method="post">
      <button type="submit" name="confirm" value="yes">はい</button>
      <button type="submit" name="confirm" value="no">いいえ</button>
    </form>
  </div>

</body>
</html>
