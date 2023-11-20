<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NatureSportsClubオンライン配信サイト</title>
<%
	String registrationMessage = (String)session.getAttribute("registrationMessage");
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
<div style="text-align:center;"><strong>ログイン</strong></div>
<form action="fc" method="post">
<table style="margin:auto;border:1px solid;">
<tr><th style="width:180px;">会員番号</th><td><input type="text" name="memberNo" required></td></tr>
<tr><th style="width:180px;">パスワード</th><td><input type="password" name="password" required></td></tr>
<tr><th colspan="2"><input type="submit" value="ログイン"></th></tr>
</table>
<input type="hidden" name="visit" value="authenticationLogin">
</form>
<br>
<%
	if(registrationMessage != null && !registrationMessage.equals("")){
%>
		<br>
		<div style="text-align:center;color:#ff0000;"><strong><%= registrationMessage %></strong></div>
		<br>
<%
		session.removeAttribute("registrationMessage");
	}
%>
<div style="text-align:center;"><a href="">新規会員登録</a></div>
</body>
</html>