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
<title>Registration</title>
</head>
<body style="background: url(/resources/img/fon2.jpg)">
	<div class="container" style="background-color: white;">
	    <div class="row">
			<div class="col-12">
				<h1 class="text-center">Registration</h1>
			</div>
		</div>
		<br>
		<div class="row" style="border-top: 1px solid beige;">
			<div class="col-md-6 col-sm-12">	
			    <br>		
				<form:form action="/registration" method="POST"	modelAttribute="registration">
					<div class="row">
						<div class="col-10 ml-5" style="color:red;">
							<form:errors path="${registrationRequest}"/>
						</div>
					</div>
					<div class="row">
						<div class="col-10 ml-5" style="color: red;">
							<form:errors path="email" />
						</div>
					</div>
					<div class="form-group row">
						<div class="col-10 ml-5">
							<form:input class="form-control" id="email" path="email" placeholder="Email"/>
						</div>
					</div>
					<div class="row">
						<div class="col-10 ml-5" style="color: red;">
							<form:errors path="password" />
						</div>
					</div>
					<div class="form-group row">
						<div class="col-10 ml-5">
							<form:password class="form-control" id="password" path="password" placeholder="Password"/>
						</div>
					</div>
					
					<div class="form-group row">
						<div class="col-10 ml-5">
							<form:password class="form-control" id="repeatpassword"	path="repeatPassword" placeholder="Repeat password"/>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-10  ml-5">
							<button class="btn btn-sm btn-outline-success">Register</button>
							<a href="/login" class="btn btn-sm btn-outline-primary">Login</a>
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