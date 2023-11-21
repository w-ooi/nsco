<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,java.util.ArrayList,beans.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NatureSportsClubオンライン配信サイト</title>
<%
	Reserve reserve = (Reserve)session.getAttribute("canselReserve");
%>
</head>
<body>
<div>
<table style="margin:auto;border-collapse:separate;border-spacing:20px;">
<tr>
<td><a href="index.jsp"><img src="images/logo3.png" width="30%" height="30%"></a></td>
	<td><form action="fc" method="post"><input type="submit" value="マイページ"><input type="hidden" name="visit" value="mypage"></form></td>
	<td><form action="fc" method="post"><input type="submit" value="ログアウト"><input type="hidden" name="visit" value="logout"></form></td>
</tr>
</table>
</div>
<div style="text-align:center;"><strong>キャンセルの確認</strong></div>
<div style="text-align:center;">次のレッスンをキャンセルします</div>
<form action="fc" method="post">
<table style="margin:auto;border:1px solid;">
	<tr><th>レッスン名</th><td style="width:600px"><%= reserve.getSchedule().getLesson().getLessonName() %></td></tr>
	<tr><th>開催日時</th><td><%= reserve.getSchedule().getEventDate() %>&nbsp;<%= reserve.getSchedule().getTimeFrame().getStartTime() %>&nbsp;～&nbsp;<%= reserve.getSchedule().getTimeFrame().getEndTime() %></td></tr>
	<tr><th>インストラクター名</th><td><%= reserve.getSchedule().getInstructor().getInstructorName() %></td></tr>
	<tr><th colspan="2"><input type="submit" value="キャンセルの確定"></th></tr>
</table>
<input type="hidden" name="visit" value="confirmCancelReserve">
<input type="hidden" name="reserveCode" value="<%= reserve.getReserveCode() %>">
</form>
<br>
<div style="text-align:center;">
<form action="fc" method="post">
<input type="submit" value="マイページへ戻る">
<input type="hidden" name="visit" value="myPage">
</form>
</div>
</body>
</html>