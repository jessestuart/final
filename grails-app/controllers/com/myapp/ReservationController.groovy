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
            ge 'startDate', date.clearTime()
            le 'endDate', (date.clearTime() + 1)
        }.groupBy { it.space }

        def availableTimes = []
        use (TimeCategory) {
            def base = new Date().clearTime() + 10.hours
            (0..10).each { i ->
                availableTimes << (base + i.hours)
            }
        }

        println locations
        println reservations

        render view: 'select-space', model: [locations: locations,
                                             reservations: reservations,
                                             availableTimes: availableTimes]
    }
}
