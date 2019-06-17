<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h1>Test Shop</h1>
</center>
<div>

    <form action="insert" method="post">
        <table border="1" cellpadding="5">
            <caption><h2>List of products</h2></caption>
            <tr>
                <th>Name: </th>
                <td>
                    <input type="text" name="name" value="name"/>
                </td>
            </tr>
            <tr>
                <th>Currancy: </th>
                <td>
                    <input type="text" name="currancy" value="currancy"/>
                </td>
            </tr>
            <tr>
                <th>Price: </th>
                <td>
                    <input type="text" name="price" value="price"/>
                </td>
            </tr>
            <tr>
                <th>Amount: </th>
                <td>
                    <input type="text" name="amount" value="amount"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
