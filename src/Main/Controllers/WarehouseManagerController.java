package Main.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import BicyclePartDistributorshipAPI.Controllers.WarehouseController;
import BicyclePartDistributorshipAPI.Models.BicyclePart;
import Main.APICaller;
import Main.Models.ExaminePartTableRow;
import Tools.BicyclePartTuple;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class WarehouseManagerController extends FXMLController {

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField update_partNumberField;

    @FXML
    private TextField update_partNameField;

    @FXML
    private TextField update_partListPriceField;

    @FXML
    private TextField update_partSalePriceField;

    @FXML
    private TextField update_partQuantityField;

    @FXML
    private CheckBox update_partOnSaleCheckbox;

    @FXML
    private ComboBox<String> paycheck_salesAssociateDropdown;

    @FXML
    private TextField update_alertThresholdField;
    
    @FXML
    private DatePicker paycheck_fromDatePicker;

    @FXML
    private DatePicker paycheck_toDatePicker;

    @FXML
    private TextArea paycheck_outputTextArea;

    @FXML
    private TextField examine_partNameField;

    @FXML
    private ComboBox<String> examine_symbolDropdown;

    @FXML
    private TextField examine_quantityField;

    @FXML
    private TableView<ExaminePartTableRow> examine_partTable;

    @FXML
    private void initialize() {
    	ArrayList<String> selections = new ArrayList<>();
        selections.add("<");
        selections.add(">");
        selections.add("=");
        examine_symbolDropdown.getItems().setAll(selections);
        examine_symbolDropdown.getSelectionModel().selectFirst();
    }
    
    @FXML
    private void update_addFromFile(ActionEvent event) {
    	
    }

    @FXML
    private void update_addFromForm(ActionEvent event) throws IOException {
    	String partName = update_partNameField.getText();
    	Long partNumber = Long.parseLong(update_partNumberField.getText());
    	double listPrice = Double.parseDouble(update_partListPriceField.getText());
    	double salePrice = Double.parseDouble(update_partSalePriceField.getText());
    	boolean isOnSale = update_partOnSaleCheckbox.isSelected();
    	int quantity = Integer.parseInt(update_partQuantityField.getText());
    	int alertThreshold = Integer.parseInt(update_alertThresholdField.getText());
    	
    	try {
    		BicyclePart part = new BicyclePart(partName, partNumber, listPrice, salePrice, isOnSale, alertThreshold);
        	APICaller.getPartController().addPart(part);
        	APICaller.getMainWarehouseController().addInventory(partNumber, quantity);
        	showInfoDialog("Part added successfully");
    	}
    	catch(IOException e) {
    		showErrorDialog("Could not add part: " + e.getMessage());
    	}
    }
    
    @FXML
    private void examine_displayPartByName(ActionEvent event) throws IOException, Exception {
    	String name = examine_partNameField.getText();
        BicyclePartTuple tuple = APICaller.getWarehouseController(WarehouseController.MAIN_WAREHOUSE_NAME).getPartTuple(name);
        ExaminePartTableRow row = new ExaminePartTableRow(tuple);
        examine_partTable.getItems().setAll(new ExaminePartTableRow[] {row});
    }

    @FXML
    private void examine_displayPartsByQuantity(ActionEvent event) throws IOException {
    	int quantity = Integer.parseInt(examine_quantityField.getText());
        char sign = examine_symbolDropdown.getValue().charAt(0);
        ArrayList<BicyclePartTuple> tuples = APICaller.getWarehouseController(WarehouseController.MAIN_WAREHOUSE_NAME).getPartTuples();
        ArrayList<ExaminePartTableRow> rows = new ArrayList<>();
        for(BicyclePartTuple tuple : tuples)
        {
        	if(sign == '<' && tuple.getQuantity() < quantity) {
        		rows.add(new ExaminePartTableRow(tuple));
        	}
        	else if(sign == '>' && tuple.getQuantity() > quantity) {
        		rows.add(new ExaminePartTableRow(tuple));
        	}
        	else if(sign == '=' && tuple.getQuantity() == quantity) {
        		rows.add(new ExaminePartTableRow(tuple));
        	}
        }
        examine_partTable.getItems().setAll(rows);
    }
}
