<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="custom" uri="/WEB-INF/tags/implicit.tld"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Статті</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="/resources/css/index.css" rel="stylesheet">                 

    <link href="http://allfont.ru/allfont.css?fonts=presentscript-cyrillic" rel="stylesheet" type="text/css" />
    </head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-3 col-md-4 mainMenu">
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
            </div>
			<div class="col-lg-7 col-md-12">
			    <div class="row">
			        <div class="col-12">
                        <img src="${meal.photoUrl}?version=${meal.version}" width="100">
                        ${articleView.title}
                        <br>${articleView.text}
			        </div>
			    </div>				
			</div>			
		</div>
		<div class="row">
			<div class="col-12">
				<h3 class="text-center">Comments</h3>
				<c:forEach var="commentsOfmeal" items="${meal.comments}">
					<div class="comment">
						<div class="row">
							<div class="col-12">
								<img src="${commentsOfmeal.user.photoUrl}?version=${commentsOfmeal.user.version}" style="width: 50px"> ${commentsOfmeal.user.email}
							</div>
							<div class="col-12">
								${commentsOfmeal.text}
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>