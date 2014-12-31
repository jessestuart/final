package com.myapp

class User {

	transient springSecurityService

	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	String fullName
	String email
	String dorm
	
	static transients = ['springSecurityService']

	static constraints = {
		fullName blank: false
		password blank: false
		email blank: false, email: true, unique:true
		dorm blank: false
	}

	static mapping = {
	    table "person"
		password column: '`password`'
	}
	
	static views = [
	    list: [includes:['fullName', 'email', 'address']],
	    show: [excludes:['password', 'enabled', 'accountExpired', 'accountLocked', 'passwordExpired']],
	    create: [excludes:['enabled', 'accountExpired', 'accountLocked', 'passwordExpired']],
	    edit: [excludes:['password', 'enabled', 'accountExpired', 'accountLocked', 'passwordExpired']]
	]

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
	
	String toString() { fullName }
}
