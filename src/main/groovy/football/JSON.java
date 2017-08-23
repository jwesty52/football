package football;

import org.grails.web.converters.exceptions.ConverterException;
import org.grails.web.json.JSONException;

import java.util.Date;

/**
 *   Overriden version of grails JSON converter that has special handling for
 *   various core Java classes and the JSONFormat interface.
 */
public class JSON extends grails.converters.JSON {

    public JSON(Object target) {
        super(target);
    }

    @Override
    public void value(Object o) throws ConverterException {
        try {
            if (o == null) {
                writer.value(null);
            } else if (o instanceof Double) {
                Double d = (Double) o;
                writer.value (d.isInfinite() || d.isNaN() ? null : d);
            } else if (o instanceof Date) {
                Date d = (Date) o;
                writer.value (d.getTime());
            } else if (o instanceof JSON) {
                value(((JSONFormat) o).formatForJSON());
            }  else {
               super.value(o);
            }
        } catch (ConverterException ce) {
            throw ce;
        } catch (JSONException e) {
            throw new ConverterException(e);
        }
    }
}

