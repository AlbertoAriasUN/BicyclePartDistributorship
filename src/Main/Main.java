package Main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entery Point for Application/FXML
 * @author Matthew
 */
public class Main extends Application {

	private static Stage stage;

	public static Stage getStage() {
		return stage;
	}

	/**
	 * Entry point for FXML
	 * @param stage stage to set
	 */
    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;
    	Parent root = FXMLLoader.load(getClass().getResource("Views/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
