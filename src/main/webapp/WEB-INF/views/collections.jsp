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
            <div class="col-lg-3 col-md-4">
                <br>
                <ul>
                    <li><a href="/" title="Main page">Main page</a></li> 
                    <li><a href="/admin" title="Main page">Admin</a></li> 
                    <li><a href="/admin/adminCollections" title="Collections management">Collections management</a></li>                    
                </ul>
                <br> 
            </div>
			<div class="col-lg-7 col-md-12">
			    <c:if test="${empty collections.content}">
	    			<h2 class="text-center">Колекції за такими параметрами не знайдено</h2>
				</c:if>
				<c:forEach var="collection" items="${collections.content}">
                    <div class="col-12">
                        <img src="${collection.photoUrl}?version=${collection.version}" style="width: 100px;">                    
                        <a href="/collection/${collection.id}">${collection.name}</a>
                    </div>
			    </c:forEach>
			</div>						
		</div>
	</div>
</body>
</html>