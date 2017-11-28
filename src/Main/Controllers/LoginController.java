package Main.Controllers;

import java.io.IOException;

import BicyclePartDistributorshipAPI.Models.User;
import Main.APICaller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

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
		else
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
					Main.Main.setStage(Main.Main.SALES_ASSOCIATE_STAGE_URL);
					break;
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

    }

}
