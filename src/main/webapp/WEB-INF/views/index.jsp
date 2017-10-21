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
                    <link href="/resources/css/index.css" rel="stylesheet"> </head>

                <body>
                    <div class="container">
                        <div class="row">
                            <div class="col-3">
                                <br>
                                <img src="resources/img/logo.png">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-2 mainMenu">
                                <br>
                                <br>
                                <ul>
                                    <li><a href="/" title="Новини">Новини</a></li>
                                    <li><a href="/articlesMenu" title="Статті">Статті</a></li>
                                    <li><a href="/" title="Про Школу">Про Школу</a></li>
                                    <li><a href="/" title="Навчання">Навчання</a></li>
                                    <li><a href="/" title="Бібліотека">Бібліотека</a></li>
                                    <li><a href="/" title="Галерея">Галерея</a></li>
                                    <li><a href="/" title="Відео">Відео</a></li>
                                    <li><a href="/" title="Інструктори">Інструктори</a></li>
                                    <li><a href="/" title="Контакти">Контакти</a></li>
                                </ul>	
                                <br>
                                <br>		
                            </div>
                            <div class="col-8">
                                <br>
                                Про школу
                                <br>Благодаря уникальным знаниям клану Шоу удалось пройти через пять тысячелетий человеческой истории и донести до нас сокровенное учение Воинов жизни. Последователи Шоу Дао назвали свою школу "Путь спокойствия" или "Путь бессмертия". Клан, зародившись в глубокой древности в Северной Европе, прошел Индию и обосновался в Китае задолго до буддизма и Бодхидхармы.

                                Жизнь Спокойные считали бесценным подарком судьбы, и одной из основных целей учения Шоу Дао стало достижение бессмертия, или, по крайней мере, предельного долголетия и крепкого здоровья для каждого члена клана.


                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <br>
                                Статті:
                                <c:forEach var="article" items="${articles}" end="5">
                                	<br>${article.date} 
                                    <br><a href="/">${article.title}</a>
                                    <br>   
                                </c:forEach>   
                                <br><a href="/articlesMenu">Дивитись всі...</a>                             
                            </div>
                            <div class="col-6">
                                <br>
                                Заходи
                                
                            </div>
                        </div>
                        
                        
                        
                        <div class="col-12 text-center">
                            <div>
                                <p>&copy; 2017 Ostap Lozinskyj</p>
                            </div>
                        </div>
                        
                    </div>
                </body>

                </html>