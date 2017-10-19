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
<link rel="stylesheet" href="resources/css/rateStars.css" type="text/css"/>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

<link href="/resources/css/index.css" rel="stylesheet">
<title>ReserveTable</title>
</head>
<body style="background: url(/resources/img/fon2.jpg)">
	<div class="container" style="background-color: white;">
		<div class="row">
			<div class="col-12">
				<h1 class="text-center">Reserve Table</h1>
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
		</div>
		<div class="row">
			<div class="col-12">
				<c:if test="${not empty myPlaces}">
					<h2 class="text-center">My tables</h2>
				</c:if>
			</div>
		</div>
		<div class="row">
			<c:forEach var="myPlace" items="${myPlaces}">
				<div class="col-2">
					<br>
					<a class="btn-cart buy btnCafe btn-sucsess btn-lg" href="/place/${myPlace.id}/order">My table ${myPlace.number}</a>
				</div>
			</c:forEach>
		</div>
		<br>
		<div class="row">
			<div class="col-lg-10 col-sm-12">
				<img src="/resources/img/tables.jpg">
			</div>
			<div class="col-lg-2 col-sm-12">
				<h2>Free tables</h2>
				<c:forEach var="place" items="${places}">
					<c:if test="${place.isFree() == 'true'}">
						<div class="row">
							<sec:authorize access="isAnonymous()">
								<div class="col-12">
									<a class="btn-cart buy btnCafe btn-sucsess btn-lg" href="/login">${place.number}</a>
								</div>
							</sec:authorize>
							<sec:authorize access="isAuthenticated()">
								<div class="col-12">
									<a class="btn-cart buy btnCafe btn-sucsess btn-lg" href="/place/setUser/${place.id}">${place.number}</a>
								</div>
							</sec:authorize>
						</div>
						<br>
					</c:if>
				</c:forEach>				
			</div>
		</div>
	</div>
</body>
</html>