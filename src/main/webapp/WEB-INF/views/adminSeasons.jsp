<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="custom" uri="/WEB-INF/tags/implicit.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<link href="/resources/css/index.css" rel="stylesheet">
<title>Seasons management</title>
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
        <a class="headerItem" href="/admin/adminEmail">EMAIL</a>
    </div>
	<div class="container">
		<div class="row">
			<div class="col-12">
				<h1 class="text-center">Seasons management</h1>
			</div>
		</div>
		<div class="row">				
			<div class="col-12">
                <div class="row">
                    <div class="col-12">
                        <h3>Add new Season</h3>
                        <form:form action="/admin/adminSeasons" method="POST" modelAttribute="season" enctype="multipart/form-data">
                            <custom:hiddenInputs excludeParams="name, _csrf"/>
                            <div class="row">
                                <div class="col-10 ml-auto" style="color: red;">
                                    <form:errors path="name" />
                                </div>
                            </div> 
                            <div class="form-group row">
                                <label class="col-2 col-form-label" for="name">Name:</label>
                                <div class="col-10">
                                    <form:input class="form-control" id="name" path="name"/>
                                </div>
                            </div>                    
                            <div class="form-group row">
                                <div class="col-8 mr-auto">
                                    <button class="btn btn-sm btn-outline-primary">Save</button>
                                    <a href="/admin/adminSeasons/cancel<custom:allParams/>" class="btn btn-sm btn-outline-warning">Cancel</a>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
		        <br>
		        <div class="row">			
                    <div class="col-2 ml-auto">
                        <button class="dropdown-toggle btn btn-outline-success btn-sm" type="button" data-toggle="dropdown">Sort</button>
                        <div class="dropdown-menu">
                            <custom:sort innerHtml="Name asc" paramValue="name"/>
                            <custom:sort innerHtml="Name desc" paramValue="name,desc"/>
                        </div>
                    </div>
                    <div class="col-2">
                        <custom:size posibleSizes="1,2,5,10" size="${showSeasons.size}" />
                    </div>			
		        </div>
		        <br>
                <div class="row">
                    <div class="col-12 ml-auto" style="color: red;">
                        <p>${error}<p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <table class="table table-bordered">
                            <tr>
                                <th class="text-center">Name</th>
                                <th class="text-center">Options</th>                               
                            </tr>
                            <c:if test="${empty showSeasons.content}">
                                <tr>
                                <td colspan=7><h3 class="text-center">Seasons with such parameters not found</h3></td>
                                </tr>
                            </c:if>
                            <c:forEach var="showSeason" items="${showSeasons.content}">
                                <tr>
                                    <td>${showSeason.name}</td>
                                    <td class="text-center">
                                        <a href="/admin/adminSeasons/update/${showSeason.id}<custom:allParams/>" class="btn btn-outline-warning btn-sm margin">Update</a>
                                        <a href="/admin/adminSeasons/delete/${showSeason.id}<custom:allParams/>" class="btn btn-outline-danger btn-sm margin">Delete</a>
                                    </td>                                    
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
		        </div>
                <div class="row">
                    <div class="col-12">
                        <custom:pageable page="${showSeasons}"/>
                    </div>
                </div>		    
			</div>
		</div>
	</div>
</body>
</html>