<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/static/insert.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add new user</title>
</head>
<body>
<%String operation = (String) request.getAttribute("operation");%>

<form class="form-login" method="POST" action='/estimate/${operation}'>
    <div class="form-log-in-with-email">

        <div class="form-white-background">
            <div class="form-title-row">
                <h1>Estimate</h1>
            </div>
            <div class="form-row">
                <label>
                    <span>ID</span>
                    <input type="text" name="id"
                           value="<c:out value="${estimate.id}" />" <%--readonly="readonly"--%>/>
                </label>
            </div>
            <div class="form-row">
                <label>
                    <span>KEKV</span>
                    <input type="text" name="kekv" value="<c:out value="${estimate.kekv}" />"/>
                </label>
            </div>
            <div class="form-row">
                <label>
                    <span>BUDGETARY INSTITUTION</span>
                    <input type="text" name="budgetaryInstitution" value="<c:out value="${estimate.budgetaryInstitution}" />"/>
                </label>
            </div>
            <div class="form-row">
                <label>
                    <span>YEAR</span>
                    <input type="text" name="year"
                           value="<c:out value="${estimate.year}" />"/>
                </label>
            </div>
            <div class="form-row">
                <label>
                    <span>SPENDING LIMIT</span>
                    <input type="text" name="spendingLimit" value="<c:out value="${estimate.spendingLimit}" />"/> <br/>
                </label>
            </div>
            <button type="submit">SUBMIT</button>
        </div>
    </div>
</form>
</body>
</html>