<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>エラー</title>
</head>
<body>
    <h2>エラーが発生しました</h2>
    <p>${errorMessage}</p>
    <p><a href="<%=request.getContextPath()%>/UserMenuServlet">トップページへ戻る</a></p>
</body>
</html>
