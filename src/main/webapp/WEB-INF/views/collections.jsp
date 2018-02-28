<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Collections</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    
    <link href="/resources/css/index.css" rel="stylesheet">
</head>
<body>
	<div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <div class="row">
                    <div class="col-12">
                        <h1>Collections</h1>
                    </div>
                </div>
            </div>
        </div>
		<div class="row">			
            <div class="col-lg-2 col-sm-12">
                <div class="btn-group-vertical text-center" role="group" aria-label="Basic example">
					<a class="btn btn-outline-primary" href="/">Головна сторінка</a>
					<a class="btn btn-outline-primary" href="/admin">Admin</a>
					<a class="btn btn-outline-primary" href="/admin/adminUsers">Users</a>
					<a class="btn btn-outline-primary" href="/admin/adminClothingModels">Clothing Models Man.</a>					
					<a class="btn btn-primary" href="/admin/adminCollections">Collections Manag.</a>
					<a class="btn btn-outline-primary" href="/admin/adminTypeOfCollections">Type Of Collections</a>
				</div>		
            </div>
			<div class="col-lg-10 col-md-12">
			    <c:if test="${empty collections.content}">
	    			<h2 class="text-center">Колекції за такими параметрами не знайдено</h2>
				</c:if>
				<c:forEach var="collection" items="${collections.content}">
                    <div class="col-12">                         
                        <a href="/collection/${collection.id}">${collection.name}</a>
                    </div>
			    </c:forEach>
			</div>						
		</div>
	</div>
</body>
</html>