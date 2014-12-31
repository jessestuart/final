package com.myapp

import groovy.time.TimeCategory

import java.text.SimpleDateFormat

class ReservationController {
    def springSecurityService

    def index() {
        render view: 'make-reservation'
    }

    def selectDate() {
        Date date = params.date

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

        render view: 'select-space', model: [locations: locations,
                                             reservations: reservations,
                                             availableTimes: availableTimes]
    }

    def confirmReservation(long locationId, Date startTime) {
        println params
        println "start time : $startTime"
        def location = Location.get locationId
        def user = springSecurityService.currentUser
        def endTime
        use (TimeCategory) {
            endTime = startTime + 1.hour
        }
        new Reservation(space: location, reserver: user, startDate: startTime, endDate: endTime).save flush:true
        println "start date : $startTime, end time: $endTime"
        flash.message = "Great! See you at ${location.building} ${location.room} on ${new SimpleDateFormat('EEE, dd MMM yyyy HH:mm a').format(startTime)}."

        redirect controller: 'reservation'
    }
}
