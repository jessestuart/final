import com.myapp.*
import grails.util.Environment

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
    
        if (!Project.count()) {
            // Don't need projects
            def lillie = User.findByEmail('libarnes@vassar.edu')
            assert lillie
            
//            def p1 = new Project(name:"Galahad Webapp", owner:ronb)
//            p1.save(failOnError:true)
//            def p2 = new Project(name:"Galahad JSON API", owner:ronb)
//            p2.save(failOnError:true)
//            def p3 = new Project(name:"Galahad Admin Tool", owner:ronb)
//            p3.save(failOnError:true)
//
//            new Task(descr:"Use f:display tag for show scaffolding", due: new Date() + 5, project:p1).save(failOnError:true)
//            new Task(descr:"Implement basic sign up flow", due: new Date() + 10, project:p1).save(failOnError:true)
//            new Task(descr:"Starter features page based on boostrap jumbotron template", due: new Date() + 15, project:p1).save(failOnError:true)
//
//            new Task(descr:"Put base project together", due: new Date() + 5, project:p2).save(failOnError:true)
//            new Task(descr:"Setup plugins", due: new Date() + 10, project:p2).save(failOnError:true)
//            new Task(descr:"Build sample controllers for two resource collections", due: new Date() + 15, project:p2).save(failOnError:true)
        }
    }
    
    def destroy = {
    }
}
