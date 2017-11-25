package Main.Controllers;

import BicyclePartDistributorshipAPI.Controllers.WarehouseController;
import BicyclePartDistributorshipAPI.Models.BicyclePart;
import BicyclePartDistributorshipAPI.Models.Inventory;
import Main.APICaller;
import Main.Models.PartOrder;
import Tools.BicyclePartTuple;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class OfficeManagerController implements Initializable{

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
    private TextArea examine_displayPartArea;
    @FXML
    private TableView<PartOrder> order_partsTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> selections = new ArrayList<>();
        selections.add("<");
        selections.add(">");
        selections.add("=");
        examine_symbolDropdown.getItems().setAll(selections);
        examine_symbolDropdown.setValue(selections.get(0));
        
        ArrayList<PartOrder> tableRows = new ArrayList<>();
        try {
            WarehouseController controller = APICaller.getWarehouseController(WarehouseController.MAIN_WAREHOUSE_NAME);
            ArrayList<Inventory> inventories = controller.getInventoryList();
            
            for(Inventory inventory : inventories) {
                PartOrder row = new PartOrder((Long) inventory.getBicyclePartNumber(), null, inventory.getQuantity(), 0);
                BicyclePart partDefinition = APICaller.getPartController().getPart(inventory.getBicyclePartNumber());
                row.setPartName(partDefinition.getPartName());
                tableRows.add(row);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        order_partsTable.getItems().setAll(tableRows);
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
        ArrayList<BicyclePartTuple> bicyclePartTuples = APICaller.getWarehouseController(WarehouseController.MAIN_WAREHOUSE_NAME).getPartTuples();
        examine_displayPartArea.setText("");
        for(BicyclePartTuple tuple : bicyclePartTuples)
        {
            if(sign == '<' && tuple.getQuantity() < quantity)
                examine_displayPartArea.appendText(tuple.toString()+"\n");
            else if(sign == '>' && tuple.getQuantity() > quantity)
                examine_displayPartArea.appendText(tuple.toString()+"\n");
            else if(sign == '=' && tuple.getQuantity() == quantity)
                examine_displayPartArea.appendText(tuple.toString()+"\n");
        }
    }

    @FXML
    private void examine_displayPartByName(ActionEvent event) throws Exception {
        String name = examine_partNameField.getText();
        BicyclePartTuple tuple = APICaller.getWarehouseController(WarehouseController.MAIN_WAREHOUSE_NAME).getPartTuple(name);
        examine_displayPartArea.setText(tuple.toString());
    }

    @FXML
    private void order_executeOrder(ActionEvent event) {
    }
}