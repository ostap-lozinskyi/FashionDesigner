<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<title>Admin</title>
</head>
<body style="background: url(/resources/img/fon2.jpg)">
	<div class="container" style="background-color: white;">
	    <div class="row">
			<div class="col-12">
				<h1 class="text-center">Admin</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-2 col-sm-12">								
				<div class="btn-group-vertical text-center" role="group" aria-label="Basic example">
					<a class="btn btn-outline-primary" href="/">Головна сторінка</a>
					<a class="btn btn-primary" href="/admin">Admin</a>
					<a class="btn btn-outline-primary" href="/admin/adminUsers">Users</a>
					<a class="btn btn-outline-primary" href="/admin/adminClothingModels">Clothing Models Manag.</a>						
					<a class="btn btn-outline-primary" href="/admin/adminCollections">Collections Manag.</a>
					<a class="btn btn-outline-primary" href="/admin/adminTypeOfCollections">Type Of Collections</a>
				</div>				
			</div>
			<div class="col-lg-9 col-sm-12">
			    <a class="btn btn-outline-primary" href="/userCabinet">User Cabinet</a>
			</div>
		</div>
		<br>
	</div>
</body>
</html>