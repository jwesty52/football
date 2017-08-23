package football

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')

        // Automap RESTful paths to controllers
        "/rest/$controller/$id?"{
            action = [POST:'create', GET:'read', PUT:'update', DELETE:'delete']
        }
    }
}
