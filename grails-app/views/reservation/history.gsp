<%--
  Created by IntelliJ IDEA.
  User: jesse
  Date: 12/31/14
  Time: 11:14 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Reservation History</title>
</head>

<body>
    <div class="panel panel-default">
        <div class="panel-heading">Reservation History</div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                    <th>Location</th>
                    <th>Reservation Date</th>
                    <th>Contact Info</th>
                </thead>
                <tbody>
                    <g:each in="${reservations}" var="reservation">
                        <tr>
                            <td>${reservation.space.building} (${reservation.space.room})</td>
                            <td><g:formatDate date="${reservation.startDate}" format="dd MMM yyyy h:mm a"/></td>
                            <td>
                                <a href="mailto:${reservation.reserver.email}">${reservation.reserver}</a>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>