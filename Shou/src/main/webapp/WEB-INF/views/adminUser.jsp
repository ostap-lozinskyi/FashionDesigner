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

<link href="/resources/css/index.css" rel="stylesheet">
<title>Users</title>
</head>
<body style="background: url(/resources/img/fon2.jpg)">
	<div class="container" style="background-color: white;">
		<div class="row">
			<div class="col-12">
				<h1 class="text-center">Users</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-2 col-sm-12">				
				<div class="btn-group-vertical text-center" role="group" aria-label="Basic example">
					<a class="btn btn-outline-success" href="/">Main Page</a>
					<a class="btn btn-outline-success" href="/admin">Admin</a>	
					<a class="btn btn-success" href="/admin/adminUser">Users</a>				
					<a class="btn btn-outline-success" href="/admin/adminIngredient">Ingredients</a>
					<a class="btn btn-outline-success" href="/admin/adminMs">Mss</a>
					<a class="btn btn-outline-success" href="/admin/adminComponent">Components</a>
					<a class="btn btn-outline-success" href="/admin/adminCuisine">Cuisines</a>
					<a class="btn btn-outline-success" href="/admin/adminMeal">Meals</a>
					<a class="btn btn-outline-success" href="/admin/adminOrder">Orders</a>
					<a class="btn btn-outline-success" href="/admin/adminPlace">Places</a>
				</div>								
			</div>		
			<div class="col-lg-10 col-sm-12">
                <div class="row">
                    <div class="col-12">
                        <h3>Add new Admin User</h3>
                        <form:form action="/admin/adminUser" method="POST" modelAttribute="user">
                            <custom:hiddenInputs excludeParams="email, password, repeatPassword, _csrf"/>
                            <div class="row">
                                <div class="col-10 ml-auto" style="color:red;">
                                    <form:errors path="${registrationRequest}"/>
                                </div>
					        </div>
                            <div class="row">
                                <div class="col-10 ml-auto" style="color: red;">
                                    <form:errors path="email" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-2 col-form-label" for="email">Name:</label>
                                <div class="col-10">
                                    <form:input class="form-control" id="email" path="email"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-10 ml-auto" style="color: red;">
                                    <form:errors path="password" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-2 col-form-label" for="password">Password:</label>
                                <div class="col-10">
                                    <form:password class="form-control" id="password" path="password"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-10 ml-auto" style="color: red;">
                                    <form:errors path="repeatPassword" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-2 col-form-label" for="repeatPassword">Repeat password:</label>
                                <div class="col-10">
                                    <form:password class="form-control" id="repeatPassword" path="repeatPassword"/>
                                </div>
                            </div>                            
                            <div class="form-group row">
                                <div class="col-8 mr-auto">
                                    <button class="btn btn-sm btn-outline-success">Save</button>
                                    <a href="/admin/adminUser/cancel<custom:allParams/>" class="btn btn-sm btn-outline-warning">Cancel</a>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>	
                <div class="row">
                    <div class="col-12">
                        <br>
                        <p>
                            <button class="btn-cart buy btnCafe btn-sucsess btn-lg" type="button"
                                data-toggle="collapse" data-target="#firstCollapse"
                                aria-expanded="false" aria-controls="firstCollapse">
                                Search User</button>
                        </p>
                        <div class="collapse" id="firstCollapse">
                            <div class="card card-body">
                                <form:form action="/admin/adminUser" method="GET" modelAttribute="userFilter">
                                    <div class="form-group row">
                                        <div class="col-lg-3 col-sm-4 margin">
                                            <form:input path="search" class="form-control" placeholder="By name"/>
                                        </div>				
                                        <div class="col-12">
                                            <button type="submit" class="btn-cart buy btnCafe btn-sucsess btn-lg">Search</button>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
		        </div>
		        <br>
		        <div class="row">			
                    <div class="col-2 ml-auto">
                        <button class="dropdown-toggle btn btn-outline-success btn-sm" type="button" data-toggle="dropdown">Sort</button>
                        <div class="dropdown-menu">
                            <custom:sort innerHtml="Name asc" paramValue="email"/>
                            <custom:sort innerHtml="Name desc" paramValue="email,desc"/>
                        </div>
                    </div>
                    <div class="col-2">
                        <custom:size posibleSizes="1,2,5,10" size="${users.size}" />
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
                                <th class="text-center">Role</th>                                
                                <th class="text-center">Photo</th>
                                <th class="text-center">Options</th>
                            </tr>
                            <c:if test="${empty users.content}">
                                <tr>
                                <td colspan=7><h3 class="text-center">Users with such parameters not found</h3></td>
                                </tr>
                            </c:if>
                            <c:forEach var="user" items="${users.content}">
                                <tr>
                                    <td>${user.email}</td>
                                    <td>${user.role}</td>                                    
                                    <td class="text-center">
                                        <img src="${user.photoUrl}?version=${user.version}" style="width: 100px;">
                                    </td>
                                    <td class="text-center">
                                        <a href="/admin/adminUser/setDefaultPhoto/${user.id}<custom:allParams/>" class="btn btn-outline-warning btn-sm margin">Set default photo</a>
                                        <a href="/admin/adminUser/delete/${user.id}<custom:allParams/>" class="btn btn-outline-danger btn-sm margin">Delete</a>
                                        <br><button class="btn btn-outline-success dropdown-toggle margin" type="button"
                                                id="dropdownMenuButton" data-toggle="dropdown"
                                                aria-haspopup="true" aria-expanded="false">Update Role</button>
                                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                            <a class="dropdown-item" href="/admin/adminUser/updateRole/${user.id}/ROLE_ADMIN<custom:allParams/>">Admin</a>
                                            <a class="dropdown-item" href="/admin/adminUser/updateRole/${user.id}/ROLE_CLIENT<custom:allParams/>">Client</a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
		        </div>
                <div class="row">
                    <div class="col-12">
                        <custom:pageable page="${users}"/>
                    </div>
                </div>		    
			</div>
		</div>
	</div>
</body>
</html>