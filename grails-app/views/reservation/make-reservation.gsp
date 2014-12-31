<%--
  Created by IntelliJ IDEA.
  User: jesse
  Date: 12/30/14
  Time: 11:47 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
    <g:javascript src="jquery-1.11.2.min.js"/>
</head>

<body>
    <g:if test="${flash.message}">
        <div class="alert alert-success" role="alert">
            <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
            <span class="sr-only">Success!</span>
            ${flash.message}
        </div>
    </g:if>
    <g:if test="${flash.error}">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span>
            <span class="sr-only">Uh oh!</span>
            ${flash.error}
        </div>
    </g:if>
    <div class="panel panel-default" id="select-date-panel">
        <div class="panel-heading">When would you like to reserve a space?</div>
        <div class="panel-body">
            <div class="pick-date">
                <g:form controller="reservation" action="selectDate">
                    <g:datePicker name="date" value="${new Date()}" precision="day" relativeYears="[0..1]"/>&nbsp;
                    <input type="submit" value="Select" class="btn btn-primary">
                </g:form>
            </div>
        </div>
    </div>
</body>
</html>