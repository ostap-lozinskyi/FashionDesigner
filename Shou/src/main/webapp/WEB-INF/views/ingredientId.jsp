<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="custom" uri="/WEB-INF/tags/implicit.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/rateStars.css" type="text/css"/>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

<link href="/resources/css/index.css" rel="stylesheet">
<title>IngredientId</title>
</head>
<body style="background: url(/resources/img/fon2.jpg)">
	<div class="container" style="background-color: white;">
		<div class="row">
			<div class="col-12">
				<h1 class="text-center">Ingredient</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="text-center">
				<div class="btn-group text-center" role="group" aria-label="Basic example">
					<a class="btn btn-outline-success" href="/">Main Page</a>
					<a class="btn btn-outline-success" href="/meal">Menu</a>
					<a class="btn btn-outline-success" href="/ingredient">Ingredients</a>
				</div>
				</div>				
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-md-6 col-sm-12">
				<h3 class="text-center">Comment ${ingredient.name}</h3>
				<form:form action="/ingredient/${ingredient.id}" method="POST"
					modelAttribute="comment">
					<custom:hiddenInputs excludeParams="text, _csrf" />
					<div class="row">
						<div class="col-10 ml-auto" style="color: red;">
							<form:errors path="text" />
							${tasteMeal}
						</div>
					</div>
					<div class="form-group row">						
						<div class="col-12">
							<form:textarea class="form-control" id="text" rows="3" path="text" placeholder="Enter your comment"></form:textarea>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-8 mr-auto">
							<sec:authorize access="isAnonymous()">
								<a href="/login">Sign up for a ingredient assessment</a>
							</sec:authorize>
							<sec:authorize access="isAuthenticated()">
								<button class="btn btn-sm btn-outline-success">Save comment</button>
								<a href="/ingredient/${ingredient.id}<custom:allParams/>" class="btn btn-sm btn-outline-warning">Cancel</a>
							</sec:authorize>
						</div>
					</div>
				</form:form>
			</div>		
			<div class="col-md-6 col-sm-12">
				<h3 class="text-center">Comments</h3>
				<c:forEach var="commentsOfingredient" items="${ingredient.comments}">
					<div class="comment">
						<div class="row">
							<div class="col-2">
								<img src="${commentsOfingredient.user.photoUrl}?version=${commentsOfingredient.user.version}" style="width: 50px"> ${commentsOfingredient.user.email} 
							</div>
							<div class="col-10">
								${commentsOfingredient.text}
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<br>
		<div class="row">
				<div class="col-12">
					<h3 class="text-center">Meals with ${ingredient.name}</h3>
				</div>
			<div class="col-md-9 col-sm-12">				
				<table class="table table-bordered">
					<tr>
						<th class="text-center">Name</th>
						<th class="text-center">Photo</th>
						<th class="text-center">Options</th>
					</tr>
					<c:if test="${meals == null}">
		    			<tr>
		    				<td colspan=3><h3 class="text-center">Meals with such ingredient not found</h3></td>
		    			</tr>
					</c:if>
					<c:forEach var="meal" items="${meals.content}">
						<tr>
							<td>${meal.name}</td>
							<td><img src="${meal.photoUrl}?version=${meal.version}" style="height: 50px"></td>
							<td>
								<sec:authorize access="isAnonymous()">
									<a href="/login">
										<button type="button" class="btn-cart buy btnCafe btn-sucsess btn-lg">Order</button>
									</a>
								</sec:authorize>
								<sec:authorize access="isAuthenticated()">
									<a href="/place">
										<button type="button" class="btn-cart buy btnCafe btn-sucsess btn-lg">Order</button>
									</a>
								</sec:authorize>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="col-md-3 col-sm-12">
				<div class="row">
					<div class="col-6 text-center">
							<button class="dropdown-toggle btn btn-outline-success btn-sm" type="button" data-toggle="dropdown">Sort
							</button>
							<div class="dropdown-menu">
								<custom:sort innerHtml="Name asc" paramValue="name"/>
								<custom:sort innerHtml="Name desc" paramValue="name,desc"/>
							</div>
					</div>
					<div class="col-6 text-center">
						<custom:size posibleSizes="1,2,5,10" size="${meals.size}" />
					</div>
				</div>
                <br>
			</div>            
		</div>
		<div class="row">
			<div class="col-12">
				<c:if test="${not empty meals}">
					<custom:pageable page="${meals}"/>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>