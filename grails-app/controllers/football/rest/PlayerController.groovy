package football.rest

import football.Player
import football.base.RestController

/**
 * Created by joshwestbrook on 8/23/17.
 */
class PlayerController extends RestController{

    def restTarget = Player
}
