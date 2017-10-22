<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
            <div class="col-6 topLogo">
                <div class="row">
                    <div class="col-2"> <img src="resources/img/logo.png"> </div>
                    <div class="col-10">
                        <p class="title">Українська федерацiя
                            <br>"ШОУ ДАО"</p>
                    </div>
                </div>
            </div>
        </div>			
		<div class="row">			
            <div class="col-lg-3 col-md-4 mainMenu">
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
<!-- 			<div class="col-12"> -->
<!-- 				<br> -->
<!-- 				<p> -->
<!-- 					<button class="btn-cart buy btnCafe btn-sucsess btn-lg" type="button" -->
<!-- 						data-toggle="collapse" data-target="#firstCollapse" -->
<!-- 						aria-expanded="false" aria-controls="firstCollapse"> -->
<!-- 						Search meal</button> -->
<!-- 				</p> -->
<!-- 				<div class="collapse" id="firstCollapse"> -->
<!-- 					<div class="card card-body"> -->
<%-- 						<form:form action="/meal" method="GET" modelAttribute="mealFilter"> --%>
<!-- 							<div class="form-group row"> -->
<!-- 								<div class="col-lg-2 col-sm-4 margin"> -->
<%-- 									<form:input path="minRate" class="form-control" placeholder="Min rate"/> --%>
<!-- 								</div> -->
<!-- 								<div class="col-lg-2 col-sm-4 margin"> -->
<%-- 									<form:input path="maxRate" class="form-control" placeholder="Max rate"/> --%>
<!-- 								</div> -->
<!-- 								<div class="col-lg-2 col-sm-4 margin"> -->
<%-- 									<form:input path="minPrice" class="form-control" placeholder="Min price"/> --%>
<!-- 								</div> -->
<!-- 								<div class="col-lg-2 col-sm-4 margin"> -->
<%-- 									<form:input path="maxPrice" class="form-control" placeholder="Max price"/> --%>
<!-- 								</div>					 -->
<!-- 								<div class="col-lg-2 col-sm-4 margin"> -->
<%-- 									<form:input path="minWeight" class="form-control" placeholder="Min weight"/> --%>
<!-- 								</div> -->
<!-- 								<div class="col-lg-2 col-sm-4 margin"> -->
<%-- 									<form:input path="maxWeight" class="form-control" placeholder="Max weight"/> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="form-group row"> -->
<!-- 								<div class="col-lg-2 col-sm-4 margin"> -->
<%-- 									<form:input path="search" class="form-control" placeholder="By name"/> --%>
<!-- 								</div>						 -->
<!-- 								<div class="col-lg-2 col-sm-4 margin"> -->
<!-- 									<p>								 -->
<!-- 										<button class="btn btn-outline-secondary" type="button" -->
<!-- 											data-toggle="collapse" data-target="#secondCollapse" -->
<!-- 											aria-expanded="false" aria-controls="secondCollapse"> -->
<!-- 											Select cuisine</button> -->
<!-- 									</p> -->
<!-- 									<div class="collapse" id="secondCollapse"> -->
<!-- 										<div class="card card-body"> -->
<%-- 											<form:checkboxes items="${cuisines}" path="cuisineName" element="div"/> --%>
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div>						 -->
<!-- 								<div class="col-12"> -->
<!-- 		        					<button type="submit" class="btn-cart buy btnCafe btn-sucsess btn-lg">Search</button> -->
<!-- 		      					</div> -->
<!-- 							</div> -->
<%-- 						</form:form> --%>
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->		
			<div class="col-lg-7 col-md-12">
			    <h1 class="text-center mainTitle">Статтi</h1>
				<c:if test="${empty articles.content}">
	    			<h2 class="text-center">Articles with such parameters not found</h2>
				</c:if>
				<c:forEach var="article" items="${articles.content}">
                    <div class="col-12">
                        <img src="resources/img/logo.png" style="width: 20px;">                    
                        <a href="/article/${article.id}">${article.title}</a>
                    </div>
			    </c:forEach>
			</div>						
		</div>
	</div>
</body>
</html>