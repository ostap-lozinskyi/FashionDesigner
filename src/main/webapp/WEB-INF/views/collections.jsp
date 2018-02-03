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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
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