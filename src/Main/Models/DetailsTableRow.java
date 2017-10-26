package Main.Models;

/**
 * Defines a row for table in "Part Details" tab
 * @author Matthew
 */
public class DetailsTableRow {
    /**
     * Name of BicyclePart property
     */
    private final String propertyName;
    
    /**
     * Value of BicyclePart property
     */
    private final Object value;
    
    /**
     * Constructor for table row
     * @param propertyName name of BicyclePart property
     * @param value value of BicyclePart property
     */
    public DetailsTableRow(String propertyName, Object value) {
        this.propertyName = propertyName;
        this.value = value;
    }
    
    /**
     * Get name of BicyclePart property
     * @return name of BicyclePart property
     */
    public final String getPropertyName() {
        return propertyName;
    }
    
    /**
     * Get value of BicyclePart property
     * @return value of BicuclePart property
     */
    public final Object getValue() {
        return value;
    }
}
