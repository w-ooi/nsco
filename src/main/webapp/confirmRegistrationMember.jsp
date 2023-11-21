<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,java.util.ArrayList,beans.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NatureSportsClubオンライン配信サイト</title>
<%
	Member member = (Member)session.getAttribute("registrationMember");
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
<div style="text-align:center;"><strong>会員登録の確認</strong></div>
<div style="text-align:center;">次の会員情報を登録します</div>
<form action="fc" method="post">
<table style="margin:auto;border:1px solid;">
<tr><th style="width:180px;">氏名（姓）</th><td><%= member.getNameSei() %></td></tr>
<tr><th style="width:180px;">氏名（名）</th><td><%= member.getNameMei() %></td></tr>
<tr><th style="width:180px;">ふりがな（姓）</th><td><%= member.getKanaSei() %></td></tr>
<tr><th style="width:180px;">ふりがな（名）</th><td><%= member.getKanaMei() %></td></tr>
<tr><th style="width:180px;">メールアドレス</th><td><%= member.getEmail() %></td></tr>
<tr><th style="width:180px;">ニックネーム</th><td><%= member.getNickname() %></td></tr>
<tr><th style="width:180px;">パスワード</th><td><%= member.getPassword() %></td></tr>
<tr><th style="width:180px;">カード会社</th><td><%= member.getCreca().getCrecaCompName() %></td></tr>
<tr><th style="width:180px;">カード番号</th><td><%= member.getCrecaNo() %></td></tr>
<tr><th style="width:180px;">カード期限</th><td><%= member.getCreacaExpiration() %></td></tr>
<tr><th colspan="2"><input type="submit" value="会員登録の確定"></th></tr>
</table>
<input type="hidden" name="visit" value="confirmRegistrationMember">
</form>
<div style="text-align:center;">
<form action="fc" method="post">
<input type="submit" value="新規会員登録ページへ戻る">
<input type="hidden" name="visit" value="registrationPage">
</form>
</div>
</body>
</html>