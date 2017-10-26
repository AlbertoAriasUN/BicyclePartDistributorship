package Main;

import javafx.scene.control.TextField;

/**
 * Validates that a given text field contains the correct datatype
 * @author Matthew
 */
public class FieldValidation {
    
    public static final byte INTEGER = 0;
    public static final byte FLOATING_POINT = 1;
    public static final byte LONG = 2;

    /**
     * The datatype to be checked against
     */
    private final byte datatype;
    
    /**
     * FXML text field to be read from
     */
    private final TextField field;

    /**
     * Constructor
     * @param field field to be read from
     * @param datatype datatype to be checked
     */
    public FieldValidation(TextField field, byte datatype) {
        this.datatype = datatype;
        this.field = field;
    }
    
    /**
     * Checks if field is valid
     * @return whether field is valid
     */
    public boolean validField() {
        switch(datatype) {
            case INTEGER: 
                return isInteger(field.getText());
            case FLOATING_POINT:
                return isFloatingPoint(field.getText());
            case LONG:
                return isLong(field.getText());
            default:
                return false;
        }
    }
    
    /**
     * Checks if string is a valid integer value
     * @param value string to be checked
     * @return whether field is integer
     */
    private boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Checks is string is a valid floating point value
     * @param value string to be checked
     * @return whether field is a floating point value
     */
    private boolean isFloatingPoint(String value) {
        try {
            Double.parseDouble(value);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Checks if string is a valid long value
     * @param value string to be checked
     * @return whether field is a long
     */
    private boolean isLong(String value) {
        try {
            Long.parseLong(value);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }
}
