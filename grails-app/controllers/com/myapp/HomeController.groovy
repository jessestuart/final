package com.myapp

import grails.transaction.Transactional

@Transactional(readOnly = true)
class HomeController {

	def index() {
		if (isLoggedIn()) {
			redirect controller: 'reservation'
		} else {
			redirect controller: 'login'
		}
	}

	def about() {
		render view: 'about'
	}
}
