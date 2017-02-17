<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../tiles/taglib.jsp"%>




<h2>imports of ${user.name}</h2>


<table class="table table-bordered">
	<thead>
		<tr>
			<th>course name</th>
			<th>course language</th>
			<th>course description</th>
			<th>Lessons</th>

		</tr>
	</thead>

	<tbody>
		<c:forEach var="course" items="${user.courses }">

			<tr>
				<td>${course.name }</a>
				</td>
				<td><c:out value="${course.lang}" /></td>
				<td><c:out value="${course.description}" /></td>
				<td><c:forEach var="lesson" items="${course.lessons}">
						<a href='<c:url value="/learn/lesson/${lesson.id}.html"></c:url>'>
							${lesson.name}</a>
					</c:forEach></td>
			</tr>
		</c:forEach>
	</tbody>
</table>


