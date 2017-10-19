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
<title>Measuring units</title>
</head>
<body style="background: url(/resources/img/fon2.jpg)">
	<div class="container" style="background-color: white;">
		<div class="row">
			<div class="col-12">
				<h1 class="text-center">Measuring units</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-2 col-sm-12">				
				<div class="btn-group-vertical text-center" role="group" aria-label="Basic example">
					<a class="btn btn-outline-success" href="/">Main Page</a>
                    <a class="btn btn-outline-success" href="/admin">Admin</a>	
                    <a class="btn btn-outline-success" href="/admin/adminUser">Users</a>				
					<a class="btn btn-outline-success" href="/admin/adminIngredient">Ingredients</a>
					<a class="btn btn-success" href="/admin/adminMs">Mss</a>
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
                        <h3>Add new Measuring unit</h3>	
                        <form:form action="/admin/adminMs" method="POST" modelAttribute="ms">
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
                                    <button class="btn btn-sm btn-outline-success">Save</button>
                                    <a href="/admin/adminMs/cancel<custom:allParams/>" class="btn btn-sm btn-outline-warning">Cancel</a>
                                </div>
                            </div>
                        </form:form>
			        </div>
			        <div class="col-3">
                        <form:form action="/admin/adminMs" method="GET" modelAttribute="filter">
                            <div class="form-group row">
                                <div class="col-12">
                                    <form:input class="form-control" path="search" placeholder="Search"/>
                                </div>
                            </div>
                        </form:form>
			        </div>
			    </div>
			    <div class="row">
					<div class="col-2 ml-auto">
							<button class="dropdown-toggle btn btn-outline-success btn-sm" type="button" data-toggle="dropdown">Sort
							</button>
							<div class="dropdown-menu">
								<custom:sort innerHtml="Name asc" paramValue="name"/>
								<custom:sort innerHtml="Name desc" paramValue="name,desc"/>
							</div>
					</div>
					<div class="col-2">
						<custom:size posibleSizes="1,2,5,10" size="${mss.size}" />
					</div>
				</div>
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
                            <c:if test="${empty mss.content}">
                                <tr>
                                <td colspan=2><h3 class="text-center">Measuring Systems with such name not found</h3></td>
                                </tr>
                            </c:if>
                            <c:forEach var="ms" items="${mss.content}">
                                <tr>
                                    <td>${ms.name}</td>
                                    <td class="text-center">
                                        <a href="/admin/adminMs/update/${ms.id}<custom:allParams/>" class="btn btn-outline-warning btn-sm margin">Update</a>
                                        <a href="/admin/adminMs/delete/${ms.id}<custom:allParams/>" class="btn btn-outline-danger btn-sm margin">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>			
                </div>
                <div class="row">
                    <div class="col-12">
                        <custom:pageable page="${mss}"/>
                    </div>
                </div>
			</div>
		</div>		
	</div>
</body>
</html>