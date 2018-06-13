<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
<link href="/resources/css/index.css" rel="stylesheet">
<title>Cabinet</title>
</head>
<body>
    <div class="header">
        <a class="headerItem" href="/"><img src="/resources/img/home.png" class="homePict"></a>
        <a class="headerItem" href="/admin">ADMIN</a>  
        <a class="headerItem" href="/admin/adminUsers">USERS</a>
        <a class="headerItem" href="/admin/adminClothingModels">MODELS</a>
        <a class="headerItem" href="/admin/adminSeasons">SEASONS</a>
        <a class="headerItem" href="/admin/adminTypeOfClothes">TYPES</a>   
        <a class="headerItem" href="/admin/adminSectionOfClothes">SECTIONS</a>
        <a class="headerItem" href="/admin/adminColors">COLORS</a>          
    </div>
	<div class="container">
		<div class="row">
			<div class="col-12">
				<h1 class="text-center">${user.email} Cabinet</h1>
			</div>
		</div>
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
		</div>
		<br>
	</div>
</body>
</html>