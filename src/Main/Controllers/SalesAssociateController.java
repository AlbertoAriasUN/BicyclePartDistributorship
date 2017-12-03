package Main.Controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import BicyclePartDistributorshipAPI.Models.Inventory;
import Main.APICaller;
import Main.Models.SalesInvoiceTableRow;
import Main.Models.SalesVanInventoryTableRow;
import Tools.BicyclePartTuple;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SalesAssociateController extends FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<SalesVanInventoryTableRow> transfer_salesVanPartsTable;

    @FXML
    private TableView<SalesInvoiceTableRow> invoice_salesVanPartsTable;

    @FXML
    private TableView<SalesInvoiceTableRow> invoice_soldPartsTable;

    @FXML
    private Label invoice_totalSalesLabel;

    @FXML
    private TextField invoice_moveAmountField;


    @FXML
    private void initialize() {
    	transfer_populateTable();
    	invoice_populateTable();
    }
    
    @FXML
    private void invoice_generateInvoice(ActionEvent event) {

    }

    @FXML
    private void invoice_moveToSalesVanTable(ActionEvent event) {

    }

    @FXML
    private void invoice_moveToSoldTable(ActionEvent event) {
    	SalesInvoiceTableRow selectedRow = invoice_salesVanPartsTable.getSelectionModel().getSelectedItem();
    	int quantity = Integer.parseInt(invoice_moveAmountField.getText());
    	ObservableList<SalesInvoiceTableRow> soldRows = invoice_soldPartsTable.getItems();
    	
    	if(soldRows.contains(selectedRow)) {
    		for(int i = 0; i < soldRows.size(); i++) {
        		if(soldRows.get(i).equals(selectedRow)) {
        			soldRows.get(i).setQuantity(soldRows.get(i).getQuantity() + quantity);
        		}
        	}
    	}
    	else {
    		SalesInvoiceTableRow row = selectedRow;
    		row.setQuantity(quantity);
    		soldRows.add(row);
    	}
    	
    	invoice_soldPartsTable.setItems(soldRows);
    	selectedRow.setQuantity(selectedRow.getQuantity() - quantity);
    }

    @FXML
    void transfer_emptyVan(ActionEvent event) {
    	try {
        	String salesVanName = APICaller.getSalesVanController().findForUser(APICaller.getLoggedInUser()).getName();
			ArrayList<Inventory> inventory = APICaller.getWarehouseController(salesVanName).getInventoryList();
			for(Inventory _inventory : inventory) {
				int quantity = _inventory.getQuantity();
				long partNumber = _inventory.getBicyclePartNumber();
				APICaller.getWarehouseController(salesVanName).removeInventory(partNumber, _inventory.getQuantity());
				APICaller.getMainWarehouseController().addInventory(partNumber, quantity);
			}
		} catch (Exception e) {
			showErrorDialog("Error: " + e.getMessage());
		}
    	
    	transfer_populateTable();
    	
    }

    @FXML
    void transfer_loadParts(ActionEvent event) {
    	Node node = (Node) event.getSource();
    	File file = getFileFromFileChooser(node, "Select transfer file");
    	try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String[] warehouses = reader.readLine().split(",");
			APICaller.getWarehouseController(warehouses[1]).transferPartsTo(warehouses[0], file);
		} catch (Exception e) {
			showErrorDialog("Error: " + e.getMessage());
		}
    	transfer_populateTable();
    }
    
    private void transfer_populateTable(){
    	ArrayList<SalesVanInventoryTableRow> rows = new ArrayList<>();
    	try {
        	String salesVanName = APICaller.getSalesVanController().findForUser(APICaller.getLoggedInUser()).getName();
    		ArrayList<BicyclePartTuple> tuples = APICaller.getWarehouseController(salesVanName).getPartTuples();
        	for(BicyclePartTuple tuple : tuples) {
        		String partName = tuple.getBicyclePart().getPartName();
        		Long partNumber = tuple.getBicyclePart().getPartNumber();
        		double price = tuple.getBicyclePart().getPrice();
        		int quantity = tuple.getQuantity();
        		rows.add(new SalesVanInventoryTableRow(partName, partNumber, price, quantity));
        	}
        	transfer_salesVanPartsTable.getItems().setAll(rows);	
    	}
    	catch(Exception e) {
    		showErrorDialog("Error: " + e.getMessage());
    	}
    }
    
    private void invoice_populateTable() {
    	ArrayList<SalesInvoiceTableRow> rows = new ArrayList<>();
    	try {
        	String salesVanName = APICaller.getSalesVanController().findForUser(APICaller.getLoggedInUser()).getName();
        	ArrayList<BicyclePartTuple> tuples = APICaller.getWarehouseController(salesVanName).getPartTuples();
        	for(BicyclePartTuple tuple : tuples) {
        		String partName = tuple.getBicyclePart().getPartName();
        		double price = tuple.getBicyclePart().getPrice();
        		int quantity = tuple.getQuantity();
        		rows.add(new SalesInvoiceTableRow(partName, price, quantity));
        	}
        	
        	invoice_salesVanPartsTable.getItems().setAll(rows);
    	}
    	catch(Exception e) {
    		showErrorDialog("Error: " + e.getMessage());
    	}
    }
}
