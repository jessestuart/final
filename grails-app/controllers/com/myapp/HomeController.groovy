package com.myapp

import grails.transaction.Transactional

@Transactional(readOnly = true)
class HomeController {

	def index() {
		if (isLoggedIn()) {
			redirect controller:"reservation"
		} else {
			render view: 'index'
		}
	}

	def about() {
		render view: 'about'
	}
}
