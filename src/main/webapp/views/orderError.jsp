<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入エラー</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/error.css">
</head>
<body>
    <h1>購入処理中にエラーが発生しました</h1>

    <%
    Exception e = (Exception) request.getAttribute("jakarta.servlet.error.exception");
    if (e != null) {
        e.printStackTrace();  // 例外のスタックトレースを出力
    }
%>
<p>${error}</p>
    <p>
        <a href="<%=request.getContextPath()%>/UserMenuServlet">トップページに戻る</a>
    </p>
</body>
</html>
