<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/tiles/taglib.jsp"%>

<h2>uploads list:</h2>
<table class="table table-bordered">
	<thead>
		<tr>
			<th>name</th>
			<th>file</th>
			<th>url</th>
			<th>remove</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach var="upload" items="${uploads}">

			<tr>
				<td><c:out value="${upload.name}" /></td>
				<td><c:out value="${upload.file}" /></td>
				<td><c:out value="${upload.url}" /></td>
				<td><a
					href='<spring:url value="/import/upload/remove/${upload.id}.html" />'
					class="btn btn-danger triggerRemove"> remove course:
						${upload.id} </a></td>
			</tr>

		</c:forEach>


	</tbody>

</table>



<h2>items list:</h2>

<table class="table table-bordered">
	<thead>
		<tr>
			<th>genre, lang, url</th>
			<th>text</th>
			<th>remove</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach var="item" items="${items}">

			<tr>
				<td><c:out value="${item.genre}" /> - <c:out value="${item.lang}" /><br>
				<c:out value="${item.audioUrl}" />
				</td>
				 
				<td><c:out value="${item.txt}" /></td>
				<td><a
					href='<spring:url value="/import/item/remove/${item.id}.html" />'
					class="btn btn-danger triggerRemove"> remove course:
						${item.id} </a></td>
			</tr>

		</c:forEach>


	</tbody>

</table>






