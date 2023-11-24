<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,java.util.ArrayList,beans.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NatureSportsClubオンライン配信サイト</title>
<%
	List<LessonCategory> lessonCategoryList = (ArrayList<LessonCategory>)session.getAttribute("lessonCategoryList");
	List<TimeFrame> timeFrameList = (ArrayList<TimeFrame>)session.getAttribute("timeFrameList");
	List<Instructor> instructorList = (ArrayList<Instructor>)session.getAttribute("instructorList");
	Member member = (Member)session.getAttribute("member");
	
	//index.jspに直接アクセスしたら、フロントコントローラへ転送
	if(lessonCategoryList == null){
%>
<jsp:forward page="fc" />
<%
	}
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
		<td><form action="fc" method="post"><input type="submit" value="ログイン"><input type="hidden" name="visit" value="login"><input type="hidden" name="page" value="index.jsp"></form></td>
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
<div style="text-align:center;"><img src="images/nsctop.jpg" width="80%" height="80%"></div>
<br>
<div style="text-align:center;"><strong>スケジュール検索</strong></div>
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
</body>
</html>