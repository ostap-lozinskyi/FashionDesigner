<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

    <link href="/resources/css/index.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-12">
            <h1 class="text-center">Login</h1>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-md-12 col-sm-12">
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
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="Password"/>
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
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>