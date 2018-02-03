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

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<link href="/resources/css/index.css" rel="stylesheet">
<title>MealId</title>
</head>
<body style="background: url(/resources/img/fon2.jpg)">
	<div class="container"  style="background-color: white;">
		<div class="row">
			<ul>
				<li><a href="/" title="Main page">Main page</a></li>
				<li><a href="/admin" title="Main page">Admin</a></li>
				<li><a href="/admin/adminCollections" title="Collections management">Collections management</a></li>
			</ul>
		</div>
		<br>
		<div class="row">
			<div class="col-4">
				<img class="product-img" src="${collectionView.photoUrl}?version=${collectionView.version}" width="400">
			</div>
			<div class="col-8">
				<div class="banner_info">
					<div class="back"></div>
					<div class="front">
						<div class="front_inside">
							<h1 class="center">${collectionView.text}</h1>
						</div>
					</div>
				</div>
			</div>
		</div>		
	</div>
</body>
</html>