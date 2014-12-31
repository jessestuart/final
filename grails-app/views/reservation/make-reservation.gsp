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
    <g:javascript src="bootstrap-datetimepicker.js"/>
    <script>
        $('#datepicker').datepicker();
    </script>
</head>

<body>
    <div class="panel panel-default">
        <div class="panel-heading">When would you like to reserve a space?</div>
        <div class="panel-body">
            <div class="pick-date">
                <g:form controller="reservation" action="selectDate">
                    <g:datePicker name="date" value="${new Date()}" precision="day"/>&nbsp;
                    <input type="submit" value="Select" class="btn btn-primary">
                </g:form>
            </div>
        </div>
    </div>
</body>
</html>