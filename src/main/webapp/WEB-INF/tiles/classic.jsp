<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ include file="../tiles/taglib.jsp"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>

<!-- ############ my style  -->
<spring:url value="/resource/css/style.css" var="mystyle"/>
<link  rel="stylesheet" href="${mystyle}" />

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>


<script type="text/javascript"
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/jquery.validate.js"></script>



<title><tiles:getAsString name="title" /></title>
</head>
<body>

	<%@ taglib prefix="tilesx"
		uri="http://tiles.apache.org/tags-tiles-extras"%>
	<tilesx:useAttribute name="current" />

	<div class="container">

		<!-- Static navbar -->
		<nav class="navbar navbar-default">
			<div class="container-fluid">

				<!--  logo  -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="<spring:url value="/"/>">LBR</a>
				</div>


				<!--  menu in top -->
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<!-- ******** menu list ******* -->


						<li class="${current =='index' ? 'active' : '' }"><a
							href='<spring:url value="/"/>'>Home</a></li>


						<!-- ******** menu for admin   *********** -->

						<security:authorize access=" hasRole('ROLE_ADMIN')">
							<li class="${current =='users' ? 'active' : '' }"><a
								href='<spring:url value="/users.html"/>'>Users</a></li>
						</security:authorize>

						<!-- ******** menu for login user   *********** -->
						<security:authorize access="isAuthenticated()">
							<li class="${current =='account' ? 'active' : '' }"><a
								href='<spring:url value="/account.html"/>'>My Account</a></li>

							<li class="${current =='import' ? 'active' : '' }"><a
								href='<spring:url value="/import.html"/>'>Import</a></li>

							<li class="${current =='learn' ? 'active' : '' }"><a
								href='<spring:url value="/learn.html"/>'>Learn</a></li>

							<li class="${current =='review' ? 'active' : '' }"><a
								href='<spring:url value="/review.html"/>'>Review</a></li>

						</security:authorize>
					</ul>


					<ul class="nav navbar-nav navbar-right">

						<!-- ******** menu for guest  *********** -->
						<security:authorize access=" ! isAuthenticated()">
							<li class="${current =='register' ? 'active' : '' }"><a
								href='<spring:url value="/register.html"/>'>Register</a></li>

							<li class="${current =='login' ? 'active' : '' }"><a
								href='<spring:url value="/login.html"/>'>Login</a></li>
						</security:authorize>

						<!-- ******** menu for login user   *********** -->
						<security:authorize access="isAuthenticated()">
							<li><a href='<spring:url value="/logout"/>'>Logout</a></li>
						</security:authorize>



						<c:if test="${sessionScope.target_lang == null }">
							<c:set var="target_lang" value="en" scope="session" />
						</c:if>

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">learn <img
								src='<spring:url value="/resource/img/${target_lang}.png"/>' />
								${target_lang} <span class="caret"></span>
						</a>
							<ul class="dropdown-menu">
								<li><a href='<spring:url value="/index/en.html"/>'>learn
										<img src='<spring:url value="/resource/img/en.png"/>' /> en
								</a></li>
								<li><a href='<spring:url value="/index/fr.html"/>'>learn
										<img src='<spring:url value="/resource/img/fr.png"/>' /> fr
								</a></li>
							</ul></li>
					</ul>




				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container-fluid -->
		</nav>


		<!--  **********body*********** -->
		<tiles:insertAttribute name="body" />


		<br> <br>


		<!--  **********footer*********** -->
		<div class="text-center">
			<tiles:insertAttribute name="footer" />
		</div>

	</div>
	<!-- /container -->



</body>
</html>