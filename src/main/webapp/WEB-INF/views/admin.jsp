<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<link href="/resources/css/index.css" rel="stylesheet">
<title>Admin</title>
</head>
<body>
    <div class="header">
        <a class="dropd" href="/">MAIN PAGE</a>
        <a class="dropd" href="/admin">ADMIN</a>  
        <a class="dropd-item" href="/admin/adminUsers">USERS</a>
        <a class="dropd-item" href="/admin/adminClothingModels">MODELS</a>
        <a class="dropd-item" href="/admin/adminSeasons">SEASONS</a>
        <a class="dropd" href="/admin/adminTypeOfClothes">TYPES</a>  
        <a class="dropd" href="/admin/adminSectionOfClothes">SECTIONS</a>            
    </div>
	<div class="container" style="background-color: white;">
	    <div class="row">
			<div class="col-12">
				<h1 class="text-center">Admin</h1>
			</div>
		</div>
		<div class="row">			
			<div class="col-lg-9 col-sm-12">
			    <a class="btn btn-outline-primary" href="/userCabinet">User Cabinet</a>
			</div>
		</div>
		<br>
	</div>
</body>
</html>