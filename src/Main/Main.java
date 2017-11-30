package Main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entry Point for Application/FXML
 * @author Matthew
 */
public class Main extends Application {

	private static Stage stage;

	public static final String WAREHOUSE_MANAGER_STAGE_URL = "Views/WarehouseManager.fxml";
	public static final String OFFICE_MANAGER_STAGE_URL = "Views/OfficeManager.fxml";
	public static final String SYSADMIN_STAGE_URL = "Views/SysAdmin.fxml";
	public static final String LOGIN_STAGE_URL = "Views/OfficeManager.fxml";

	public static void setStage(String stageUrl) throws IOException {
		Parent root = FXMLLoader.load(Main.class.getResource(stageUrl));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Entry point for FXML
	 * @param stage stage to set
	 */
    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;
        setStage(LOGIN_STAGE_URL);
    }

    /**
	 * Entry Point for Application
     * @param args the command line arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
    	launch(args);
    }

}
