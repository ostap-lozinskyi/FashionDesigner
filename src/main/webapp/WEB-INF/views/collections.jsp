<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Колекції</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="/resources/css/index.css" rel="stylesheet">
</head>
<body>
	<div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <div class="row">
                    <div class="col-12">
                        <h1>Колекції</h1>
                    </div>
                </div>
            </div>
        </div>
		<div class="row">			
            <div class="col-lg-3 col-md-4">
                <br>
                <ul>
                    <li><a href="/admin" title="Головна сторінка">Головна сторінка</a></li> 
                    <li><a href="/admin" title="Управління колеціями">Управління колеціями</a></li>                    
                </ul>
                <br> 
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
			<div class="col-lg-7 col-md-12">
			    <c:if test="${empty collections.content}">
	    			<h2 class="text-center">Колекції за такими параметрами не знайдено</h2>
				</c:if>
				<c:forEach var="collection" items="${collections.content}">
                    <div class="col-12">
                        <img src="${collection.photoUrl}?version=${collection.version}" style="width: 100px;">                    
                        <a href="/collection/${collection.id}">${collection.title}</a>
                    </div>
			    </c:forEach>
			</div>						
		</div>
	</div>
</body>
</html>