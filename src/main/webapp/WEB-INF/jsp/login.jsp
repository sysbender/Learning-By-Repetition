<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../tiles/taglib.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">

			<form role="form"
				action='<spring:url value="/j_spring_security_check"/>'
				method="POST">

				<div id="legend">
					<legend class="">Login</legend>
				</div>

				<div class="form-group">
					<label for="name">Name:</label> <input type="text" id="name"
						name="j_username" required autofocus" class="form-control">
				</div>

				<div class="form-group">
					<label for="password">Password:</label> <input type="password"
						id="password" name="j_password" class="form-control">
				</div>
				<div class="checkbox">
					<label> <input type="checkbox"> remember me
					</label>
				</div>

				<div class="form-group">
					<button class="btn btn-default" type="submit">Login</button>
				</div>

			</form>
		</div>
	</div>
</div>