<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../tiles/taglib.jsp"%>

<h2>imports of ${user.name}</h2>


<table class="table table-bordered">
	<thead>
		<tr>
			<th>course name</th>
			<th>course description</th>
			<th>course language</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach var="course" items="${user.courses }">

			<tr>
				<td>${course.name }</td>
				<td>${course.desc }</td>
				<td>${course.lang }</td>
			</tr>

		</c:forEach>


	</tbody>

</table>

