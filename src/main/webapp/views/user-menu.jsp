<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.co.aforce.beans.Login" %>
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
	<p>ようこそ、<%= user.getLastName() %> <%= user.getFirstName() %> さん</p>
	<button type="submit" name="action">修正</button>
	<button type="submit" name="action">削除</button>
	<button type="submit" name="action">ログアウト</button>
</body>
</html>