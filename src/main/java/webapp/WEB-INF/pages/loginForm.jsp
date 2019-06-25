
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>SignIn</title>
</head>
<body>
<h1>Login form</h1>
<div>
    <form action="login" method="post">
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <div><input type="submit" value="SignIn"/></div>
    </form>

    <a href="/shop/registration">Registration</a>

</div>
</body>
</html>
