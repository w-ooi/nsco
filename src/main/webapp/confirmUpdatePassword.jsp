<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,java.util.ArrayList,beans.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NatureSportsClubオンライン配信サイト</title>
<%
	String nowPassword = (String)session.getAttribute("nowPassword");
	String newPassword = (String)session.getAttribute("newPassword");

%>
</head>
<body>
<div>
<table style="margin:auto;border-collapse:separate;border-spacing:20px;">
<tr>
<td><a href="index.jsp"><img src="images/logo3.png" width="30%" height="30%"></a></td>
</tr>
</table>
</div>
<div style="text-align:center;"><strong>パスワード変更の確認</strong></div>
<div style="text-align:center;">次のパスワードで変更します</div>
<form action="fc" method="post">
<table style="margin:auto;border:1px solid;">
	<tr><th style="width:300px">現在のパスワード</th><td style="width:480px"><%= nowPassword %></td></tr>
	<tr><th>新しいパスワード</th><td><%= newPassword %></td></tr>
</table>
<input type="hidden" name="visit" value="confirmUpdatePassword">
</form>
<div style="text-align:center;">
<form action="fc" method="post">
<input type="submit" value="マイページへ戻る">
<input type="hidden" name="visit" value="myPage">
</form>
</div>
</body>
</html>