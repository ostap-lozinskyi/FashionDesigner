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
<title>Olha Lozinska</title>
</head>
<body>
    <div class="text-center divLogo">
        <img src="/resources/img/olha-lozinska-logo-1.png" class="logoID">
    </div> 
    <div class="header">
        <a class="headerItem" href="/"><img src="/resources/img/home.png" class="homePict"></a>
        <a class="headerItem" href="/readyToWear">READY TO WEAR</a>
        <a class="headerItem" href="/hauteCouture">HAUTE COUTURE</a>
        <a class="headerItem" href="/aboutUs">ABOUT US</a>                        
        <a class="headerItem" href="/contacts">CONTACTS</a>   
    </div>
	<div class="container">
		<div class="row">
			<div class="col-12">
				<h1 class="text-center">Контакти</h1>
			</div>
		</div>
		<div class="row">				
			<div class="col-12">
                <div class="row">
                    <div class="col-12">
                        <h3>Зв'язатися з нами</h3>
                        <form:form action="/contacts" method="POST" modelAttribute="email">
                            <div class="form-group row">
                                <label class="col-2 col-form-label" for="tel">Номер телефону або електронна пошта:</label>
                                <div class="col-10">
                                    <form:input class="form-control" id="tel" path="tel"/>
                                </div>
                                <label class="col-2 col-form-label" for="text">Текст повідомлення:</label>
                                <div class="col-10">
                                    <form:input class="form-control" id="text" path="text"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-8 mr-auto">
                                    <button class="btn btn-sm btn-outline-success">Save</button>
                                    <a href="/contacts" class="btn btn-sm btn-outline-warning">Cancel</a>
                                </div>
                            </div>
				        </form:form>
                    </div>
                </div>		         
			</div>
		</div>
	</div>
</body>
</html>