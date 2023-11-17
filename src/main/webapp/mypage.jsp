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
<div style="text-align:center;"><strong>予約レッスン一覧</strong></div>
<div style="text-align:center;">前日の18:00までキャンセルが可能です</div>
<%
	if(cancelMessage != null && cancelMessage.equals("")){
%>
		<br>
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
			<tr><th>レッスン名</th><td style="width:600px"><%= reserve.getSchedule().getLesson().getLessonName() %></td></tr>
			<tr><th>開催日時</th><td><%= reserve.getSchedule().getEventDate() %>&nbsp;<%= reserve.getSchedule().getTimeFrame().getStartTime() %>&nbsp;～&nbsp;<%= reserve.getSchedule().getTimeFrame().getEndTime() %></td></tr>
			<tr><th>インストラクター名</th><td><%= reserve.getSchedule().getInstructor().getInstructorName() %></td></tr>
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
<%
	}
%>
<hr>
<div style="text-align:center;"><strong>受講済みレッスン一覧</strong></div>
<div style="text-align:center;">アンケートにご協力ください</div>
<%
	if(fillOutMessage != null && fillOutMessage.equals("")){
%>
		<br>
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
			<tr><th>レッスン名</th><td style="width:600px"><%= reserve.getSchedule().getLesson().getLessonName() %></td></tr>
			<tr><th>開催日時</th><td><%= reserve.getSchedule().getEventDate() %>&nbsp;<%= reserve.getSchedule().getTimeFrame().getStartTime() %>&nbsp;～&nbsp;<%= reserve.getSchedule().getTimeFrame().getEndTime() %></td></tr>
			<tr><th>インストラクター名</th><td><%= reserve.getSchedule().getInstructor().getInstructorName() %></td></tr>
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
<%
	}
%>

</body>
</html>