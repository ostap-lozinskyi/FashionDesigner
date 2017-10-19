<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">
	
<link href="/resources/css/index.css" rel="stylesheet">
<title>Cabinet</title>
</head>
<body style="background: url(/resources/img/fon2.jpg)">
	<div class="container" style="background-color: white;">
		<div class="row">
			<div class="col-12">
				<h1 class="text-center">${user.email} Cabinet</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-2 col-sm-4">
				<br>
				<a class="btn-cart buy btnCafe btn-sucsess btn-lg" href="/">Main page</a>
			</div>
			<div class="col-lg-2 col-sm-4">
				<br>
				<a class="btn-cart buy btnCafe btn-sucsess btn-lg" href="/meal">Menu</a>
			</div>
			<div class="col-lg-2 col-sm-4">
				<br>
				<a class="btn-cart buy btnCafe btn-sucsess btn-lg" href="/place">Tables</a>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-lg-3 col-sm-12">
				<img src="${user.photoUrl}?version=${user.version}" style="height: 200px;">
				<br>
				<br>							
				<form:form action="/userCabinet" method="POST" modelAttribute="fileRequest" enctype="multipart/form-data">
					<input name="file" type="file">
					<br>
					<br><button>Ok</button>
				</form:form>
			</div>		
			<div class="col-lg-8 col-sm-12">	
				<h3>Already eaten meals</h3>
				<table class="table table-bordered">
					<tr>
						<th class="text-center">Name</th>
						<th class="text-center">Full Description</th>
						<th class="text-center">Price</th>
						<th class="text-center">Cuisine</th>
						<th class="text-center">Photo</th>
					</tr>
					<c:if test="${empty meals}">
						<tr>
							<td colspan=7><h3 class="text-center">Meals with such
									parameters not found</h3></td>
						</tr>
					</c:if>
					<c:forEach var="meal" items="${meals}">
						<tr>
							<td>${meal.name}</td>
							<td>${meal.fullDescription}</td>
							<td>${meal.price}</td>
							<td>${meal.cuisine}</td>
							<td class="text-center"><img
								src="${meal.photoUrl}?version=${meal.version}"
								style="width: 100px;"></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<br>
	</div>
</body>
</html>