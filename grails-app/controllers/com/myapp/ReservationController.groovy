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
            def base = date.clearTime() + 10.hours
            (0..10).each { i ->
                availableTimes << (base + i.hours)
            }
        }

        render view: 'select-space', model: [locations: locations,
                                             reservations: reservations,
                                             availableTimes: availableTimes]
    }

    def confirmReservation(long locationId, long startTime) {
        def location = Location.get locationId
        def startDate = new Date(startTime)
        def user = springSecurityService.currentUser
        def endTime
        use (TimeCategory) {
            endTime = startDate + 59.minutes + 59.seconds
        }
        new Reservation(space: location, reserver: user, startDate: startDate, endDate: endTime).save flush:true
        flash.message = "Great! See you at ${location.building} ${location.room} on ${new SimpleDateFormat('EEE, dd MMM yyyy h:mm a').format(startTime)}."

        redirect controller: 'reservation'
    }

    def deleteReservation(long locationId, long startTime) {
        def location = Location.get locationId
        def startDate = new Date(startTime)
        def res = Reservation.findBySpaceAndStartDate(location, startDate)
        if (res) {
            res.delete flush: true
            flash.message = "Your reservation for ${location.building} ${location.room} on ${new SimpleDateFormat('EEE, dd MMM yyyy h:mm a').format(startTime)} has been removed."
        }
        else {
            flash.error = "Unable to retrieve reservation at this time."
        }
        redirect controller: 'reservation'
    }

    def reservationHistory() {
        render view: 'history', model: [
                reservations: Reservation.list([sort: 'startDate'])
        ]
    }
}
