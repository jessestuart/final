package com.myapp

class Reservation {
    Location space
    Date start
    Date end
    User owner

    static belongsTo = [owner: User]

    static constraints = {
    }
}
