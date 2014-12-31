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
            <table class="table table-hover" id="availability-table">
                <thead>
                    <th colspan="2">Name</th>
                    <g:each in="${availableTimes}" var="date">
                        <th><g:formatDate date="${date}" format="h:mm a"/></th>
                    </g:each>
                </thead>
                <tbody>
                    <g:each in="${locations}" var="location">
                        %{--<g:set var="location_reservations" value="${reservations[location]}">--}%
                        <tr>
                            <td colspan="2">${location.building} ${location.room}</td>
                            <g:each in="${availableTimes}" var="time" status="i">
                                <g:if test="${reservations[location].any { it.startDate <= time && it.endDate >= time} }">
                                    <td class="unavailable"></td>
                                </g:if>
                                <g:else>
                                    <td class="available"></td>
                                </g:else>
                            </g:each>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>