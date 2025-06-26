<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jp.co.aforce.beans.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報確認</title>
</head>
<body>

<h2>ユーザー情報の確認</h2>

<%
    User user = (User) request.getAttribute("user");
%>

<form action="<%=request.getContextPath()%>/admin/AdminUserEditSuccessServlet" method="post">
    <input type="hidden" name="member_id" value="<%=user.getMemberId()%>">
    <input type="hidden" name="last_name" value="<%=user.getLastName()%>">
    <input type="hidden" name="first_name" value="<%=user.getFirstName()%>">
    <input type="hidden" name="address" value="<%=user.getAddress()%>">
    <input type="hidden" name="mail_address" value="<%=user.getMailAddress()%>">
    <input type="hidden" name="admin" value="<%=user.getAdmin()%>">

    <p>姓：<%=user.getLastName()%></p>
    <p>名：<%=user.getFirstName()%></p>
    <p>住所：<%=user.getAddress()%></p>
    <p>メールアドレス：<%=user.getMailAddress()%></p>
    <p>権限：<%=user.getAdmin() == 1 ? "管理者" : "一般"%></p>

    <input type="submit" value="登録する">
    <button type="button" onclick="history.back();">戻る</button>
</form>

</body>
</html>
