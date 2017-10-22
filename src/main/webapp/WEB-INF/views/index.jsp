<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
            <%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
                <!DOCTYPE html>
                <html>

                <head>
                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                    <title>Шоу Дао Львів</title>
                    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
                    <link href="/resources/css/index.css" rel="stylesheet">                 

                <link href="http://allfont.ru/allfont.css?fonts=presentscript-cyrillic" rel="stylesheet" type="text/css" />
                </head>
                <body>
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-6 col-sm-12 topLogo">
                                <div class="row">
                                    <div class="col-md-2 col-sm-4">
                                        <img src="/resources/img/logo.png">
                                    </div>
                                    <div class="col-10">
                                        <p class="title">Українська федерацiя 
                                        <br>"ШОУ ДАО"</p>
                                    </div>
                                </div>                                
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-3 col-md-5 col-sm-6 mainMenu">
                                <br>                                
                                <ul>
                                    <li><a href="/" title="Новини">Новини</a></li>
                                    <li><a href="/articlesMenu" title="Статті">Статтi</a></li>
                                    <li><a href="/" title="Про Школу">Про Школу</a></li>
                                    <li><a href="/" title="Навчання">Навчання</a></li>
                                    <li><a href="/" title="Бібліотека">Бiблiотека</a></li>
                                    <li><a href="/" title="Галерея">Галерея</a></li>
                                    <li><a href="/" title="Відео">Вiдео</a></li>
                                    <li><a href="/" title="Інструктори">Iнструктори</a></li>
                                    <li><a href="/" title="Контакти">Контакти</a></li>
                                </ul> 
                                <br>                               		
                            </div>
                            <div class="col-lg-7 col-md-12 about">                                
                                <span class="mainTitle">Про школу</span>
                                <br>Благодаря уникальным знаниям клану Шоу удалось пройти через пять тысячелетий человеческой истории и донести до нас сокровенное учение Воинов жизни. Последователи Шоу Дао назвали свою школу "Путь спокойствия" или "Путь бессмертия". Клан, зародившись в глубокой древности в Северной Европе, прошел Индию и обосновался в Китае задолго до буддизма и Бодхидхармы.

                                Жизнь Спокойные считали бесценным подарком судьбы, и одной из основных целей учения Шоу Дао стало достижение бессмертия, или, по крайней мере, предельного долголетия и крепкого здоровья для каждого члена клана.


                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <br>
                                <a href="/articlesMenu"><span class="title">Статтi:</span></a>
                                <c:forEach var="article" items="${articles}" end="5">
                                    <br><span class="bold">${article.date}</span> 
                                    <br><a href="/article/${article.id}">${article.title}</a>
                                    <br>   
                                </c:forEach>   
                                <br><a href="/articlesMenu">Дивитись всi статтi...</a>                             
                            </div>
                            <div class="col-6">
                                <br>
                                <span class="title">Заходи</span>
                                
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12 text-center">
                                Контактний номер: +380671480750 Вадим                   
                            </div>                            
                        </div>
                        <div class="col-12 text-center footer">
                            <div>
                                <p>Розробка сайту: Ostap Lozinskyj</p>
                            </div>
                        </div>
                        
                    </div>
                </body>

                </html>