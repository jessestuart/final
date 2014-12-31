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
                    lillie: ['Lillie Barnes', 'libarnes@vassar.edu'],
                    jesse: ['Jesse Stuart', 'jdstuart@icloud.com'],
                    james: ['James Joyce', 'james@joyce.com']
            ]
        
            users.each { uname, data ->
                def user = new User(
                        password:'asdf',
                        fullName: data[0],
                        email: data[1]
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
            def joyce = User.findByFullName('James Joyce')

            use(TimeCategory) {
                def today = new Date().clearTime()

                new Reservation(space: sanders, startDate: today+12.hours, endDate: today+12.hours+59.minutes+59.seconds, reserver: lillie).save flush: true
                new Reservation(space: powerhouse, startDate: today+14.hours, endDate: today+14.hours+59.minutes, reserver: jesse).save flush: true
                new Reservation(space: powerhouse, startDate: today+18.hours, endDate: today+18.hours+59.minutes, reserver: joyce).save flush: true
            }
        }
    }
    
    def destroy = {
    }
}
