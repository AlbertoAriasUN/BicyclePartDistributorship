package Main.Controllers;

import Database.Database;
import BicyclePartDistributorshipAPI.Controllers.PartController;
import BicyclePartDistributorshipAPI.Models.BicyclePart;
import BicyclePartDistributorshipAPI.Models.Inventory;
import Main.APICaller;
import Tools.BicyclePartTuple;

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
    private TableView<BicyclePart> partListTable;

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

    public class PartWithQuantity extends BicyclePart {
    	private int quantity;
    	public PartWithQuantity(BicyclePart part, Integer quantity) {
    		super(part.getPartName(), part.getPartNumber(), part.getListPrice(),
    			  part.getSalePrice(), part.getIsOnSale());
    		this.quantity = quantity;
    	}

    	public int getQuantity() {
    		return quantity;
    	}
    }

    /**
     * Event callback for updating information in pane
     */
    public void loadTabPane() {
        try {
        	ArrayList<PartController> partControllers = APICaller.getAllPartControllers();
        	ArrayList<BicyclePartTuple> partListings = new ArrayList<>();
        	for(PartController partController : partControllers) {
        		partListings.addAll(partController.getParts());
        	}
            partListTable.getItems().setAll((BicyclePart[]) partListings.toArray());

            //Add the names of all the warehouses to the dropdown
            ArrayList<String> dropdownItems = new ArrayList<>();
            dropdownItems.add("All Warehouses");
            HashMap<String, Database<Inventory>> warehouseMap = APICaller.getWarehouseController().getWarehouseMap();
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
        ArrayList<BicyclePartTuple> partListings = new ArrayList<>();

        if(selectedValue.equals("All Warehouses")) {
        	ArrayList<PartController> partControllers = APICaller.getAllPartControllers();
        	for(PartController partController : partControllers) {
        		partListings.addAll(partController.getParts());
        	}
        }
        else {
            partListings = APICaller.getPartController(selectedValue).getParts();
        }

        partListTable.getItems().setAll((BicyclePart[]) partListings.toArray());
    }
}
