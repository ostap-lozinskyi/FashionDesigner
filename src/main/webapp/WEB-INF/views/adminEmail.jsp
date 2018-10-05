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
<title>Email management</title>
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
				<h1 class="text-center">Email management</h1>
			</div>
		</div>
		<div class="row">				
			<div class="col-12">
                <div class="row">
                    <div class="col-12">
                        <h3>Add new Color</h3>
                        <form:form action="/admin/adminEmail" method="POST" modelAttribute="email" enctype="multipart/form-data">
                            <custom:hiddenInputs excludeParams="senderMail, _csrf"/>
                            <div class="row">
                                <div class="col-10 ml-auto" style="color: red;">
                                    <form:errors path="senderMail" />
                                </div>
                            </div> 
                            <div class="form-group row">
                                <label class="col-2 col-form-label" for="senderMail">Sender Mail:</label>
                                <div class="col-10">
                                    <form:input class="form-control" id="senderMail" path="senderMail"/>
                                </div>
                            </div>   
                            <div class="row">
                                <div class="col-10 ml-auto" style="color: red;">
                                    <form:errors path="senderPassword" />
                                </div>
                            </div> 
                            <div class="form-group row">
                                <label class="col-2 col-form-label" for="senderPassword">Sender Password:</label>
                                <div class="col-10">
                                    <form:input class="form-control" id="senderPassword" path="senderPassword"/>
                                </div>
                            </div>   
                            <div class="row">
                                <div class="col-10 ml-auto" style="color: red;">
                                    <form:errors path="receiverMail" />
                                </div>
                            </div> 
                            <div class="form-group row">
                                <label class="col-2 col-form-label" for="receiverMail">Receiver Mail:</label>
                                <div class="col-10">
                                    <form:input class="form-control" id="receiverMail" path="receiverMail"/>
                                </div>
                            </div>   
                            <div class="form-group row">
                                <div class="col-8 mr-auto">
                                    <button class="btn btn-sm btn-outline-primary">Save</button>
                                    <a href="/admin/adminEmail/cancel<custom:allParams/>" class="btn btn-sm btn-outline-warning">Cancel</a>
                                </div>
                            </div>
                        </form:form>
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
                                <th class="text-center">Sender Mail</th>
                                <th class="text-center">Sender Password</th>  
                                <th class="text-center">Receiver Mail</th>
                                <th class="text-center">Control</th>
                            </tr>
                            <c:if test="${empty credentials}">
                                <tr>
                                <td colspan=7><h3 class="text-center">Email credentials not found</h3></td>
                                </tr>
                            </c:if>                            
                            <tr>
                                <td>${credentials.senderMail}</td>
                                <td>${credentials.senderPassword}</td>
                                <td>${credentials.receiverMail}</td>
                                <td class="text-center">
                                    <a href="/admin/adminEmail/update/${credentials.id}<custom:allParams/>" class="btn btn-outline-warning btn-sm margin">Update</a>
                                    <a href="/admin/adminEmail/delete/${credentials.id}<custom:allParams/>" class="btn btn-outline-danger btn-sm margin">Delete</a>
                                </td>                                    
                            </tr>                            
                        </table>
                    </div>
		        </div>                	    
			</div>
		</div>
	</div>
</body>
</html>