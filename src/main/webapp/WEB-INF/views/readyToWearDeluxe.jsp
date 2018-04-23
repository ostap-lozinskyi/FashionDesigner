<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
            <%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
                <!DOCTYPE html>
                <html>
                <head>
                   <!-- Global site tag (gtag.js) - Google Analytics -->
                    <script async src="https://www.googletagmanager.com/gtag/js?id=UA-118033320-1"></script>
                    <script>
                    window.dataLayer = window.dataLayer || [];
                    function gtag(){dataLayer.push(arguments);}
                    gtag('js', new Date());
                    gtag('config', 'UA-118033320-1');
                    </script>
                    
                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                    <title>Olha Lozinska</title>
                    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
					<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
					<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
                    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
                   
                    <link href="/resources/css/index.css" rel="stylesheet">
                </head>
                <body>
                    <div class="header">
                        <a class="dropd" href="/">MAIN</a>
                        <a class="dropd" href="/readyToWear">READY TO WEAR</a>
				        <a class="dropd" href="/readyToWearDeluxe">READY TO WEAR DE LUXE</a>  
                        <a class="dropd-item" href="/hauteCouture">HAUTE COUTURE</a>
				        <a class="dropd-item" href="#">ACCESSORIES</a>
				        <a class="dropd-item" href="#">NEW COLLECTIONS</a>
                        <a class="dropd" href="#">ABOUT</a>                        
						<a class="dropd" href="/admin">ADMIN</a>       
                    </div>
                    <div class="container">                        
                        <div class="row">
                            <div class="col-12 divLogo">
                                <img src="/resources/img/olha-lozinska-logo.png" class="logo">
                            </div>
                        </div>
                        <div class="row">                               
                            <c:if test="${empty showClothingModels.content}">
                                <div class="row">
                                    <div class="col-12">
                                        <h3 class="text-center">Clothing Models with such parameters not found</h3>
                                    </div>
                                </div>
                            </c:if>
                            <c:forEach var="showClothingModel" items="${showClothingModels.content}">
                                <div class="col-4">
                                    
                                            <a href="/clothingModel/${showClothingModel.id}">
                                                <img src="${showClothingModel.photoUrls[0]}?version=${showClothingModel.version}" class="item">
                                            </a>
                                        
                                                                                                                          
                                </div>
                            </c:forEach> 
                        </div>
                    </div>
                </body>
                </html>