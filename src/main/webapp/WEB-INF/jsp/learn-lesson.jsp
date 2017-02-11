<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/tiles/taglib.jsp"%>


<div class="container">
	<div class="row">
		<div class="col-md-8">
			<h2>lesson items</h2>
			<!-- tab navigation -->

			<ul class="nav nav-tabs">
				<c:forEach varStatus="status" var="item1" items="${items}">

					<li class="<c:if test="${status.first }">active</c:if>"><a
						href="#${item1.genre} " data-toggle="tab"> ${item1.genre}</a></li>

				</c:forEach>
			</ul>

			<!--  tab sections -->

			<div class="tab-content">
				<c:forEach var="item2" items="${items}">

					<div class="tab-pane " id="${item2.genre}">

						<div class="panel">
							<div class="panel-heading">
								<audio controls="" name="media">
									<source src="${item2.audioUrl}" type="audio/mpeg">
								</audio>
							</div>
							<div class="panel-body" style="white-space: pre-wrap; height: 500px ; overflow-y: scroll; ">
								<p>${item2.txt}</p>
							</div>
						</div>

					</div>

				</c:forEach>
			</div>

		</div>

		<div class="col-md-4"></div>
	</div>
</div>



