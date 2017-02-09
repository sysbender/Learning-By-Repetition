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
				// jquery validator
				$(".courseForm").validate(
						{
							rules : {
								name : {
									required : true,
									minlength : 3
								}

							},
							highlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-success').addClass('has-error');
							},
							unhighlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-error').addClass('has-success');
							}
						});

			});
</Script>


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

