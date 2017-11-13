package Main.Controllers;

import java.io.IOException;

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
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private ComboBox<UserType> userTypeComboBox;

    @FXML
    private PasswordField passwordField;
    @FXML
    void registerUser(ActionEvent event) throws IOException {
    	String firstName = firstNameField.getText();
    	String lastName = lastNameField.getText();
    	String username = usernameField.getText();
    	String email = emailField.getText();
    	String password = passwordField.getText();
    	UserType userType = userTypeComboBox.getValue();
    	APICaller.getUserController().registerUser(firstName, lastName, username, password, email, userType);
    }

}
