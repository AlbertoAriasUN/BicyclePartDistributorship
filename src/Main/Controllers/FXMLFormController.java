package Main.Controllers;

import Main.FieldValidation;
import java.util.ArrayList;

/**
 * Defines a generic FXML Form
 * @author Matthew
 */
public class FXMLFormController {
    
    /**
     * Fields to validate
     */
    protected ArrayList<FieldValidation> fieldValidations;
    
    public FXMLFormController() {
        fieldValidations = new ArrayList<>();
    }
    
    /**
     * Checks if form is valid
     * @return whether form is valid
     */
    public boolean validForm() {
        for(FieldValidation validation : fieldValidations) {
            if(!validation.validField()) {
                return false;
            }
        }
        
        return true;
    }
     
}
