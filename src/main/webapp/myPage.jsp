<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,java.util.ArrayList,beans.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NatureSportsClubオンライン配信サイト</title>
<%
	List<Reserve> beforeTakeLesson = (ArrayList<Reserve>)session.getAttribute("beforeTakeLesson");
	List<Reserve> afterTakeLesson = (ArrayList<Reserve>)session.getAttribute("afterTakeLesson");
	Member member = (Member)session.getAttribute("member");
	String updateMessage = (String)session.getAttribute("updateMessage");
	String cancelMessage = (String)session.getAttribute("cancelMessage");
	String fillOutMessage = (String)session.getAttribute("fillOutMessage");

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
<div style="text-align:center;"><strong>パスワード変更</strong></div>
<div style="text-align:center;">新旧パスワードを入力して変更ができます</div>
<%
	if(updateMessage != null && !updateMessage.equals("")){
%>
		<div style="text-align:center;color:#ff0000;"><strong><%= updateMessage %></strong></div>
		<br>
<%
		session.removeAttribute("updateMessage");
	}
%>

<br>
<form action="fc" method="post">
<table style="margin:auto;border:1px solid;">
	<tr><td style="width:300px;text-align:right;"><strong>現在のパスワード</strong></td><td style="width:480px"><input type="text" name="nowPassword" required></td></tr>
	<tr><td style="text-align:right;"><strong>新しいパスワード</strong></td><td><input type="text" name="newPassword" required></td></tr>
	<tr><th colspan="2"><input type="submit" value="変更する"></th></tr>
</table>
<input type="hidden" name="visit" value="updatePassword">
</form>
<hr>
<div style="text-align:center;"><strong>予約レッスン一覧</strong></div>
<div style="text-align:center;">前日の18:00までキャンセルが可能です</div>
<br>
<%
	if(cancelMessage != null && !cancelMessage.equals("")){
%>
		<div style="text-align:center;color:#ff0000;"><strong><%= cancelMessage %></strong></div>
		<br>
<%
		session.removeAttribute("cancelMessage");
	}
%>

<%
	if(beforeTakeLesson.size() > 0){
		for(Reserve reserve:beforeTakeLesson){
%>
		<form action="fc" method="post">
		<table style="margin:auto;border:1px solid;">
			<tr><td style="width:180px;text-align:right;"><strong>レッスン名</strong></td><td style="width:600px"><%= reserve.getSchedule().getLesson().getLessonName() %></td></tr>
			<tr><td style="width:180px;text-align:right;"><strong>開催日時</strong></td><td><%= reserve.getSchedule().getEventDate() %>&nbsp;<%= reserve.getSchedule().getTimeFrame().getStartTime() %>&nbsp;～&nbsp;<%= reserve.getSchedule().getTimeFrame().getEndTime() %></td></tr>
			<tr><td style="width:180px;text-align:right;"><strong>インストラクター名</strong></td><td><%= reserve.getSchedule().getInstructor().getInstructorName() %></td></tr>
			<tr><th colspan="2"><input type="submit" value="キャンセル"></th></tr>
		</table>
		<input type="hidden" name="visit" value="cancelReserve">
		<input type="hidden" name="reserveCode" value="<%= reserve.getReserveCode() %>">
		</form>
<%
		}
	}else{
%>
	<div style="text-align:center;">予約しているレッスンはありません</div>
	<br>
<%
	}
%>
<hr>
<div style="text-align:center;"><strong>受講済みレッスン一覧</strong></div>
<div style="text-align:center;">アンケートにご協力ください</div>
<br>
<%
	if(fillOutMessage != null && !fillOutMessage.equals("")){
%>
		<div style="text-align:center;color:#ff0000;"><strong><%= fillOutMessage %></strong></div>
		<br>
<%
		session.removeAttribute("fillOutMessage");
	}
%>

<%
	if(afterTakeLesson.size() > 0){
		for(Reserve reserve:afterTakeLesson){
%>
		<form action="fc" method="post">
		<table style="margin:auto;border:1px solid;">
			<tr><td style="width:180px;text-align:right;"><strong>レッスン名</strong></td><td style="width:600px"><%= reserve.getSchedule().getLesson().getLessonName() %></td></tr>
			<tr><td style="width:180px;text-align:right;"><strong>開催日時</strong></td><td><%= reserve.getSchedule().getEventDate() %>&nbsp;<%= reserve.getSchedule().getTimeFrame().getStartTime() %>&nbsp;～&nbsp;<%= reserve.getSchedule().getTimeFrame().getEndTime() %></td></tr>
			<tr><td style="width:180px;text-align:right;"><strong>インストラクター名</strong></td><td><%= reserve.getSchedule().getInstructor().getInstructorName() %></td></tr>
			<tr><th colspan="2"><input type="submit" value="アンケート入力"></th></tr>
		</table>
		<input type="hidden" name="visit" value="fillOutQuestion">
		<input type="hidden" name="reserveCode" value="<%= reserve.getReserveCode() %>">
		</form>
<%
		}
	}else{
%>
	<div style="text-align:center;">受講したレッスンはありません</div>
	<br>
<%
	}
%>

</body>
</html>