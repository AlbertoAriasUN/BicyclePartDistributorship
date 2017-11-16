package Main.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class OfficeManagerController implements Initializable{

    @FXML
    private TextField partName;

    @FXML
    private Button displayButton;

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
    private ComboBox<String> dropDown;

    @FXML
    private TextField quantity;

    @FXML
    void GeneratePDF(ActionEvent event) {

    }

    @FXML
    void displayPart(ActionEvent event) {

    }

    @FXML
    void generateToText(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

}
