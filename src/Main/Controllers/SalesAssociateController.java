package Main.Controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import BicyclePartDistributorshipAPI.Models.Inventory;
import BicyclePartDistributorshipAPI.Models.InvoiceSaleRecord;
import FileGeneration.InvoiceGenerator;
import FileGeneration.InvoiceTXTGenerator;
import Main.APICaller;
import Main.Models.SalesInvoiceTableRow;
import Main.Models.SalesVanInventoryTableRow;
import Tools.BicyclePartTuple;

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
    private TextField invoice_employeeName;


    @FXML
    private void initialize() {
    	transfer_populateTable();
    	invoice_populateTable();
    }
    
    @FXML
    private void invoice_generateInvoice(ActionEvent event) {
    	ArrayList<SalesInvoiceTableRow> rows = new ArrayList<>(invoice_soldPartsTable.getItems());
    	String employeeName = invoice_employeeName.getText();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    	String invoiceName = APICaller.getLoggedInUser() + "-" + format.format(new Date());
    	
    	//Create invoice and add values from rows
    	try {
        	String salesVanName = APICaller.getSalesVanController().findForUser(APICaller.getLoggedInUser()).getName();
			APICaller.getInvoiceListController().addInvoice(invoiceName);
			
			for(SalesInvoiceTableRow row : rows) {
				long partNumber = APICaller.getPartController().getPart(row.getPartName()).getPartNumber();
				InvoiceSaleRecord record = new InvoiceSaleRecord();
				record.setPartNumber(partNumber);
				record.setQuantity(row.getQuantity());
				record.setSalesAssociate(APICaller.getLoggedInUser());
				record.setDatetime(new Date());
				APICaller.getInvoiceController(invoiceName).addSaleRecord(record);
				APICaller.getWarehouseController(salesVanName).removeInventory(partNumber, row.getQuantity());
			}
			
			InvoiceGenerator generator = new InvoiceGenerator(new InvoiceTXTGenerator());
			generator.generateInvoice(invoiceName, employeeName);
			invoice_soldPartsTable.getItems().setAll(new ArrayList<>());
			
		} catch (Exception e) {
			showErrorDialog("Error: " + e.getMessage());
			e.printStackTrace();
		}
    }

    @FXML
    private void invoice_moveToSalesVanTable(ActionEvent event) {
    	SalesInvoiceTableRow selectedRow = invoice_soldPartsTable.getSelectionModel().getSelectedItem();
    	int quantity = Integer.parseInt(invoice_moveAmountField.getText());
    	ArrayList<SalesInvoiceTableRow> vanRows = new ArrayList<>(invoice_salesVanPartsTable.getItems());
    	
    	quantity = (quantity > selectedRow.getQuantity() ? selectedRow.getQuantity() : quantity);
    	
    	for(int i = 0; i < vanRows.size(); i++) {
        	if(vanRows.get(i).equals(selectedRow)) {
        		vanRows.get(i).setQuantity(vanRows.get(i).getQuantity() + quantity);
        	}
        }
    	invoice_salesVanPartsTable.getItems().setAll(vanRows);
    	
    	int index = invoice_salesVanPartsTable.getSelectionModel().getSelectedIndex();
    	ArrayList<SalesInvoiceTableRow> rows = new ArrayList<>(invoice_soldPartsTable.getItems());
    	rows.get(index).setQuantity(rows.get(index).getQuantity() - quantity);
    	invoice_soldPartsTable.getItems().setAll(rows);
    }

    @FXML
    private void invoice_moveToSoldTable(ActionEvent event) {
    	SalesInvoiceTableRow selectedRow = invoice_salesVanPartsTable.getSelectionModel().getSelectedItem();
    	int quantity = Integer.parseInt(invoice_moveAmountField.getText());
    	ArrayList<SalesInvoiceTableRow> soldRows = new ArrayList<>(invoice_soldPartsTable.getItems());
    	
    	quantity = (quantity > selectedRow.getQuantity() ? selectedRow.getQuantity() : quantity);
    	
    	if(soldRows.contains(selectedRow)) {
    		for(int i = 0; i < soldRows.size(); i++) {
        		if(soldRows.get(i).equals(selectedRow)) {
        			soldRows.get(i).setQuantity(soldRows.get(i).getQuantity() + quantity);
        		}
        	}
    	}
    	else {
    		SalesInvoiceTableRow row = new SalesInvoiceTableRow(selectedRow);
    		row.setQuantity(quantity);
    		soldRows.add(row);
    	}
    	
    	invoice_soldPartsTable.getItems().setAll(soldRows);
    	
    	int index = invoice_salesVanPartsTable.getSelectionModel().getSelectedIndex();
    	ArrayList<SalesInvoiceTableRow> rows = new ArrayList<>(invoice_salesVanPartsTable.getItems());
    	rows.get(index).setQuantity(rows.get(index).getQuantity() - quantity);
    	invoice_salesVanPartsTable.getItems().setAll(rows);
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
    	invoice_populateTable();
    	
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
    	invoice_populateTable();
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
