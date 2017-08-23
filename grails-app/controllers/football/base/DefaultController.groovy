package football.base

/**
 * Main entry point for the JavaScript GUI.
 */
class DefaultController extends BaseController {

    def springSecurityService


    def index = {
        log.info("Welcome to Proto - loading GUI...")
        // Renders views/default/index.gsp by convention...
    }

}
