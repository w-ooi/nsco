<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NatureSportsClubオンライン配信サイト</title>
</head>
<body>
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
<div style="text-align:center;"><a href="">新規会員登録</a></div>
</body>
</html>