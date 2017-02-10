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




<!-- **************            ******************* -->
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
					class="btn btn-danger triggerRemove"> remove:
						${upload.id} </a></td>
			</tr>

		</c:forEach>


	</tbody>

</table>


<!-- *******************items **************************** -->
<h2>items list:</h2>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal"
	data-target="#myModal">New Item</button>

<form:form commandName="item" cssClass="form-horizontal itemForm">

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
					<h4 class="modal-title" id="myModalLabel">Import : New Item</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="name" class="col-sm-2" control-label>URL:</label>
						<div class="col-sm-10">
							<form:input path="audioUrl" cssClass="form-control" />
							<label>start at(s): </label>
							<form:input path="cueStart" size="5" />
							<label>end at(s): </label>
							<form:input path="cueEnd" size="5" />
						</div>
					</div>

					<div class="form-group">
						<label for="lang" class="col-sm-2" control-label>language:</label>
						<div class="col-sm-10">
							<form:input path="lang" />
							<label>match: </label>
							<form:checkbox path="match" />
						</div>
					</div>

					<div class="form-group">
						<label for="share" class="col-sm-2" control-label>Genre:</label>
						<div class="col-sm-10">
							<form:select path="genre">
								<form:option value="NONE" label="--- Select ---" />
								<form:option value="text" label="text" />
								<form:option value="explain" label="explain" />
								<form:option value="tranlation" label="translatoin" />
								<%-- <form:options items="${countryList}" /> --%>
							</form:select>
						</div>
					</div>

					<div class="form-group">
						<label for="desc" class="col-sm-2" control-label>Text:</label>
						<div class="col-sm-10">
							<form:textarea path="txt" cssClass="form-control"  rows="8"/>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input name="item" type="submit" value="Save"
						class="btn btn-primary" />
				</div>
			</div>
		</div>
	</div>
</form:form>

<!-- ************ list itmes ******** -->
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
				<td><c:out value="${item.genre}" /> - <c:out
						value="${item.lang}" /><br> <c:out value="${item.audioUrl}" />
				</td>

				<td><c:out value="${item.txt}" /></td>
				<td><a
					href='<spring:url value="/import/item/remove/${item.id}.html" />'
					class="btn btn-danger triggerRemove"> remove: ${item.id}
				</a></td>
			</tr>

		</c:forEach>


	</tbody>

</table>







<!-- ***********************   Modal : alert before remove -->
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

