<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../tiles/taglib.jsp" %>


<Script type="text/javascript">
	$(document).ready(function() {
		
		$(".triggerRemove").click(function(e) {
			e.preventDefault(); // do not run link
			$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
			$("#modalRemove").modal();
		});
		
	});
</Script>


<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>user name</th>
			<th>operations</th>
		</tr>

	</thead>

	<tbody>
		<c:forEach var="user" items="${users}">
			<tr>
				<td> <a href='<spring:url value="/users/${user.id}.html"/>'> ${user.name } </a>				
				</td>
				<td > <a href='<spring:url value="/users/remove/${user.id}.html"/>' class="btn btn-danger triggerRemove"> remove: ${user.name } </a>	</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<!-- Modal : alert before remove : a user-->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Remove User</h4>
			</div>
			
			<div class="modal-body">Really remove?</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<a href="" class="btn btn-danger removeBtn">Remove</a>
			</div>
		</div>
	</div>
</div>