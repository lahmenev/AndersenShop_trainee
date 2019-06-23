
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="task_6.shop.model.User" %>
<% User user = (User) session.getAttribute("user"); %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h1><%=user.getName()%>, welcome to Test Shop</h1>
    <h2>
        <a href="/shop/products/insert">Add New Product</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/shop/products">Product List</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/shop/bucket">Bucket List</a>
        &nbsp;
        <a href="/shop/logout">Log Out</a>

    </h2>
</center>
    <div>

        <table border="1" cellpadding="5">
            <caption><h2>List of products</h2></caption>

            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Currency</th>
                <th>Price</th>
                <th>Amount</th>
                <th>Action</th>
            </tr>
            <tbody>
            <c:forEach items="${productList}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>${p.currency}</td>
                    <td>${p.price}</td>
                    <td>${p.amount}</td>
                    <td>
                        <a href="/shop/addToBucket/${p.id}&${p.name}&${p.currency}&${p.price}&${p.amount}">Add to bucket</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/shop/products/${p.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
