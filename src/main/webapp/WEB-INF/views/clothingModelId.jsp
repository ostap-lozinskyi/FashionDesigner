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
                    
                    <!-- Carousel -->
                    <script>
                        $('.carousel').carousel({
                            interval: false,
                            
                        })
                    </script> 
                   
                    <link href="/resources/css/index.css" rel="stylesheet">
                </head>
                <body>
                    <div class="text-center divLogo">
                        <img src="/resources/img/olha-lozinska-logo-1.png" class="logoID">
                    </div>                    
                    <div class="header">
                        <a class="headerItem" href="/"><img src="/resources/img/home.png" class="homePict"></a>
                        <a class="headerItem" href="/readyToWear">READY TO WEAR</a>
				        <a class="headerItem" href="/hauteCouture">HAUTE COUTURE</a>
				        <a class="headerItem" href="#">ABOUT US</a>                        
						<a class="headerItem" href="#">CONTACTS</a>       
                    </div>
                    <div class="container">
                        <div class="row"> 
                            <div class="col-lg-2 col-md-12 text-center">
                                <a class="arrow" href="/clothingModel/${previousModel}"><img src="/resources/img/arrowLeft.png" style="width: 100px;"></a>
                            </div>                           
                            <div class="col-lg-8 col-md-12 text-center carousel slide" id="carouselExampleIndicators" data-ride="carousel">
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                            <img class="photoID" src="${clothingModel.photoUrls[0]}?version=${clothingModel.version}" alt="First slide">
                                    </div>
                                    <c:forEach var="photoUrl" items="${clothingModel.photoUrls}" begin="1">
                                        <div class="carousel-item">
                                            <img img class="photoID" src="${photoUrl}?version=${clothingModel.version}" alt="...">
                                        </div>
                                    </c:forEach>
                                </div>
                            </div> 
                            <div class="col-lg-2 col-md-12 text-center">
                                <a class="arrow" href="/clothingModel/${nextModel}"><img src="/resources/img/arrowRight.png" style="width: 100px;"></a>
                            </div>                   
                        </div>
                        <div class="row">
                            <div class="col-12 text-center">  
                                <c:set var="count" value="0" scope="page" />                         
                                <c:forEach var="photoUrl" items="${clothingModel.photoUrls}">                                   
                                    <img data-target="#carouselExampleIndicators" data-slide-to="${count}" class="photoIDmini" src="${photoUrl}?version=${clothingModel.version}">                                     
                                    <c:set var="count" value="${count + 1}" scope="page"/>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                        <div class="text-center footer">
                            <div class="container">
                               <div class="row">
                                   <div class="col-8 footerText">
                                    © Olha Lozinska 2018
                                   </div>
                                   <div class="col-4">
                                       <div class="row">
                                           <div class="col-12 text-left">
                                               FOLLOW US
                                           </div>                                       
                                       </div>
                                       <div class="row">
                                           <div class="col-12 text-left">
                                                <a href="https://www.instagram.com/olha.lozinska/">
                                                    <img src="/resources/img/instagramLogo.png" class="footerLogo">
                                                </a>
                                                <a href="https://www.facebook.com/olha.lozinska.official/">
                                                    <img src="/resources/img/facebookLogo.png" class="footerLogo">
                                                </a>
                                           </div>                                       
                                       </div> 
                                   </div>
                                </div>
                            </div>
                        </div>
                </body>
                </html>