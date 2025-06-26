<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jp.co.aforce.beans.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報編集</title>
</head>
<body>

<h2>ユーザー編集</h2>

<%
    User user = (User) request.getAttribute("user");
    if (user == null) {
%>
    <p>ユーザー情報が見つかりません。</p>
<%
    } else {
%>

<form action="<%=request.getContextPath()%>/admin/AdminUserEditConfirmServlet" method="post">
    <input type="hidden" name="member_id" value="<%=user.getMemberId()%>">
    
    <p>姓：<input type="text" name="last_name" value="<%=user.getLastName()%>"></p>
    <p>名：<input type="text" name="first_name" value="<%=user.getFirstName()%>"></p>
    <p>住所：<input type="text" name="address" value="<%=user.getAddress()%>"></p>
    <p>メールアドレス：<input type="text" name="mail_address" value="<%=user.getMailAddress()%>"></p>
    <p>権限：
        <select name="admin">
            <option value="0" <%=user.getAdmin() == 0 ? "selected" : ""%>>一般</option>
            <option value="1" <%=user.getAdmin() == 1 ? "selected" : ""%>>管理者</option>
        </select>
    </p>

    <input type="submit" value="確認">
</form>

<%
    }
%>

<p><a href="<%=request.getContextPath()%>/admin/AdminUserList">← ユーザー一覧に戻る</a></p>

</body>
</html>
