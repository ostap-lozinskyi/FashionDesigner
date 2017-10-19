<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">
<title>Login</title>
</head>
<body style="background: url(/resources/img/fon2.jpg)">
	<div class="container" style="background-color: white;">
		<div class="row">
			<div class="col-12">
				<h1 class="text-center">Login</h1>
			</div>
		</div>
		<br>
		<div class="row" style="border-top: 1px solid beige;">		    
			<div class="col-md-6 col-sm-12">
			    <br>			    
				<form:form action="/login" method="POST">
					<c:if test="${param.fail}">
						<div class="col-10 ml-auto" style="color: red;">
  							Wrong user or password
						</div>
					</c:if>
					<div class="form-group row">						
						<div class="col-10 ml-5">
							<input class="form-control" id="email" name="login" placeholder="Email"/>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-10 ml-5">
							<input type="password" class="form-control" id="password" name="password" placeholder="Password"/>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-10 ml-5">
							<label><input type="checkbox" name="rememberMe">Remember me</label>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-10 ml-5">
							<button class="btn btn-sm btn-outline-success">Sign in</button>
							<a href="/registration" class="btn btn-sm btn-outline-primary">Registration</a>
						</div>
					</div>
				</form:form>                
			</div>
			<div class="col-md-6 col-sm-12">
                <br>
			    <div class="row">                    
			        <div class="col-6" style="border-left: 1px solid beige;">			            			    
				        <a href="/createFacebookAuthorization"><img src="/resources/img/facebook.png" style="width: 250px;"></a>                
			        	<a href="/createGoogleAuthorization"><img src="/resources/img/google.png" style="width: 250px; margin-top: 10px"></a>
			        </div>
                </div>
                <br>
            </div>
		</div>
	</div>
</body>
</html>