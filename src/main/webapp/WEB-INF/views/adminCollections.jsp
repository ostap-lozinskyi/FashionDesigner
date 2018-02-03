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

<!--For Date-->
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/gh/atatanasov/gijgo@1.8.0/dist/combined/js/gijgo.min.js" type="text/javascript"></script>
<link href="https://cdn.jsdelivr.net/gh/atatanasov/gijgo@1.8.0/dist/combined/css/gijgo.min.css" rel="stylesheet" type="text/css" />

<link href="/resources/css/index.css" rel="stylesheet">
<title>Collections management</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-12">
				<h1 class="text-center">Collections management</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-2 col-sm-12">				
				<div class="btn-group-vertical text-center" role="group" aria-label="Basic example">
					<a class="btn btn-outline-primary" href="/">Головна сторінка</a>
					<a class="btn btn-outline-primary" href="/admin">Admin</a>
					<a class="btn btn-outline-primary" href="/admin/adminUsers">Users</a>					
					<a class="btn btn-primary" href="/admin/adminCollections">Collections Manag.</a>
					<a class="btn btn-outline-primary" href="/admin/adminTypeOfCollections">Type Of Collections</a>
				</div>								
			</div>			
			<div class="col-lg-10 col-sm-12">
                <div class="row">
                    <div class="col-12">
                        <h3>Add new collection</h3>
                        <form:form action="/admin/adminCollections" method="POST" modelAttribute="collection" enctype="multipart/form-data">
                            <custom:hiddenInputs excludeParams="name, text, _csrf"/>
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
                            <div class="row">
                                <div class="col-10 ml-auto" style="color: red;">
                                    <form:errors path="date" />
                                </div>
                            </div> 
                            <div class="form-group row">
                                <label class="col-2 col-form-label" for="name">Date:</label>
                                <div class="col-10">
                                    <input id="datepicker" width="276" />
                                    <script>$('#datepicker').datepicker({uiLibrary: 'bootstrap4'});</script>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col-10 ml-auto" style="color: red;">
                                    <form:errors path="text" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-2 col-form-label" for="text">Text:</label>
                                <div class="col-10">
                                    <form:textarea class="form-control" id="text" path="text"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-2 col-form-label" for="file">Photo:</label>
                                <div class="col-10">
                                    <input name="file" type="file">
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-8 mr-auto">
                                    <button class="btn btn-sm btn-outline-primary">Save</button>
                                    <a href="/admin/adminCollections/cancel<custom:allParams/>" class="btn btn-sm btn-outline-warning">Cancel</a>
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
                        <custom:size posibleSizes="1,2,5,10" size="${showCollections.size}" />
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
                                <th class="text-center">Text</th>
                                <th class="text-center">Options</th>
                                <th class="text-center">Photo</th>
                            </tr>
                            <c:if test="${empty showCollections.content}">
                                <tr>
                                <td colspan=7><h3 class="text-center">Collections with such parameters not found</h3></td>
                                </tr>
                            </c:if>
                            <c:forEach var="showCollection" items="${showCollections.content}">
                                <tr>
                                    <td>${showCollection.name}</td>
                                    <td>${showCollection.text}</td>
                                    <td class="text-center">
                                        <a href="/admin/adminCollections/update/${showCollection.id}<custom:allParams/>"	class="btn btn-outline-warning btn-sm margin">Update</a>
                                        <a href="/admin/adminCollections/delete/${showCollection.id}<custom:allParams/>"	class="btn btn-outline-danger btn-sm margin">Delete</a>
                                    </td>
                                    <td class="text-center">
                                        <img src="${showCollection.photoUrl}?version=${showCollection.version}" style="width: 100px;">
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
		        </div>
                <div class="row">
                    <div class="col-12">
                        <custom:pageable page="${showCollections}"/>
                    </div>
                </div>		    
			</div>
		</div>
	</div>
</body>
</html>