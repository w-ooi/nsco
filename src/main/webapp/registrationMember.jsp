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
	String registrationEmailMessage = (String)session.getAttribute("registrationEmailMessage");
	String registrationNickNameMessage = (String)session.getAttribute("registrationNickNameMessage");
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
	if(registrationEmailMessage != null && !registrationEmailMessage.equals("")){
%>
		<br>
		<div style="text-align:center;color:#ff0000;"><strong><%= registrationEmailMessage %></strong></div>
		<br>
<%
		session.removeAttribute("registrationEmailMessage");
	}
%>

<%
	if(registrationNickNameMessage != null && !registrationNickNameMessage.equals("")){
%>
		<br>
		<div style="text-align:center;color:#ff0000;"><strong><%= registrationNickNameMessage %></strong></div>
		<br>
<%
		session.removeAttribute("registrationNickNameMessage");
	}
%>


<form action="fc" method="post">
<table style="margin:auto;border:1px solid;">
<tr><td style="width:180px;text-align:right;"><strong>氏名（姓）</strong></td><td><input type="text" name="nameSei" required value="<% if(member!=null){ %><%= member.getNameSei() %><% } %>"></td></tr>
<tr><td style="width:180px;text-align:right;"><strong>氏名（名）</strong></td><td><input type="text" name="nameMei" required value="<% if(member!=null){ %><%= member.getNameMei() %><% } %>"></td></tr>
<tr><td style="width:180px;text-align:right;"><strong>ふりがな（姓）</strong></td><td><input type="text" name="kanaSei" required value="<% if(member!=null){ %><%= member.getKanaSei() %><% } %>"></td></tr>
<tr><td style="width:180px;text-align:right;"><strong>ふりがな（名）</strong></td><td><input type="text" name="kanaMei" required value="<% if(member!=null){ %><%= member.getKanaMei() %><% } %>"></td></tr>
<tr><td style="width:180px;text-align:right;"><strong>メールアドレス</strong></td><td><input type="email" name="email" required value="<% if(member!=null){ %><%= member.getEmail() %><% } %>"></td></tr>
<tr><td style="width:180px;text-align:right;"><strong>ニックネーム</strong></td><td><input type="text" name="nickName" required value="<% if(member!=null){ %><%= member.getNickname() %><% } %>"></td></tr>
<tr><td style="width:180px;text-align:right;"><strong>パスワード</strong></td><td><input type="text" name="password" required value="<% if(member!=null){ %><%= member.getPassword() %><% } %>"></td></tr>
<tr><td style="width:180px;text-align:right;"><strong>カード会社</strong></td><td><select name="crecaCompId">
<%
	for(Creca creca:crecaList){
%>
		<option value="<%= creca.getCrecaCompId() %>" <% if(member!=null && (member.getCreca().getCrecaCompId() == creca.getCrecaCompId())){ %>selected<% } %>><%= creca.getCrecaCompName() %></option>
<%
	}
%>
</select></td>
</tr>
<tr><td style="width:180px;text-align:right;"><strong>カード番号</strong></td><td><input type="text" name="crecaNo" required placeholder="数値16桁(ハイフン無し)" value="<% if(member!=null){ %><%= member.getCrecaNo() %><% } %>"></td></tr>
<tr><td style="width:180px;text-align:right;"><strong>カード期限</strong></td><td><input type="text" name="creacaExpiration" required placeholder="MM/YY" value="<% if(member!=null){ %><%= member.getCreacaExpiration() %><% } %>"></td></tr>
<tr><th colspan="2"><input type="submit" value="会員登録"></th></tr>
</table>
<input type="hidden" name="visit" value="registrationMember">
</form>
<br>
<div style="text-align:center;">
<form action="fc" method="post">
<input type="submit" value="トップページへ戻る">
<input type="hidden" name="visit" value="topPage">
</form>
</div>
</body>
</html>