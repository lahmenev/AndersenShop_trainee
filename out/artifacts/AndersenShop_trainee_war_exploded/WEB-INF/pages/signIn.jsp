
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SignIn</title>
</head>
<body>
<h1>Login form</h1>
<div>
    <form action="signIn" method="post">
        <table style="width: 50%" >
            <tr>
                <td>Login</td>
                <td><input type="text" name="name" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" /></td>
            </tr>
        </table>
        <input type="submit" value="SignIn" /></form>
    </form>
</div>
</body>
</html>
