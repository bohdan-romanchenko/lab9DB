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

<form class="form-login" method="POST" action='/paymentorder/${operation}'>

    <div class="form-log-in-with-email">

        <div class="form-white-background">
            <div class="form-title-row">
                <h1>Payment Order</h1>
            </div>
            <div class="form-row">
                <label>
                    <span>ID</span>
                    <input type="text" name="id"
                           value="<c:out value="${paymentorder.id}"/>"
                           readonly="readonly"/>
                </label>
            </div>
            <div class="form-row">
                <label>
                    <span>KEKV</span>
                    <input type="text" name="kekv" value="<c:out value="${paymentorder.kekv}" />"/>
                </label>
            </div>
            <div class="form-row">
                <label>
                    <span>BUDGETARY INSTITUTION</span>
                    <input type="text" name="budgetaryInstitution"
                           value="<c:out value="${paymentorder.budgetaryInstitution}" />"/>
                </label>
            </div>
            <div class="form-row">
                <label>
                    <span>COMMERCIAL BANK</span>
                    <input type="text" name="commercialBank"
                           value="<c:out value="${paymentorder.commercialBank}" />"/>
                </label>
            </div>
            <div class="form-row">
                <label>
                    <span>BASIS</span>
                    <input type="text" name="basis"
                           value="<c:out value="${paymentorder.basis}" />"/>
                </label>
            </div>
            <div class="form-row">
                <label>
                    <span>AMOUNT</span>
                    <input type="text" name="amount"
                           value="<c:out value="${paymentorder.amount}" />"/>
                </label>
            </div>
            <div class="form-row">
                <label>
                    <span>CREATED</span>
                    <input type="text" name="created"
                           value="<c:out value="${paymentorder.created}" />"/>
                </label>
            </div>
            <div class="form-row">
                <label>
                    <span>RECEIVER</span>
                    <input type="text" name="receiver"
                           value="<c:out value="${paymentorder.receiver}" />"/>
                </label>
            </div>
            <button type="submit">SUBMIT</button>
        </div>
    </div>
</form>
</body>
</html>