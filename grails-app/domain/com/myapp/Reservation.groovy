package com.myapp

class Reservation {
    Location space
    Date startDate
    Date endDate
    User reserver

    static belongsTo = [reserver: User]

    static constraints = {
    }
}
