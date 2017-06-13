<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="com.codingdojo.web.models.Timer, java.util.Date, java.text.SimpleDateFormat, java.util.List, java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>StopWatch</title>
	</head>
<body>
	<a href="/StopWatch/?status=start">Start</a> | <a href="/StopWatch/?status=stop">Stop</a> | <a href="/StopWatch/?status=reset">Reset</a>
	<% 
		Timer t = (Timer) session.getAttribute("time");
		Date current = new Date();
		SimpleDateFormat pattern = new SimpleDateFormat("HH:mm a");
		
		List<?> currentTimes = (List<?>) session.getAttribute("allTimes");
		List<Timer> tempTimes = new ArrayList<Timer>();
		for(Object object : currentTimes) {
			if(object instanceof Timer) {
				tempTimes.add((Timer) object);
			}
		}

		String start;
	%>
	
	<% if(t.getStart() == null) { %>
		<% start = "-"; %>
	<% } else  { %>
		<% start = pattern.format(t.getStart()); %>
	<% } %>
	
	<h4>Start: <%= start %></h4>
	<h4>Current Time: <%= pattern.format(current) %></h4>
	<h4>Running Time: <%= Timer.getFromCurrentTime(t) %></h4>
	
	<table>
		<thead>
			<tr>
				<td>Start</td>
				<td>Stop</td>
				<td>Total</td>
			</tr>
		</thead>
		<tbody>
 		<% for(Timer time : tempTimes) { %>
			<tr>
				<td><%= pattern.format(time.getStart()) %></td>
				<td><%= pattern.format(time.getStop()) %></td>
				<td><%= time.getTotal() %></td>
			</tr>
		<% } %>
		</tbody>
	</table>
</body>
</html>