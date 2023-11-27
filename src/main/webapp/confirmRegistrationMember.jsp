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
<tr><td style="width:180px;text-align:right;"><strong>氏名（姓）</strong></td><td><%= member.getNameSei() %></td></tr>
<tr><td style="width:180px;text-align:right;"><strong>氏名（名）</strong></td><td><%= member.getNameMei() %></td></tr>
<tr><td style="width:180px;text-align:right;"><strong>ふりがな（姓）</strong></td><td><%= member.getKanaSei() %></td></tr>
<tr><td style="width:180px;text-align:right;"><strong>ふりがな（名）</strong></td><td><%= member.getKanaMei() %></td></tr>
<tr><td style="width:180px;text-align:right;"><strong>メールアドレス</strong></td><td><%= member.getEmail() %></td></tr>
<tr><td style="width:180px;text-align:right;"><strong>ニックネーム</strong></td><td><%= member.getNickname() %></td></tr>
<tr><td style="width:180px;text-align:right;"><strong>パスワード</strong></td><td><%= member.getPassword() %></td></tr>
<tr><td style="width:180px;text-align:right;"><strong>カード会社</strong></td><td><%= member.getCreca().getCrecaCompName() %></td></tr>
<tr><td style="width:180px;text-align:right;"><strong>カード番号</strong></td><td><%= member.getCrecaNo() %></td></tr>
<tr><td style="width:180px;text-align:right;"><strong>カード期限</strong></td><td><%= member.getCreacaExpiration() %></td></tr>
<tr><th colspan="2"><input type="submit" value="会員登録の確定"></th></tr>
</table>
<input type="hidden" name="visit" value="confirmRegistrationMember">
</form>
<br>
<div style="text-align:center;">
<form action="fc" method="post">
<input type="submit" value="新規会員登録ページへ戻る">
<input type="hidden" name="visit" value="registrationPage">
</form>
</div>
</body>
</html>