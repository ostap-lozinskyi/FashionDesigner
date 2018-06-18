<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
            <%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
                <!DOCTYPE html>
                <html>
                <head>
                    <!-- Global site tag (gtag.js) - Google Analytics -->
                    <script async src="https://www.googletagmanager.com/gtag/js?id=UA-118033320-3"></script>
                    <script>
                    window.dataLayer = window.dataLayer || [];
                    function gtag(){dataLayer.push(arguments);}
                    gtag('js', new Date());
                    gtag('config', 'UA-118033320-3');
                    </script>

                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                    <title>Olha Lozinska</title>
                    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
					<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
					<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
                    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script> 
                                      
                    <style>
                        .active {
                        box-shadow: 8px 8px 5px grey;
                        }
                    </style>                   
                                       
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
                            <div class="col-lg-3 col-md-1 text-center">
                                <a class="arrow" href="/readyToWear/${previousModel}"><img src="/resources/img/arrowLeft.png" style="width: 100px;"></a>
                            </div>                           
                            <div class="col-lg-6 col-md-10 text-center">
                                <div id="carouselExampleIndicators" class="carousel" data-ride="carousel">
                                    <div class="carousel-inner">
                                        <div class="carousel-item active">
                                            <img class="photoID" data-toggle="modal" data-target="#exampleModalCenter" src="${clothingModel.photoUrls[0]}?version=${clothingModel.version}" alt="First slide">                                            
                                        </div>
                                        <c:forEach var="photoUrl" items="${clothingModel.photoUrls}" begin="1">
                                            <div class="carousel-item">
                                                <img class="photoID" data-toggle="modal" data-target="#exampleModalCenter" src="${photoUrl}?version=${clothingModel.version}" alt="...">
                                            </div>
                                        </c:forEach>                                    
                                    </div>
                                    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                        <span class="sr-only">Next</span>
                                    </a>
                                    <script>
                                    $('.carousel-control-prev').click(function() {                                        
                                        var element = document.getElementsByClassName('photoIDmini');
                                        var i;
                                        
                                        for (i = 0; i < element.length; i++) {
                                            if (element[i].classList.contains("active")) { 
                                                element[i].classList.remove("active");
                                                if (element[i-1]) {
                                                   element[i-1].classList.add("active"); 
                                                } else {
                                                   element[element.length-1].classList.add("active");  
                                                }                                                
                                                break;
                                            }                                            
                                        }                                                                                
                                    });
                                    </script>
                                    <script>
                                    $('.carousel-control-next').click(function() {                                        
                                        var element = document.getElementsByClassName('photoIDmini');
                                        var i;
                                        
                                        for (i = 0; i < element.length; i++) {
                                            if (element[i].classList.contains("active")) { 
                                                element[i].classList.remove("active");
                                                if (element[i+1]) {
                                                   element[i+1].classList.add("active"); 
                                                } else {
                                                   element[0].classList.add("active");  
                                                }                                                
                                                break;
                                            }                                            
                                        }                                                                                
                                    });
                                    </script>                                      
                                </div>
                            </div> 
                            <div class="col-lg-3 col-md-1 text-center">
                                <a class="arrow" href="/readyToWear/${nextModel}"><img src="/resources/img/arrowRight.png" style="width: 100px;"></a>
                            </div>                   
                        </div>
                        <div class="row">
                            <div class="col-12 text-center">                                
                                <img data-target="#carouselExampleIndicators" data-slide-to="0" class="photoIDmini active" src="${clothingModel.photoUrls[0]}?version=${clothingModel.version}"> 
                                <c:set var="count" value="1" scope="page" />                         
                                <c:forEach var="photoUrl" items="${clothingModel.photoUrls}" begin="1">                                   
                                    <img data-target="#carouselExampleIndicators" data-slide-to="${count}" class="photoIDmini" src="${photoUrl}?version=${clothingModel.version}">                                     
                                    <c:set var="count" value="${count + 1}" scope="page"/>
                                </c:forEach>
                                <script>
                                    $('.photoIDmini').click(function() {                                        
                                        var element = document.getElementsByClassName('active'); 
                                        element[1].classList.remove("active");

                                        $(this).addClass('active');                                        
                                    });
                                </script>
                                <script>
                                    $('.photoID').click(function() {                                        
                                        var elementActive = document.getElementsByClassName('active'); 
                                        var adress = elementActive[1].getAttribute('src');
                                        
                                        var elementModal = document.getElementsByClassName('photoIDmodal');                                        
                                        elementModal[0].src = adress;
                                        
                                        var modalDialog = document.getElementsByClassName('modal-dialog');
                                        
                                        var screenHeight = screen.height;
                                        var windowWidth = window.innerWidth;
                                        if(window.innerHeight > window.innerWidth){
                                            if(elementModal[0].width<elementModal[0].height) {
                                                modalDialog[0].style.width = windowWidth*0.95+"px";                                         
                                            } else {
                                                modalDialog[0].style.width = screenHeight/1.8+"px";
                                            }  
                                        } else {
                                            if(elementModal[0].width<elementModal[0].height) {
                                               modalDialog[0].style.width = screenHeight/1.75+"px";                                            
                                            } else {
                                               modalDialog[0].style.width = windowWidth*0.7+"px";                                           
                                            }   
                                        }
                                                                            
                                    });
                                </script>                                
                            </div>
                        </div>
                    </div>
                    <!-- Modal -->
                    <div class="modal fade text-center" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-lg" role="document">
                            <div class="modal-content">                                
                                <div class="modal-body">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                    <img class="photoIDmodal" src="">                                    
                                </div>                                
                            </div>
                        </div>
                    </div>
                    <!-- Modal end-->
                        <div class="text-center footer">
                            <div class="container">
                               <div class="row">
                                   <div class="col-8 footerText">
                                    © Olha Lozinska 2018
                                   </div>
                                   <div class="col-4">                                       
                                       <div class="row">
                                            <div class="col-12 text-left">
                                                <span>Follow Us:</span>
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
                    <script>
                        $('.carousel').carousel({
                            interval: false,                            
                        })
                    </script>
                </body>
                </html>