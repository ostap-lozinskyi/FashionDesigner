<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="custom" uri="/WEB-INF/tags/implicit.tld"%>
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

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    <link href="/resources/css/index.css" rel="stylesheet">
    <script src="/resources/js/footer.js"></script>
<title>Olha Lozinska</title>
</head>
<body>
    <div class="text-center divLogo">
        <img src="/resources/img/olha-lozinska-logo-1.png" class="logoID">
    </div>
    <div class="header">
        <div class="headerItem"><a href="/"><img src="/resources/img/home.png" class="homePict"></a></div>
        <div class="headerItem"><a href="/readyToWear">READY TO WEAR</a></div>
        <div class="headerItem"><a href="/hauteCouture">HAUTE COUTURE</a></div>
        <div class="headerItem"><a href="/aboutUs">ABOUT US</a></div>
        <div class="headerItem"><a href="/contacts">CONTACTS</a></div>
    </div>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-6 contactsText">
				<h1 class="text-center">Зв'язатися з нами Ви можете</h1>
                <ul>
                    <li>за телефоном <span class="textBold">+38067-33-70-672</span></li>
                    <li>електронною поштою: <span class="textBold">olhalozinska.official@gmail.com</span></li>
                    <li>за допомогою форми зворотнього зв'язку</li>
                </ul>

                </p>
			</div>
		</div>
        <div class="row">
            <div class="col-12">
                <form:form action="/contacts" method="POST" modelAttribute="email">
                    <div class="form-group">
                        <div class="row justify-content-center">
                            <div class="col-lg-8">
                                <label class="col-form-label emailLabel" for="tel">Номер телефону або електронна пошта*</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-10 ml-auto" style="color: red;">
                                <form:errors path="tel"/>
                            </div>
                        </div>
                        <div class="row justify-content-center">
                            <div class="col-lg-8">
                                <form:input class="form-control emailTel" id="tel" path="tel"/>
                            </div>
                        </div>
                        <div class="row justify-content-center">
                            <div class="col-lg-8 text-left">
                                <label class="col-form-label emailLabel" for="text">Текст повідомлення*</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-10 ml-auto" style="color: red;">
                                <form:errors path="text"/>
                            </div>
                        </div>
                        <div class="row justify-content-center">
                            <div class="col-lg-8 ">
                                <form:textarea class="form-control emailText" id="text" path="text"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row justify-content-center">
                        <div class="col-8 text-center">
                            <button class="emailSendButton">Send</button>
                        </div>
                    </div>
                    <div class="row justify-content-center">
                        <div class="col-8 ">
                            <p>${emailSent}<p>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
	</div>
    <div class="footer">
        <div class="footerText">
            <a href="/aboutUs">© Olha Lozinska 2018</a>
        </div>
        <div class="footerFollow">
            Follow Us:
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
</body>
</html>