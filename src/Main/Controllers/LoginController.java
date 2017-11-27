package Main.Controllers;

import java.io.IOException;

import BicyclePartDistributorshipAPI.Models.User;
import Main.APICaller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void login(ActionEvent event) {
    	User user = null;
		try {
			String username = usernameField.getText();
	    	String password = passwordField.getText();
			user = APICaller.getUserController().authenticateLogin(username, password);
		} catch (IOException e) {
			System.out.println(e);
		}

		if(user == null) {
			System.out.println("Username/Password incorrect");
		}

		//Uncomment switch cases as FXML pages get added
		try {
			switch(user.getType()) {
				case SYSADMIN:
					Main.Main.setStage(Main.Main.SYSADMIN_STAGE_URL);
					break;
				case OFFICE_MANAGER:
					Main.Main.setStage(Main.Main.OFFICE_MANAGER_STAGE_URL);
					break;
				case WAREHOUSE_MANAGER:
					Main.Main.setStage(Main.Main.WAREHOUSE_MANAGER_STAGE_URL);
					break;
				case SALES_ASSOCIATE:
					//Main.Main.getStage().setScene(salesAssociateScene);
					break;
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

    }

}
