<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../tiles/taglib.jsp"%>

<h2>statistics of ${enSummary.user.name}</h2>

<table class="table table-bordered">
	<thead>
		<tr>
			<th>language</th>
			<th>imported courses</th>
			<th>imported lessons</th>
			<th>known words</th>
			<th>unknown words</th>
		</tr>
	</thead>

	<tbody>
		<tr>
			<td>${enSummary.lang}</td>
			<td>${enSummary.countCourse}</td>
			<td>${enSummary.countLesson}</td>
			<td>${enSummary.countWordKnown}</td>
			<td>${enSummary.countWordUnknown}</td>
		</tr>
		<tr>
			<td>${frSummary.lang}</td>
			<td>${frSummary.countCourse}</td>
			<td>${frSummary.countLesson}</td>
			<td>${frSummary.countWordKnown}</td>
			<td>${frSummary.countWordUnknown}</td>
		</tr>
	</tbody>
</table>
