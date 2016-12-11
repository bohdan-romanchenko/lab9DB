<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/static/tab.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Show All teachers</title>
</head>
<body>
    <c:if test="${exception != null}">
        <p>${exception}</p>
    </c:if>

    <table border=1>
        <thead>
        <tr>
            <th>ID</th>
            <th>KEKV</th>
            <th>BUDGETARY INSTITUTION</th>
            <th>COMMERCIAL BANK</th>
            <th>BASIS</th>
            <th>AMOUNT</th>
            <th>DATE CREATED</th>
            <th>RECEIVER</th>
            <th colspan=2>ACTION</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${paymentorders}" var="paymentorder">
            <tr>
                <td><c:out value="${paymentorder.id}" /></td>
                <td><c:out value="${paymentorder.kekv}" /></td>
                <td><c:out value="${paymentorder.budgetaryInstitution}" /></td>
                <td><c:out value="${paymentorder.commercialBank}" /></td>
                <td><c:out value="${paymentorder.basis}" /></td>
                <td><c:out value="${paymentorder.amount}" /></td>
                <td><c:out value="${paymentorder.created}" /></td>
                <td><c:out value="${paymentorder.receiver}" /></td>

                <td><a href="/paymentorder/preUpdate?id=<c:out value="${paymentorder.id}"/>">Update</a></td>
                <td><a href="/paymentorder/delete?id=<c:out value="${paymentorder.id}"/>">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <p><a href="/paymentorder/preInsert">Add Payment Order</a></p>
</body>
</html>