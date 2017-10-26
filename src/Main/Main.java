package Main;

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
    
	/**
	 * Entry point for FXML
	 * @param stage stage to set
	 */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Views/FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
	 * Entry Point for Application
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
