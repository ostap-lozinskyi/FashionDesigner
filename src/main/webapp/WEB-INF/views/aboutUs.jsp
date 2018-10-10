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
                   
                    <link href="/resources/css/index.css" rel="stylesheet">
                    <script src="/resources/js/footer.js"></script>
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
                            <div class="col-4">
                                <img src="/resources/img/olha-lozinska.jpg" class="aboutUsPhoto">
                            </div>
                            <div class="col-8 aboutUsText">
                                <p>Olha Lozinska – авторський бренд ексклюзивного жіночого одягу.</p> 
                                <p>В асортиментному ряді представлена широка лінійка моделей:</p>
                                <ul>
	                               <li>трендовий street style;</li>
	                               <li>романтичний smart casual;</li>
	                               <li>сексуальні coctail dresses;</li>
	                               <li>розкішні вечірні та весільні сукні</li>
	                               <li>а також ексклюзивні вироби, оздоблені авторською кутюрною вишивкою та ручною роботою від дизайнера.
                                </ul>
                                <p>В основі бренду незмінно присутні  жіночність, індивідуальність та комфорт, саме тому ми підбираємо для вас найкращі ексклюзивні тканини та фурнітуру і майстерно втілюємо мрії у життя.</p>
                                <p>Неповторний дизайн, якісне виконання, авторська система конструювання, увага до найдрібніших деталей – все це гармонійно поєднується у виробах від львівського дизайнера одягу Ольги Лозінської.</p>
                            </div>
                        </div>                        
                    </div>
                    <div class="text-center footer">
                            <div class="container">
                               <div class="row">
                                   <div class="col-7 footerText">
                                        <a href="/aboutUs">© Olha Lozinska 2018</a>
                                   </div>
                                   <div class="col-5">
                                       <div class="row">
                                            <div class="col-12 text-left">
                                                <span>Follow Us:</span>
                                                <a href="https://www.instagram.com/olha.lozinska/" target="_blank">
                                                    <img src="/resources/img/instagramLogo.png" class="footerLogo">
                                                </a>
                                                <a href="https://www.facebook.com/olha.lozinska.official/" target="_blank">
                                                    <img src="/resources/img/facebookLogo.png" class="footerLogo">
                                                </a>
                                                <a href="https://in.pinterest.com/olhalozinska/" target="_blank">
                                                    <img src="/resources/img/pinterestLogo.png" class="footerLogo">
                                                </a>
                                           </div>                                       
                                       </div> 
                                   </div>
                                </div>
                            </div>
                    </div>                     
                </body>
                </html>