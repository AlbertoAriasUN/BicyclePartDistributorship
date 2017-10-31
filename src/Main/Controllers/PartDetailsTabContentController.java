package Main.Controllers;

import BicyclePartDistributorshipAPI.Controllers.WarehouseController;
import BicyclePartDistributorshipAPI.Models.BicyclePart;
import Main.APICaller;
import Main.Models.DetailsTableRow;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Controller for "Part Details" tab pane
 * @author Matthew
 */
public class PartDetailsTabContentController extends FXMLFormController implements Initializable {

    /**
     * Text field for entering part name
     */
    @FXML
    private TextField partNameField;

    /**
     * Table for showing part details
     */
    @FXML
    private TableView<DetailsTableRow> partDetailsTable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Search for a part
     * @param event FXML event object
     * @throws Exception
     */
    @FXML
    private void partSearch_Click(ActionEvent event) throws Exception {
        if(validForm()) {
            try {
                String partName = partNameField.getText();
                BicyclePart listing = APICaller.getPartController(WarehouseController.MAIN_WAREHOUSE_NAME).getPart(partName).getBicyclePart();

                ArrayList<DetailsTableRow> rows = new ArrayList<>(2);
                rows.add(new DetailsTableRow("Part Name", listing.getPartName()));
                rows.add(new DetailsTableRow("Cost", listing.getPrice()));

                partDetailsTable.getItems().setAll(rows);
            }
            catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
