<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="custom" uri="/WEB-INF/tags/implicit.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

<link rel="stylesheet" href="/resources/chosen/style.css">
<link rel="stylesheet" href="/resources/chosen/chosen.css">

<link href="/resources/css/index.css" rel="stylesheet">

<title>Order</title>
</head>
<body style="background: url(/resources/img/fon2.jpg)">
	<div class="container" class="container" style="background-color: white;">
		<div class="row">
			<div class="col-12">
				<h1 class="text-center">Order</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="text-center">
				<div class="btn-group text-center" role="group" aria-label="Basic example">
					<a class="btn btn-outline-success" href="/">Main Page</a>
					<a class="btn btn-outline-success" href="/meal">Menu</a>
					<a class="btn btn-outline-success" href="/place">Tables</a>
				</div>
				</div>				
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-12">
				<form:form action="/place/${placeCurrent.id}/order" method="POST" modelAttribute="order">
					<div class="row">
						<div class="col-6 ml-auto" style="color: red;">
							<form:errors path="meals" />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label" for="meal">Meals:</label>
						<div class="col-6">
							<div class="side-by-side clearfix">
								<form:select data-placeholder="Select meals" class="chosen-select" id="meal" path="meals">
									<option value=""></option>
									<c:forEach var="meal" items="${meals}">
										<option>${meal}</option>
									</c:forEach>
								</form:select>
								<script src="/resources/chosen/chosen.jquery.js" type="text/javascript"></script>
								<script src="/resources/chosen/init.js" type="text/javascript" charset="utf-8"></script>
							</div>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-8 mr-auto">
							<button class="btn-cart buy btnCafe btn-sucsess btn-lg">Order</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<table class="table table-bordered">
					<tr>
						<th class="text-center">Meal</th>
						<th class="text-center" style="width:20%">Status</th>
					</tr>
					<c:if test="${empty orders}">
		    			<tr>
		    			<td colspan=3><h3 class="text-center">There are no orders in this table</h3></td>
		    			</tr>
					</c:if>
					<c:forEach var="order" items="${orders}">
						<tr>
							<td>
								<div class="row">
									<c:forEach var="orderedMeal" items="${order.mealViews}">
										<div class="col-2">
											<div class="row">
												<div class="col-12">
													<img src="${orderedMeal.photoUrl}?version=${orderedMeal.version}" style="height: 50px">
												</div>
											</div>
											<div class="row">
												<div class="col-2">
													${orderedMeal.name}
												</div>
											</div>
										
										</div>
									</c:forEach>
								</div>
							</td>
							<td class="text-center">${order.status}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>