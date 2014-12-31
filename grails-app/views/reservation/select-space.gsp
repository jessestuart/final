<%--
  Created by IntelliJ IDEA.
  User: jesse
  Date: 12/31/14
  Time: 12:11 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<body>
    <div class="panel panel-default">
        <div class="panel-heading">Select a Space and Time</div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                </thead>
                <tbody>
                    <g:each in="${locations}" var="location">
                        <tr>
                            <td>${location.building} ${location.room}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>