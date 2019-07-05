
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h1>Test Shop</h1>
    <h2>
        <a href="/shop/products">List All Product</a>
    </h2>
</center>
<div>

    <table border="1" cellpadding="5">
        <caption><h2>Bucket List</h2></caption>

        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Currency</th>
            <th>Price</th>
            <th>Amount</th>
            <th>Country</th>
            <th>Shelf life</th>
            <th>Sum</th>
        </tr>
        <tbody>
        <c:forEach items="${bucketList}" var="item">
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.currency}</td>
                <td>${item.price}</td>
                <td>${item.amount}</td>
                <td>${item.country.nameCountry}</td>
                <td>${item.shelfLife}</td>
                <td>${item.sum}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
