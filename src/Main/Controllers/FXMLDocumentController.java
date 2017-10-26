package Main.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author Matthew
 */
public class FXMLDocumentController implements Initializable {
    
    /**
     * Controller for "Part List" tab pane
     */
    @FXML
    private PartListTabContentController partListTabContentController;
    
    /**
     * Controller for "Sell Part" tab pane
     */
    @FXML
    private SellPartTabContentController sellPartTabContentController;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Update "Parts List" tab pane when tab is clicked
     * @param event FXML event object
     */
    @FXML
    private void partsListTabSelected(Event event) {
        partListTabContentController.loadTabPane();
    }

    /**
     * Update Sell Part tab pane when tab is clicked
     * @param event FXML event object
     * @throws IOException Exception in reading file
     */
    @FXML
    private void sellPartTabSelected(Event event) throws IOException {
        sellPartTabContentController.loadTabPane();
    }
}
