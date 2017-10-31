package Main.Controllers;

import Database.Database;
import BicyclePartDistributorshipAPI.Models.BicyclePartListing;
import Main.APICaller;
import Main.FieldValidation;
import Main.Models.PartSaleTableRow;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Controller for "Sell Part" tab pane
 * @author MAneiro
 */
public class SellPartTabContentController extends FXMLFormController implements Initializable {

    /**
     * Text field for entering part number
     */
    @FXML
    private TextField partNumberField;

    /**
     * Combo box for selecting warehouse
     */
    @FXML
    private ComboBox<String> warehouseDropdown;

    /**
     * Table containing records of sales
     */
    @FXML
    private TableView<PartSaleTableRow> salesRecordTable;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fieldValidations.add(new FieldValidation(partNumberField, FieldValidation.LONG));
    }

    /**
     * Event callback for updating information displayed on pane
     * @throws IOException Exception in reading file
     */
    public void loadTabPane() throws IOException {
        ArrayList<String> dropdownItems = new ArrayList<>();
        HashMap<String, Database<BicyclePartListing>> warehouseMap = APICaller.getWarehouseController().getWarehouseMap();
        warehouseMap.forEach((k,v) -> dropdownItems.add(k));
        warehouseDropdown.getItems().setAll(dropdownItems);
        warehouseDropdown.setValue(dropdownItems.get(0));
    }

    /**
     * Sell a part
     * @param event FXML event object
     * @throws IOException Exception in reading file
     */
    @FXML
    private void sellPart(ActionEvent event) throws IOException {
        if(validForm()) {
            String warehouse = warehouseDropdown.getValue();
            Long partNumber = Long.parseLong(partNumberField.getText());
            BicyclePartListing part = APICaller.getPartController(warehouse).getPart(partNumber);
            APICaller.getPartController(warehouse).sellPart(partNumber);

            //Get datetime of current time in American-ized format
            String timestamp = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(Date.from(Instant.now()));
            PartSaleTableRow row = new PartSaleTableRow(warehouse, part.getPartName(), part.getPrice(), timestamp);
            salesRecordTable.getItems().add(row);
        }
    }
}
