<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">
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
					<a class="btn btn-outline-primary" href="/admin/adminUser">Користувачі</a>					
					<a class="btn btn-outline-primary" href="/admin/adminCollections">Колекції</a>
				</div>				
			</div>
			<div class="col-lg-9 col-sm-12">
			    <a class="btn btn-outline-primary" href="/userCabinet">Налаштування користувача</a>
			</div>
		</div>
		<br>
	</div>
</body>
</html>