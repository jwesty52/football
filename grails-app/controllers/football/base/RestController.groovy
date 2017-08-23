package football.base

import org.grails.web.json.JSONObject

/**
 * RESTful controller for managing GORM domain objects.
 */
abstract class RestController extends BaseController {

    def restTarget  // Implementations set to value of GORM domain class they are editing.

    def create = {
        def data = request.JSON.data ?: request.JSON
        log.info(data)
        preprocessSubmit(data)

        def obj = restTarget.newInstance(data)
        boolean success = obj.save(flush:true) != null
        def message = success ?
            "Created new ${restTarget} with ID ${obj.id}." :
            "Failed to create ${restTarget}."

        def ret = [
                success: success,
                data: obj,
                message: message
        ]
        if (!success) ret.errors = allErrors(obj)
        renderJSON(ret)
    }


    def read = {
        def ret = (params?.id) ? [restTarget?.get(params.id)] : restTarget?.list()
        renderJSON([
                success: true,
                data: ret,
                total: ret.size()
        ])
    }


    def update = {
        def data = request.JSON.data ?: request.JSON
        preprocessSubmit(data)

        def obj = restTarget.get(data.id)
        bindData(obj, data)
        def success = obj.save(flush:true) != null

        def message = success ?
            "Updated ${restTarget} with ID ${obj.id}." :
            "Failed to update ${restTarget}."

        def ret = [
                success: success,
                data: obj,
                message: message
        ]
        if (!success) ret.errors = allErrors(obj)
        renderJSON(ret)
    }


    def delete = {
        def success, message
        def obj = restTarget.get(params.id)
        try {
            obj.delete(flush:true)
            success = true
            message = "${restTarget} deleted."
        } catch (Exception e) {
            success = false
            message = "Failed to delete ${restTarget}: ${e.message}"
        }

        renderJSON([
                success: success,
                message: message
        ])
    }


    // Override if you need to do some processing on the submitted data before it's sent to create/update
    // Just modify the data object that's passed in.
    def preprocessSubmit = { JSONObject submit -> }

}
