package football;

/**
 * Interface used by a class to indicate
 * its structure for serialization by the JSON converter.
 */
public interface JSONFormat {

    /**
     * Specify a desired format for JSON serialization.
     * @return any object that can be handled by the JSON converter.
     */
    Object formatForJSON();
}
