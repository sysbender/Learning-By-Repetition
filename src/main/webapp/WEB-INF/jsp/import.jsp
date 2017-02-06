<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../tiles/taglib.jsp"%>


<Script type="text/javascript">
	$(document).ready(function() {
		
		$(".triggerRemove").click(function(e) {
			e.preventDefault(); // do not run link
			$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
			$("#modalRemove").modal();
		});
		
	});
</Script>


<h2>imports of ${user.name}</h2>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal"
	data-target="#myModal">New Course</button>

<form:form commandName="course" cssClass="form-horizontal">

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Import New Course</h4>
				</div>
				<div class="modal-body">



					<div class="form-group">
						<label for="name" class="col-sm-2" control-label>name:</label>
						<div class="col-sm-10">
							<form:input path="name" cssClass="form-control" />
							<form:errors path="name"/>
						</div>
					</div>

					<div class="form-group">
						<label for="desc" class="col-sm-2" control-label>description:</label>
						<div class="col-sm-10">
							<form:textarea path="desc" cssClass="form-control" />
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input type="submit" value="Save" class="btn btn-primary" />
				</div>
			</div>
		</div>
	</div>
</form:form>




<table class="table table-bordered">
	<thead>
		<tr>
			<th>course name</th>
			<th>course description</th>
			<th>course language</th>
			<th>remove</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach var="course" items="${user.courses }">

			<tr>
				<td>${course.name }</td>
				<td>${course.desc }</td>
				<td>${course.lang }</td>
				<td><a
					href='<spring:url value="/import/remove/${course.id}.html" />'
					class="btn btn-danger triggerRemove"> remove course:
						${course.id} </a></td>
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