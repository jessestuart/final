import com.myapp.*
import groovy.time.TimeCategory

class BootStrap {

    def init = { servletContext ->
        if (!Role.count()) {
            new Role(authority: 'ROLE_USER').save(flush:true, failOnError:true)
            new Role(authority: 'ROLE_ADMIN').save(flush:true, failOnError:true)
        }
        
        if (!User.count()) {
            def roleUser = Role.findByAuthority('ROLE_USER')
            def roleAdmin = Role.findByAuthority('ROLE_ADMIN')
            assert roleUser
            assert roleAdmin
            
            def users = [
                    lillie: ['Lillie Barnes', 'libarnes@vassar.edu', 'Joss'],
                    jesse: ['Jesse Stuart', 'jdstuart@icloud.com', 'TAs']
            ]
        
            users.each { uname, data ->
                def user = new User(
                        password:'asdf',
                        fullName: data[0],
                        email: data[1],
                        dorm: data[2]
                )
                user.save flush:true, failOnError:true
                UserRole.create user, roleAdmin, true
            }
        }

        if (!Location.count()) {
            def locations = [
                    sandersAuditorium: ['Sanders English', 'Auditorium'],
                    rocky200: ['Rockefeller Hall', '200'],
                    powerhouse: ['Powerhouse Theater', 'Blackbox'],
                    shiva: ['Shiva Theater', 'Blackbox'],
                    villard: ['Main', 'Villard Room'],
                    mug: ['Main', 'The Mug']
            ]

            locations.each { name, data ->
                (new Location(building: data[0], room: data[1])).save flush: true
            }
        }

        if (!Reservation.count()) {
            def sanders = Location.findByBuilding('Sanders English')
            def powerhouse = Location.findByBuilding('Powerhouse Theater')
            def lillie = User.findByFullName('Lillie Barnes')
            def jesse = User.findByFullName('Jesse Stuart')

            def noon
            def onepm
            def twopm
            use(TimeCategory) {
                def today = new Date().clearTime()
                noon = today + 12.hours
                onepm = today + 13.hours
                twopm = today + 14.hours
            }

            new Reservation(space: sanders, startDate: noon, endDate: onepm, reserver: lillie).save flush: true
            new Reservation(space: powerhouse, startDate: noon, endDate: twopm, reserver: jesse).save flush: true
        }
    }
    
    def destroy = {
    }
}
