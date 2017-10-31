package Main.Controllers;

import Database.Database;
import BicyclePartDistributorshipAPI.Models.BicyclePartListing;
import Main.APICaller;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

/**
 * Controller for "Part List" tab pane
 * @author maneiro
 */
public class PartListTabContentController implements Initializable {

    /**
     * Table containing list of parts in selected warehouse
     */
    @FXML
    private TableView<BicyclePartListing> partListTable;

    /**
     * Combo box containing list of warehouses
     */
    @FXML
    private ComboBox<String> warehouseDropdown;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Event callback for updating information in pane
     */
    public void loadTabPane() {
        try {
            ArrayList<BicyclePartListing> partListings = APICaller.getAPIController().getParts();
            partListTable.getItems().setAll(partListings);

            //Add the names of all the warehouses to the dropdown
            ArrayList<String> dropdownItems = new ArrayList<>();
            dropdownItems.add("All Warehouses");
            HashMap<String, Database<BicyclePartListing>> warehouseMap = APICaller.getWarehouseController().getWarehouseMap();
            warehouseMap.forEach((k,v) -> dropdownItems.add(k));
            warehouseDropdown.getItems().setAll(dropdownItems);
            warehouseDropdown.setValue(dropdownItems.get(0));

        }
        catch(IOException e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     * Select a warehouse to be viewed
     * @param event FXML event object
     * @throws IOException Exception in reading file
     */
    @FXML
    private void selectWarehouse(ActionEvent event) throws IOException {
        String selectedValue = warehouseDropdown.getValue();
        ArrayList<BicyclePartListing> partListings;

        if(selectedValue.equals("All Warehouses")) {
            partListings = APICaller.getAPIController().getParts();
        }
        else {
            partListings = APICaller.getAPIController(selectedValue).getParts();
        }

        partListTable.getItems().setAll(partListings);
    }
}
