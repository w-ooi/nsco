<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,java.util.ArrayList,beans.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NatureSportsClubオンライン配信サイト</title>
<%
	List<Creca> crecaList = (ArrayList<Creca>)session.getAttribute("crecaList");
	Member member = (Member)session.getAttribute("registrationMember");
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
<div style="text-align:center;"><strong>新規会員登録</strong></div>
<div style="text-align:center;">すべての項目を入力してください</div>
<div style="text-align:center;">お支払いはクレジットカードのみとなります</div>
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

<form action="fc" method="post">
<table style="margin:auto;border:1px solid;">
<tr><th style="width:180px;">氏名（姓）</th><td><input type="text" name="nameSei" required value=<% if(member!=null){ %><%= member.getNameSei() %><% } %>></td></tr>
<tr><th style="width:180px;">氏名（名）</th><td><input type="text" name="nameMei" required value=<% if(member!=null){ %><%= member.getNameMei() %><% } %>></td></tr>
<tr><th style="width:180px;">ふりがな（姓）</th><td><input type="text" name="kanaSei" required value=<% if(member!=null){ %><%= member.getKanaSei() %><% } %>></td></tr>
<tr><th style="width:180px;">ふりがな（名）</th><td><input type="text" name="kanaMei" required value=<% if(member!=null){ %><%= member.getKanaMei() %><% } %>></td></tr>
<tr><th style="width:180px;">メールアドレス</th><td><input type="email" name="email" required value=<% if(member!=null){ %><%= member.getEmail() %><% } %>></td></tr>
<tr><th style="width:180px;">ニックネーム</th><td><input type="text" name="nickName" required value=<% if(member!=null){ %><%= member.getNickname() %><% } %>></td></tr>
<tr><th style="width:180px;">パスワード</th><td><input type="text" name="password" required value=<% if(member!=null){ %><%= member.getPassword() %><% } %>></td></tr>
<tr><th style="width:180px;">カード会社</th><td><select name="crecaCompId">
<%
	for(Creca creca:crecaList){
%>
		<option value=<%= creca.getCrecaCompId() %> <% if(member!=null && (member.getCreca().getCrecaCompId() == creca.getCrecaCompId())){ %>selected<% } %>><%= creca.getCrecaCompName() %></option>
<%
	}
%>
</select></td>
</tr>
<tr><th style="width:180px;">カード番号</th><td><input type="text" name="crecaNo" required value=<% if(member!=null){ %><%= member.getCrecaNo() %><% } %>></td></tr>
<tr><th style="width:180px;">カード期限</th><td><input type="text" name="creacaExpiration" required value=<% if(member!=null){ %><%= member.getCreacaExpiration() %><% } %>></td></tr>
<tr><th colspan="2"><input type="submit" value="会員登録"></th></tr>
</table>
<input type="hidden" name="visit" value="registrationMember">
</form>
<form action="fc" method="post">
<input type="submit" value="トップページへ戻る">
<input type="hidden" name="visit" value="topPage">
</form>
</body>
</html>