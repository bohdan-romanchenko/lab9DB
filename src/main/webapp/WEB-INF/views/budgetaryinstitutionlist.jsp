<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/static/tab.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Budgetary institutions</title>
</head>
<body>
<c:if test="${exception != null}">
    <p>${exception}</p>
</c:if>

<table border=1>
    <thead>
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th colspan=2>ACTION</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${budgetaryInstitutions}" var="budgetaryInstitution">
        <tr>
            <td><c:out value="${budgetaryInstitution.id}"/></td>
            <td><c:out value="${budgetaryInstitution.name}"/></td>

            <td><a href="/budgetaryinstitution/preUpdate?id=<c:out value="${budgetaryInstitution.id}"/>">Update</a></td>
            <td><a href="/budgetaryinstitution/delete?id=<c:out value="${budgetaryInstitution.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p><a href="/budgetaryinstitution/preInsert">Add Budgetary Institution</a></p>
</body>
</html>