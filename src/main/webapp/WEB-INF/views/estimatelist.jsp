<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/static/tab.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Show All subjects</title>
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
        <th>YEAR</th>
        <th>SPENDING LIMIT</th>
        <th colspan=2>ACTION</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${estimates}" var="estimate">
        <tr>
            <td><c:out value="${estimate.id}"/></td>
            <td><c:out value="${estimate.kekv}"/></td>
            <td><c:out value="${estimate.budgetaryInstitution}"/></td>
            <td><c:out value="${estimate.year}"/></td>
            <td><c:out value="${estimate.spendingLimit}"/></td>

            <td><a href="/estimate/preUpdate?id=<c:out value="${estimate.id}"/>">Update</a></td>
            <td><a href="/estimate/delete?id=<c:out value="${estimate.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p><a href="/estimate/preInsert">Add Estimate</a></p>
</body>
</html>