<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/tiles/taglib.jsp"%>



<Script type="text/javascript">
	$(document).ready(
			function() {
				//alert for removing
				$(".triggerRemove").click(
						function(e) {
							e.preventDefault(); // do not run link
							$("#modalRemove .removeBtn").attr("href",
									$(this).attr("href"));
							$("#modalRemove").modal();
						});

);

			});
</Script>
				

<table class="table table-bordered">
	<thead>
		<tr>
			<th>Lesson name</th>
			<th>Lesson Items</th>
			<th>Lesson Uploads</th>
			<th>remove</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach var="lesson" items="${lessons}">

			<tr>
				<td><a href="<spring:url value='/import/lesson/${lesson.id}.html'/>">${lesson.name }</a>
					</td>
				<td> </td>
				<td> </td>
 

				<td><a
					href='<spring:url value="/import/lesson/remove/${lesson.id}.html" />'
					class="btn btn-danger triggerRemove"> remove course:
						${lesson.id} </a></td>
			</tr>

		</c:forEach>


	</tbody>

</table>






<!-- Modal : alert before remove -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Remove Import</h4>
			</div>
			<div class="modal-body">Really remove?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<a href="" class="btn btn-danger removeBtn">Remove</a>
			</div>
		</div>
	</div>
</div>

