package Main.Controllers;
import java.io.IOException;
import java.util.ArrayList;

import BicyclePartDistributorshipAPI.Models.User.UserType;
import Main.APICaller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SysAdminController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;
    
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
    
    @FXML
    private ComboBox<String> userTypeComboBox;
    
    @FXML  	
    void initialize() {
    	ArrayList<String> userTypeSelections = new ArrayList<>();
    	userTypeSelections.add("System Administrator");
    	userTypeSelections.add("Office Manager");
    	userTypeSelections.add("Warehouse Manager");
    	userTypeSelections.add("Sales Associate");
    	userTypeComboBox.getItems().setAll(userTypeSelections);
    }
    
    @FXML
    void registerUser(ActionEvent event) throws IOException {
    	String firstName = firstNameField.getText();
    	String lastName = lastNameField.getText();
    	String username = usernameField.getText();
    	String email = emailField.getText();
    	String password = passwordField.getText();
    	UserType userType = null;
    	switch(userTypeComboBox.getValue().toLowerCase()) {
    		case "sysadmin":
    			userType = UserType.SYSADMIN;
    			break;
    		case "office manager":
    			userType = UserType.OFFICE_MANAGER;
    			break;
    		case "warehouse manager": 
    			userType = UserType.WAREHOUSE_MANAGER;
    			break;
    		case "sales associate":
    			userType = UserType.SALES_ASSOCIATE;
    			break;
    		default:
    			userType = null;
    			break;
    	}
    	APICaller.getUserController().registerUser(firstName, lastName, username, password, email, userType);
    }

}
