<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../tiles/taglib.jsp"%>



<script type="text/javascript">
$(document).ready(function(){

    $('.play_tts').click(function(event){
        event.preventDefault();
        var t = event.target;
        var mp3 = $(t).attr('href');

        document.getElementById('word_audio').setAttribute('src', mp3);
        document.getElementById('word_audio').play();


    });
});

</script>
 

<c:url var="firstUrl" value="/review/1.html" />
<c:url var="lastUrl" value="/review/${wordPage.totalPages}.html" />
<c:url var="previousUrl" value="/review/${currentIndex - 1}.html" />
<c:url var="nextUrl" value="/review/${currentIndex + 1 }.html" />


<h2>review:</h2>

<ul class="pagination">
	<c:choose>
		<c:when test="${currentIndex == 1}">
			<li class="disabled"><a href="#">&lt;&lt;</a></li>
			<li class="disabled"><a href="#">&lt;</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${firstUrl}">&lt;&lt;</a></li>
			<li><a href="${prevUrl}">&lt;</a></li>
		</c:otherwise>
	</c:choose>


	<c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
		<c:url var="pageUrl" value="/review/${i}.html" />
		<c:choose>
			<c:when test="${i == currentIndex}">
				<li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>



	<c:choose>
		<c:when test="${currentIndex == wordPage.totalPages}">
			<li class="disabled"><a href="#">&gt;</a></li>
			<li class="disabled"><a href="#">&gt;&gt;</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${nextUrl}">&gt;</a></li>
			<li><a href="${lastUrl}">&gt;&gt;</a></li>
		</c:otherwise>
	</c:choose>

</ul>



<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>word</th>
			<th>play</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach var="word" items="${wordPage.content }">
			<tr>
				<td>${word.txt}</td>
				<td> 
				<span class="glyphicon glyphicon-play-circle play_tts"  
				href="<spring:url value="http://localhost:8000/GetAudioStream?s=${word.txt}"/>"></span>
				 
			</tr>
		</c:forEach>
	</tbody>
</table>

<audio src="" id="word_audio"></audio>



