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
            <caption><h2>List of food products</h2></caption>

            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Currency</th>
                <th>Price</th>
                <th>Amount</th>
                <th>Country</th>
                <th>Shelf life</th>
                <th>Action</th>
            </tr>
            <tbody>
            <c:forEach items="${eatable_productList}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>${p.currency}</td>
                    <td>${p.price}</td>
                    <td>${p.amount}</td>
                    <td>${p.country.nameCountry}</td>
                    <td>${p.shelfLife}</td>
                    <td>
                        <a href="/shop/bucket/eatable_product/insert/${p.id}&${p.name}&${p.currency}&${p.price}&${p.amount}&${p.country.nameCountry}&${p.shelfLife}">Add to bucket</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/shop/products/eatable_product/${p.id}">Delete</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/shop/products/eatable_product/product-info/${p.id}">Product information</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <table border="1" cellpadding="5">
            <caption><h2>List of non food products</h2></caption>

            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Currency</th>
                <th>Price</th>
                <th>Amount</th>
                <th>Country</th>
                <th>Action</th>
            </tr>
            <tbody>
            <c:forEach items="${uneatable_productList}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>${p.currency}</td>
                    <td>${p.price}</td>
                    <td>${p.amount}</td>
                    <td>${p.country.nameCountry}</td>
                    <td>
                        <a href="/shop/bucket/uneatable_product/insert/${p.id}&${p.name}&${p.currency}&${p.price}&${p.amount}&${p.country.nameCountry}">Add to bucket</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/shop/products/uneatable_product/${p.id}">Delete</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/shop/products/uneatable_product/product-info/${p.id}">Product information</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
