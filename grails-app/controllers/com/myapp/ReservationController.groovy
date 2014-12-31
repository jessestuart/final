package com.myapp

import groovy.time.TimeCategory

class ReservationController {

    def index() {
        render view: 'make-reservation'
    }

    def selectDate() {
        Date date = params.date
        println date

        use (TimeCategory) {
            def today = new Date().clearTime()
            def noon = today + 12.hours
            def onepm = today + 13.hours
            println "noon : $noon, onepm : $onepm"
        }

        def locations = Location.list()
        def reservations = Reservation.createCriteria().list {
            ge 'start', date.clearTime()
            le 'end', (date.clearTime() + 1)
        }.groupBy { it.space }

        render view: locations, model: [locations: locations, reservations: reservations]
    }
}
