<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/tiles/taglib.jsp"%>

<script src="https://cdn.jsdelivr.net/tether/1.1.0/tether.min.js"></script>
<script src="/resource/js/selection-menu.js"></script>
<script src="https://cdn.jsdelivr.net/mark.js/8.8.3/jquery.mark.js"></script>

<!-- Pre-load the menu -->
<div id="mymenu" class="selection-menu btn-toolbar "
	style="visibility: hidden; position: absolute">
	<ul class="btn-group">
		<li id="menu_new" class="shortcut btn btn-info">New</li>
		<li id="menu_known" class="shortcut btn btn-default">Known</li>
		<li id="menu_unknown" class="shortcut btn btn-danger">Unknown</li>
	</ul>
</div>

<Script type="text/javascript">
	$(document).ready(function() {
		
		
		var list = ${words_new};
		$.each(list, function( index, value ) {
			//alert( index + ": " + value );
			console.log(index + ": " + value );
			markWord(value, 'word_new');
		});

		
		
		markWord('the', 'word_known');
		markWord('menu', 'word_unknown');
		markWord('selection', 'word_new');
		markWord('long', 'word_unknown');
		
		
	});

	function changeState(txt, state) {
		$('mark').each(
				function(i, obj) {
					if ($(obj).text() == txt) {
						console
								.log(" index ========" + i
										+ "class name ========="
										+ $(obj).attr('class'));
						$(obj).removeClass("word_new word_known word_unknown");
						$(obj).addClass(state);

					}
				});
	}

	function markWord(txt, state) {

		var selected_text = $.trim(txt);
		var limiter = [ ".", ".", ",", "?", "!", ":", ";", "'", '"', "[", "]",
				"(", ")", "{", "}", "‐", "–", "—", "'", "/" ];
		$("#lesson_txt").mark(selected_text, {
			"accuracy" : {
				"value" : "exactly",
				"limiters" : limiter
			},
			"className" : state,
			"diacritics" : false,
			"debug" : true
		});
	}

	// add menu to selected words
	document
			.addEventListener(
					'DOMContentLoaded',
					function() {

						new SelectionMenu(
								{
									container : document
											.querySelector('#lesson_txt'),
									content : document.querySelector('#mymenu'),
									minlength : 2,
									handler : function(event) {
										var target = event.target, id = target.id
												|| target.parentNode.id // for the <strong> in the #create-new-recall
										;
										var selected_text = $
												.trim(this.selectedText);
										//var limiter = [".",".",",","?","!",":",";","'",'"',"[","]","(",")","{","}","‐","–","—","'","/"];

										if (target.id == 'menu_unknown') {
											changeState(selected_text,
													'word_unknown');
										}

										if (target.id == 'menu_new') {
											changeState(selected_text,
													'word_new');
										}

										if (target.id == 'menu_known') {
											changeState(selected_text,
													'word_known');
										}

										this.hide(true); // hide the selection after hiding the menu; useful if opening a link in a new tab
									},
									onselect : function(event) {
										// this.menu.innerHTML = 'Selection length: ' + this.selectedText.length;
										var x = event.clientX, y = event.clientY, elementMouseIsOver = document
												.elementFromPoint(x, y);
										console.log(event);
									},
									debug : false
								});

					});
	// upload words state

	function doSave() {

		/*
		 * url,  parameters, 
		
		 */
		$.getJSON("<c:url value='/learn/word/save/${lesson_id}.html'/>", //url
		{
			unknowns : 'apple'
		}, // parameters
		function(data) { // call back
			alert(" response received :" + data);
		});
	}
</Script>

<div class="container">
	<div class="row">
		<div class="col-md-8">
			<h3>lesson items</h3>
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

							<c:choose>
								<c:when test="${item2.genre == 'text'}">
									<div class="panel-body" id="lesson_txt"
										style="white-space: pre-wrap; height: 400px; overflow-y: scroll;">
										<p>${item2.txt}</p>
									</div>
								</c:when>

								<c:otherwise>
									<div class="panel-body"
										style="white-space: pre-wrap; height: 400px; overflow-y: scroll;">
										<p>${item2.txt}</p>
									</div>
								</c:otherwise>
							</c:choose>

							<c:if test="${item2.genre == 'text'}">
								<button onclick="doSave()">Save</button>
								<button onclick="showSpan()">show span</button>
							</c:if>
						</div>

					</div>

				</c:forEach>
			</div>

		</div>

		<div class="col-md-4"></div>
	</div>
</div>



