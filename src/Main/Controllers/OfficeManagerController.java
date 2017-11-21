package Main.Controllers;

import BicyclePartDistributorshipAPI.Controllers.PartController;
import BicyclePartDistributorshipAPI.Controllers.WarehouseController;
import BicyclePartDistributorshipAPI.Models.BicyclePart;
import Main.APICaller;
import Tools.BicyclePartTuple;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class OfficeManagerController implements Initializable{

    @FXML
    private TextArea displayTextArea;

    @FXML
    private TextField associateName;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    @FXML
    private Button genToText;

    @FXML
    private Button genToPDF;

    @FXML
    private TextField partName;

    @FXML
    private Button displayPartQuantityButton;

    @FXML
    private ComboBox<String> dropDown;

    @FXML
    private TextField quantity;

    @FXML
    private Button displayPartNameButton;

    @FXML
    private TextArea displayPartArea;

    @FXML
    void displayPartByName(ActionEvent event) throws Exception {
        String name = partName.getText();
        PartController controller = APICaller.getPartController(WarehouseController.MAIN_WAREHOUSE_NAME);
        BicyclePartTuple bp = controller.getPart(name);
        displayPartArea.setText(bp.toString());
    }

    @FXML
    void displayPartsByQuantity(ActionEvent event) throws Exception {
        int quant = Integer.parseInt(quantity.getText());
        char sign = dropDown.getValue().charAt(0);
        PartController controller = APICaller.getPartController(WarehouseController.MAIN_WAREHOUSE_NAME);
        ArrayList<BicyclePartTuple> bicycleTupleArray = controller.getParts();
        for(BicyclePartTuple t : bicycleTupleArray)
        {
            if(sign == '<' && t.getQuantity() < quant)
                displayPartArea.appendText(t.toString()+"\n");
            else if(sign == '>' && t.getQuantity() > quant)
                displayPartArea.appendText(t.toString()+"\n");
            else if(sign == '=' && t.getQuantity() == quant)
                displayPartArea.appendText(t.toString()+"\n");
        }
    }

    @FXML
    void generateToText(ActionEvent event) {
        String salesName = associateName.getText();
        LocalDate start = startDate.getValue();
        LocalDate end = endDate.getValue();
        
    }
    
    @FXML
    void GeneratePDF(ActionEvent event) {
        String salesName = associateName.getText();
        LocalDate start = startDate.getValue();
        LocalDate end = endDate.getValue();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> selections = new ArrayList<>();
        selections.add("<");
        selections.add(">");
        selections.add("=");
        dropDown.getItems().setAll(selections);
    }
}