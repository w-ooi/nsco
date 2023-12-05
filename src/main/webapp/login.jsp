<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NatureSportsClubオンライン配信サイト</title>
<%
	String authenticationMessage = (String)session.getAttribute("authenticationMessage");
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
<tr><td style="width:180px;text-align:right;"><strong>会員番号</strong></td><td><input type="text" name="memberNo" required></td></tr>
<tr><td style="width:180px;text-align:right;"><strong>パスワード</strong></td><td><input type="password" name="password" required></td></tr>
<tr><th colspan="2"><input type="submit" value="ログイン"></th></tr>
</table>
<input type="hidden" name="visit" value="authenticationLogin">
</form>
<br>
<%
	if(authenticationMessage != null && !authenticationMessage.equals("")){
%>
		<div style="text-align:center;color:#ff0000;"><strong><%= authenticationMessage %></strong></div>
		<br>
<%
		session.removeAttribute("authenticationMessage");
	}
%>
<%
	if(registrationMessage != null && !registrationMessage.equals("")){
%>
		<div style="text-align:center;color:#ff0000;"><strong><%= registrationMessage %></strong></div>
		<br>
<%
		session.removeAttribute("registrationMessage");
	}
%>
<div style="text-align:center;"><form action="fc" method="post"><input type="submit" value="新規会員登録"><input type="hidden" name="visit" value="registrationPage"></form></div>
</body>
</html>