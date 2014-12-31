package com.myapp

class Location {
    String building
    String room
    List reservations

    static hasMany = [reservations: Reservation]

    static constraints = {
        building blank: false
        room blank: false
    }

    @Override
    String toString() {
        "$building $room"
    }
}
