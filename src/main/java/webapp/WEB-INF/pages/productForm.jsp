<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h1>Test Shop</h1>
</center>
<div>

    <form:form action="insert" modelAttribute="product" method="post">
        <table border="1" cellpadding="5">
            <caption><h2>List of products</h2></caption>
            <tr>
                <th>Name: </th>
                <td>
                    <form:input path="name"/>
                </td>
            </tr>
            <tr>
                <th>Currency: </th>
                <td>
                    <form:input path="currency"/>
                </td>
            </tr>
            <tr>
                <th>Price: </th>
                <td>
                    <form:input path="price"/>
                </td>
            </tr>
            <tr>
                <th>Amount: </th>
                <td>
                    <form:input path="amount"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
