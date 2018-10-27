<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Olha Lozinska</title>

    <!-- Global site tag (gtag.js) - Google Analytics -->
    <script async src="https://www.googletagmanager.com/gtag/js?id=UA-118033320-3"></script>
    <script>
        window.dataLayer = window.dataLayer || [];

        function gtag() {
            dataLayer.push(arguments);
        }

        gtag('js', new Date());
        gtag('config', 'UA-118033320-3');
    </script>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
            integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

    <link href="/resources/css/index.css" rel="stylesheet">
    <script src="/resources/js/footer.js"></script>
</head>
<body>
<div class="text-center">
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
    <div class="row">
        <div class="col-sm-12 text-center">
            <a href="/aboutUs">
                <video class="mainVideo" autoplay loop muted playsInline>
                    <source src="https://lozinska.ml/resources/video/videoIntro.mp4" type="video/mp4">
                </video>
            </a>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-6 text-center">
            <div class="row">
                <div class="col-12 text-center">
                    <a href="https://bestpeopleclub.com/17-th-odessa-fashion-day-dnevnik-vtorogo-dnya.html"
                       target="_blank">
                        <img src="/resources/img/Odessa-Fashion-Day.jpg" class="articlePhoto">
                    </a>
                </div>
            </div>
            <div class="row">
                <div class="col-12 text-center">
                    <a href="https://bestpeopleclub.com/17-th-odessa-fashion-day-dnevnik-vtorogo-dnya.html"
                       target="_blank">
                        <p class="articleTitle">17-TH ODESSA FASHION DAY</p>
                        <p class="articleText">Дизайнер Ольга Лозінська представила актуальні наряди для літнього
                            сезону, який як правило насичений подіями: випускні вечори, весілля, святкування,
                            різноманітні вечірки.</p>
                    </a>
                </div>
            </div>
        </div>
        <div class="col-lg-6 text-center">
            <div class="row">
                <div class="col-12 text-center">
                    <a href="https://www.instagram.com/p/BjmUUhHgsln/?taken-by=slanovskiy_kiev" target="_blank">
                        <img src="/resources/img/slanovsky.jpg" class="articlePhoto">
                    </a>
                </div>
            </div>
            <div class="row">
                <div class="col-12 text-center">
                    <a href="https://www.instagram.com/p/BjmUUhHgsln/?taken-by=slanovskiy_kiev" target="_blank">
                        <p class="articleTitle">OLHA LOZINSKA У КИЄВІ</p>
                        <p class="articleText">Відтепер приміряти вишукані наряди від дизайнера Ольга Лозінська можна і
                            в столиці, у салоні вечірніх та весільних суконь Slanovskiy, по вул. С. Петлюри, 6</p>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-12 text-center">
            <a href="/hauteCouture"><p class="mainTitle">THE NEW COLLECTIONS</p></a>
        </div>
    </div>
    <div class="row">
        <c:forEach var="showClothingModel" items="${showClothingModels}" end="2">
            <div class="col-lg-4 col-md-12 text-center">
                <a href="/hauteCouture/${showClothingModel.id}">
                    <img src="${showClothingModel.photoUrls[0]}?version=${showClothingModel.version}"
                         class="itemMainPage">
                </a>
            </div>
        </c:forEach>
    </div>
</div>
<div class="footer">
    <div class="footerFirstRow">
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
    <div class="footerDevelopedBy">
        Developed by <a href="https://ua.linkedin.com/in/ostap-lozinskyj" target="_blank" class="underlined">Ostap
        Lozinskyj</a>
    </div>
</div>
</body>
</html>