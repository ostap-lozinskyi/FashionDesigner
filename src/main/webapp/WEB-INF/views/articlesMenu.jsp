<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" href="/resources/css/rateStars.css" type="text/css"/>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

<link href="/resources/css/index.css" rel="stylesheet">
<title>Статті</title>
</head>
<body style="background: url(/resources/img/fon2.jpg)">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<h1 class="text-center">Статті</h1>
			</div>
		</div>	
		<div class="row">
			<div class="col-lg-2 col-sm-4 text-center">
				<br>
				<a class="btn-cart buy btnCafe btn-sucsess btn-lg" href="/">Main page</a>
			</div>
<!-- 			<div class="col-12"> -->
<!-- 				<br> -->
<!-- 				<p> -->
<!-- 					<button class="btn-cart buy btnCafe btn-sucsess btn-lg" type="button" -->
<!-- 						data-toggle="collapse" data-target="#firstCollapse" -->
<!-- 						aria-expanded="false" aria-controls="firstCollapse"> -->
<!-- 						Search meal</button> -->
<!-- 				</p> -->
<!-- 				<div class="collapse" id="firstCollapse"> -->
<!-- 					<div class="card card-body"> -->
<%-- 						<form:form action="/meal" method="GET" modelAttribute="mealFilter"> --%>
<!-- 							<div class="form-group row"> -->
<!-- 								<div class="col-lg-2 col-sm-4 margin"> -->
<%-- 									<form:input path="minRate" class="form-control" placeholder="Min rate"/> --%>
<!-- 								</div> -->
<!-- 								<div class="col-lg-2 col-sm-4 margin"> -->
<%-- 									<form:input path="maxRate" class="form-control" placeholder="Max rate"/> --%>
<!-- 								</div> -->
<!-- 								<div class="col-lg-2 col-sm-4 margin"> -->
<%-- 									<form:input path="minPrice" class="form-control" placeholder="Min price"/> --%>
<!-- 								</div> -->
<!-- 								<div class="col-lg-2 col-sm-4 margin"> -->
<%-- 									<form:input path="maxPrice" class="form-control" placeholder="Max price"/> --%>
<!-- 								</div>					 -->
<!-- 								<div class="col-lg-2 col-sm-4 margin"> -->
<%-- 									<form:input path="minWeight" class="form-control" placeholder="Min weight"/> --%>
<!-- 								</div> -->
<!-- 								<div class="col-lg-2 col-sm-4 margin"> -->
<%-- 									<form:input path="maxWeight" class="form-control" placeholder="Max weight"/> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="form-group row"> -->
<!-- 								<div class="col-lg-2 col-sm-4 margin"> -->
<%-- 									<form:input path="search" class="form-control" placeholder="By name"/> --%>
<!-- 								</div>						 -->
<!-- 								<div class="col-lg-2 col-sm-4 margin"> -->
<!-- 									<p>								 -->
<!-- 										<button class="btn btn-outline-secondary" type="button" -->
<!-- 											data-toggle="collapse" data-target="#secondCollapse" -->
<!-- 											aria-expanded="false" aria-controls="secondCollapse"> -->
<!-- 											Select cuisine</button> -->
<!-- 									</p> -->
<!-- 									<div class="collapse" id="secondCollapse"> -->
<!-- 										<div class="card card-body"> -->
<%-- 											<form:checkboxes items="${cuisines}" path="cuisineName" element="div"/> --%>
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div>						 -->
<!-- 								<div class="col-12"> -->
<!-- 		        					<button type="submit" class="btn-cart buy btnCafe btn-sucsess btn-lg">Search</button> -->
<!-- 		      					</div> -->
<!-- 							</div> -->
<%-- 						</form:form> --%>
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
		</div>
		<br>
		<div class="row">
			<div class="col-12">
				<c:if test="${empty articles.content}">
	    			<h2 class="text-center">Articles with such parameters not found</h2>
				</c:if>
			</div>
			<c:forEach var="article" items="${articles.content}">
				<div class="col-2">
					<img src="${article.photoUrl}?version=${article.version}" style="width: 100px;">
				</div>
				<div class="col-10">
					${article.title}
				</div>
			</c:forEach>			
		</div>
	</div>
</body>
</html>