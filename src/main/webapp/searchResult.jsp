<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,java.util.ArrayList,beans.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NatureSportsClubオンライン配信サイト</title>
<%
	List<LessonCategory> lessonCategoryList = (ArrayList<LessonCategory>)session.getAttribute("lessonCategoryList");
	List<TimeFrame> timeFrameList = (ArrayList<TimeFrame>)session.getAttribute("timeFrameList");
	List<Instructor> instructorList = (ArrayList<Instructor>)session.getAttribute("instructorList");
	List<Schedule> scheduleList = (ArrayList<Schedule>)session.getAttribute("scheduleList");
	Member member = (Member)session.getAttribute("member");
	List<Reserve> reserveList = (ArrayList<Reserve>)session.getAttribute("reserveList");
	String reserveMessage = (String)session.getAttribute("reserveMessage");
	
%>
</head>
<body>
<div>
<table style="margin:auto;border-collapse:separate;border-spacing:20px;">
<tr>
<td><a href="index.jsp"><img src="images/logo3.png" width="30%" height="30%"></a></td>
<%
	if(member == null){
%>
		<td><form action="fc" method="post"><input type="submit" value="新規会員登録"><input type="hidden" name="visit" value="registrationPage"></form></td>
		<td><form action="fc" method="post"><input type="submit" value="ログイン"><input type="hidden" name="visit" value="login"><input type="hidden" name="page" value="searchResult.jsp"></form></td>
<%
	}else{
%>
		<td><form action="fc" method="post"><input type="submit" value="マイページ"><input type="hidden" name="visit" value="myPage"></form></td>
		<td><form action="fc" method="post"><input type="submit" value="ログアウト"><input type="hidden" name="visit" value="logout"></form></td>
<%
	}
%>
</tr>
</table>
</div>
<div style="text-align:center;"><strong>スケジュール再検索</strong></div>
<div style="text-align:center;">次のいずれかの条件で検索ができます</div>
<form action="fc" method="post">
<table style="margin:auto;border:1px solid;">
<tr><td style="width:180px;text-align:right;"><strong>カテゴリ</strong></td><td colspan="2" style="width:300px;"><select name="code">
<option value="all">すべて</option>
<%
	for(LessonCategory category:lessonCategoryList){
%>
		<option value=<%= category.getLessonCategoryCode() %>><%= category.getLessonCategoryName() %></option>
<%
	}
%>
</select></td>
<td style="width:50px;"><input type="submit" value="検索"></td></tr>
</table>
<input type="hidden" name="visit" value="lessonCategorySearch">
</form>
<br>
<form action="fc" method="post">
<table style="margin:auto;border:1px solid;">
<tr><td style="width:180px;text-align:right;"><strong>日時</strong></td><td style="width:150px;"><input type="date" name="date" required></td><td style="width:150px;"><select name="code">
<option value="all">すべて</option>
<%
	for(TimeFrame timeFrame:timeFrameList){
%>
		<option value=<%= timeFrame.getTimeFrameCode() %>><%= timeFrame.getStartTime() %>～<%= timeFrame.getEndTime() %></option>
<%
	}
%>
</select></td>
<td style="width:50px;"><input type="submit" value="検索"></td></tr>
</table>
<input type="hidden" name="visit" value="timeFrameSearch">
</form>
<br>
<form action="fc" method="post">
<table style="margin:auto;border:1px solid;">
<tr><td style="width:180px;text-align:right;"><strong>インストラクター</strong></td><td colspan="2" style="width:300px;"><select name="code">
<option value="all">すべて</option>
<%
	for(Instructor instructor:instructorList){
%>
		<option value=<%= instructor.getInstructorCode() %>><%= instructor.getInstructorName() %></option>
<%
	}
%>
</select></td>
<td style="width:50px;"><input type="submit" value="検索"></td></tr>
</table>
<input type="hidden" name="visit" value="instructorSearch">
</form>
<br>
<div style="text-align:center;"><strong>検索結果</strong></div>
<%
	if(reserveMessage != null && !reserveMessage.equals("")){
%>
		<div style="text-align:center;color:#ff0000;"><strong><%= reserveMessage %></strong></div>
		<br>
<%
		session.removeAttribute("reserveMessage");
	}
%>
<%
	if(scheduleList.size() > 0){
%>
<div style="text-align:center;">(<%= scheduleList.size() %>件)</div>
<%
		for(Schedule schedule:scheduleList){
			if(schedule.getCancelFlag() == 0){
%>
		<div>
		<form action="fc" method="post">
		<table>
		<tr><td style="width:180px;text-align:right;"><strong>カテゴリ</strong></td><td style="width:600px"><%= schedule.getLesson().getLessonCategory().getLessonCategoryName() %></td><td rowspan="5"><img src=images/<%= schedule.getInstructor().getImageFile() %>></td></tr>
		<tr><td style="width:180px;text-align:right;"><strong>レッスン名</strong></td><td><%= schedule.getLesson().getLessonName() %></td></tr>
		<tr><td style="width:180px;text-align:right;"><strong>レッスン詳細</strong></td><td><%= schedule.getLesson().getDescription() %></td></tr>
		<tr><td style="width:180px;text-align:right;"><strong>開催日時</strong></td><td><%= schedule.getEventDate() %>&nbsp;
		<%= schedule.getTimeFrame().getStartTime() %>～<%= schedule.getTimeFrame().getEndTime() %></td></tr>
		<tr><td style="width:180px;text-align:right;"><strong>インストラクター</strong></td><td><%= schedule.getInstructor().getInstructorName() %></td></tr>
		<tr><th colspan="3">
<%
		boolean reserveFlag = false;
		if(member != null && reserveList != null){
			for(Reserve reserve : reserveList){
				if(schedule.getScheduleCode() == reserve.getSchedule().getScheduleCode()){
					reserveFlag = true;
					break;
				}
			}
		}
		
		if(reserveFlag){
%>
			<span>予約済みです</span>
<%
		}else{
%>
			<input type="submit" value="予約する">
<%
		}
%>
		</th></tr>
		</table>
		<input type="hidden" name="scheduleCode" value=<%= schedule.getScheduleCode() %>>
		<input type="hidden" name="visit" value="reserveSchedule">
		</form>
		</div>
		<br>
<%
			}
		}
%>
<%
	}else{
%>
		<div style="text-align:center;">検索結果はありません</div>
<%		
	}
%>
</body>
</html>