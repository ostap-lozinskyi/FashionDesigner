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
<title>Clothing Models management</title>
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
        <a class="dropd" href="/admin/adminColors">COLORS</a>            
    </div>
	<div class="container">
		<div class="row">
			<div class="col-12">
				<h1 class="text-center">Clothing Models management</h1>
			</div>
		</div>
		<div class="row">				
			<div class="col-12">
                <div class="row">
                    <div class="col-12">
                        <h3>Add new Model</h3>
                        <form:form action="/admin/adminClothingModels" method="POST" modelAttribute="clothingModel" enctype="multipart/form-data">
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
                                    <form:errors path="text" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-2 col-form-label" for="text">Text:</label>
                                <div class="col-10">
                                    <form:textarea class="form-control" id="text" path="text"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-10 ml-auto" style="color: red;">
                                    <form:errors path="season" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-2 col-form-label" for="season">Season:</label>
                                <div class="col-10">
                                    <form:select class="form-control" id="season" path="season" onchange="${seasons}">
                                        <form:option value="" label="Select Season" style="color: gray;"/>
                                        <form:options items="${seasons}"/>
                                    </form:select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-10 ml-auto" style="color: red;">
                                    <form:errors path="typeOfClothes" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-2 col-form-label" for="typeOfClothes">Type:</label>
                                <div class="col-10">
                                    <form:select class="form-control" id="typeOfClothes" path="typeOfClothes" onchange="${typesOfClothes}">
                                        <form:option value="" label="Select Type" style="color: gray;"/>
                                        <form:options items="${typesOfClothes}"/>
                                    </form:select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-10 ml-auto" style="color: red;">
                                    <form:errors path="sectionOfClothes" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-2 col-form-label" for="sectionOfClothes">Section:</label>
                                <div class="col-10">
                                    <form:select class="form-control" id="sectionOfClothes" path="sectionOfClothes" onchange="${sectionsOfClothes}">
                                        <form:option value="" label="Select Section" style="color: gray;"/>
                                        <form:options items="${sectionsOfClothes}"/>
                                    </form:select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-10 ml-auto" style="color: red;">
                                    <form:errors path="colors" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-2 col-form-label" for="color">Color:</label>
                                <div class="col-10">
                                    <form:checkboxes items="${colors}" path="colors" element="div" />
                                </div>                                
                            </div>
                            <div class="form-group row">
                                <label class="col-2 col-form-label" for="files">Photo:</label>
                                <div class="col-10">
                                    <input name="files" type="file" multiple>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-8 mr-auto">
                                    <button class="btn btn-sm btn-outline-primary">Save</button>
                                    <a href="/admin/adminClothingModels/cancel<custom:allParams/>" class="btn btn-sm btn-outline-warning">Cancel</a>
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
                        <custom:size posibleSizes="1,2,5,10" size="${showClothingModels.size}" />
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
                        <div class="row">                               
                            <c:if test="${empty showClothingModels.content}">
                                <tr>
                                <td colspan=7><h3 class="text-center">Clothing Models with such parameters not found</h3></td>
                                </tr>
                            </c:if>
                            <c:forEach var="showClothingModel" items="${showClothingModels.content}">
                                <div class="col-3 text-center item">
                                    <div class="row">
                                        <div class="col-12">
                                            <img src="${showClothingModel.photoUrls[0]}?version=${showClothingModel.version}" style="width: 200px;">
                                        </div>
                                    </div>                                    
                                    <div class="hover">
                                        <div class="hide">
                                            <div class="row">
                                                <div class="col-12">
                                                <a href="/admin/adminClothingModels/update/${showClothingModel.id}<custom:allParams/>"	class="btn btn-warning btn-sm">Update</a>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-12">                                                  
                                                <a href="/admin/adminClothingModels/delete/${showClothingModel.id}<custom:allParams/>"	class="btn btn-danger btn-sm">Delete</a>
                                                </div>
                                            </div>
                                        </div>  
                                    </div>
                                    <div class="row">
                                        <div class="col-12">
                                             ${showClothingModel.name}
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-12">
                                            ${showClothingModel.season}
                                        </div>
                                    </div>                                                                      
                                </div>
                            </c:forEach> 
                        </div>                        
                    </div>
		        </div>               
                <div class="row">
                    <div class="col-12">
                        <custom:pageable page="${showClothingModels}"/>
                    </div>
                </div>		    
			</div>
		</div>
	</div>
</body>
</html>