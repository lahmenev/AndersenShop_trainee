
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>SignUp</title>
</head>
<body>
<h1>Registration form</h1>
<div>
    <form:form action="registration" modelAttribute="user" method="post">
        <table style="width: 30%">
            <tr>
                <td>Login</td>
                <td><form:input path="name" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><form:input path="password" /></td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
    </form:form>
</div>
</body>
</html>
