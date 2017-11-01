package Main.Controllers;

import BicyclePartDistributorshipAPI.Controllers.WarehouseController;
import BicyclePartDistributorshipAPI.Models.BicyclePart;
import Main.APICaller;
import Main.FieldValidation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 * Controller for "Add Part" tab pane
 * @author Matthew
 */
public class AddPartTabContentController extends FXMLFormController implements Initializable {

    /**
     * Text field for entering part number
     */
    @FXML
    private TextField partNumberField;

    /**
     * Text field for entering part name
     */
    @FXML
    private TextField partNameField;

    /**
     * Text field for entering list price
     */
    @FXML
    private TextField partListPriceField;

    /**
     * Text field for entering sale price
     */
    @FXML
    private TextField partSalePriceField;

    /**
     * Text field for entering quantity
     */
    @FXML
    private TextField partQuantityField;

    /**
     * Check box for selecting if part is on sale
     */
    @FXML
    private CheckBox partOnSaleCheckbox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fieldValidations.add(new FieldValidation(partNumberField, FieldValidation.LONG));
        fieldValidations.add(new FieldValidation(partListPriceField, FieldValidation.FLOATING_POINT));
        fieldValidations.add(new FieldValidation(partSalePriceField, FieldValidation.FLOATING_POINT));
        fieldValidations.add(new FieldValidation(partQuantityField, FieldValidation.INTEGER));
    }

    /**
     * Add a part through the FXML form input
     * @param event FXML event object
     */
    @FXML
    private void addPartListing_Click(ActionEvent event) {
        if(validForm()) {
            String partName = partNameField.getText();
            long partNumber = Long.parseLong(partNumberField.getText());
            double partListPrice = Double.parseDouble(partListPriceField.getText());
            double partSalePrice = Double.parseDouble(partSalePriceField.getText());
            boolean isOnSale = partOnSaleCheckbox.isSelected();

            try {
                BicyclePart listing =
                    new BicyclePart(partName, partNumber, partListPrice, partSalePrice, isOnSale);
                APICaller.getPartController(WarehouseController.MAIN_WAREHOUSE_NAME).addPart(listing);
            }
            catch(IOException e) {
                System.out.println(e.getMessage());
            }

        }

    }

    /**
     * Add parts through uploading a file
     * @param event FMXL event object
     */
    @FXML
    private void addFromFile(ActionEvent event) {
        Node node = (Node) event.getSource();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Bicycle Parts File");
        try {
            File file = fileChooser.showOpenDialog(node.getScene().getWindow());
            if(file != null) {
                APICaller.getPartController(WarehouseController.MAIN_WAREHOUSE_NAME).addPartsFromFile(file.getPath());
            }
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
