<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../tiles/taglib.jsp"%>

<h2>${user.name}</h2>
<c:forEach var="course" items="${user.courses }">
	<h1>${course.name }</h1>
	<p>${course.desc }</p>

	<table class="table table-bordered">
		<thead>
			<tr>
				<th>lesson name</th>
				<th>lesson text</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="lesson" items="${course.lessons }">

				<tr>
					<td>${lesson.name }</td>

					<td>${lesson.text1 }</td> 
				</tr>

			</c:forEach>


		</tbody>

	</table>

</c:forEach>