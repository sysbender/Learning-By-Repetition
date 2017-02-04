<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/tiles/taglib.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>user name</th>
		</tr>

	</thead>

	<tbody>
		<c:forEach var="user" items="${users}">
			<tr>
				<td><c:out value="${user.name}" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>