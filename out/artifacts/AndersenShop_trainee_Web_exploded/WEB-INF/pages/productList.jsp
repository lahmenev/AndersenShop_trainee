
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
        <a href="/shop/new">Add New Product</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/shop/list">Product List</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/shop/bucket">Bucket List</a>

    </h2>
</center>
    <div>

        <table border="1" cellpadding="5">
            <caption><h2>List of products</h2></caption>

            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Currancy</th>
                <th>Price</th>
                <th>Amount</th>
                <th>Action</th>
            </tr>
            <tbody>
            <c:forEach items="${allProducts}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>${p.currancy}</td>
                    <td>${p.price}</td>
                    <td>${p.amount}</td>
                    <td>
                        <a href="/shop/addBucket?id=${p.id}">Add to bucket</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/shop/delete?id=${p.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
