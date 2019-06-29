<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h1>Add product to bucket</h1>
</center>
<div>

    <form:form action="/shop/bucket/eatable_product/insert" modelAttribute="eatableProduct" method="post">
        <table border="1" cellpadding="5">
            <caption><h2>Adding product</h2></caption>
            <tr>
                <th>Id: </th>
                <td>
                    <form:input  path="id"/>
                </td>
            </tr>
            <tr>
                <th>Name: </th>
                <td>
                    <form:input  path="name"/>
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
                <th>Country: </th>
                <td>
                    <form:input path="country.nameCountry"/>
                </td>
            </tr>
            <tr>
            <tr>
                <th>Shelf life: </th>
                <td>
                    <form:input path="shelfLife"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Add" />
                </td>
            </tr>
        </table>
    </form:form>

    <c:if test="${errorMessage != null}">
        <div style="color:red;font-style:italic">${errorMessage}</div>
    </c:if>
</div>
</body>
</html>
