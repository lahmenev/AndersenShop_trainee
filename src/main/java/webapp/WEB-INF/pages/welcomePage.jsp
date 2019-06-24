<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>
    Hello <b><c:out value="${pageContext.request.remoteUser}"/></b>. You are welcome to Shop.
</h1>

<h2>
    <a href="/shop/products/insert">Add New Product</a>
    &nbsp;&nbsp;&nbsp;
    <a href="/shop/products">Product List</a>
    &nbsp;&nbsp;&nbsp;
    <a href="/shop/bucket">Bucket List</a>
    &nbsp;
    <a href="/shop/logout">Log Out</a>

</h2>
</body>
</html>
