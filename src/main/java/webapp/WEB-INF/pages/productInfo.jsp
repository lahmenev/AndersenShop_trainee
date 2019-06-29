
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h2>
        <a href="/shop/products/eatable_product/insert">Add new food product</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/shop/products/uneatable_product/insert">Add new non food product</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/shop/bucket">Bucket List</a>
        &nbsp;
        <a href="/shop/logout">Log Out</a>

    </h2>
</center>
<div>
    <table border="1" cellpadding="5">
        <caption><h2>Product Information</h2></caption>
        <tr>
            <th>Name</th>
            <th>Country</th>
            <th>Price</th>
            <th>Currency</th>
        </tr>
        <tr>
            <th>${productInfo.name}</th>
            <th>${productInfo.country.nameCountry}</th>
            <th>${productInfo.price}</th>
            <th>${productInfo.currency}</th>
        </tr>
    </table>
</div>
</body>
</html>
