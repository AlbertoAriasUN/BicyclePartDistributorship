package Main.Controllers;

import java.io.File;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;

public class FXMLController {
	public void showWarningDialog(String message) {
		showDialog(AlertType.WARNING, "Warning", message);
	}

	public void showErrorDialog(String message) {
		showDialog(AlertType.ERROR, "Error", message);
	}
	
	public void showInfoDialog(String message) {
		showDialog(AlertType.INFORMATION, "Notice", message);
	}
	
	public File getFileFromFileChooser(Node node, String title) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(title);
		File file = fileChooser.showOpenDialog(node.getScene().getWindow());
		return file;
	}
	
	private void showDialog(AlertType type, String title, String message) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
		alert.showAndWait();
	}
}
