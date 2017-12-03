package Main.Controllers;

import BicyclePartDistributorshipAPI.Controllers.WarehouseController;
import BicyclePartDistributorshipAPI.Models.BicyclePart;
import BicyclePartDistributorshipAPI.Models.Inventory;
import Main.APICaller;
import Main.Models.ExaminePartTableRow;
import Main.Models.PartOrder;
import Tools.BicyclePartTuple;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

public class OfficeManagerController extends FXMLController implements Initializable {

    @FXML
    private TextArea invoice_textArea;
    @FXML
    private TextField invoice_salesAssociateField;
    @FXML
    private DatePicker invoice_startDatePicker;
    @FXML
    private DatePicker invoice_endDatePicker;
    @FXML
    private TextField examine_partNameField;
    @FXML
    private ComboBox<String> examine_symbolDropdown;
    @FXML
    private TextField examine_quantityField;
    @FXML
    private TableView<ExaminePartTableRow> examine_partTable;
    @FXML
    private TableView<PartOrder> order_partsTable;
    @FXML
    private TableColumn<PartOrder, Integer> order_requestColumn;
    @FXML
    private TableColumn<PartOrder, Integer> order_thresholdColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> selections = new ArrayList<>();
        selections.add("<");
        selections.add(">");
        selections.add("=");
        examine_symbolDropdown.getItems().setAll(selections);
        examine_symbolDropdown.getSelectionModel().selectFirst();

        order_populateTable();
        order_requestColumn.setCellFactory(TextFieldTableCell.<PartOrder, Integer>forTableColumn(new IntegerStringConverter()));
        order_requestColumn.setOnEditCommit(event -> event.getRowValue().setRequestedQuantity(event.getNewValue()));

        //Show minimum part threshold warning dialog
        List<PartOrder> orders = order_partsTable.getItems();
        String message = "";
        for(PartOrder order : orders) {
        	int deficit = order.getStockThreshold() - order.getCurrentQuantity();
        	if(deficit > 0) {
        		message += "Part #" + order.getPartNumber() + ": " + deficit + " parts below minimum threshold.\n";
        	}
        }
        if(!message.equals("")) {
        	showWarningDialog(message);
        }

    }

    @FXML
    private void invoice_generateTextInvoice(ActionEvent event) {
        String salesName = invoice_salesAssociateField.getText();
        LocalDate start = invoice_startDatePicker.getValue();
        LocalDate end = invoice_endDatePicker.getValue();
    }

    @FXML
    private void invoice_generatePDFInvoice(ActionEvent event) {
        String salesName = invoice_salesAssociateField.getText();
        LocalDate start = invoice_startDatePicker.getValue();
        LocalDate end = invoice_endDatePicker.getValue();
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

    @FXML
    private void examine_displayPartByName(ActionEvent event) throws Exception {
        String name = examine_partNameField.getText();
        BicyclePartTuple tuple = APICaller.getWarehouseController(WarehouseController.MAIN_WAREHOUSE_NAME).getPartTuple(name);
        ExaminePartTableRow row = new ExaminePartTableRow(tuple);
        examine_partTable.getItems().setAll(new ExaminePartTableRow[] {row});
    }

    @FXML
    private void order_executeOrder(ActionEvent event) throws IOException {
        List<PartOrder> orders = order_partsTable.getItems();
        WarehouseController controller = APICaller.getMainWarehouseController();
        for(PartOrder order : orders) {
            if(order.getRequestedQuantity() > 0) {
                controller.addInventory(order.getPartNumber(), order.getRequestedQuantity());
            }
        }
        order_populateTable();
    }

    @FXML
    private void order_fillThreshold(ActionEvent event) throws IOException {
    	List<PartOrder> orders = order_partsTable.getItems();
    	WarehouseController controller = APICaller.getMainWarehouseController();
    	for(PartOrder order : orders) {
    		int deficit = order.getStockThreshold() - order.getCurrentQuantity();
    		if(deficit > 0) {
    			controller.addInventory(order.getPartNumber(), deficit);
    		}
    	}
    	order_populateTable();
    }

    private void order_populateTable() {
        ArrayList<PartOrder> tableRows = new ArrayList<>();
        try {
            WarehouseController controller = APICaller.getMainWarehouseController();
            ArrayList<Inventory> inventories = controller.getInventoryList();

            for(Inventory inventory : inventories) {
                PartOrder row = new PartOrder((Long) inventory.getBicyclePartNumber(), null, inventory.getQuantity(), 0, 0);
                BicyclePart partDefinition = APICaller.getPartController().getPart(inventory.getBicyclePartNumber());
                row.setPartName(partDefinition.getPartName());
                row.setStockThreshold(partDefinition.getStockThreshold());
                tableRows.add(row);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        order_partsTable.getItems().setAll(tableRows);

        //Set color to rows with part deficit
        order_partsTable.setRowFactory(row -> new TableRow<PartOrder>() {
        	@Override
        	public void updateItem(PartOrder order, boolean empty) {
        		super.updateItem(order, empty);
        		if(!empty && order.getCurrentQuantity() < order.getStockThreshold()) {
        			setStyle("-fx-background-color: #f28181");
        		}
        	}
        });
    }
}