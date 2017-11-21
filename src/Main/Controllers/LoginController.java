package Main.Controllers;

import java.io.IOException;

import BicyclePartDistributorshipAPI.Models.User;
import Main.APICaller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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


		//TODO: Add .fxml pages for each user type
		/*try {
			Scene adminScene = new Scene(FXMLLoader.load(getClass().getResource("Views/SysAdmin.fxml")));
			Scene warehouseManagerScene = new Scene(FXMLLoader.load(getClass().getResource("Views/WarehouseManager.fxml")));
			Scene officeManagerScene = new Scene(FXMLLoader.load(getClass().getResource("Views/OfficeManager.fxml")));
			Scene salesAssociateScene = new Scene(FXMLLoader.load(getClass().getResource("Views/SalesAssociate.fxml")));

			switch(user.getType()) {
				case SYSADMIN:
					Main.Main.getStage().setScene(adminScene);
					break;
				case OFFICE_MANAGER:
					Main.Main.getStage().setScene(officeManagerScene);
					break;
				case WAREHOUSE_MANAGER:
					Main.Main.getStage().setScene(warehouseManagerScene);
					break;
				case SALES_ASSOCIATE:
					Main.Main.getStage().setScene(salesAssociateScene);
					break;
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}*/

    }

}
