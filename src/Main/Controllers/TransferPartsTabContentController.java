package Main.Controllers;

import Main.APICaller;
import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 * Controller for "Transfer Parts" tab pane
 * @author MAneiro
 */
public class TransferPartsTabContentController {

    /**
     * Text field for Sales van Name
     */
    @FXML
    private TextField salesvanNameField;

    /**
     * Add a new sales van to the fleet
     * @param event FXML event object
     * @throws IOException Exception in reading file
     */
    @FXML
    private void addSalesvan(ActionEvent event) throws IOException {
        final String salesvanName = salesvanNameField.getText();
        APICaller.getWarehouseController().addWarehouse(salesvanName + ".txt");
    }

    /**
     * Remove a sales van from the fleet
     * @param event FXML event object
     * @throws IOException Exception in reading file
     */
    @FXML
    private void removeSalesvan(ActionEvent event) throws IOException {
        final String salesvanName = salesvanNameField.getText();
        APICaller.getWarehouseController().removeWarehouse(salesvanName);
    }

    /**
     * Upload a file to transfer part
     * @param event FXML event object
     */
    @FXML
    private void uploadTransferFile(ActionEvent event) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        Node node = (Node) event.getSource();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Transfer File");
        File file = fileChooser.showOpenDialog(node.getScene().getWindow());
		if(file != null) {
		    //APICaller.getWarehouseController().transferParts(file.getPath());
		}
    }
}
