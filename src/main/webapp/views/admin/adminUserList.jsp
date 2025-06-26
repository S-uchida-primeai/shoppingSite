<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, jp.co.aforce.beans.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー一覧</title>
</head>
<body>

	<h2>ユーザー一覧</h2>

	<table border="1">
		<tr>
			<th>会員ID</th>
			<th>姓</th>
			<th>名</th>
			<th>住所</th>
			<th>メールアドレス</th>
			<th>権限</th>
			<th>修正</th>
			<th>削除</th>
		</tr>
		<%
		List<User> userList = (List<User>) request.getAttribute("userList");
		if (userList != null) {
			for (User u : userList) {
		%>
		<tr>
			<td><%=u.getMemberId()%></td>
			<td><%=u.getLastName()%></td>
			<td><%=u.getFirstName()%></td>
			<td><%=u.getAddress()%></td>
			<td><%=u.getMailAddress()%></td>
			<td><%=u.getAdmin() == 1 ? "管理者" : "一般"%></td>
			<td><a href="<%=request.getContextPath()%>/admin/AdminUserEditServlet?member_id=<%=u.getMemberId()%>">編集</a>
			</td>
			<td>
				<form action="<%=request.getContextPath()%>/admin/AdminUserDeleteServlet"
					method="post" onsubmit="return confirm('本当に削除しますか？');">
					<input type="hidden" name="member_id" value="<%=u.getMemberId()%>">
					<input type="submit" value="削除">
				</form>
			</td>
		</tr>
		<%
		}
		}
		%>
	</table>

	<p><a href="<%=request.getContextPath()%>/views/admin/adminHome.jsp">管理者メニューに戻る</a></p>


</body>
</html>
